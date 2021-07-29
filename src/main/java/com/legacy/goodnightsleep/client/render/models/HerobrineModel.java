package com.legacy.goodnightsleep.client.render.models;

import com.legacy.goodnightsleep.entity.HerobrineEntity;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;

public class HerobrineModel<T extends HerobrineEntity> extends HumanoidModel<T>
{
	public HerobrineModel(ModelPart model)
	{
		super(model);
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		return LayerDefinition.create(meshdefinition, 64, 32);
	}
}