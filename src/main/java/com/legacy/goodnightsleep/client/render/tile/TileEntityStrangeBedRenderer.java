package com.legacy.goodnightsleep.client.render.tile;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.client.render.tile.util.GNSBedTileEntityRenderer;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileEntityStrangeBedRenderer extends GNSBedTileEntityRenderer
{
	public TileEntityStrangeBedRenderer(TileEntityRendererDispatcher dispatcher)
	{
		super(dispatcher);
	}

	@Override
	protected ResourceLocation getBedTexture()
	{
		return GoodNightSleep.locate("textures/entity/strange_bed.png");
	}
}