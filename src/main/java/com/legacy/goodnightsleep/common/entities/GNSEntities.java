package com.legacy.goodnightsleep.common.entities;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import com.legacy.goodnightsleep.common.GoodNightSleep;
import com.legacy.goodnightsleep.common.entities.dream.EntityBabyCreeper;
import com.legacy.goodnightsleep.common.entities.dream.EntityGummyBear;
import com.legacy.goodnightsleep.common.entities.nightmare.EntityGiant;
import com.legacy.goodnightsleep.common.entities.nightmare.EntityHerobrine;
import com.legacy.goodnightsleep.common.entities.nightmare.EntityTormenter;

public class GNSEntities 
{

	public static void initialization()
	{
		register(EntityTormenter.class, "tormenter", 0, 10516796, 5525034);
		register(EntityBabyCreeper.class, "baby_creeper", 1, 45079, 16711901);
		register(EntityGiant.class, "giant", 2, 1598464, 30652);
		register(EntityHerobrine.class, "herobrine", 3);
		register(EntityGummyBear.class, "gummy_bear", 4, 0xffffff, 0xffffff);
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