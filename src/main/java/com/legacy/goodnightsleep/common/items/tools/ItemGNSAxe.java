package com.legacy.goodnightsleep.common.items.tools;

import com.legacy.goodnightsleep.common.registry.GNSCreativeTabs;

import net.minecraft.item.ItemAxe;

public class ItemGNSAxe extends ItemAxe
{

	public ItemGNSAxe(ToolMaterial material) 
	{
		super(material);
		this.setCreativeTab(GNSCreativeTabs.tools);
	}

}