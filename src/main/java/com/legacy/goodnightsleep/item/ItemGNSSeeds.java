package com.legacy.goodnightsleep.item;

import com.legacy.goodnightsleep.blocks.BlockGNSFarmland;
import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class ItemGNSSeeds extends ItemSeeds
{

	private final IBlockState field_195978_a;

	public ItemGNSSeeds(Block crop, Item.Properties builder)
	{
		super(crop, builder);
		this.field_195978_a = crop.getDefaultState();
	}

	@Override
	public EnumActionResult onItemUse(ItemUseContext p_195939_1_)
	{
		IWorld iworld = p_195939_1_.getWorld();
		BlockPos blockpos = p_195939_1_.getPos().up();
		if (p_195939_1_.getFace() == EnumFacing.UP && iworld.isAirBlock(blockpos) && (this.field_195978_a.isValidPosition(iworld, blockpos) && (iworld.getBlockState(p_195939_1_.getPos()).getBlock() == BlocksGNS.dream_farmland || iworld.getBlockState(p_195939_1_.getPos()).canSustainPlant(iworld, p_195939_1_.getPos(), EnumFacing.UP, this))))
		{
			iworld.setBlockState(blockpos, this.field_195978_a, 11);
			ItemStack itemstack = p_195939_1_.getItem();
			EntityPlayer entityplayer = p_195939_1_.getPlayer();
			if (entityplayer instanceof EntityPlayerMP)
			{
				CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) entityplayer, blockpos, itemstack);
			}
			itemstack.shrink(1);
			return EnumActionResult.SUCCESS;
		}
		else
		{
			return EnumActionResult.FAIL;
		}
	}
}
