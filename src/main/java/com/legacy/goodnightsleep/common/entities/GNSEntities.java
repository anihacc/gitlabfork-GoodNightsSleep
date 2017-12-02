package com.legacy.goodnightsleep.common.entities;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import com.legacy.goodnightsleep.common.GoodNightSleep;
import com.legacy.goodnightsleep.common.entities.nightmare.EntityTormenter;

public class GNSEntities 
{

	public static void initialization()
	{
		register(EntityTormenter.class, "tormenter", 0, 10516796, 5525034);
		//DataSerializerRegistry.initialize();
	}

	public static void register(Class<? extends Entity> entityClass, String entityName, int entityID)
	{
		EntityRegistry.registerModEntity(GoodNightSleep.locate(entityName), entityClass, entityName, entityID, GoodNightSleep.modid, 80, 3, true);
	}

	public static void register(Class<? extends Entity> entityClass, String entityName, int entityID, int primaryEggColor, int secondaryEggColor)
	{
		EntityRegistry.registerModEntity(GoodNightSleep.locate(entityName), entityClass, entityName, entityID, GoodNightSleep.instance, 80, 3, false, primaryEggColor, secondaryEggColor);
	}

}