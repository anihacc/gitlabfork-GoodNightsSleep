package com.legacy.goodnightsleep.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class BaseGNSBiome extends Biome
{
	protected BaseGNSBiome(Builder biomeBuilder)
	{
		super(biomeBuilder);
	}

	public void addDreamFeature(Decoration decorationStage, ConfiguredFeature<?, ?> featureIn)
	{
		super.addFeature(decorationStage, featureIn);
	}

	/**
	 * We don't want other mods adding features. Sorry not sorry.
	 */
	@Override
	public void addFeature(GenerationStage.Decoration decorationStage, ConfiguredFeature<?, ?> featureIn)
	{
		return;
	}

	public <C extends IFeatureConfig> void addDreamStructure(ConfiguredFeature<C, ? extends Structure<C>> structureIn)
	{
		super.addStructure(structureIn);
	}

	/**
	 * We don't want other mods adding structures either. Sorry not sorry.
	 */
	public <C extends IFeatureConfig> void addStructure(ConfiguredFeature<C, ? extends Structure<C>> structureIn)
	{
		return;
	}

	/**
	 * We _really_ don't want other mods adding stuff. You get the idea.
	 */
	@Override
	public List<ConfiguredFeature<?, ?>> getFeatures(GenerationStage.Decoration decorationStage)
	{
		return new GNSImmutableList<ConfiguredFeature<?, ?>>(this.features.get(decorationStage));
	}

	@Override
	public List<Biome.SpawnListEntry> getSpawns(EntityClassification creatureType)
	{
		return new GNSImmutableList<Biome.SpawnListEntry>(super.getSpawns(creatureType));
	}

	public static class GNSImmutableList<E> extends ArrayList<E>
	{
		private static final long serialVersionUID = 2841387369607212751L;

		public GNSImmutableList(List<E> list)
		{
			super.addAll(list);
		}

		@Override
		public boolean add(E e)
		{
			return false;
		}

		@Override
		public void add(int index, E element)
		{
			return;
		}

		@Override
		public boolean addAll(Collection<? extends E> c)
		{
			return false;
		}

		@Override
		public boolean addAll(int index, Collection<? extends E> c)
		{
			return false;
		}
	}
}
