package com.legacy.goodnightsleep.world.dream.features;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.AbstractFlowersFeature;

public class GNSFlowersFeature extends AbstractFlowersFeature
{

	public IBlockState getRandomFlower(Random rand, BlockPos pos)
	{
		return rand.nextInt(2) == 0 ? BlocksGNS.cyan_flower.getDefaultState() : rand.nextInt(3) == 0 ? BlocksGNS.lolipop_bush.getDefaultState() : BlocksGNS.orange_flower.getDefaultState();
	}
}