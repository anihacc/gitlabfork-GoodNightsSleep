package com.legacy.goodnightsleep.client.render;

import com.legacy.goodnightsleep.client.render.entity.BabyCreeperRenderer;
import com.legacy.goodnightsleep.client.render.entity.GummyBearRenderer;
import com.legacy.goodnightsleep.client.render.entity.HerobrineRenderer;
import com.legacy.goodnightsleep.client.render.entity.TormenterRenderer;
import com.legacy.goodnightsleep.client.render.entity.UnicornRenderer;
import com.legacy.goodnightsleep.entity.HerobrineEntity;
import com.legacy.goodnightsleep.entity.TormenterEntity;
import com.legacy.goodnightsleep.entity.dream.BabyCreeperEntity;
import com.legacy.goodnightsleep.entity.dream.GummyBearEntity;
import com.legacy.goodnightsleep.entity.dream.UnicornEntity;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class GNSEntityRendering
{

	public static void initialization()
	{
		register(UnicornEntity.class, UnicornRenderer::new);
		register(GummyBearEntity.class, GummyBearRenderer::new);
		register(BabyCreeperEntity.class, BabyCreeperRenderer::new);
		register(TormenterEntity.class, TormenterRenderer::new);
		register(HerobrineEntity.class, HerobrineRenderer::new);
	}

	private static <T extends Entity> void register(Class<T> entityClass, IRenderFactory<? super T> renderFactory)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

}