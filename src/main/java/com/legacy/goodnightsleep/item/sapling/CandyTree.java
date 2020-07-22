package com.legacy.goodnightsleep.item.sapling;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.world.dream.features.DreamTreeFeature;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class CandyTree extends Tree
{
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean p_225546_2_)
	{
		return new DreamTreeFeature(TreeFeatureConfig::deserializeFoliage, true, 5, GNSBlocks.white_log.getDefaultState(), GNSBlocks.candy_leaves.getDefaultState()).withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG);
	}
}