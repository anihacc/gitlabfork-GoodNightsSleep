package com.legacy.goodnightsleep.client.render;

import com.legacy.goodnightsleep.tile_entity.TileEntityLuxuriousBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityStrangeBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityWretchedBed;

import net.minecraftforge.fml.client.registry.ClientRegistry;

public class GNSTileEntityRendering
{
	public static void initialization()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLuxuriousBed.class, new TileEntityLuxuriousBedRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWretchedBed.class, new TileEntityWretchedBedRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStrangeBed.class, new TileEntityStrangeBedRenderer());
	}
}
