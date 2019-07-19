package com.legacy.goodnightsleep.client.render.item;

import com.legacy.goodnightsleep.tile_entity.TileEntityStrangeBed;

import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StrangeBedItemRenderer extends ItemStackTileEntityRenderer
{

	private final TileEntityStrangeBed bed = new TileEntityStrangeBed();

	public StrangeBedItemRenderer()
	{
	}

	@Override
	public void renderByItem(ItemStack stack)
	{
		TileEntityRendererDispatcher.instance.renderAsItem(this.bed);
	}
}
