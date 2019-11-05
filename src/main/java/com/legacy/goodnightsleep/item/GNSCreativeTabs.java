package com.legacy.goodnightsleep.item;

import com.legacy.goodnightsleep.blocks.GNSBlocks;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public abstract class GNSCreativeTabs extends ItemGroup
{

	public GNSCreativeTabs(String label)
	{
		super("goodnightsleep." + label);
	}
	
	public static final ItemGroup blocks = new ItemGroup("gns_blocks")
	{
		@Override
		public ItemStack createIcon() 
		{
			return new ItemStack(GNSBlocks.dream_grass_block);
		}
	};
	public static final ItemGroup tools = new ItemGroup("gns_tools")
	{
		@Override
		public ItemStack createIcon() 
		{
			return new ItemStack(GNSItems.candy_axe);
		}
	};
	public static final ItemGroup armor = new ItemGroup("gns_armor")
	{
		@Override
		public ItemStack createIcon() 
		{
			return new ItemStack(GNSItems.candy_chestplate);
		}
	};
	public static final ItemGroup items = new ItemGroup("gns_items")
	{
		@Override
		public ItemStack createIcon() 
		{
			return new ItemStack(GNSItems.positite_gem);
		}
	};
}