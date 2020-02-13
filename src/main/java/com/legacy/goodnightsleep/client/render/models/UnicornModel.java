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
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
	{
		super.setLivingAnimations((T) entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
		this.horn.rotationPointY = this.field_217128_b.rotationPointY;
		this.horn.rotationPointZ = this.field_217128_b.rotationPointZ;
		this.horn.rotateAngleX = this.field_217128_b.rotateAngleX;
		this.horn.rotateAngleY = this.field_217128_b.rotateAngleY;
	}
}
