package com.legacy.goodnightsleep.client.render.models;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

public class BabyCreeperModel<T extends Entity> extends ListModel<T>
{

	ModelPart head;

	ModelPart body;

	ModelPart leg3;

	ModelPart leg4;

	ModelPart leg1;

	ModelPart leg2;

	public BabyCreeperModel()
	{
		this.texWidth = 64;
		this.texHeight = 32;
		this.head = new ModelPart(this, 0, 0);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
		this.head.setPos(0.0F, 12.0F, 0.0F);
		this.head.setTexSize(64, 32);
		this.head.mirror = true;
		this.setRotationPoint(this.head, 0.0F, 0.0F, 0.0F);
		this.body = new ModelPart(this, 16, 16);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 8, 4);
		this.body.setPos(0.0F, 12.0F, 0.0F);
		this.body.setTexSize(64, 32);
		this.body.mirror = true;
		this.setRotationPoint(this.body, 0.0F, 0.0F, 0.0F);
		this.leg3 = new ModelPart(this, 0, 16);
		this.leg3.addBox(-2.0F, 0.0F, -4.0F, 4, 4, 4);
		this.leg3.setPos(-2.0F, 20.0F, -2.0F);
		this.leg3.setTexSize(64, 32);
		this.leg3.mirror = true;
		this.setRotationPoint(this.leg3, 0.0F, 0.0F, 0.0F);
		this.leg4 = new ModelPart(this, 0, 16);
		this.leg4.addBox(-2.0F, 0.0F, -4.0F, 4, 4, 4);
		this.leg4.setPos(2.0F, 20.0F, -2.0F);
		this.leg4.setTexSize(64, 32);
		this.leg4.mirror = true;
		this.setRotationPoint(this.leg4, 0.0F, 0.0F, 0.0F);
		this.leg1 = new ModelPart(this, 0, 16);
		this.leg1.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 4);
		this.leg1.setPos(-2.0F, 20.0F, 2.0F);
		this.leg1.setTexSize(64, 32);
		this.leg1.mirror = true;
		this.setRotationPoint(this.leg1, 0.0F, 0.0F, 0.0F);
		this.leg2 = new ModelPart(this, 0, 16);
		this.leg2.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 4);
		this.leg2.setPos(2.0F, 20.0F, 2.0F);
		this.leg2.setTexSize(64, 32);
		this.leg2.mirror = true;
		this.setRotationPoint(this.leg2, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public Iterable<ModelPart> parts()
	{
		return ImmutableList.of(this.head, this.body, this.leg3, this.leg4, this.leg1, this.leg2);
	}

	private void setRotationPoint(ModelPart model, float x, float y, float z)
	{
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.leg1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg2.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg3.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg4.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
}
