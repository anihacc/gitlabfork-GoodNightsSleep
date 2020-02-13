package com.legacy.goodnightsleep.item.sapling;

import java.util.Random;

import com.legacy.goodnightsleep.world.dream.features.DreamTreeFeature;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class DreamTree extends Tree
{
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean p_225546_2_)
	{
		return new DreamTreeFeature(TreeFeatureConfig::func_227338_a_, true).withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG);
	}
}