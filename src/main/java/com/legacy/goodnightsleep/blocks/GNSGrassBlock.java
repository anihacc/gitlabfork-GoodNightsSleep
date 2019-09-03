package com.legacy.goodnightsleep.blocks;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.material.Material;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;

public class GNSGrassBlock extends GrassBlock
{

	public GNSGrassBlock()
	{
		super(Properties.from(Blocks.GRASS_BLOCK));
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

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
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
	
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
	{
		PlantType plantType = plantable.getPlantType(world, pos.offset(facing));
		switch (plantType)
		{
			case Plains:
				return true;
			case Beach:
				boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER || world.getBlockState(pos.west()).getMaterial() == Material.WATER || world.getBlockState(pos.north()).getMaterial() == Material.WATER || world.getBlockState(pos.south()).getMaterial() == Material.WATER);
				return hasWater;
			default:
				break;
		}
		return super.canSustainPlant(state, world, pos, facing, plantable);
	}
}