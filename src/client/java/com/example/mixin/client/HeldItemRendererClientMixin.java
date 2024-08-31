package com.example.mixin.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.example.ExampleModClient.defaultShields;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererClientMixin {
    @Shadow public abstract void renderItem(LivingEntity entity, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light);

    @Shadow @Final private ItemRenderer itemRenderer;

    @Inject(method = "renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
    at = @At("HEAD"), cancellable = true)
    private void asd(LivingEntity entity, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (Items.SHIELD == stack.getItem()) {
            stack.applyChanges(defaultShields.getOrDefault(stack.getName().getString(), stack).getComponentChanges());
        }

        if (!stack.isEmpty()) {
            this.itemRenderer
                    .renderItem(
                            entity,
                            stack,
                            renderMode,
                            leftHanded,
                            matrices,
                            vertexConsumers,
                            entity.getWorld(),
                            light,
                            OverlayTexture.DEFAULT_UV,
                            entity.getId() + renderMode.ordinal()
                    );
        }

        ci.cancel();
    }

}
