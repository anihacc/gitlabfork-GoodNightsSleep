package com.legacy.goodnightsleep.client;

import com.legacy.goodnightsleep.client.render.TileEntityLuxuriousBedRenderer;
import com.legacy.goodnightsleep.client.render.TileEntityStrangeBedRenderer;
import com.legacy.goodnightsleep.client.render.TileEntityWretchedBedRenderer;
import com.legacy.goodnightsleep.tile_entity.TileEntityLuxuriousBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityStrangeBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityWretchedBed;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;

@OnlyIn(Dist.CLIENT)
public class ClientSpecificStuff
{
	public static void fleep()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLuxuriousBed.class, new TileEntityLuxuriousBedRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWretchedBed.class, new TileEntityWretchedBedRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStrangeBed.class, new TileEntityStrangeBedRenderer());
	}
}
