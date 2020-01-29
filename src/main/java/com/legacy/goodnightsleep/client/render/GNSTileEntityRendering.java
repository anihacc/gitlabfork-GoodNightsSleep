package com.legacy.goodnightsleep.client.render;

import com.legacy.goodnightsleep.client.render.tile.TileEntityLuxuriousBedRenderer;
import com.legacy.goodnightsleep.client.render.tile.TileEntityStrangeBedRenderer;
import com.legacy.goodnightsleep.client.render.tile.TileEntityWretchedBedRenderer;
import com.legacy.goodnightsleep.tile_entity.GNSTileEntityTypes;

import net.minecraftforge.fml.client.registry.ClientRegistry;

public class GNSTileEntityRendering
{
	public static void initialization()
	{
		ClientRegistry.bindTileEntityRenderer(GNSTileEntityTypes.LUXURIOUS_BED, TileEntityLuxuriousBedRenderer::new);
		ClientRegistry.bindTileEntityRenderer(GNSTileEntityTypes.WRETCHED_BED, TileEntityWretchedBedRenderer::new);
		ClientRegistry.bindTileEntityRenderer(GNSTileEntityTypes.STRANGE_BED, TileEntityStrangeBedRenderer::new);
	}
}
