package com.legacy.goodnightsleep.client.render.models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ModelTormenter<T extends LivingEntity> extends BipedModel<T>
{
	
	public ModelTormenter(float modelSize, boolean p_i1168_2_)
    {
        super(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
    }
	
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
	{
		super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
		float f6 = MathHelper.sin(this.swingProgress * (float) Math.PI);
		float f7 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
		this.bipedRightArm.rotateAngleZ = 0.34906587F;
		this.bipedLeftArm.rotateAngleZ = -0.5235988F;
		this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F) - 0.5235988F;
		this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F;
		this.bipedRightArm.rotateAngleX = -((float) Math.PI / 2F);
		this.bipedLeftArm.rotateAngleX = -1.7453293F;
		this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
		this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
		this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.bipedHead.rotateAngleZ = -0.5235988F;
		this.bipedHead.rotateAngleY = netHeadYaw / (180F / (float) Math.PI) - 0.17453294F;
		this.bipedHead.rotateAngleX = headPitch / (180F / (float) Math.PI) - 0.34906587F;
	}
}
