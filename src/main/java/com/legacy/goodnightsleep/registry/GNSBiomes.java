package com.legacy.goodnightsleep.registry;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.data.GNSBiomeProv;
import com.legacy.goodnightsleep.world.biome_provider.DreamBiomeProvider;
import com.legacy.goodnightsleep.world.biome_provider.NightmareBiomeProvider;

import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;

public class GNSBiomes
{
	public static final Biome SLEEPY_HILLS = GNSBiomeProv.Makers.SLEEPY_HILLS;
	public static final Biome DREAMY_FOREST = GNSBiomeProv.Makers.DREAMY_FOREST;
	public static final Biome GOOD_DREAM_PLAINS = GNSBiomeProv.Makers.GOOD_DREAM_PLAINS;
	public static final Biome LOLLIPOP_LANDS = GNSBiomeProv.Makers.LOLLIPOP_LANDS;

	public static final Biome NIGHTMARE_HILLS = GNSBiomeProv.Makers.NIGHTMARE_HILLS;
	public static final Biome SHAMEFUL_PLAINS = GNSBiomeProv.Makers.SHAMEFUL_PLAINS;
	public static final Biome WASTED_FOREST = GNSBiomeProv.Makers.WASTED_FOREST;

	public static final Biome HOPEFUL_FIELDS = GNSBiomeProv.Makers.HOPEFUL_FIELDS;

	public static void init(Register<Biome> event)
	{
		IForgeRegistry<Biome> registry = event.getRegistry();
		GNSDimensions.initNoiseSettings();

		register(registry, "sleepy_hills", SLEEPY_HILLS);
		register(registry, "dreamy_forest", DREAMY_FOREST);
		register(registry, "good_dream_plains", GOOD_DREAM_PLAINS);
		register(registry, "lollipop_land", LOLLIPOP_LANDS);

		register(registry, "nightmare_hills", NIGHTMARE_HILLS);
		register(registry, "shameful_plains", SHAMEFUL_PLAINS);
		register(registry, "wasted_forest", WASTED_FOREST);

		register(registry, "hopeful_fields", HOPEFUL_FIELDS);

		Registry.register(Registry.BIOME_PROVIDER_CODEC, "dream_multi_noise", DreamBiomeProvider.dreamProviderCodec);
		Registry.register(Registry.BIOME_PROVIDER_CODEC, "nightmare_multi_noise", NightmareBiomeProvider.nightmareProviderCodec);

		/*BiomeDictionary.addTypes(GOOD_DREAM_PLAINS, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS);
		BiomeDictionary.addTypes(NIGHTMARE_HILLS, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DEAD);*/
	}

	private static void register(IForgeRegistry<Biome> registryIn, String keyIn, Biome biomeIn)
	{
		GNSRegistry.register(registryIn, keyIn, biomeIn);
	}

	public static class SurfaceBuilders
	{
		public static final SurfaceBuilderConfig DREAM_GRASS_DIRT_GRAVEL_CONFIG = new SurfaceBuilderConfig(GNSBlocks.dream_grass_block.getDefaultState(), GNSBlocks.dream_dirt.getDefaultState(), Blocks.GRAVEL.getDefaultState());
		public static final SurfaceBuilderConfig NIGHTMARE_GRASS_DIRT_GRAVEL_CONFIG = new SurfaceBuilderConfig(GNSBlocks.nightmare_grass_block.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState());

		public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> DREAM_GRASS_SURFACE_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(DREAM_GRASS_DIRT_GRAVEL_CONFIG);
		public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> NIGHTMARE_GRASS_SURFACE_BUILDER = SurfaceBuilder.DEFAULT.func_242929_a(NIGHTMARE_GRASS_DIRT_GRAVEL_CONFIG);
	}

	public static class Keys
	{
		public static final RegistryKey<Biome> SLEEPY_HILLS = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, GoodNightSleep.locate("sleepy_hills"));
		public static final RegistryKey<Biome> DREAMY_FOREST = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, GoodNightSleep.locate("dreamy_forest"));
		public static final RegistryKey<Biome> GOOD_DREAM_PLAINS = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, GoodNightSleep.locate("good_dream_plains"));
		public static final RegistryKey<Biome> LOLLIPOP_LANDS = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, GoodNightSleep.locate("lollipop_land"));

		public static final RegistryKey<Biome> NIGHTMARE_HILLS = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, GoodNightSleep.locate("nightmare_hills"));
		public static final RegistryKey<Biome> SHAMEFUL_PLAINS = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, GoodNightSleep.locate("shameful_plains"));
		public static final RegistryKey<Biome> WASTED_FOREST = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, GoodNightSleep.locate("wasted_forest"));

		public static final RegistryKey<Biome> HOPEFUL_FIELDS = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, GoodNightSleep.locate("hopeful_fields"));

		public static final List<RegistryKey<Biome>> DREAM_BIOMES = ImmutableList.of(SLEEPY_HILLS, DREAMY_FOREST, GOOD_DREAM_PLAINS, HOPEFUL_FIELDS, LOLLIPOP_LANDS);
		public static final List<RegistryKey<Biome>> NIGHTMARE_BIOMES = ImmutableList.of(NIGHTMARE_HILLS, SHAMEFUL_PLAINS, WASTED_FOREST);

		public static RegistryKey<Biome> getKeyFromBiome(World world, Biome biomeIn)
		{
			Optional<RegistryKey<Biome>> biome = world.func_241828_r().getRegistry(Registry.BIOME_KEY).getOptionalKey(biomeIn);

			if (biome.isPresent())
				return biome.get();

			return null;
		}
	}
}
