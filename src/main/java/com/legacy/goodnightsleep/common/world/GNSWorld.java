package com.legacy.goodnightsleep.common.world;

import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;

import com.legacy.goodnightsleep.common.GNSConfig;
import com.legacy.goodnightsleep.common.world.dream.BiomeGenGoodDreamPlains;
import com.legacy.goodnightsleep.common.world.dream.GoodDreamWorldProvider;

public class GNSWorld
{

	public static Biome good_dream_plains, nightmare_hills;

	public static DimensionType dream_dimension_type, nightmare_dimension_type;

	public static void initialization()
	{
		//aether_biome = new AetherBiome();
		good_dream_plains = new BiomeGenGoodDreamPlains();

		//nightmare_hills = (new BiomeGenNightmareHills(51, new BiomeProperties("Nightmare Hills").setRainDisabled().setBaseHeight(0.1F).setHeightVariation(2.0F).setTemperature(2.0F).setRainfall(0.0F);

		//Biome.registerBiome(AetherConfig.getAetherBiomeID(), "aether_legacy:aether_highlands", aether_biome);

		Biome.registerBiome(GNSConfig.getDreamBiomeID(), "goodnightsleep:good_dream_plains", good_dream_plains);
		//Biome.registerBiome(GNSConfig.getNightmareBiomeID(), "goodnightsleep:nightmare_hills", nightmare_hills);
		
		dream_dimension_type = DimensionType.register("GoodDream", "_goodDream", GNSConfig.getDreamDimensionID(), GoodDreamWorldProvider.class, false);

		DimensionManager.registerDimension(GNSConfig.getDreamDimensionID(), dream_dimension_type);
	}

}