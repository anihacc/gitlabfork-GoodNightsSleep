package com.legacy.goodnightsleep.registry;

import java.lang.reflect.Constructor;
import java.util.OptionalLong;
import java.util.function.Function;
import java.util.function.Supplier;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.world.biome_provider.DreamBiomeProvider;
import com.legacy.goodnightsleep.world.biome_provider.NightmareBiomeProvider;
import com.mojang.serialization.Lifecycle;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.FuzzedBiomeMagnifier;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.NoiseSettings;
import net.minecraft.world.gen.settings.ScalingSettings;
import net.minecraft.world.gen.settings.SlideSettings;

public class GNSDimensions
{
	public static final RegistryKey<World> DREAM = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, GoodNightSleep.locate("good_dream"));
	public static final RegistryKey<World> NIGHTMARE = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, GoodNightSleep.locate("nightmare"));

	public static final RegistryKey<DimensionSettings> DREAM_NOISE_SETTINGS = RegistryKey.getOrCreateKey(Registry.NOISE_SETTINGS_KEY, GoodNightSleep.locate("dream"));
	public static final RegistryKey<DimensionSettings> NIGHTMARE_NOISE_SETTINGS = RegistryKey.getOrCreateKey(Registry.NOISE_SETTINGS_KEY, GoodNightSleep.locate("nightmare"));

	public static final RegistryKey<DimensionType> DREAM_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, GoodNightSleep.locate("dream"));
	public static final RegistryKey<DimensionType> NIGHTMARE_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, GoodNightSleep.locate("nightmare"));

	public static final RegistryKey<Dimension> DREAM_DIM = RegistryKey.getOrCreateKey(Registry.DIMENSION_KEY, GoodNightSleep.locate("good_dream"));
	public static final RegistryKey<Dimension> NIGHTMARE_DIM = RegistryKey.getOrCreateKey(Registry.DIMENSION_KEY, GoodNightSleep.locate("nightmare"));

	public static ResourceLocation getDimensionLocations(boolean dream)
	{
		return getDimensionKeys(dream).getLocation();
	}

	public static RegistryKey<World> getDimensionKeys(boolean dream)
	{
		return dream ? DREAM : NIGHTMARE;
	}

	public static void init(SimpleRegistry<Dimension> simpleRegistry, MutableRegistry<DimensionType> mutableRegistry, MutableRegistry<Biome> biomeRegistry, MutableRegistry<DimensionSettings> dimSettingsRegistry, long seed)
	{
		Function<RegistryKey<DimensionSettings>, DimensionSettings> dreamSettings = (noiseSettings) -> createNoiseSettings(new DimensionStructuresSettings(false), false, GNSBlocks.delusion_stone.getDefaultState(), Blocks.WATER.getDefaultState(), DREAM_NOISE_SETTINGS.getLocation());
		Function<DimensionSettings, ChunkGenerator> dreamGenerator = (s) -> createDreamChunkGenerator(biomeRegistry, dimSettingsRegistry, seed);
		Supplier<DimensionType> dreamDimensionType = () -> createDimSettings(OptionalLong.of(6000L), false, false);

		Function<RegistryKey<DimensionSettings>, DimensionSettings> nightmareSettings = (noiseSettings) -> createNoiseSettings(new DimensionStructuresSettings(false), false, Blocks.STONE.getDefaultState(), Blocks.LAVA.getDefaultState(), NIGHTMARE_NOISE_SETTINGS.getLocation());
		Function<DimensionSettings, ChunkGenerator> nightmareGenerator = (s) -> createNightmareChunkGenerator(biomeRegistry, dimSettingsRegistry, seed);
		Supplier<DimensionType> nightmareDimensionType = () -> createDimSettings(OptionalLong.of(18000L), true, true);

		Dimension dreamDim = new Dimension(dreamDimensionType, dreamGenerator.apply(dreamSettings.apply(DREAM_NOISE_SETTINGS)));
		Dimension nightmareDim = new Dimension(nightmareDimensionType, nightmareGenerator.apply(nightmareSettings.apply(NIGHTMARE_NOISE_SETTINGS)));

		simpleRegistry.register(DREAM_DIM, dreamDim, Lifecycle.stable());
		simpleRegistry.register(NIGHTMARE_DIM, nightmareDim, Lifecycle.stable());
	}

	public static void initNoiseSettings()
	{
		registerNoiseSettings(DREAM_NOISE_SETTINGS, createNoiseSettings(new DimensionStructuresSettings(false), false, GNSBlocks.delusion_stone.getDefaultState(), Blocks.WATER.getDefaultState(), DREAM_NOISE_SETTINGS.getLocation()));
		registerNoiseSettings(NIGHTMARE_NOISE_SETTINGS, createNoiseSettings(new DimensionStructuresSettings(false), false, Blocks.STONE.getDefaultState(), Blocks.LAVA.getDefaultState(), NIGHTMARE_NOISE_SETTINGS.getLocation()));
	}

	public static DimensionSettings createNoiseSettings(DimensionStructuresSettings structureSettingsIn, boolean flag1, BlockState fillerBlockIn, BlockState fluidBlockIn, ResourceLocation settingsLocationIn)
	{
		try
		{
			Constructor<DimensionSettings> constructor = DimensionSettings.class.getDeclaredConstructor(DimensionStructuresSettings.class, NoiseSettings.class, BlockState.class, BlockState.class, int.class, int.class, int.class, boolean.class);
			constructor.setAccessible(true);
			return constructor.newInstance(structureSettingsIn, new NoiseSettings(256, new ScalingSettings(0.9999999814507745D, 0.9999999814507745D, 80.0D, 160.0D), new SlideSettings(-10, 3, 0), new SlideSettings(-30, 0, 0), 1, 2, 1.0D, -0.46875D, true, true, false, flag1), fillerBlockIn, fluidBlockIn, -10, 0, 63, false);
		}
		catch (Exception e)
		{
			GoodNightSleep.LOGGER.error("Failed to create dimension settings. This issue should be reported!");
			e.printStackTrace();
		}

		return null;
	}

	private static DimensionSettings registerNoiseSettings(RegistryKey<DimensionSettings> settingsKeyIn, DimensionSettings dimSettingsIn)
	{
		WorldGenRegistries.register(WorldGenRegistries.NOISE_SETTINGS, settingsKeyIn.getLocation(), dimSettingsIn);
		return dimSettingsIn;
	}

	private static ChunkGenerator createDreamChunkGenerator(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimSettingsRegistry, long seed)
	{
		return new NoiseChunkGenerator(DreamBiomeProvider.DreamPreset.dreamPreset.func_242619_a(biomeRegistry, seed), seed, () ->
		{
			return dimSettingsRegistry.getOrThrow(GNSDimensions.DREAM_NOISE_SETTINGS);
		});
	}

	private static ChunkGenerator createNightmareChunkGenerator(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimSettingsRegistry, long seed)
	{
		return new NoiseChunkGenerator(NightmareBiomeProvider.NightmarePreset.nightmarePreset.func_242619_a(biomeRegistry, seed), seed, () ->
		{
			return dimSettingsRegistry.getOrThrow(GNSDimensions.NIGHTMARE_NOISE_SETTINGS);
		});
	}

	private static DimensionType createDimSettings(OptionalLong time, boolean ultrawarm, boolean piglinSafe)
	{
		return new DimensionType(time, true, false, ultrawarm, true, 1, false, piglinSafe, true, false, false, 256, FuzzedBiomeMagnifier.INSTANCE, BlockTags.INFINIBURN_OVERWORLD.getName(), DimensionType.OVERWORLD_ID, 0.0F)
		{
		};
	}
}
