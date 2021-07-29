package com.legacy.goodnightsleep.client.render.models;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.animal.horse.AbstractHorse;

public class UnicornModel<T extends AbstractHorse> extends HorseModel<T>
{
	private final ModelPart horn;

	public UnicornModel(ModelPart model)
	{
		super(model);

		this.horn = model.getChild("horn");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = HorseModel.createBodyMesh(CubeDeformation.NONE);
		PartDefinition root = meshdefinition.getRoot();
		//PartDefinition head = root.getChild("head_parts");

		root.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(56, 0).addBox(-1.0F, -16.0F, 2.0F, 2, 7, 2), PartPose.offset(0.0F, 4.0F, -10.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public Iterable<ModelPart> headParts()
	{
		return ImmutableList.of(this.headParts, this.horn);
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
