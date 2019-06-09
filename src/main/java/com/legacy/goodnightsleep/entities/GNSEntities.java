package com.legacy.goodnightsleep.entities;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.entities.dream.EntityBabyCreeper;
import com.legacy.goodnightsleep.entities.dream.EntityGummyBear;
import com.legacy.goodnightsleep.entities.nightmare.EntityHerobrine;
import com.legacy.goodnightsleep.entities.nightmare.EntityTormenter;
import com.legacy.goodnightsleep.registry.VariableConstants;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class GNSEntities 
{
	private static int modEntityId;
	
	public static void initialization()
	{
		register("tormenter", EntityTormenter.class, 10516796, 5525034);
		register("baby_creeper", EntityBabyCreeper.class, 45079, 16711901);
		register("herobrine", EntityHerobrine.class);
		register("gummy_bear", EntityGummyBear.class); //, 0xffffff, 0xffffff
		
		register("gns_spawner", EntityGNSSpawner.class);
	}

	private static void register(String entityName, Class<? extends Entity> clazz, int primaryEggColor, int secondaryEggColor)
	{
		EntityRegistry.registerModEntity(clazz, entityName, modEntityId, GoodNightSleep.instance, 80, 3, false, primaryEggColor, secondaryEggColor);

		modEntityId++;
	}

	private static void register(String entityName, Class<? extends Entity> clazz)
	{
		EntityRegistry.registerModEntity(clazz, entityName, modEntityId, GoodNightSleep.instance, 64, 3, false);

		modEntityId++;
	}

}