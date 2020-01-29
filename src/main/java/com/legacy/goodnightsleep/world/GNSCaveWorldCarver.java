package com.legacy.goodnightsleep.world;

import java.util.function.Function;

import com.google.common.collect.ImmutableSet;
import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class GNSCaveWorldCarver extends CaveWorldCarver
{
	public GNSCaveWorldCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> p_i49929_1_, int p_i49929_2_)
	{
		super(p_i49929_1_, p_i49929_2_);
		this.carvableBlocks = ImmutableSet.of(GNSBlocks.delusion_stone, Blocks.STONE, GNSBlocks.dream_dirt, Blocks.DIRT, GNSBlocks.dream_grass_block, GNSBlocks.nightmare_grass_block, Blocks.GRAVEL);

	}
}
