package com.legacy.goodnightsleep.common.items.tools;

import com.legacy.goodnightsleep.common.registry.GNSCreativeTabs;

import net.minecraft.item.ItemSpade;

public class ItemGNSShovel extends ItemSpade
{

	public ItemGNSShovel(ToolMaterial material)
	{
		super(material);
		this.setCreativeTab(GNSCreativeTabs.tools);
	}

}