package com.legacy.goodnightsleep.client.render.models;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;

public class UnicornModel<T extends AbstractHorseEntity> extends HorseModel<T>
{
	private final ModelRenderer horn;

	public UnicornModel(float scale)
	{
		super(scale);
		this.horn = new ModelRenderer(this, 56, 0);
		this.horn.setRotationPoint(0.0F, 4.0F, -10.0F);
		this.horn.addBox(-1.0F, -16.0F, 2.0F, 2, 7, 2, 0.0F);
	}

	@Override
	public Iterable<ModelRenderer> getHeadParts()
	{
		return ImmutableList.of(this.field_217128_b, this.horn);
	}

	@Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	/*private float updateHorseRotation(float p_110683_1_, float p_110683_2_, float p_110683_3_)
	{
		float f;
	
		for (f = p_110683_2_ - p_110683_1_; f < -180.0F; f += 360.0F)
		{
			;
		}
	
		while (f >= 180.0F)
		{
			f -= 360.0F;
		}
	
		return p_110683_1_ + p_110683_3_ * f;
	}*/

	@Override
	public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
	{
		super.setLivingAnimations((T) entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
		/*float f = this.updateHorseRotation(entitylivingbaseIn.prevRenderYawOffset, entitylivingbaseIn.renderYawOffset, partialTickTime);
		float f1 = this.updateHorseRotation(entitylivingbaseIn.prevRotationYawHead, entitylivingbaseIn.rotationYawHead, partialTickTime);
		float f3 = f1 - f;
		
		if (f3 > 20.0F)
		{
			f3 = 20.0F;
		}
		
		if (f3 < -20.0F)
		{
			f3 = -20.0F;
		}
		
		AbstractHorseEntity abstracthorse = (AbstractHorseEntity) entitylivingbaseIn;
		boolean flag2 = abstracthorse.isBeingRidden();
		
		if (flag2)
		{
			limbSwing = limbSwing / 2;
		}*/

		this.horn.rotationPointY = this.field_217128_b.rotationPointY;
		this.horn.rotationPointZ = this.field_217128_b.rotationPointZ;
		this.horn.rotateAngleX = this.field_217128_b.rotateAngleX;
		this.horn.rotateAngleY = this.field_217128_b.rotateAngleY;
	}
}
