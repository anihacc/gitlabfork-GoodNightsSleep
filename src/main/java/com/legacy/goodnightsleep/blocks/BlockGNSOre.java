package com.legacy.goodnightsleep.blocks;

import java.util.Random;

import com.legacy.goodnightsleep.item.ItemsGNS;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockGNSOre extends Block
{

	public BlockGNSOre(Block.Properties builder)
	{
		super(builder);
	}

	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune)
	{
		if (this == BlocksGNS.necrum_ore)
    	{
    		return ItemsGNS.necrum;
    	}
    	
    	if (this == BlocksGNS.candy_ore)
    	{
    		return ItemsGNS.candy;
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
    		return BlocksGNS.zitrite_ore.asItem();
    	}
    	
    	if (this == BlocksGNS.rainbow_ore)
    	{
    		int chances = worldIn.rand.nextInt(14);
    	      switch(chances)
    	      {
		      case 0:
		         return BlocksGNS.rainbow_ore.asItem();
		      case 1:
		         return Blocks.GOLD_ORE.asItem();
		      case 2:
		         return Blocks.IRON_ORE.asItem();
		      case 3:
		         return Items.REDSTONE;
		      case 4:
		         return Items.EMERALD;
		      case 5:
		         return Items.CYAN_DYE; //Random dyes
		      case 6:
		         return ItemsGNS.candy;
		      case 7:
		         return Items.DIAMOND;
		      default:
		         return BlocksGNS.rainbow_ore.asItem();
    	      }
    	}
    	
    	else if (this == BlocksGNS.coal_ore)
    	{
    		return Items.COAL;
    	}
    	
    	else if (this == BlocksGNS.lapis_ore)
    	{
    		return Items.LAPIS_LAZULI;
    	}

    	else
    	{
    		return Items.AIR;
    	}
	}

	@Override
	public int getHarvestLevel(IBlockState state)
    {
		if (this == BlocksGNS.necrum_ore)
    	{
    		return 0;
    	}
    	
    	if (this == BlocksGNS.candy_ore)
    	{
    		return 0;
    	}
    	
    	if (this == BlocksGNS.positite_ore)
    	{
    		return 2;
    	}
    	
    	if (this == BlocksGNS.negatite_ore)
    	{
    		return 2;
    	}
    	
    	if (this == BlocksGNS.zitrite_ore)
    	{
    		return 1;
    	}
    	
    	if (this == BlocksGNS.rainbow_ore)
    	{
    		return 1;
    	}
    	
    	if (this == BlocksGNS.coal_ore)
    	{
    		return 0;
    	}
    	
    	if (this == BlocksGNS.lapis_ore)
    	{
    		return 2;
    	}

    	else
    	{
    		return 0;
    	}
    }

	@Override
	public ToolType getHarvestTool(IBlockState state)
	{
		return ToolType.PICKAXE;
	}

	@Override
	public int quantityDropped(IBlockState state, Random random)
	{
		if (this == BlocksGNS.candy_ore || this == BlocksGNS.necrum_ore)
		{
			return 1 + random.nextInt(4);
		}
		else if (this == BlocksGNS.lapis_ore)
		{
			return 4 + random.nextInt(5);
		}
		else
		{
			return 1;
		}
    }
}