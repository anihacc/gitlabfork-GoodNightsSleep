package com.legacy.goodnightsleep.client.render.entity;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.render.models.UnicornModel;
import com.legacy.goodnightsleep.entity.dream.UnicornEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UnicornRenderer extends MobRenderer<UnicornEntity, UnicornModel<UnicornEntity>>
{
	public UnicornRenderer(EntityRendererManager manager)
	{
		super(manager, new UnicornModel<>(0.0F), 0.75F);
	}

	@Override
	public ResourceLocation getEntityTexture(UnicornEntity entity)
	{
		String type = entity.getUnicornType() == 1 ? "green" : entity.getUnicornType() == 2 ? "yellow" : entity.getUnicornType() == 3 ? "blue" : "pink";
		return GoodNightSleep.locate("textures/entities/unicorn/unicorn_" + type + ".png");
	}
}