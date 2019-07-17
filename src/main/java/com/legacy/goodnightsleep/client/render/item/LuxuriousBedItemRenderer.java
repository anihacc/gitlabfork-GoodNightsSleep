package com.legacy.goodnightsleep.client.render.item;

import com.legacy.goodnightsleep.tile_entity.TileEntityLuxuriousBed;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LuxuriousBedItemRenderer extends TileEntityItemStackRenderer
{

	private final TileEntityLuxuriousBed bed = new TileEntityLuxuriousBed();

	public LuxuriousBedItemRenderer()
	{
	}

	@Override
	public void renderByItem(ItemStack stack)
	{
		TileEntityRendererDispatcher.instance.renderAsItem(this.bed);
	}
}
