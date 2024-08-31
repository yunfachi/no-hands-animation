package com.example.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAttachmentType;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class NameTagClientMixin {
    @Shadow @Final protected EntityRenderDispatcher dispatcher;

    @Shadow public abstract TextRenderer getTextRenderer();

    @Inject(at = @At("HEAD"), cancellable = true, method = "renderLabelIfPresent(Lnet/minecraft/entity/Entity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IF)V")
    public void asd(Entity entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float tickDelta, CallbackInfo ci) {
        double d = this.dispatcher.getSquaredDistanceToCamera(entity);
        if (!(d > 3072.0)) {
            Vec3d vec3d = entity.getAttachments().getPointNullable(EntityAttachmentType.NAME_TAG, 0, entity.getYaw(tickDelta));
            if (vec3d != null) {
                boolean bl = !entity.isSneaky();
                int i = "deadmau5".equals(text.getString()) ? -10 : 0;
                matrices.push();
                matrices.translate(vec3d.x, vec3d.y + 0.5, vec3d.z);
                matrices.multiply(this.dispatcher.getRotation());
                matrices.scale(0.025F, -0.025F, 0.025F);
                Matrix4f matrix4f = matrices.peek().getPositionMatrix();
                float f = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25F);
                int j = (int)(f * 255.0F) << 24;
                TextRenderer textRenderer = this.getTextRenderer();
                float g = (float)(-textRenderer.getWidth(text) / 2);
                textRenderer.draw(
                        text, g, (float)i, 553648127, false, matrix4f, vertexConsumers, bl ? TextRenderer.TextLayerType.SEE_THROUGH : TextRenderer.TextLayerType.NORMAL, j, light
                );
                if (bl) {
                    textRenderer.draw(text, g, (float)i, Colors.WHITE, false, matrix4f, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, light);
                }

                matrices.pop();
            }
        }
        ci.cancel();
    }
}
