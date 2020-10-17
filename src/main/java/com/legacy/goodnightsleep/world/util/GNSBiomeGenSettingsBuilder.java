package com.legacy.goodnightsleep.world.util;

import java.util.function.Supplier;

import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;

/**
 * Used to prevent other mods from messing with our biomes.
 */
public class GNSBiomeGenSettingsBuilder extends BiomeGenerationSettingsBuilder
{
	public GNSBiomeGenSettingsBuilder(BiomeGenerationSettings orig)
	{
		super(orig);
	}

	// Our methods that reference vanilla
	public BiomeGenerationSettings.Builder withSkySurfaceBuilder(ConfiguredSurfaceBuilder<?> configuredSurfaceBuilder)
	{
		return super.withSurfaceBuilder(configuredSurfaceBuilder);
	}

	public BiomeGenerationSettings.Builder withSkySurfaceBuilder(Supplier<ConfiguredSurfaceBuilder<?>> configuredSurfaceBuilderSupplier)
	{
		return super.withSurfaceBuilder(configuredSurfaceBuilderSupplier);
	}

	public BiomeGenerationSettings.Builder withSkyFeature(GenerationStage.Decoration decorationStage, ConfiguredFeature<?, ?> feature)
	{
		return super.withFeature(decorationStage, feature);
	}

	public BiomeGenerationSettings.Builder withSkyFeature(int stage, Supplier<ConfiguredFeature<?, ?>> features)
	{
		return super.withFeature(stage, features);
	}

	public <C extends ICarverConfig> BiomeGenerationSettings.Builder withSkyCarver(GenerationStage.Carving carvingStage, ConfiguredCarver<C> carver)
	{
		return super.withCarver(carvingStage, carver);
	}

	public BiomeGenerationSettings.Builder withSkyStructure(StructureFeature<?, ?> structure)
	{
		return super.withStructure(structure);
	}

	// Vanilla methods forced to do nothing
	@Override
	public BiomeGenerationSettings.Builder withSurfaceBuilder(ConfiguredSurfaceBuilder<?> configuredSurfaceBuilder)
	{
		return this;
	}

	@Override
	public BiomeGenerationSettings.Builder withSurfaceBuilder(Supplier<ConfiguredSurfaceBuilder<?>> configuredSurfaceBuilderSupplier)
	{
		return this;
	}

	@Override
	public BiomeGenerationSettings.Builder withFeature(GenerationStage.Decoration decorationStage, ConfiguredFeature<?, ?> feature)
	{
		return this;
	}

	@Override
	public BiomeGenerationSettings.Builder withFeature(int stage, Supplier<ConfiguredFeature<?, ?>> features)
	{
		return this;
	}

	@Override
	public <C extends ICarverConfig> BiomeGenerationSettings.Builder withCarver(GenerationStage.Carving carvingStage, ConfiguredCarver<C> carver)
	{
		return this;
	}

	@Override
	public BiomeGenerationSettings.Builder withStructure(StructureFeature<?, ?> structure)
	{
		return this;
	}
}
