package com.legacy.goodnightsleep.common.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.legacy.goodnightsleep.common.blocks.BlocksGNS;

public class GNSCreativeTabs 
{

	public static GNSTab blocks = new GNSTab("gns_blocks");
	
	public static void initialization()
	{
		blocks.setIcon(new ItemStack(BlocksGNS.dream_grass));
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