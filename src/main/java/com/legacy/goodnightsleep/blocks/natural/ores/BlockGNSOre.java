package com.legacy.goodnightsleep.blocks.natural.ores;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.ItemsGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockGNSOre extends Block
{
	public BlockGNSOre() 
	{
		super(Material.rock);

		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setCreativeTab(GNSCreativeTabs.blocks);
	}

	@Override
    public int quantityDropped(Random random)
    {
		if (this == BlocksGNS.candy_ore || this == BlocksGNS.necrum_ore)
		{
    	 return 1 + random.nextInt(4);
		}
    	else
    	{
    		return 1;
    	}
    }

	@Override
    public Item getItemDropped(int meta, Random rand, int fortune)
    {
    	if (this == BlocksGNS.necrum_ore)
    	{
    		return ItemsGNS.necrum;
    	}
    	
    	if (this == BlocksGNS.candy_ore)
    	{
    		return ItemsGNS.candy;
    	}
    	
    	if (this == BlocksGNS.positite_ore)
    	{
    		return ItemsGNS.positite_gem;
    	}
    	
    	if (this == BlocksGNS.negatite_ore)
    	{
    		return ItemsGNS.negatite_gem;
    	}
    	
    	if (this == BlocksGNS.positite_ore)
    	{
    		return ItemsGNS.positite_gem;
    	}
    	
    	if (this == BlocksGNS.zitrite_ore)
    	{
    		return Item.getItemFromBlock(this);
    	}
    	
    	if (this == BlocksGNS.rainbow_ore)
    	{
    		int chances = rand.nextInt(14);
    	      switch(chances)
    	      {
		      case 0:
		         return Item.getItemFromBlock(this);
		      case 1:
		         return Item.getItemFromBlock(Blocks.gold_ore);
		      case 2:
		         return Item.getItemFromBlock(Blocks.iron_ore);
		      case 3:
		         return Items.redstone;
		      case 4:
		         return Items.emerald;
		      case 5:
		         return Items.dye; //Random dyes
		      case 6:
		         return ItemsGNS.candy;
		      case 7:
		         return Items.diamond;
		      default:
		         return Item.getItemFromBlock(this);
    	      }
    	}
    	
    	else
    	{
    		return Item.getItemFromBlock(this);
    	}
    }

}