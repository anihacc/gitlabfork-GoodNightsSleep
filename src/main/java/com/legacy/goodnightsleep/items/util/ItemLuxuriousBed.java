package com.legacy.goodnightsleep.items.util;

import com.legacy.goodnightsleep.blocks.BlockGNSBed;
import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemLuxuriousBed extends Item
{
    public ItemLuxuriousBed()
    {
        this.setCreativeTab(GNSCreativeTabs.blocks);
    }

    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (p_77648_3_.isRemote)
        {
            return true;
        }
        else if (p_77648_7_ != 1)
        {
            return false;
        }
        else
        {
            ++p_77648_5_;
            BlockGNSBed blockbed = (BlockGNSBed)BlocksGNS.luxurious_bed;
            int i1 = MathHelper.floor_double((double)(p_77648_2_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            byte b0 = 0;
            byte b1 = 0;

            if (i1 == 0)
            {
                b1 = 1;
            }

            if (i1 == 1)
            {
                b0 = -1;
            }

            if (i1 == 2)
            {
                b1 = -1;
            }

            if (i1 == 3)
            {
                b0 = 1;
            }

            if (p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && p_77648_2_.canPlayerEdit(p_77648_4_ + b0, p_77648_5_, p_77648_6_ + b1, p_77648_7_, p_77648_1_))
            {
                if (p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_, p_77648_6_) && p_77648_3_.isAirBlock(p_77648_4_ + b0, p_77648_5_, p_77648_6_ + b1) && World.doesBlockHaveSolidTopSurface(p_77648_3_, p_77648_4_, p_77648_5_ - 1, p_77648_6_) && World.doesBlockHaveSolidTopSurface(p_77648_3_, p_77648_4_ + b0, p_77648_5_ - 1, p_77648_6_ + b1))
                {
                    p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, blockbed, i1, 3);

                    if (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) == blockbed)
                    {
                        p_77648_3_.setBlock(p_77648_4_ + b0, p_77648_5_, p_77648_6_ + b1, blockbed, i1 + 8, 3);
                    }

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
}