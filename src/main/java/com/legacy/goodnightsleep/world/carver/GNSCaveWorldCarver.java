package com.legacy.goodnightsleep.world.carver;

import com.google.common.collect.ImmutableSet;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class GNSCaveWorldCarver extends CaveWorldCarver
{
	public GNSCaveWorldCarver(Codec<ProbabilityConfig> p_i49929_1_, int p_i49929_2_)
	{
		super(p_i49929_1_, p_i49929_2_);
		this.replaceableBlocks = ImmutableSet.of(GNSBlocks.delusion_stone, Blocks.STONE, GNSBlocks.dream_dirt, Blocks.DIRT, GNSBlocks.dream_grass_block, GNSBlocks.nightmare_grass_block, Blocks.GRAVEL);

	}
}
