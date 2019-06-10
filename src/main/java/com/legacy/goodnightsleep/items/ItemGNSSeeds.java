package com.legacy.goodnightsleep.items;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemGNSSeeds extends ItemSeeds
{

	private Block crops;

	public ItemGNSSeeds(Block crops)
	{
		super(crops, BlocksGNS.dream_farmland);
		this.crops = crops;
	}

	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		if (p_77648_7_ != 1)
		{
			return false;
		}
		else if (p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_ + 1, p_77648_6_, p_77648_7_, p_77648_1_))
		{
			if (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_).canSustainPlant(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, ForgeDirection.UP, this) && p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_ + 1, p_77648_6_))
			{
				p_77648_3_.setBlock(p_77648_4_, p_77648_5_ + 1, p_77648_6_, this.crops);
				--p_77648_1_.stackSize;
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}
