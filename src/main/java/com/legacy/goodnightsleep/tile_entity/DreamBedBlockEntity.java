package com.legacy.goodnightsleep.tile_entity;

import com.legacy.goodnightsleep.registry.GNSBlockEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DreamBedBlockEntity extends BlockEntity
{
	public DreamBedBlockEntity(BlockPos pos, BlockState state)
	{
		super(GNSBlockEntityTypes.DREAM_BED, pos, state);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket()
	{
		return new ClientboundBlockEntityDataPacket(this.worldPosition, -999, this.getUpdateTag());
	}
}