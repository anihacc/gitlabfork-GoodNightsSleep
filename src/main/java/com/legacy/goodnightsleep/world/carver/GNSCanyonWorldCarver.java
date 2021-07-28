package com.legacy.goodnightsleep.world.carver;

import com.google.common.collect.ImmutableSet;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CanyonWorldCarver;

public class GNSCanyonWorldCarver extends CanyonWorldCarver
{
	public GNSCanyonWorldCarver(Codec<CanyonCarverConfiguration> config)
	{
		super(config);
		this.replaceableBlocks = ImmutableSet.of(GNSBlocks.delusion_stone, Blocks.STONE, GNSBlocks.dream_dirt, Blocks.DIRT, GNSBlocks.dream_grass_block, GNSBlocks.nightmare_grass_block, Blocks.GRAVEL);
	}
}
