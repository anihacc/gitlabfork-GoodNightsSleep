package com.legacy.goodnightsleep.blocks.natural.ores;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.ItemsGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockGNSOre extends Block
{
	public BlockGNSOre() 
	{
		super(Material.ROCK);

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
	 
	/*@Override
 	public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune) && !(this == BlocksSkies.everbright_charoite_ore || this == BlocksSkies.everdawn_charoite_ore))
        {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        }
        else
        {
            return this.quantityDropped(random);
        }
    }*/

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
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
		         return Item.getItemFromBlock(Blocks.GOLD_ORE);
		      case 2:
		         return Item.getItemFromBlock(Blocks.IRON_ORE);
		      case 3:
		         return Items.REDSTONE;
		      case 4:
		         return Items.EMERALD;
		      case 5:
		         return Items.DYE; //Random dyes
		      case 6:
		         return ItemsGNS.candy;
		      case 7:
		         return Items.DIAMOND;
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