package com.legacy.goodnightsleep.world.nightmare.features;

import java.util.Random;
import java.util.function.Function;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class NightmareFlowersFeature extends FlowersFeature
{

	public NightmareFlowersFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49889_1_)
	{
		super(p_i49889_1_);
	}

	public BlockState getRandomFlower(Random rand, BlockPos pos)
	{
		return GNSBlocks.dead_flower.getDefaultState();
	}
}