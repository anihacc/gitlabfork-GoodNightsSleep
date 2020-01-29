package com.legacy.goodnightsleep.tile_entity;

import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLuxuriousBed extends TileEntity
{
	public TileEntityLuxuriousBed()
	{
		super(GNSTileEntityTypes.LUXURIOUS_BED);
	}

	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet)
	{
		super.onDataPacket(net, packet);
		read(packet.getNbtCompound());
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket()
	{
		return new SUpdateTileEntityPacket(this.pos, -999, this.getUpdateTag());
	}
}