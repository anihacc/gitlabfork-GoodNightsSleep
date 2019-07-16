package com.legacy.goodnightsleep.tile_entity;

import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStrangeBed extends TileEntity
{
	public TileEntityStrangeBed()
	{
		super(GNSTileEntityTypes.STRANGE_BED);
	}

	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(this.pos, 11, this.getUpdateTag());
	}
}