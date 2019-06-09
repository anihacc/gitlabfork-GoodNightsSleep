package com.legacy.goodnightsleep.world;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.world.dream.GoodDreamWorldProvider;
import com.legacy.goodnightsleep.world.dream.biome.BiomeGoodDreamPlains;
import com.legacy.goodnightsleep.world.nightmare.NightmareWorldProvider;
import com.legacy.goodnightsleep.world.nightmare.biome.BiomeNightmareHills;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

public class GNSWorld
{

	public static BiomeGenBase good_dream_plains = new BiomeGoodDreamPlains();
	
	public static BiomeGenBase nightmare_hills = new BiomeNightmareHills();

	public static void initialization()
	{
		good_dream_plains = new BiomeGoodDreamPlains();
		nightmare_hills = new BiomeNightmareHills();


		DimensionManager.registerProviderType(GNSConfig.getDreamDimensionID(), GoodDreamWorldProvider.class, false);
		DimensionManager.registerProviderType(GNSConfig.getNightmareDimensionID(), NightmareWorldProvider.class, false);
		
		DimensionManager.registerDimension(GNSConfig.getDreamDimensionID(), GNSConfig.getDreamDimensionID());
		DimensionManager.registerDimension(GNSConfig.getNightmareDimensionID(), GNSConfig.getNightmareDimensionID());

	}

}