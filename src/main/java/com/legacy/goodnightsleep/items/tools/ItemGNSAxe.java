package com.legacy.goodnightsleep.items.tools;

import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.item.ItemAxe;

public class ItemGNSAxe extends ItemAxe
{

	public ItemGNSAxe(ToolMaterial material) 
	{
		super(material);
		this.setCreativeTab(GNSCreativeTabs.tools);
	}

}