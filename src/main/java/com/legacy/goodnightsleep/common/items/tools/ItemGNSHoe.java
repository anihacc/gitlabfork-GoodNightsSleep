package com.legacy.goodnightsleep.common.items.tools;

import com.legacy.goodnightsleep.common.registry.GNSCreativeTabs;

import net.minecraft.item.ItemHoe;

public class ItemGNSHoe extends ItemHoe
{

	public ItemGNSHoe(ToolMaterial material) 
	{
		super(material);
		this.setCreativeTab(GNSCreativeTabs.tools);
	}

}