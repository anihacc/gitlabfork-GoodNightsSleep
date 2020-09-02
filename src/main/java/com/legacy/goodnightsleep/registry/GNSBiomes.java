package com.legacy.goodnightsleep.registry;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent.Register;

public class GNSBiomes
{
	public static final RegistryKey<Biome> GOOD_DREAM_PLAINS = RegistryKey.func_240903_a_(Registry.BIOME_KEY, GoodNightSleep.locate("good_dream_plains"));
	public static final RegistryKey<Biome> NIGHTMARE_HILLS = RegistryKey.func_240903_a_(Registry.BIOME_KEY, GoodNightSleep.locate("nightmare_hills"));

	public static void init(Register<Biome> event)
	{
		/*BiomeDictionary.addTypes(GOOD_DREAM_PLAINS, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS);
		BiomeDictionary.addTypes(NIGHTMARE_HILLS, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DEAD);*/
	}

	public static class SurfaceBuilders
	{
		public static final SurfaceBuilderConfig DREAM_GRASS_DIRT_GRAVEL_CONFIG = new SurfaceBuilderConfig(GNSBlocks.dream_grass_block.getDefaultState(), GNSBlocks.dream_dirt.getDefaultState(), Blocks.GRAVEL.getDefaultState());
		public static final SurfaceBuilderConfig NIGHTMARE_GRASS_DIRT_GRAVEL_CONFIG = new SurfaceBuilderConfig(GNSBlocks.nightmare_grass_block.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState());

		public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DREAM_GRASS_SURFACE_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(DREAM_GRASS_DIRT_GRAVEL_CONFIG);
		public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> NIGHTMARE_GRASS_SURFACE_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(NIGHTMARE_GRASS_DIRT_GRAVEL_CONFIG);
	}
}
