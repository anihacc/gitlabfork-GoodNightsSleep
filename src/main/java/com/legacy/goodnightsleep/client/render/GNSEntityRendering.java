package com.legacy.goodnightsleep.client.render;

import com.legacy.goodnightsleep.client.render.entity.BabyCreeperRenderer;
import com.legacy.goodnightsleep.client.render.entity.GummyBearRenderer;
import com.legacy.goodnightsleep.client.render.entity.HerobrineRenderer;
import com.legacy.goodnightsleep.client.render.entity.SpawnerRenderer;
import com.legacy.goodnightsleep.client.render.entity.TormenterRenderer;
import com.legacy.goodnightsleep.client.render.entity.UnicornRenderer;
import com.legacy.goodnightsleep.entity.GNSEntityTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class GNSEntityRendering
{

	public static void initialization()
	{
		register(GNSEntityTypes.UNICORN, UnicornRenderer::new);
		register(GNSEntityTypes.GUMMY_BEAR, GummyBearRenderer::new);
		register(GNSEntityTypes.BABY_CREEPER, BabyCreeperRenderer::new);
		register(GNSEntityTypes.TORMENTER, TormenterRenderer::new);
		register(GNSEntityTypes.HEROBRINE, HerobrineRenderer::new);
		register(GNSEntityTypes.SPAWNER_ENTITY, SpawnerRenderer::new);
	}

	private static <T extends Entity> void register(EntityType<T> entityClass, IRenderFactory<? super T> renderFactory)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

}