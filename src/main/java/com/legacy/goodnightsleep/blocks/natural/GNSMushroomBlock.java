package com.legacy.goodnightsleep.blocks.natural;

import java.util.function.Supplier;

import com.legacy.goodnightsleep.registry.GNSBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class GNSMushroomBlock extends MushroomBlock
{
	public GNSMushroomBlock(Properties properties, Supplier<ConfiguredFeature<?, ?>> feature)
	{
		super(properties, feature);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
	{
		BlockPos blockpos = pos.below();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		Block block = blockstate.getBlock();

		if (block != Blocks.MYCELIUM && block != Blocks.PODZOL && block != GNSBlocks.dream_grass_block && block != GNSBlocks.nightmare_grass_block)
		{
			return worldIn.getRawBrightness(pos, 0) < 13 && blockstate.canSustainPlant(worldIn, blockpos, Direction.UP, this);
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient)
	{
		return true;
	}

	/*public boolean growMushroom(ServerLevel world, BlockPos pos, BlockState state, Random rand)
	{
		world.removeBlock(pos, false);
		ConfiguredFeature<?, ?> configuredfeature;
	
		if (this == GNSBlocks.despair_mushroom)
		{
			configuredfeature = GNSFeatures.Configured.BASE_HUGE_DESPAIR_MUSHROOM;
		}
		else
		{
			if (this != GNSBlocks.hope_mushroom)
			{
				world.setBlock(pos, state, 3);
				return false;
			}
	
			configuredfeature = GNSFeatures.Configured.BASE_HUGE_HOPE_MUSHROOM;
		}
	
		if (configuredfeature.place(world, world.getChunkSource().getGenerator(), rand, pos))
		{
			return true;
		}
		else
		{
			world.setBlock(pos, state, 3);
			return false;
		}
	}*/
}
