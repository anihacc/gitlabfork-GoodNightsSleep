package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.render.GNSRenderRefs;
import com.legacy.goodnightsleep.client.render.models.GummyBearModel;
import com.legacy.goodnightsleep.entity.dream.GummyBearEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GummyBearRenderer extends MobRenderer<GummyBearEntity, GummyBearModel<GummyBearEntity>>
{
	private static final ResourceLocation TEXTURE = GoodNightSleep.locate("textures/entity/gummy_bear.png");

	public GummyBearRenderer(EntityRendererProvider.Context context)
	{
		super(context, new GummyBearModel<>(context.bakeLayer(GNSRenderRefs.GUMMY_BEAR)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(GummyBearEntity entity)
	{
		return TEXTURE;
	}
}