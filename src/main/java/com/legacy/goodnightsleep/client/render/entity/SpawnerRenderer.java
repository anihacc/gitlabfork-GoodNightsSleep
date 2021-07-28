package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.entity.GNSSpawnerEntity;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.PigModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpawnerRenderer extends MobRenderer<GNSSpawnerEntity, PigModel<GNSSpawnerEntity>>
{
	public SpawnerRenderer(EntityRenderDispatcher manager)
	{
		super(manager, new PigModel<>(0.0F), 0.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(GNSSpawnerEntity entity)
	{
		return new ResourceLocation("textures/entity/pig/pig.png");
	}
}