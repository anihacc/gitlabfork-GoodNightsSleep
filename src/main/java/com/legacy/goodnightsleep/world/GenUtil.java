package com.legacy.goodnightsleep.world;

import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage.Carving;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class GenUtil
{
	public static boolean doesBiomeMatch(Biome biomeIn, RegistryKey<Biome> wantedBiomeIn)
	{
		return biomeIn.toString().matches(wantedBiomeIn.func_240901_a_().toString());
	}

	public static void addFeature(Biome biome, Decoration stage, ConfiguredFeature<?, ?> feature)
	{
		if (getGenSettings(biome).field_242484_f instanceof ImmutableList)
			getGenSettings(biome).field_242484_f = Lists.newArrayList(getGenSettings(biome).field_242484_f);

		while (getGenSettings(biome).field_242484_f.size() <= stage.ordinal())
			getGenSettings(biome).field_242484_f.add(Lists.newArrayList());

		getGenSettings(biome).field_242484_f.get(stage.ordinal()).add(() -> feature);
	}

	public static void addCarver(Biome biome, Carving carvingType, ConfiguredCarver<?> configuredCarver)
	{
		if (getGenSettings(biome).field_242483_e instanceof ImmutableMap || (getGenSettings(biome).field_242483_e.containsKey(carvingType) && getGenSettings(biome).field_242483_e.get(carvingType) instanceof ImmutableList))
			getGenSettings(biome).field_242483_e = getGenSettings(biome).field_242483_e.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, (e) -> Lists.newArrayList(e.getValue())));

		if (!getGenSettings(biome).field_242483_e.containsKey(carvingType))
			getGenSettings(biome).field_242483_e.put(carvingType, Lists.newArrayList());

		getGenSettings(biome).field_242483_e.get(carvingType).add(() -> configuredCarver);
	}

	public static BiomeGenerationSettings getGenSettings(Biome biome)
	{
		return biome.func_242440_e();
	}
}
