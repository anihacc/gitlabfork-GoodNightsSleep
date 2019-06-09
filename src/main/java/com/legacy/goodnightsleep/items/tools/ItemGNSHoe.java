package com.legacy.goodnightsleep.items.tools;

import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.item.ItemHoe;

public class ItemGNSHoe extends ItemHoe
{

	public ItemGNSHoe(ToolMaterial material) 
	{
		super(material);
		this.setCreativeTab(GNSCreativeTabs.tools);
	}

}