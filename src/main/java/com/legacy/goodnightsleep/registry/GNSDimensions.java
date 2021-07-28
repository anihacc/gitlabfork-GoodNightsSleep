package com.legacy.goodnightsleep.registry;

import java.util.OptionalLong;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.world.biome_provider.DreamBiomeProvider;
import com.legacy.goodnightsleep.world.biome_provider.NightmareBiomeProvider;
import com.mojang.serialization.Lifecycle;

import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FuzzyOffsetConstantColumnBiomeZoomer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSamplingSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.NoiseSlideSettings;
import net.minecraft.world.level.levelgen.StructureSettings;

public class GNSDimensions
{
	public static final ResourceLocation DREAM_ID = GoodNightSleep.locate("good_dream");
	public static final ResourceLocation NIGHTMARE_ID = GoodNightSleep.locate("nightmare");

	public static final ResourceKey<Level> DREAM_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY, DREAM_ID);
	public static final ResourceKey<Level> NIGHTMARE_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY, NIGHTMARE_ID);

	public static final ResourceKey<NoiseGeneratorSettings> DREAM_NOISE_KEY = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, GoodNightSleep.locate("dream"));
	public static final ResourceKey<NoiseGeneratorSettings> NIGHTMARE_NOISE_KEY = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, NIGHTMARE_ID);

	public static final ResourceKey<DimensionType> DREAM_TYPE_KEY = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, GoodNightSleep.locate("dream"));
	public static final ResourceKey<DimensionType> NIGHTMARE_TYPE_KEY = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, NIGHTMARE_ID);

	public static final ResourceKey<LevelStem> DREAM_DIM = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, DREAM_ID);
	public static final ResourceKey<LevelStem> NIGHTMARE_DIM = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, NIGHTMARE_ID);

	public static final NoiseGeneratorSettings DREAM_NOISE = createNoiseSettings(new StructureSettings(false), false, GNSBlocks.delusion_stone.defaultBlockState(), Blocks.WATER.defaultBlockState());
	public static final NoiseGeneratorSettings NIGHTMARE_NOISE = createNoiseSettings(new StructureSettings(false), false, Blocks.STONE.defaultBlockState(), Blocks.LAVA.defaultBlockState());

	public static final DimensionType DREAM_TYPE = createDimSettings(OptionalLong.of(6000L), false, false, DREAM_ID);
	public static final DimensionType NIGHTMARE_TYPE = createDimSettings(OptionalLong.of(18000L), true, true, NIGHTMARE_ID);

	public static ResourceLocation getDimensionLocations(boolean dream)
	{
		return getDimensionKeys(dream).location();
	}

	public static ResourceKey<Level> getDimensionKeys(boolean dream)
	{
		return dream ? DREAM_KEY : NIGHTMARE_KEY;
	}

	public static void init(MappedRegistry<LevelStem> simpleRegistry, WritableRegistry<DimensionType> mutableRegistry, Registry<Biome> biomeRegistry, WritableRegistry<NoiseGeneratorSettings> dimSettingsRegistry, long seed)
	{
		DimensionType dreamDimensionType = createDimSettings(OptionalLong.of(6000L), false, false, DREAM_ID);
		mutableRegistry.register(DREAM_TYPE_KEY, dreamDimensionType, Lifecycle.stable());

		dimSettingsRegistry.register(DREAM_NOISE_KEY, DREAM_NOISE, Lifecycle.stable());

		ChunkGenerator dreamGenerator = createDreamChunkGenerator(biomeRegistry, dimSettingsRegistry, seed);

		DimensionType nightmareDimensionType = createDimSettings(OptionalLong.of(18000L), true, true, NIGHTMARE_ID);
		mutableRegistry.register(NIGHTMARE_TYPE_KEY, nightmareDimensionType, Lifecycle.stable());

		dimSettingsRegistry.register(NIGHTMARE_NOISE_KEY, NIGHTMARE_NOISE, Lifecycle.stable());

		ChunkGenerator nightmareGenerator = createNightmareChunkGenerator(biomeRegistry, dimSettingsRegistry, seed);

		simpleRegistry.register(DREAM_DIM, new LevelStem(() -> DREAM_TYPE, dreamGenerator), Lifecycle.stable());
		simpleRegistry.register(NIGHTMARE_DIM, new LevelStem(() -> NIGHTMARE_TYPE, nightmareGenerator), Lifecycle.stable());
	}

	public static NoiseGeneratorSettings createNoiseSettings(StructureSettings structureSettingsIn, boolean flag1, BlockState fillerBlockIn, BlockState fluidBlockIn)
	{
		return new NoiseGeneratorSettings(structureSettingsIn, NoiseSettings.create(0, 256, new NoiseSamplingSettings(0.9999999814507745D, 0.9999999814507745D, 80.0D, 160.0D), new NoiseSlideSettings(-10, 3, 0), new NoiseSlideSettings(15, 3, 0), 1, 2, 1.0D, -0.46875D, true, true, false, flag1), fillerBlockIn, fluidBlockIn, -10, 0, 63, 0, false, false, false, false, false, false);
	}

	private static ChunkGenerator createDreamChunkGenerator(Registry<Biome> biomeRegistry, Registry<NoiseGeneratorSettings> dimSettingsRegistry, long seed)
	{
		return new NoiseBasedChunkGenerator(DreamBiomeProvider.DreamPreset.dreamPreset.biomeSource(biomeRegistry, seed), seed, () -> dimSettingsRegistry.getOrThrow(GNSDimensions.DREAM_NOISE_KEY));
	}

	private static ChunkGenerator createNightmareChunkGenerator(Registry<Biome> biomeRegistry, Registry<NoiseGeneratorSettings> dimSettingsRegistry, long seed)
	{
		return new NoiseBasedChunkGenerator(NightmareBiomeProvider.NightmarePreset.nightmarePreset.biomeSource(biomeRegistry, seed), seed, () -> dimSettingsRegistry.getOrThrow(GNSDimensions.NIGHTMARE_NOISE_KEY));
	}

	private static DimensionType createDimSettings(OptionalLong time, boolean ultrawarm, boolean piglinSafe, ResourceLocation effectsId)
	{
		return DimensionType.create(time, true, false, ultrawarm, true, 1, false, piglinSafe, true, false, false, 0, 256, 256, FuzzyOffsetConstantColumnBiomeZoomer.INSTANCE, BlockTags.INFINIBURN_OVERWORLD.getName(), effectsId, 0.0F);
	}
}