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
		this.horn.setPos(0.0F, 4.0F, -10.0F);
		this.horn.addBox(-1.0F, -16.0F, 2.0F, 2, 7, 2, 0.0F);
	}

	@Override
	public Iterable<ModelRenderer> headParts()
	{
		return ImmutableList.of(this.headParts, this.horn);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public void prepareMobModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
	{
		super.prepareMobModel((T) entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
		this.horn.y = this.headParts.y;
		this.horn.z = this.headParts.z;

		this.horn.copyFrom(this.headParts);
	}
}
