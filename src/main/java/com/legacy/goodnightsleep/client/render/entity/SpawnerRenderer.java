package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.entity.GNSSpawnerEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpawnerRenderer extends MobRenderer<GNSSpawnerEntity, PigModel<GNSSpawnerEntity>>
{
	public SpawnerRenderer(EntityRendererManager manager)
	{
		super(manager, new PigModel<>(0.0F), 0.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(GNSSpawnerEntity entity)
	{
		return new ResourceLocation("textures/entity/pig/pig.png");
	}
}