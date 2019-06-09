package com.legacy.goodnightsleep.world;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.world.dream.GoodDreamWorldProvider;
import com.legacy.goodnightsleep.world.dream.biome.BiomeGoodDreamPlains;
import com.legacy.goodnightsleep.world.nightmare.NightmareWorldProvider;
import com.legacy.goodnightsleep.world.nightmare.biome.BiomeNightmareHills;

import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;

public class GNSWorld
{

	public static Biome good_dream_plains = new BiomeGoodDreamPlains();
	
	public static Biome nightmare_hills = new BiomeNightmareHills();

	public static DimensionType dream_dimension_type, nightmare_dimension_type;

	public static void initialization()
	{
		Biome.registerBiome(GNSConfig.getDreamBiomeID(), "goodnightsleep:good_dream_plains", good_dream_plains);
		Biome.registerBiome(GNSConfig.getNightmareBiomeID(), "goodnightsleep:nightmare_hills", nightmare_hills);
		
		dream_dimension_type = DimensionType.register("GoodDream", "_goodDream", GNSConfig.getDreamDimensionID(), GoodDreamWorldProvider.class, false);
		nightmare_dimension_type = DimensionType.register("Nightmare", "_nightmare", GNSConfig.getDreamDimensionID(), NightmareWorldProvider.class, false);

		DimensionManager.registerDimension(GNSConfig.getDreamDimensionID(), dream_dimension_type);
		DimensionManager.registerDimension(GNSConfig.getNightmareDimensionID(), nightmare_dimension_type);
	}

}