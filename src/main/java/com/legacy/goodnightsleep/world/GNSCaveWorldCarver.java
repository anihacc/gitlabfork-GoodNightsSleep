package com.legacy.goodnightsleep.world;

import java.util.function.Function;

import com.google.common.collect.ImmutableSet;
import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class GNSCaveWorldCarver extends CaveWorldCarver
{
	public GNSCaveWorldCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> p_i49929_1_, int p_i49929_2_)
	{
		super(p_i49929_1_, p_i49929_2_);
		this.carvableBlocks = ImmutableSet.of(BlocksGNS.delusion_stone, Blocks.STONE, BlocksGNS.dream_dirt, Blocks.DIRT, BlocksGNS.dream_grass_block, BlocksGNS.nightmare_grass_block, Blocks.GRAVEL);

	}
}
