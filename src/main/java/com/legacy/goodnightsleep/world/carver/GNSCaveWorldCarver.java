package com.legacy.goodnightsleep.world.carver;

import com.google.common.collect.ImmutableSet;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;

public class GNSCaveWorldCarver extends CaveWorldCarver
{
	public GNSCaveWorldCarver(Codec<CaveCarverConfiguration> config)
	{
		super(config);
		this.replaceableBlocks = ImmutableSet.of(GNSBlocks.delusion_stone, Blocks.STONE, GNSBlocks.dream_dirt, Blocks.DIRT, GNSBlocks.dream_grass_block, GNSBlocks.nightmare_grass_block, Blocks.GRAVEL);

	}
}
