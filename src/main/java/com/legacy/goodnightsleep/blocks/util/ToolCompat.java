package com.legacy.goodnightsleep.blocks.util;

import com.google.common.collect.Maps;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.mojang.datafixers.util.Pair;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ToolCompat
{
	public static void init()
	{
		axeStripping(GNSBlocks.dream_log, GNSBlocks.stripped_dream_log);
		axeStripping(GNSBlocks.white_log, GNSBlocks.stripped_white_log);
		axeStripping(GNSBlocks.dead_log, GNSBlocks.stripped_dead_log);
		axeStripping(GNSBlocks.blood_log, GNSBlocks.stripped_blood_log);
		axeStripping(GNSBlocks.dream_wood, GNSBlocks.stripped_dream_wood);
		axeStripping(GNSBlocks.white_wood, GNSBlocks.stripped_white_wood);
		axeStripping(GNSBlocks.dead_wood, GNSBlocks.stripped_dead_wood);
		axeStripping(GNSBlocks.blood_wood, GNSBlocks.stripped_blood_wood);

		hoeTilling(GNSBlocks.dream_grass_block, GNSBlocks.dream_farmland);
		hoeTilling(GNSBlocks.dream_dirt, GNSBlocks.dream_farmland);
		hoeTilling(GNSBlocks.nightmare_grass_block, Blocks.FARMLAND);
	}

	static void axeStripping(Block log, Block stripped)
	{
		// FIXME
		/*AxeItem.STRIPABLES = Maps.newHashMap(AxeItem.STRIPABLES);
		AxeItem.STRIPABLES.put(log, stripped);*/
	}

	static void hoeTilling(Block dirt, Block farmland)
	{
		HoeItem.TILLABLES = Maps.newHashMap(HoeItem.TILLABLES);
		HoeItem.TILLABLES.put(dirt, Pair.of(HoeItem::onlyIfAirAbove, HoeItem.changeIntoState(farmland.defaultBlockState())));
	}
}
