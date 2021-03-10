package com.legacy.goodnightsleep.tile_entity;

import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLuxuriousBed extends TileEntity
{
	public TileEntityLuxuriousBed()
	{
		super(GNSTileEntityTypes.LUXURIOUS_BED);
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket()
	{
		return new SUpdateTileEntityPacket(this.worldPosition, -999, this.getUpdateTag());
	}
}