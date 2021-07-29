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
import net.minecraft.world.entity.Entity;

public class GummyBearModel<T extends Entity> extends ListModel<T>
{
	protected final ModelPart head;
	protected final ModelPart snout;
	protected final ModelPart body;
	protected final ModelPart rightArm;
	protected final ModelPart leftArm;
	protected final ModelPart leftLeg;
	protected final ModelPart rightLeg;
	protected final ModelPart leftEar;
	protected final ModelPart rightEar;
	protected final ModelPart belly;

	public GummyBearModel(ModelPart model)
	{
		this.head = model.getChild("head");
		this.snout = model.getChild("snout");

		this.body = model.getChild("body");
		this.belly = model.getChild("belly");

		this.rightArm = model.getChild("right_arm");
		this.leftArm = model.getChild("left_arm");

		this.rightLeg = model.getChild("right_leg");
		this.leftLeg = model.getChild("left_leg");

		this.rightEar = model.getChild("right_ear");
		this.leftEar = model.getChild("left_ear");
	}

	public static LayerDefinition createBodyLayer(CubeDeformation size)
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -3.0F, 8, 7, 6, size), PartPose.offset(0.0F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(28, 0).addBox(-2.0F, -4.0F, -5.0F, 4, 4, 2, size), PartPose.offset(0.0F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 13).addBox(-4.0F, -4.0F, -2.0F, 8, 9, 4, size), PartPose.offset(0.0F, 16.0F, 0.0F));
		partdefinition.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(28, 6).addBox(-3.0F, -1.0F, -3.0F, 6, 5, 1, size), PartPose.offset(0.0F, 16.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 13).addBox(-3.0F, -1.0F, -2.0F, 3, 5, 4, size), PartPose.offset(-4.0F, 13.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -2.0F, 3, 5, 4, size), PartPose.offset(2.0F, 21.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(24, 22).addBox(-2.0F, 0.0F, -3.0F, 3, 3, 5, size), PartPose.offset(-2.0F, 21.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(24, 22).addBox(-4.0F, -7.0F, -3.0F, 8, 7, 6, size), PartPose.offset(2.0F, 21.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 26).addBox(-4.0F, -9.0F, -1.0F, 3, 2, 1, size), PartPose.offset(0.0F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(0, 26).addBox(1.0F, -9.0F, -1.0F, 3, 2, 1, size), PartPose.offset(0.0F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public Iterable<ModelPart> parts()
	{
		return ImmutableList.of(this.head, this.body, this.snout, this.rightArm, this.leftArm, this.belly, this.leftEar, this.rightEar, this.leftLeg, this.rightLeg);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
	}
}