package com.legacy.goodnightsleep.registry;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.ItemsGNS;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class GNSCreativeTabs 
{

	public static final CreativeTabs blocks = new CreativeTabs("gns_blocks")
	{
		@Override
		public ItemStack createIcon() 
		{
			return new ItemStack(BlocksGNS.dream_grass);
		}
	};
	public static final CreativeTabs tools = new CreativeTabs("gns_tools")
	{
		@Override
		public ItemStack createIcon() 
		{
			return new ItemStack(ItemsGNS.candy_axe);
		}
	};
	public static final CreativeTabs armor = new CreativeTabs("gns_armor")
	{
		@Override
		public ItemStack createIcon() 
		{
			return new ItemStack(ItemsGNS.candy_chestplate);
		}
	};
	public static final CreativeTabs items = new CreativeTabs("gns_items")
	{
		@Override
		public ItemStack createIcon() 
		{
			return new ItemStack(ItemsGNS.positite_gem);
		}
	};

}