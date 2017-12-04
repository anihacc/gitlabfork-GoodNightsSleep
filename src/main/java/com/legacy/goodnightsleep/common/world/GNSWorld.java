package com.legacy.goodnightsleep.common.world;

import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;

import com.legacy.goodnightsleep.common.GNSConfig;
import com.legacy.goodnightsleep.common.world.dream.BiomeGenGoodDreamPlains;
import com.legacy.goodnightsleep.common.world.dream.GoodDreamWorldProvider;
import com.legacy.goodnightsleep.common.world.nightmare.BiomeGenNightmareHills;
import com.legacy.goodnightsleep.common.world.nightmare.NightmareWorldProvider;

public class GNSWorld
{

	public static Biome good_dream_plains, nightmare_hills;

	public static DimensionType dream_dimension_type, nightmare_dimension_type;

	public static void initialization()
	{
		good_dream_plains = new BiomeGenGoodDreamPlains();
		nightmare_hills = new BiomeGenNightmareHills();

		Biome.registerBiome(GNSConfig.getDreamBiomeID(), "goodnightsleep:good_dream_plains", good_dream_plains);
		Biome.registerBiome(GNSConfig.getNightmareBiomeID(), "goodnightsleep:nightmare_hills", nightmare_hills);
		
		dream_dimension_type = DimensionType.register("GoodDream", "_goodDream", GNSConfig.getDreamDimensionID(), GoodDreamWorldProvider.class, false);
		nightmare_dimension_type = DimensionType.register("Nightmare", "_nightmare", GNSConfig.getDreamDimensionID(), NightmareWorldProvider.class, false);

		DimensionManager.registerDimension(GNSConfig.getDreamDimensionID(), dream_dimension_type);
		DimensionManager.registerDimension(GNSConfig.getNightmareDimensionID(), nightmare_dimension_type);
	}

}