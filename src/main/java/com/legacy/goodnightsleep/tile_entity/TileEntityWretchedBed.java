package com.legacy.goodnightsleep.tile_entity;

import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWretchedBed extends TileEntity
{
	public TileEntityWretchedBed()
	{
		super(GNSTileEntityTypes.WRETCHED_BED);
	}

	public SUpdateTileEntityPacket getUpdatePacket()
	{
		return new SUpdateTileEntityPacket(this.pos, 11, this.getUpdateTag());
	}
}