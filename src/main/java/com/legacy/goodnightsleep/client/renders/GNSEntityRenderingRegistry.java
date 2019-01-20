package com.legacy.goodnightsleep.client.renders;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import com.legacy.goodnightsleep.client.renders.entities.factory.BabyCreeperFactory;
import com.legacy.goodnightsleep.client.renders.entities.factory.GiantFactory;
import com.legacy.goodnightsleep.client.renders.entities.factory.GummyBearFactory;
import com.legacy.goodnightsleep.client.renders.entities.factory.HerobrineFactory;
import com.legacy.goodnightsleep.client.renders.entities.factory.TormenterFactory;
import com.legacy.goodnightsleep.entities.dream.EntityBabyCreeper;
import com.legacy.goodnightsleep.entities.dream.EntityGummyBear;
import com.legacy.goodnightsleep.entities.nightmare.EntityGiant;
import com.legacy.goodnightsleep.entities.nightmare.EntityHerobrine;
import com.legacy.goodnightsleep.entities.nightmare.EntityTormenter;

public class GNSEntityRenderingRegistry 
{

	public static void initialize()
	{
		register(EntityTormenter.class, new TormenterFactory());
		register(EntityBabyCreeper.class, new BabyCreeperFactory());
		register(EntityGiant.class, new GiantFactory());
		register(EntityHerobrine.class, new HerobrineFactory());
		register(EntityGummyBear.class, new GummyBearFactory());
	}

	public static <ENTITY extends Entity> void register(Class<ENTITY> classes, IRenderFactory<? super ENTITY> factory)
	{
		RenderingRegistry.registerEntityRenderingHandler(classes, factory);
	}

}