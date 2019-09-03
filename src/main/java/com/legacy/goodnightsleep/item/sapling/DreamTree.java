package com.legacy.goodnightsleep.item.sapling;

import java.util.Random;

import javax.annotation.Nullable;

import com.legacy.goodnightsleep.world.dream.features.DreamTreeFeature;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class DreamTree extends Tree
{

	@Nullable
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random)
	{
		return new DreamTreeFeature(NoFeatureConfig::deserialize, true);
	}
}