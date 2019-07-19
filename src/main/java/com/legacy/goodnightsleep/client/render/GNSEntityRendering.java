package com.legacy.goodnightsleep.client.render;

import com.legacy.goodnightsleep.client.render.entity.RenderBabyCreeper;
import com.legacy.goodnightsleep.client.render.entity.RenderGummyBear;
import com.legacy.goodnightsleep.client.render.entity.RenderHerobrine;
import com.legacy.goodnightsleep.client.render.entity.RenderTormenter;
import com.legacy.goodnightsleep.client.render.entity.RenderUnicorn;
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
		register(UnicornEntity.class, RenderUnicorn::new);
		register(GummyBearEntity.class, RenderGummyBear::new);
		register(BabyCreeperEntity.class, RenderBabyCreeper::new);
		register(TormenterEntity.class, RenderTormenter::new);
		register(HerobrineEntity.class, RenderHerobrine::new);
	}

	private static <T extends Entity> void register(Class<T> entityClass, IRenderFactory<? super T> renderFactory)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

}