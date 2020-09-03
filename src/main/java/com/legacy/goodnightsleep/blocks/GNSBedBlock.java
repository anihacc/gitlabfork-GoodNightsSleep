package com.legacy.goodnightsleep.blocks;

import com.legacy.goodnightsleep.capabillity.DreamPlayer;
import com.legacy.goodnightsleep.network.PacketHandler;
import com.legacy.goodnightsleep.network.SendEnteredTimePacket;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.legacy.goodnightsleep.tile_entity.TileEntityLuxuriousBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityStrangeBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityWretchedBed;
import com.legacy.goodnightsleep.world.GNSTeleporter;

import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
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
			if (player.world.getDimensionKey() == World.OVERWORLD)
			{
				if (player instanceof ServerPlayerEntity)
					((ServerPlayerEntity) player).func_242111_a(World.OVERWORLD, pos, 0.0F, false, false);

				player.setBedPosition(pos);
			}

			if (this == GNSBlocks.luxurious_bed)
				this.travelToDream(player, true);
			else if (this == GNSBlocks.wretched_bed)
				this.travelToDream(player, false);
			else if (this == GNSBlocks.strange_bed)
				this.travelToDream(player, worldIn.rand.nextBoolean());

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.SUCCESS;
	}

	/**
	 * How the beds teleport the player.
	 */
	private void travelToDream(PlayerEntity player, boolean dream)
	{
		if (!(player instanceof ServerPlayerEntity))
			return;

		ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
		RegistryKey<World> transferDimension = player.world.getDimensionKey() == GNSDimensions.getDimensionKeys(dream) ? World.OVERWORLD : GNSDimensions.getDimensionKeys(dream);

		if (transferDimension != World.OVERWORLD && DreamPlayer.get(serverPlayer) != null)
		{
			DreamPlayer.get(serverPlayer).setEnteredDreamTime(serverPlayer.world.getGameTime());
			PacketHandler.sendTo(new SendEnteredTimePacket(serverPlayer.world.getGameTime()), serverPlayer);
		}

		try
		{
			ServerWorld serverWorld = player.getServer().getWorld(World.OVERWORLD);
			BlockPos pos = player.world.getDimensionKey() != World.OVERWORLD ? serverPlayer.func_241140_K_() : serverWorld.getSpawnPoint();
			GNSTeleporter.changeDimension(transferDimension, player, pos);
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