package com.legacy.goodnightsleep.world;

import java.util.function.Function;

import com.google.common.collect.ImmutableSet;
import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.carver.CanyonWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class GNSCanyonWorldCarver extends CanyonWorldCarver
{
	public GNSCanyonWorldCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> p_i49930_1_)
	{
		super(p_i49930_1_);
		this.carvableBlocks = ImmutableSet.of(BlocksGNS.delusion_stone, Blocks.STONE, BlocksGNS.dream_dirt, Blocks.DIRT, BlocksGNS.dream_grass_block, BlocksGNS.nightmare_grass_block, Blocks.GRAVEL);
	}
}
