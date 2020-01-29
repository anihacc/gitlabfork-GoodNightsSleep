package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.render.models.GummyBearModel;
import com.legacy.goodnightsleep.entity.dream.GummyBearEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GummyBearRenderer extends MobRenderer<GummyBearEntity, GummyBearModel<GummyBearEntity>>
{
	private static final ResourceLocation TEXTURE = GoodNightSleep.locate("textures/entities/gummy_bear.png");

	public GummyBearRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new GummyBearModel<>(), 0.5F);
	}

	@Override
	public ResourceLocation getEntityTexture(GummyBearEntity entity)
	{
		return TEXTURE;
	}
}