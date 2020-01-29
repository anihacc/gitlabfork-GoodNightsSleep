package com.legacy.goodnightsleep.world.dream.features;

import com.legacy.goodnightsleep.blocks.GNSBlocks;

import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;

public class GNSFeatures
{
	public static final BlockClusterFeatureConfig HOPE_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.hope_mushroom.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
	public static final BlockClusterFeatureConfig DESPAIR_MUSHROOM_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(GNSBlocks.despair_mushroom.getDefaultState()), new SimpleBlockPlacer())).func_227315_a_(64).func_227317_b_().func_227322_d_();
}
