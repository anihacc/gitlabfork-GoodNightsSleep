package com.legacy.goodnightsleep.blocks;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSBlocks;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

public class GNSOreBlock extends OreBlock
{
	public GNSOreBlock(Properties properties)
	{
		super(properties);
	}

	@Override
	protected int getExperience(Random rand)
	{
		if (this == GNSBlocks.candy_ore || this == GNSBlocks.necrum_ore)
		{
			return MathHelper.nextInt(rand, 0, 1);
		}
		else if (this == GNSBlocks.coal_ore)
		{
			return MathHelper.nextInt(rand, 0, 2);
		}
		else if (this == GNSBlocks.positite_ore || this == GNSBlocks.negatite_ore)
		{
			return MathHelper.nextInt(rand, 3, 7);
		}
		else if (this == GNSBlocks.lapis_ore)
		{
			return MathHelper.nextInt(rand, 2, 5);
		}
		else
		{
			return 0;
		}
	}
}
