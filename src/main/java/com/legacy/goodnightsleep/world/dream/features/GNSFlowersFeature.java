package com.legacy.goodnightsleep.world.dream.features;

import java.util.Random;
import java.util.function.Function;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class GNSFlowersFeature extends FlowersFeature
{

	public GNSFlowersFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49889_1_)
	{
		super(p_i49889_1_);
	}

	public BlockState getRandomFlower(Random rand, BlockPos pos)
	{
		return rand.nextInt(2) == 0 ? BlocksGNS.cyan_flower.getDefaultState() : rand.nextInt(3) == 0 ? BlocksGNS.lolipop_bush.getDefaultState() : BlocksGNS.orange_flower.getDefaultState();
	}
}