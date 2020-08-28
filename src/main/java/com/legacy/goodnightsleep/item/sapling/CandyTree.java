package com.legacy.goodnightsleep.item.sapling;

import java.util.Random;

import com.legacy.goodnightsleep.world.general_features.GNSFeatures;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class CandyTree extends Tree
{
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random random, boolean p_225546_2_)
	{
		return (ConfiguredFeature<BaseTreeFeatureConfig, ?>) GNSFeatures.CANDY_TREE;
	}
}