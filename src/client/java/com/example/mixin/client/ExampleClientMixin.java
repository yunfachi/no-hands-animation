package com.example.mixin.client;

import net.minecraft.client.render.entity.model.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.minecraft.client.render.entity.model.EntityModelLayers.SHIELD;

@Mixin(BipedEntityModel.class)
public class ExampleClientMixin {
    @Inject(at = @At("RETURN"), method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void asd(LivingEntity livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        BipedEntityModel thisObject = (BipedEntityModel) (Object)this;

        if (livingEntity.getHandItems().iterator().next().getItem() == Items.SHIELD) {//(livingEntity.isUsingItem()) {
            thisObject.leftArm.roll = 0.0F;
            thisObject.leftArm.yaw = 0.0F;
            thisObject.leftArm.pitch = 0.0F;
            thisObject.rightArm.roll = 0.0F;
            thisObject.rightArm.yaw = 0.0F;
            thisObject.rightArm.pitch = -0.75F;
        } else {
            thisObject.leftArm.roll = 0.0F;
            thisObject.leftArm.yaw = 0.0F;
            thisObject.leftArm.pitch = 0.0F;
            thisObject.rightArm.roll = 0.0F;
            thisObject.rightArm.yaw = 0.0F;
            thisObject.rightArm.pitch = 0.0F;
        }
    }
}