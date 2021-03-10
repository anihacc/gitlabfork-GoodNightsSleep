package com.legacy.goodnightsleep.item.sapling;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSFeatures;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class CandyTree extends Tree
{
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random random, boolean p_225546_2_)
	{
		return GNSFeatures.Configured.BASE_CANDY_TREE;
	}
}