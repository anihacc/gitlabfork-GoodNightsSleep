package com.legacy.goodnightsleep.tile_entity;

import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLuxuriousBed extends TileEntity
{
	public TileEntityLuxuriousBed()
	{
		super(GNSTileEntityTypes.LUXURIOUS_BED);
	}

	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(this.pos, 11, this.getUpdateTag());
	}
}