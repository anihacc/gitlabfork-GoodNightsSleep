package com.legacy.goodnightsleep.tile_entity;

import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStrangeBed extends TileEntity
{
	public TileEntityStrangeBed()
	{
		super(GNSTileEntityTypes.STRANGE_BED);
	}

	public SUpdateTileEntityPacket getUpdatePacket()
	{
		return new SUpdateTileEntityPacket(this.worldPosition, 11, this.getUpdateTag());
	}
}