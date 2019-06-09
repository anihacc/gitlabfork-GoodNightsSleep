package com.legacy.goodnightsleep.items.tools;

import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.item.ItemPickaxe;

public class ItemGNSPickaxe extends ItemPickaxe 
{

	public ItemGNSPickaxe(ToolMaterial material) 
	{
		super(material);
		this.setCreativeTab(GNSCreativeTabs.tools);
	}

}