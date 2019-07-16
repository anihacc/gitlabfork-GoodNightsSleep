package com.legacy.goodnightsleep.world.nightmare.features;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.AbstractFlowersFeature;

public class NightmareFlowersFeature extends AbstractFlowersFeature
{

	public IBlockState getRandomFlower(Random rand, BlockPos pos)
	{
		return BlocksGNS.dead_flower.getDefaultState();
	}
}