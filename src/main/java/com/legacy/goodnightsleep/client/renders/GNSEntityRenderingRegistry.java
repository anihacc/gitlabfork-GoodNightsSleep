package com.legacy.goodnightsleep.client.renders;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

public class GNSEntityRenderingRegistry
{

	public static void initialize()
	{
		//register(EntityTormenter.class, RenderTormenter());
		//register(EntityBabyCreeper.class, RenderBabyCreeper());
		//register(EntityHerobrine.class, RenderHerobrine());
		//register(EntityGummyBear.class, RenderGummyBear());
	}

	public static void register(Class<? extends Entity> entityClass, Render render)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, render);
	}
}