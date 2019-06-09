package com.legacy.goodnightsleep.items.tools;

import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.item.ItemSpade;

public class ItemGNSShovel extends ItemSpade
{

	public ItemGNSShovel(ToolMaterial material)
	{
		super(material);
		this.setCreativeTab(GNSCreativeTabs.tools);
	}

}