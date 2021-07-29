package com.legacy.goodnightsleep.client.render.models;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class BabyCreeperModel<T extends Entity> extends ListModel<T>
{
	public ModelPart root;
	public ModelPart head;
	public ModelPart body;

	public ModelPart rightHindLeg;
	public ModelPart leftHindLeg;

	public ModelPart rightFrontLeg;
	public ModelPart leftFrontLeg;

	public BabyCreeperModel(ModelPart model)
	{
		this.root = model;

		this.head = model.getChild("head");
		this.body = model.getChild("body");

		this.rightFrontLeg = model.getChild("right_front_leg");
		this.leftFrontLeg = model.getChild("left_front_leg");
		this.rightHindLeg = model.getChild("right_hind_leg");
		this.leftHindLeg = model.getChild("left_hind_leg");

		/*this.texWidth = 64;
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
		this.setRotationPoint(this.leg2, 0.0F, 0.0F, 0.0F);*/
	}

	public static LayerDefinition createBodyLayer(CubeDeformation size)
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, size), PartPose.offset(0.0F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8, 8, 4, size), PartPose.offset(0.0F, 12.0F, 0.0F));

		CubeListBuilder frontLeg = CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -4.0F, 4, 4, 4, size);
		CubeListBuilder backLeg = CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, 0.0F, 4, 4, 4, size);

		partdefinition.addOrReplaceChild("right_front_leg", frontLeg, PartPose.offset(-2.0F, 20.0F, -2.0F)); // leg 3
		partdefinition.addOrReplaceChild("left_front_leg", frontLeg, PartPose.offset(2.0F, 20.0F, -2.0F)); // leg 4
		partdefinition.addOrReplaceChild("right_hind_leg", backLeg, PartPose.offset(-2.0F, 20.0F, 2.0F)); // leg 1
		partdefinition.addOrReplaceChild("left_hind_leg", backLeg, PartPose.offset(2.0F, 20.0F, 2.0F)); // leg 2

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public Iterable<ModelPart> parts()
	{
		return ImmutableList.of(this.head, this.body, this.rightHindLeg, this.leftHindLeg, this.rightFrontLeg, this.leftFrontLeg);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.rightHindLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftHindLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
}
