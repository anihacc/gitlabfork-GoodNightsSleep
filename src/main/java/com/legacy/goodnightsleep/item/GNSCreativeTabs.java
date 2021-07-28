package com.legacy.goodnightsleep.item;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSItems;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public abstract class GNSCreativeTabs extends CreativeModeTab
{

	public GNSCreativeTabs(String label)
	{
		super(GoodNightSleep.MODID + "." + label);
	}
	
	public static final CreativeModeTab blocks = new CreativeModeTab("gns_blocks")
	{
		@Override
		public ItemStack makeIcon() 
		{
			return new ItemStack(GNSBlocks.dream_grass_block);
		}
	};
	public static final CreativeModeTab tools = new CreativeModeTab("gns_tools")
	{
		@Override
		public ItemStack makeIcon() 
		{
			return new ItemStack(GNSItems.candy_axe);
		}
	};
	public static final CreativeModeTab armor = new CreativeModeTab("gns_armor")
	{
		@Override
		public ItemStack makeIcon() 
		{
			return new ItemStack(GNSItems.candy_chestplate);
		}
	};
	public static final CreativeModeTab items = new CreativeModeTab("gns_items")
	{
		@Override
		public ItemStack makeIcon() 
		{
			return new ItemStack(GNSItems.positite);
		}
	};
}