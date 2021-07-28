package com.legacy.goodnightsleep.registry;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.data.GNSBiomeProv;
import com.legacy.goodnightsleep.world.biome_provider.DreamBiomeProvider;
import com.legacy.goodnightsleep.world.biome_provider.NightmareBiomeProvider;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
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

		BuiltinRegistries.register(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, GNSDimensions.DREAM_NOISE_KEY.location(), GNSDimensions.DREAM_NOISE);
		BuiltinRegistries.register(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, GNSDimensions.NIGHTMARE_NOISE_KEY.location(), GNSDimensions.NIGHTMARE_NOISE);

		register(registry, "sleepy_hills", SLEEPY_HILLS);
		register(registry, "dreamy_forest", DREAMY_FOREST);
		register(registry, "good_dream_plains", GOOD_DREAM_PLAINS);
		register(registry, "lollipop_lands", LOLLIPOP_LANDS);

		register(registry, "nightmare_hills", NIGHTMARE_HILLS);
		register(registry, "shameful_plains", SHAMEFUL_PLAINS);
		register(registry, "wasted_forest", WASTED_FOREST);

		register(registry, "hopeful_fields", HOPEFUL_FIELDS);

		Registry.register(Registry.BIOME_SOURCE, "dream_multi_noise", DreamBiomeProvider.dreamProviderCodec);
		Registry.register(Registry.BIOME_SOURCE, "nightmare_multi_noise", NightmareBiomeProvider.nightmareProviderCodec);

		/*BiomeDictionary.addTypes(GOOD_DREAM_PLAINS, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.PLAINS);
		BiomeDictionary.addTypes(NIGHTMARE_HILLS, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DEAD);*/
	}

	private static void register(IForgeRegistry<Biome> registryIn, String keyIn, Biome biomeIn)
	{
		GNSRegistry.register(registryIn, keyIn, biomeIn);
	}

	public static class SurfaceBuilders
	{
		public static final SurfaceBuilderBaseConfiguration DREAM_GRASS_DIRT_GRAVEL_CONFIG = new SurfaceBuilderBaseConfiguration(GNSBlocks.dream_grass_block.defaultBlockState(), GNSBlocks.dream_dirt.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
		public static final SurfaceBuilderBaseConfiguration NIGHTMARE_GRASS_DIRT_GRAVEL_CONFIG = new SurfaceBuilderBaseConfiguration(GNSBlocks.nightmare_grass_block.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());

		public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> DREAM_GRASS_SURFACE_BUILDER = SurfaceBuilder.DEFAULT.configured(DREAM_GRASS_DIRT_GRAVEL_CONFIG);
		public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> NIGHTMARE_GRASS_SURFACE_BUILDER = SurfaceBuilder.DEFAULT.configured(NIGHTMARE_GRASS_DIRT_GRAVEL_CONFIG);
	}

	public static class Keys
	{
		public static final ResourceKey<Biome> SLEEPY_HILLS = ResourceKey.create(Registry.BIOME_REGISTRY, GoodNightSleep.locate("sleepy_hills"));
		public static final ResourceKey<Biome> DREAMY_FOREST = ResourceKey.create(Registry.BIOME_REGISTRY, GoodNightSleep.locate("dreamy_forest"));
		public static final ResourceKey<Biome> GOOD_DREAM_PLAINS = ResourceKey.create(Registry.BIOME_REGISTRY, GoodNightSleep.locate("good_dream_plains"));
		public static final ResourceKey<Biome> LOLLIPOP_LANDS = ResourceKey.create(Registry.BIOME_REGISTRY, GoodNightSleep.locate("lollipop_lands"));

		public static final ResourceKey<Biome> NIGHTMARE_HILLS = ResourceKey.create(Registry.BIOME_REGISTRY, GoodNightSleep.locate("nightmare_hills"));
		public static final ResourceKey<Biome> SHAMEFUL_PLAINS = ResourceKey.create(Registry.BIOME_REGISTRY, GoodNightSleep.locate("shameful_plains"));
		public static final ResourceKey<Biome> WASTED_FOREST = ResourceKey.create(Registry.BIOME_REGISTRY, GoodNightSleep.locate("wasted_forest"));

		public static final ResourceKey<Biome> HOPEFUL_FIELDS = ResourceKey.create(Registry.BIOME_REGISTRY, GoodNightSleep.locate("hopeful_fields"));

		public static final List<ResourceKey<Biome>> DREAM_BIOMES = ImmutableList.of(SLEEPY_HILLS, DREAMY_FOREST, GOOD_DREAM_PLAINS, HOPEFUL_FIELDS, LOLLIPOP_LANDS);
		public static final List<ResourceKey<Biome>> NIGHTMARE_BIOMES = ImmutableList.of(NIGHTMARE_HILLS, SHAMEFUL_PLAINS, WASTED_FOREST);

		public static ResourceKey<Biome> getKeyFromBiome(Level world, Biome biomeIn)
		{
			Optional<ResourceKey<Biome>> biome = world.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getResourceKey(biomeIn);

			if (biome.isPresent())
				return biome.get();

			return null;
		}
	}
}
