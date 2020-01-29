package com.legacy.goodnightsleep.world;

import java.util.function.Function;

import com.google.common.collect.ImmutableSet;
import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.carver.CanyonWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class GNSCanyonWorldCarver extends CanyonWorldCarver
{
	public GNSCanyonWorldCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> p_i49930_1_)
	{
		super(p_i49930_1_);
		this.carvableBlocks = ImmutableSet.of(GNSBlocks.delusion_stone, Blocks.STONE, GNSBlocks.dream_dirt, Blocks.DIRT, GNSBlocks.dream_grass_block, GNSBlocks.nightmare_grass_block, Blocks.GRAVEL);
	}
}
