package com.legacy.goodnightsleep.world.carver;

import com.google.common.collect.ImmutableSet;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.carver.CanyonWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class GNSCanyonWorldCarver extends CanyonWorldCarver
{
	public GNSCanyonWorldCarver(Codec<ProbabilityConfig> p_i49930_1_)
	{
		super(p_i49930_1_);
		this.carvableBlocks = ImmutableSet.of(GNSBlocks.delusion_stone, Blocks.STONE, GNSBlocks.dream_dirt, Blocks.DIRT, GNSBlocks.dream_grass_block, GNSBlocks.nightmare_grass_block, Blocks.GRAVEL);
	}
}
