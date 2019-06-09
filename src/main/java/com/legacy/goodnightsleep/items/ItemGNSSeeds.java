package com.legacy.goodnightsleep.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemGNSSeeds extends ItemSeeds
{
	private Block crops;
	
    public ItemGNSSeeds(Block crops)
    {
        super(crops, Blocks.farmland);
        this.crops = crops;
    }
    
    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock() instanceof BlockFarmland && worldIn.isAirBlock(pos.up()))
        {
            worldIn.setBlockState(pos.up(), this.crops.getDefaultState());

            --itemstack.stackSize;
            return true;
        }
        else
        {
            return false;
        }
    }
}
