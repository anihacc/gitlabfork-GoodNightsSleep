package com.legacy.goodnightsleep.tile_entity;

import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWretchedBed extends TileEntity
{
	public TileEntityWretchedBed()
	{
		super(GNSTileEntityTypes.WRETCHED_BED);
	}

	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(this.pos, 11, this.getUpdateTag());
	}
}