package com.legacy.goodnightsleep.common.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.legacy.goodnightsleep.common.blocks.BlocksGNS;
import com.legacy.goodnightsleep.common.items.ItemsGNS;

public class GNSCreativeTabs 
{

	public static GNSTab blocks = new GNSTab("blocks");
	
	public static GNSTab items = new GNSTab("items");
	
	public static GNSTab tools = new GNSTab("tools");

	public static GNSTab armor = new GNSTab("armor");
	
	public static void initialization()
	{
		blocks.setIcon(new ItemStack(BlocksGNS.dream_grass));
		items.setIcon(new ItemStack(ItemsGNS.positite_gem));
		tools.setIcon(new ItemStack(ItemsGNS.positite_sword));
		armor.setIcon(new ItemStack(ItemsGNS.rainbow_chestplate));
	}
	
	public static class GNSTab extends CreativeTabs
	{

		private ItemStack stack;

		public GNSTab(String unlocalizedName)
		{
			super(unlocalizedName);
		}

		public GNSTab(String unlocalizedName, ItemStack stack)
		{
			super(unlocalizedName);
			this.stack = stack;
		}

		public void setIcon(ItemStack stack)
		{
			this.stack = stack;
		}

	    @SideOnly(Side.CLIENT)
	    public String getTranslatedTabLabel()
	    {
	        return "tab." + this.getTabLabel();
	    }

		@Override
		public ItemStack getIconItemStack()
		{
			return stack;
		}

		@Override
		public ItemStack getTabIconItem()
		{
			return stack;
		}
		
	}

}