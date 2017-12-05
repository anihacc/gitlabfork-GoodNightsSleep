package com.legacy.goodnightsleep.common.items.tools;

import com.legacy.goodnightsleep.common.registry.GNSCreativeTabs;

import net.minecraft.item.ItemPickaxe;

public class ItemGNSPickaxe extends ItemPickaxe 
{

	public ItemGNSPickaxe(ToolMaterial material) 
	{
		super(material);
		this.setCreativeTab(GNSCreativeTabs.tools);
	}

}