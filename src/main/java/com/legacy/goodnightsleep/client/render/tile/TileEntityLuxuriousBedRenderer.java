package com.legacy.goodnightsleep.client.render.tile;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.render.tile.util.GNSBedTileEntityRenderer;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileEntityLuxuriousBedRenderer extends GNSBedTileEntityRenderer
{
	public TileEntityLuxuriousBedRenderer(TileEntityRendererDispatcher dispatcher)
	{
		super(dispatcher);
	}

	@Override
	protected ResourceLocation getBedTexture()
	{
		return GoodNightSleep.locate("textures/entity/luxurious_bed.png");
	}
}