package com.legacy.goodnightsleep.client.render.models;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class BabyCreeperModel<T extends Entity> extends SegmentedModel<T>
{

	ModelRenderer head;

	ModelRenderer body;

	ModelRenderer leg3;

	ModelRenderer leg4;

	ModelRenderer leg1;

	ModelRenderer leg2;

	public BabyCreeperModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
		this.head.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.head.setTextureSize(64, 32);
		this.head.mirror = true;
		this.setRotationPoint(this.head, 0.0F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 16, 16);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 8, 4);
		this.body.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.body.setTextureSize(64, 32);
		this.body.mirror = true;
		this.setRotationPoint(this.body, 0.0F, 0.0F, 0.0F);
		this.leg3 = new ModelRenderer(this, 0, 16);
		this.leg3.addBox(-2.0F, 0.0F, -4.0F, 4, 4, 4);
		this.leg3.setRotationPoint(-2.0F, 20.0F, -2.0F);
		this.leg3.setTextureSize(64, 32);
		this.leg3.mirror = true;
		this.setRotationPoint(this.leg3, 0.0F, 0.0F, 0.0F);
		this.leg4 = new ModelRenderer(this, 0, 16);
		this.leg4.addBox(-2.0F, 0.0F, -4.0F, 4, 4, 4);
		this.leg4.setRotationPoint(2.0F, 20.0F, -2.0F);
		this.leg4.setTextureSize(64, 32);
		this.leg4.mirror = true;
		this.setRotationPoint(this.leg4, 0.0F, 0.0F, 0.0F);
		this.leg1 = new ModelRenderer(this, 0, 16);
		this.leg1.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 4);
		this.leg1.setRotationPoint(-2.0F, 20.0F, 2.0F);
		this.leg1.setTextureSize(64, 32);
		this.leg1.mirror = true;
		this.setRotationPoint(this.leg1, 0.0F, 0.0F, 0.0F);
		this.leg2 = new ModelRenderer(this, 0, 16);
		this.leg2.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 4);
		this.leg2.setRotationPoint(2.0F, 20.0F, 2.0F);
		this.leg2.setTextureSize(64, 32);
		this.leg2.mirror = true;
		this.setRotationPoint(this.leg2, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public Iterable<ModelRenderer> getParts()
	{
		return ImmutableList.of(this.head, this.body, this.leg3, this.leg4, this.leg1, this.leg2);
	}

	private void setRotationPoint(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
		this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);
		this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
}
