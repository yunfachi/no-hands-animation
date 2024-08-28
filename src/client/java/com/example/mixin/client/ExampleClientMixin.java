package com.example.mixin.client;

import net.minecraft.client.render.entity.model.*;
import net.minecraft.entity.LivingEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BipedEntityModel.class)
public class ExampleClientMixin {
    @Inject(at = @At("RETURN"), method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void asd(LivingEntity livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        BipedEntityModel thisObject = (BipedEntityModel) (Object)this;

        if (livingEntity.isUsingItem()) {
            thisObject.leftArm.roll = 0.0F;
            thisObject.leftArm.yaw = 0.0F;
            thisObject.leftArm.pitch = 0.0F;
            thisObject.rightArm.roll = 0.0F;
            thisObject.rightArm.yaw = 10.0F;
            thisObject.rightArm.pitch = 20.0F;
        } else {
            thisObject.leftArm.roll = 0.0F;
            thisObject.leftArm.yaw = 0.0F;
            thisObject.leftArm.pitch = 0.0F;
            thisObject.rightArm.roll = 0.0F;
            thisObject.rightArm.yaw = 0.0F;
            thisObject.rightArm.pitch = 0.0F;
        }
    }

//	@Inject(at = @At("HEAD"), method = "getTexturedModelData(Lnet/minecraft/client/model/Dilation;Z)Lnet/minecraft/client/model/ModelData;", cancellable = true)
//	private static void getTextureModelData(Dilation dilation, boolean slim, CallbackInfoReturnable<ModelData> cir) {
//		ModelData modelData = BipedEntityModel.getModelData(dilation, 0.0F);
//		ModelPartData modelPartData = modelData.getRoot();
//		modelPartData.addChild("ear", ModelPartBuilder.create().uv(24, 0).cuboid(-3.0F, -6.0F, -1.0F, 6.0F, 6.0F, 1.0F, dilation), ModelTransform.NONE);
//		modelPartData.addChild(
//				"cloak", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, 0.0F, -1.0F, 10.0F, 16.0F, 1.0F, dilation, 1.0F, 0.5F), ModelTransform.pivot(0.0F, 0.0F, 0.0F)
//		);
//		float f = 0.25F;
//		if (slim) {
//			modelPartData.addChild(
//					"fake left arm",
//					ModelPartBuilder.create()
//							.uv(32, 48).cuboid(-1.0F, 0.0F, 0.0F, 3.0F, 120.0F, 4.0F, dilation),
//					ModelTransform.pivot(0.0F, 220.0F, 0.0F)
//			);
//			modelPartData.addChild(
//					EntityModelPartNames.LEFT_LEG,
//					ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation),
//					ModelTransform.pivot(1.9F, 12.0F, 0.0F)
//			);
//			modelPartData.addChild(
//					EntityModelPartNames.RIGHT_ARM,
//					ModelPartBuilder.create().uv(40, 16).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, dilation),
//					ModelTransform.pivot(-5.0F, 2.5F, 0.0F)
//			);
//			modelPartData.addChild(
//					"left_sleeve",
//					ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, dilation.add(0.25F)),
//					ModelTransform.pivot(5.0F, 2.5F, 0.0F)
//			);
//			modelPartData.addChild(
//					"right_sleeve",
//					ModelPartBuilder.create().uv(40, 32).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, dilation.add(0.25F)),
//					ModelTransform.pivot(-5.0F, 2.5F, 0.0F)
//			);
//		} else {
//			modelPartData.addChild(
//					EntityModelPartNames.LEFT_ARM,
//					ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -22.0F, 4.0F, 12.0F, 4.0F, dilation),
//					ModelTransform.pivot(5.0F, 23.0F, 0.0F)
//			);
//			modelPartData.addChild(
//					"left_sleeve",
//					ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -2.0F, -32.0F, 4.0F, 12.0F, 4.0F, dilation.add(0.25F)),
//					ModelTransform.pivot(25.0F, 2.0F, 0.0F)
//			);
//			modelPartData.addChild(
//					"right_sleeve",
//					ModelPartBuilder.create().uv(40, 32).cuboid(-32.0F, -2.0F, -2.0F, 4.0F, 132.0F, 4.0F, dilation.add(0.25F)),
//					ModelTransform.pivot(-5.0F, 2.0F, 0.0F)
//			);
//		}
//
//		modelPartData.addChild(
//				EntityModelPartNames.LEFT_LEG,
//				ModelPartBuilder.create().uv(16, 48).cuboid(-22.0F, 0.0F, -23.0F, 4.0F, 122.0F, 4.0F, dilation),
//				ModelTransform.pivot(1.9F, 12.0F, 0.0F)
//		);
//		modelPartData.addChild(
//				"left_pants",
//				ModelPartBuilder.create().uv(0, 48).cuboid(-22.0F, 20.0F, -2.0F, 43.0F, 12.0F, 4.0F, dilation.add(0.25F)),
//				ModelTransform.pivot(1.9F, 12.0F, 0.0F)
//		);
//		modelPartData.addChild(
//				"right_pants",
//				ModelPartBuilder.create().uv(0, 32).cuboid(-52.0F, 0.0F, -2.0F, 64.0F, 612.0F, 64.0F, dilation.add(0.25F)),
//				ModelTransform.pivot(-1.9F, 12.0F, 0.0F)
//		);
//		modelPartData.addChild(
//				EntityModelPartNames.JACKET, ModelPartBuilder.create().uv(16, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, dilation.add(0.25F)), ModelTransform.NONE
//		);
//		cir.setReturnValue(modelData);
//	}
}