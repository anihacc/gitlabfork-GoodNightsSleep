package com.legacy.goodnightsleep.client.render.item;

import com.legacy.goodnightsleep.tile_entity.TileEntityWretchedBed;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WretchedBedItemRenderer extends TileEntityItemStackRenderer
{

	private final TileEntityWretchedBed bed = new TileEntityWretchedBed();

	public WretchedBedItemRenderer()
	{
	}

	@Override
	public void renderByItem(ItemStack stack)
	{
		TileEntityRendererDispatcher.instance.renderAsItem(this.bed);
	}
}
