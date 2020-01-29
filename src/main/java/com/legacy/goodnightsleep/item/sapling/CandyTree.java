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
	protected ConfiguredFeature<TreeFeatureConfig, ?> func_225546_b_(Random random, boolean p_225546_2_)
	{
		return new DreamTreeFeature(TreeFeatureConfig::func_227338_a_, true, 5, GNSBlocks.white_log.getDefaultState(), GNSBlocks.candy_leaves.getDefaultState()).withConfiguration(DefaultBiomeFeatures.field_226810_e_);
	}
}