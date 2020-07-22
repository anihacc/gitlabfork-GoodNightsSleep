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
		return ImmutableList.of(this.head, this.horn);
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
		this.horn.rotationPointY = this.head.rotationPointY;
		this.horn.rotationPointZ = this.head.rotationPointZ;

		this.horn.copyModelAngles(this.head);
	}
}
