package com.legacy.goodnightsleep.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockGNSGrass extends Block
{

	public BlockGNSGrass()
	{
		super(Properties.create(Material.GRASS).needsRandomTick().hardnessAndResistance(0.6F).sound(SoundType.PLANT));
	}

	private static boolean func_196383_a(IWorldReader p_196383_0_, BlockPos p_196383_1_)
	{
		BlockPos blockpos = p_196383_1_.up();
		return p_196383_0_.getLight(blockpos) >= 4 || p_196383_0_.getBlockState(blockpos).getOpacity(p_196383_0_, blockpos) < p_196383_0_.getMaxLightLevel();
	}

	private static boolean func_196384_b(IWorldReader p_196384_0_, BlockPos p_196384_1_)
	{
		BlockPos blockpos = p_196384_1_.up();
		return p_196384_0_.getLight(blockpos) >= 4 && p_196384_0_.getBlockState(blockpos).getOpacity(p_196384_0_, blockpos) < p_196384_0_.getMaxLightLevel() && !p_196384_0_.getFluidState(blockpos).isTagged(FluidTags.WATER);
	}

	public void tick(IBlockState state, World worldIn, BlockPos pos, Random random)
	{
		if (!worldIn.isRemote)
		{
			if (!worldIn.isAreaLoaded(pos, 3))
				return;
			if (!func_196383_a(worldIn, pos) && this == BlocksGNS.dream_grass_block)
			{
				worldIn.setBlockState(pos, BlocksGNS.dream_dirt.getDefaultState());
			}
			else if (!func_196383_a(worldIn, pos) && this == BlocksGNS.nightmare_grass_block)
			{
				worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
			}
			else
			{
				if (worldIn.getLight(pos.up()) >= 9)
				{
					for (int i = 0; i < 4; ++i)
					{
						BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
						if (!worldIn.isBlockPresent(blockpos))
						{
							return;
						}
						if (worldIn.getBlockState(blockpos).getBlock() == BlocksGNS.dream_dirt && func_196384_b(worldIn, blockpos) && this == BlocksGNS.dream_grass_block)
						{
							worldIn.setBlockState(blockpos, this.getDefaultState());
						}
						else if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && func_196384_b(worldIn, blockpos) && this == BlocksGNS.nightmare_grass_block)
						{
							worldIn.setBlockState(blockpos, this.getDefaultState());
						}
					}
				}
			}
		}
	}
	
	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune)
    {
		if (this == BlocksGNS.dream_grass_block)
		{
			return BlocksGNS.dream_dirt.asItem();
		}
		
		else
		{
			return Blocks.DIRT.asItem();
		}
    }
}