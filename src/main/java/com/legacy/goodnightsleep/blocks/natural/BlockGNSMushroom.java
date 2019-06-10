package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockGNSMushroom extends Block
{
    private static final String[] field_149793_a = new String[] {"skin_brown", "skin_red"};
    private final int field_149792_b;
    @SideOnly(Side.CLIENT)
    private IIcon[] field_149794_M;
    @SideOnly(Side.CLIENT)
    private IIcon field_149795_N;
    @SideOnly(Side.CLIENT)
    private IIcon field_149796_O;
    private static final String __OBFID = "CL_00000258";

    public BlockGNSMushroom()
    {
        super(Material.wood);
        this.field_149792_b = 0;
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_2_ == 10 && p_149691_1_ > 1 ? this.field_149795_N : (p_149691_2_ >= 1 && p_149691_2_ <= 9 && p_149691_1_ == 1 ? this.field_149794_M[this.field_149792_b] : (p_149691_2_ >= 1 && p_149691_2_ <= 3 && p_149691_1_ == 2 ? this.field_149794_M[this.field_149792_b] : (p_149691_2_ >= 7 && p_149691_2_ <= 9 && p_149691_1_ == 3 ? this.field_149794_M[this.field_149792_b] : ((p_149691_2_ == 1 || p_149691_2_ == 4 || p_149691_2_ == 7) && p_149691_1_ == 4 ? this.field_149794_M[this.field_149792_b] : ((p_149691_2_ == 3 || p_149691_2_ == 6 || p_149691_2_ == 9) && p_149691_1_ == 5 ? this.field_149794_M[this.field_149792_b] : (p_149691_2_ == 14 ? this.field_149794_M[this.field_149792_b] : (p_149691_2_ == 15 ? this.field_149795_N : this.field_149796_O)))))));
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_149745_1_)
    {
        int i = p_149745_1_.nextInt(10) - 7;

        if (i < 0)
        {
            i = 0;
        }

        return i;
    }

    @Override
    public Item getItemDropped(int state, Random rand, int fortune)
    {
    	if (this == BlocksGNS.hope_mushroom_cap)
    	{
    		return  Item.getItemFromBlock(BlocksGNS.hope_mushroom);
    	}
    	
    	else if (this == BlocksGNS.despair_mushroom_cap)
    	{
    		return  Item.getItemFromBlock(BlocksGNS.despair_mushroom);
    	}
    	
    	else
    	{
            return Item.getItemFromBlock(Blocks.red_mushroom);
    	}
    }

    @Override
    public Item getItem(World worldIn, int x, int y, int z)
    {
    	if (this == BlocksGNS.hope_mushroom_cap)
    	{
    		return  Item.getItemFromBlock(BlocksGNS.hope_mushroom);
    	}
    	
    	else if (this == BlocksGNS.despair_mushroom_cap)
    	{
    		return  Item.getItemFromBlock(BlocksGNS.despair_mushroom);
    	}
    	else
    	{
    		return Item.getItemFromBlock(Blocks.red_mushroom);
    	}
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_149794_M = new IIcon[field_149793_a.length];

        for (int i = 0; i < this.field_149794_M.length; ++i)
        {
            this.field_149794_M[i] = p_149651_1_.registerIcon(this.getTextureName());
        }

        this.field_149796_O = p_149651_1_.registerIcon(this.getTextureName() + "_" + "inside");
        this.field_149795_N = p_149651_1_.registerIcon(this.getTextureName() + "_" + "skin_stem");
    }
}