package com.legacy.goodnightsleep.blocks;

import com.legacy.goodnightsleep.registry.GNSBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockState;

public class GNSOreBlock extends OreBlock
{
	public GNSOreBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader reader, BlockPos pos, int fortune, int silktouch)
	{
		if (silktouch != 0)
			return 0;

		if (this == GNSBlocks.candy_ore || this == GNSBlocks.necrum_ore)
			return Mth.nextInt(RANDOM, 0, 1);
		else if (this == GNSBlocks.coal_ore)
			return Mth.nextInt(RANDOM, 0, 2);
		else if (this == GNSBlocks.positite_ore || this == GNSBlocks.negatite_ore)
			return Mth.nextInt(RANDOM, 3, 7);
		else if (this == GNSBlocks.lapis_ore)
			return Mth.nextInt(RANDOM, 2, 5);
		
		return 0;
	}
}
