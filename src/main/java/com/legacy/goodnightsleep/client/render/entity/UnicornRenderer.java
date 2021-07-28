package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.render.models.UnicornModel;
import com.legacy.goodnightsleep.entity.dream.UnicornEntity;

import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UnicornRenderer extends AbstractHorseRenderer<UnicornEntity, UnicornModel<UnicornEntity>>
{
	public UnicornRenderer(EntityRenderDispatcher manager)
	{
		super(manager, new UnicornModel<>(0.0F), 1.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(UnicornEntity entity)
	{
		String type = entity.getUnicornType() == 1 ? "green" : entity.getUnicornType() == 2 ? "yellow" : entity.getUnicornType() == 3 ? "blue" : "pink";
		return GoodNightSleep.locate("textures/entity/unicorn/unicorn_" + type + ".png");
	}
}