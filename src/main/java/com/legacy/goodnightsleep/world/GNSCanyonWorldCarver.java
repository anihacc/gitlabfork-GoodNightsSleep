package com.legacy.goodnightsleep.world;

import com.google.common.collect.ImmutableSet;
import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.carver.CanyonWorldCarver;

public class GNSCanyonWorldCarver extends CanyonWorldCarver
{
	public GNSCanyonWorldCarver()
	{
		super();
		this.terrainBlocks = ImmutableSet.of(BlocksGNS.delusion_stone, Blocks.STONE, BlocksGNS.dream_dirt, Blocks.DIRT, BlocksGNS.dream_grass_block, BlocksGNS.nightmare_grass_block, Blocks.GRAVEL);

	}
}
