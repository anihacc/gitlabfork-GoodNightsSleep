package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.render.models.TormenterModel;
import com.legacy.goodnightsleep.entity.TormenterEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TormenterRenderer extends MobRenderer<TormenterEntity, TormenterModel<TormenterEntity>>
{
	private static final ResourceLocation TEXTURE = GoodNightSleep.locate("textures/entities/tormenter.png");

	public TormenterRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new TormenterModel<>(0.0F, true), 0.5F);
	}

	protected ResourceLocation getEntityTexture(TormenterEntity entity)
	{
		return TEXTURE;
	}
}