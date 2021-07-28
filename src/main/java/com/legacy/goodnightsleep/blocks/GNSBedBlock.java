package com.legacy.goodnightsleep.blocks;

import com.legacy.goodnightsleep.capabillity.DreamPlayer;
import com.legacy.goodnightsleep.network.PacketHandler;
import com.legacy.goodnightsleep.network.SendEnteredTimePacket;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSDimensions;
import com.legacy.goodnightsleep.tile_entity.DreamBedBlockEntity;
import com.legacy.goodnightsleep.world.GNSTeleporter;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class GNSBedBlock extends BedBlock
{
	public GNSBedBlock(Block.Properties properties)
	{
		super(DyeColor.WHITE, properties);
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit)
	{
		if (!worldIn.isClientSide)
		{
			if (player.level.dimension() == Level.OVERWORLD)
			{
				if (player instanceof ServerPlayer)
					((ServerPlayer) player).setRespawnPosition(Level.OVERWORLD, pos, 0.0F, false, false);

				player.setSleepingPos(pos);
			}

			if (this == GNSBlocks.luxurious_bed)
				this.travelToDream(player, true);
			else if (this == GNSBlocks.wretched_bed)
				this.travelToDream(player, false);
			else if (this == GNSBlocks.strange_bed)
				this.travelToDream(player, worldIn.random.nextBoolean());

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.SUCCESS;
	}

	/**
	 * How the beds teleport the player.
	 */
	private void travelToDream(Player player, boolean dream)
	{
		if (!(player instanceof ServerPlayer))
			return;

		ServerPlayer serverPlayer = (ServerPlayer) player;
		ResourceKey<Level> transferDimension = player.level.dimension() == GNSDimensions.getDimensionKeys(dream) ? Level.OVERWORLD : GNSDimensions.getDimensionKeys(dream);

		if (transferDimension != Level.OVERWORLD && DreamPlayer.get(serverPlayer) != null)
		{
			DreamPlayer.get(serverPlayer).setEnteredDreamTime(serverPlayer.level.getGameTime());
			PacketHandler.sendTo(new SendEnteredTimePacket(serverPlayer.level.getGameTime()), serverPlayer);
		}

		try
		{
			ServerLevel serverWorld = player.getServer().getLevel(Level.OVERWORLD);
			BlockPos pos = player.level.dimension() != Level.OVERWORLD ? serverPlayer.getRespawnPosition() : serverWorld.getSharedSpawnPos();
			GNSTeleporter.changeDimension(transferDimension, player, pos);
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
	{
		/*if (this == GNSBlocks.luxurious_bed)
			return new TileEntityLuxuriousBed(pos, state);
		else if (this == GNSBlocks.wretched_bed)
			return new TileEntityWretchedBed(pos, state);
		else
			return new TileEntityStrangeBed(pos, state);*/
		
		return new DreamBedBlockEntity(pos, state);
	}
}