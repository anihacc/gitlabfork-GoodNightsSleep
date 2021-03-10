package com.legacy.goodnightsleep.client.render.models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class TormenterModel<T extends LivingEntity> extends BipedModel<T>
{

	public TormenterModel(float modelSize, boolean p_i1168_2_)
	{
		super(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float f6 = MathHelper.sin(this.attackTime * (float) Math.PI);
		float f7 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float) Math.PI);
		this.rightArm.zRot = 0.34906587F;
		this.leftArm.zRot = -0.5235988F;
		this.rightArm.yRot = -(0.1F - f6 * 0.6F) - 0.5235988F;
		this.leftArm.yRot = 0.1F - f6 * 0.6F;
		this.rightArm.xRot = -((float) Math.PI / 2F);
		this.leftArm.xRot = -1.7453293F;
		this.rightArm.xRot -= f6 * 1.2F - f7 * 0.4F;
		this.leftArm.xRot -= f6 * 1.2F - f7 * 0.4F;
		this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.rightArm.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.leftArm.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.head.zRot = -0.5235988F;
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI) - 0.17453294F;
		this.head.xRot = headPitch / (180F / (float) Math.PI) - 0.34906587F;
	}
}
