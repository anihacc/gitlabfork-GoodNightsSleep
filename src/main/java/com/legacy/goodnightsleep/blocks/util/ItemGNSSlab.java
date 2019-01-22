package com.legacy.goodnightsleep.blocks.util;

import com.legacy.goodnightsleep.blocks.construction.BlockGNSSlab;
import com.legacy.goodnightsleep.blocks.construction.BlockGNSSlab.EnumBlockHalf;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ItemGNSSlab extends ItemBlock
{
    private final Block slab;

    public ItemGNSSlab(Block slab)
    {
        super(slab);
        this.slab = slab;
        this.setMaxDamage(0);
    }

    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	ItemStack heldItem = playerIn.getHeldItem(hand);

        if (!heldItem.isEmpty() && playerIn.canPlayerEdit(pos.offset(facing), facing, heldItem))
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);

            if (iblockstate.getBlock() == this.slab)
            {
            	BlockGNSSlab.EnumBlockHalf blockslab$enumblockhalf = (BlockGNSSlab.EnumBlockHalf)iblockstate.getValue(BlockGNSSlab.TYPE);

                if ((facing == EnumFacing.UP && blockslab$enumblockhalf == BlockGNSSlab.EnumBlockHalf.BOTTOM || facing == EnumFacing.DOWN && blockslab$enumblockhalf == BlockGNSSlab.EnumBlockHalf.TOP))
                {
                    IBlockState iblockstate1 = this.makeState();
                    AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, pos);

                    if (axisalignedbb != Block.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11))
                    {
                        SoundType soundtype = this.slab.getSoundType(this.slab.getDefaultState(), worldIn, pos, playerIn);
                        worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                        heldItem.shrink(1);
                    }

                    return EnumActionResult.SUCCESS;
                }
            }

            return this.tryPlace(playerIn, heldItem, worldIn, pos.offset(facing)) ? EnumActionResult.SUCCESS : super.onItemUse(playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack)
    {
        BlockPos blockpos = pos;
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() == this.slab)
        {
            boolean flag = iblockstate.getValue(BlockGNSSlab.TYPE) == BlockGNSSlab.EnumBlockHalf.TOP;

            if ((side == EnumFacing.UP && !flag || side == EnumFacing.DOWN && flag))
            {
                return true;
            }
        }

        pos = pos.offset(side);
        IBlockState iblockstate1 = worldIn.getBlockState(pos);
        return iblockstate1.getBlock() == this.slab ? true : super.canPlaceBlockOnSide(worldIn, blockpos, side, player, stack);
    }

    private boolean tryPlace(EntityPlayer player, ItemStack stack, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() == this.slab)
        {
            IBlockState iblockstate1 = this.makeState();
            AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, pos);

            if (axisalignedbb != Block.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11))
            {
                SoundType soundtype = this.slab.getSoundType(this.slab.getDefaultState(), worldIn, pos, player);
                worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                stack.shrink(1);
            }

            return true;
        }

        return false;
    }

    protected <T extends Comparable<T>> IBlockState makeState()
    {
        return this.slab.getDefaultState().withProperty(BlockGNSSlab.TYPE, EnumBlockHalf.FULL);
    }

}