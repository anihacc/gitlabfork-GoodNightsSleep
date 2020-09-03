package com.legacy.goodnightsleep.registry;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.data.GNSBiomeGen;
import com.legacy.goodnightsleep.world.biome_provider.DreamBiomeProvider;
import com.legacy.goodnightsleep.world.biome_provider.NightmareBiomeProvider;

import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.ForgeRegistries;

public class GNSBiomes
{
	public static final RegistryKey<Biome> SLEEPY_HILLS = RegistryKey.func_240903_a_(Registry.BIOME_KEY, GoodNightSleep.locate("sleepy_hills"));
	public static final RegistryKey<Biome> DREAMY_FOREST = RegistryKey.func_240903_a_(Registry.BIOME_KEY, GoodNightSleep.locate("dreamy_forest"));
	public static final RegistryKey<Biome> GOOD_DREAM_PLAINS = RegistryKey.func_240903_a_(Registry.BIOME_KEY, GoodNightSleep.locate("good_dream_plains"));

	public static final RegistryKey<Biome> NIGHTMARE_HILLS = RegistryKey.func_240903_a_(Registry.BIOME_KEY, GoodNightSleep.locate("nightmare_hills"));
	public static final RegistryKey<Biome> SHAMEFUL_PLAINS = RegistryKey.func_240903_a_(Registry.BIOME_KEY, GoodNightSleep.locate("shameful_plains"));
	public static final RegistryKey<Biome> WASTED_FOREST = RegistryKey.func_240903_a_(Registry.BIOME_KEY, GoodNightSleep.locate("wasted_forest"));

	public static final RegistryKey<Biome> HOPEFUL_FIELDS = RegistryKey.func_240903_a_(Registry.BIOME_KEY, GoodNightSleep.locate("hopeful_fields"));

	public static void init(Register<Biome> event)
	{
		GNSDimensions.initNoiseSettings();
		Registry.register(Registry.BIOME_PROVIDER_CODEC, "dream_multi_noise", DreamBiomeProvider.dreamProviderCodec);
		Registry.register(Registry.BIOME_PROVIDER_CODEC, "nightmare_multi_noise", NightmareBiomeProvider.nightmareProviderCodec);

		register(SLEEPY_HILLS, GNSBiomeGen.Makers.SLEEPY_HILLS);
		register(DREAMY_FOREST, GNSBiomeGen.Makers.DREAMY_FOREST);
		register(GOOD_DREAM_PLAINS, GNSBiomeGen.Makers.GOOD_DREAM_PLAINS);

		register(NIGHTMARE_HILLS, GNSBiomeGen.Makers.NIGHTMARE_HILLS);
		register(SHAMEFUL_PLAINS, GNSBiomeGen.Makers.SHAMEFUL_PLAINS);
		register(WASTED_FOREST, GNSBiomeGen.Makers.WASTED_FOREST);

		register(HOPEFUL_FIELDS, GNSBiomeGen.Makers.HOPEFUL_FIELDS);

		/*BiomeDictionary.addTypes(GOOD_DREAM_PLAINS, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS);
		BiomeDictionary.addTypes(NIGHTMARE_HILLS, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DEAD);*/
	}

	@SuppressWarnings("deprecation")
	private static Biome register(RegistryKey<Biome> biomeKeyIn, Biome biomeIn)
	{
		int id = ForgeRegistries.BIOMES.getEntries().size() + 1;
		return WorldGenRegistries.func_243662_a(WorldGenRegistries.field_243657_i, id, biomeKeyIn, biomeIn);
	}

	public static class SurfaceBuilders
	{
		public static final SurfaceBuilderConfig DREAM_GRASS_DIRT_GRAVEL_CONFIG = new SurfaceBuilderConfig(GNSBlocks.dream_grass_block.getDefaultState(), GNSBlocks.dream_dirt.getDefaultState(), Blocks.GRAVEL.getDefaultState());
		public static final SurfaceBuilderConfig NIGHTMARE_GRASS_DIRT_GRAVEL_CONFIG = new SurfaceBuilderConfig(GNSBlocks.nightmare_grass_block.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState());

		public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DREAM_GRASS_SURFACE_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(DREAM_GRASS_DIRT_GRAVEL_CONFIG);
		public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> NIGHTMARE_GRASS_SURFACE_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(NIGHTMARE_GRASS_DIRT_GRAVEL_CONFIG);
	}
}
