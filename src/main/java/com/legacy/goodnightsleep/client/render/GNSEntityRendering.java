package com.legacy.goodnightsleep.client.render;

import com.legacy.goodnightsleep.client.render.entity.RenderBabyCreeper;
import com.legacy.goodnightsleep.client.render.entity.RenderGummyBear;
import com.legacy.goodnightsleep.client.render.entity.RenderHerobrine;
import com.legacy.goodnightsleep.client.render.entity.RenderTormenter;
import com.legacy.goodnightsleep.client.render.entity.RenderUnicorn;
import com.legacy.goodnightsleep.entity.EntityHerobrine;
import com.legacy.goodnightsleep.entity.EntityTormenter;
import com.legacy.goodnightsleep.entity.dream.EntityBabyCreeper;
import com.legacy.goodnightsleep.entity.dream.EntityGummyBear;
import com.legacy.goodnightsleep.entity.dream.EntityUnicorn;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class GNSEntityRendering
{

	public static void initialization()
	{
		register(EntityUnicorn.class, RenderUnicorn::new);
		register(EntityGummyBear.class, RenderGummyBear::new);
		register(EntityBabyCreeper.class, RenderBabyCreeper::new);
		register(EntityTormenter.class, RenderTormenter::new);
		register(EntityHerobrine.class, RenderHerobrine::new);
	}

	private static <T extends Entity> void register(Class<T> entityClass, IRenderFactory<? super T> renderFactory)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

}