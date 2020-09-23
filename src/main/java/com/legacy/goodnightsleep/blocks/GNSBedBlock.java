package com.legacy.goodnightsleep.blocks;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.tile_entity.TileEntityLuxuriousBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityStrangeBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityWretchedBed;
import com.legacy.goodnightsleep.world.GNSDimensions;
import com.legacy.goodnightsleep.world.GNSTeleportationUtil;
import com.legacy.goodnightsleep.world.dream.GoodDreamDimension;
import com.legacy.goodnightsleep.world.nightmare.NightmareDimension;

import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

public class GNSBedBlock extends BedBlock
{
	public GNSBedBlock(Block.Properties properties)
	{
		super(DyeColor.WHITE, properties);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if (!worldIn.isRemote)
		{
			if (player.dimension == DimensionType.OVERWORLD)
			{
				player.setSpawnPoint(pos, false, false, DimensionType.OVERWORLD);
				player.setBedPosition(pos);
			}

			if (this == GNSBlocks.luxurious_bed)
			{
				this.travelToDream(player, true);
			}
			else if (this == GNSBlocks.wretched_bed)
			{
				this.travelToDream(player, false);
			}
			else if (this == GNSBlocks.strange_bed)
			{
				this.travelToDream(player, worldIn.rand.nextBoolean());
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.SUCCESS;
	}

	/**
	 * How the beds teleport the player.
	 * 
	 * TODO: backport actual good system from 1.16.3
	 */
	private void travelToDream(PlayerEntity player, boolean dream)
	{
		DimensionType transferDimension = player.dimension == GNSDimensions.dimensionType(dream) ? DimensionType.OVERWORLD : GNSDimensions.dimensionType(dream);

		if (transferDimension == GNSDimensions.dimensionType(true))
		{
			ServerWorld serverWorld = player.getServer().getWorld(GNSDimensions.dimensionType(true));

			if (((GoodDreamDimension) serverWorld.getDimension()).dreamPlayerList.size() <= 0)
			{
				if (!GNSConfig.disableTimePassing)
				{
					player.world.setDayTime(0L);
				}

				// serverWorld.setDayTime(0L);
			}
		}
		else if (transferDimension == GNSDimensions.dimensionType(false))
		{
			ServerWorld serverWorld = player.getServer().getWorld(GNSDimensions.dimensionType(false));

			if (((NightmareDimension) serverWorld.getDimension()).nightmarePlayerList.size() <= 0)
			{
				if (!GNSConfig.disableTimePassing)
				{
					player.world.setDayTime(0L);
				}

				// serverWorld.setDayTime(0L);
			}
		}

		try
		{
			BlockPos pos = player.dimension != DimensionType.OVERWORLD ? player.getBedLocation(DimensionType.OVERWORLD) : player.world.getSpawnPoint();
			GNSTeleportationUtil.changeDimension(transferDimension, player, pos);
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		if (this == GNSBlocks.luxurious_bed)
			return new TileEntityLuxuriousBed();
		else if (this == GNSBlocks.wretched_bed)
			return new TileEntityWretchedBed();
		else
			return new TileEntityStrangeBed();
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}
}