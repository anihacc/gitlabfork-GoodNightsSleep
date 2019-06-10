package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGNSLeaves extends BlockLeaves
{

	public BlockGNSLeaves()
	{
		super();
		this.setCreativeTab(GNSCreativeTabs.blocks);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta)
	{
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	{
		return true;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		if (this == BlocksGNS.dream_leaves || this == BlocksGNS.diamond_leaves)
		{
			return Item.getItemFromBlock(BlocksGNS.dream_sapling);
		}
		else if (this == BlocksGNS.candy_leaves)
		{
			return Item.getItemFromBlock(BlocksGNS.candy_sapling);
		}
		else
		{
			return Item.getItemFromBlock(Blocks.sapling);
		}
	}

	@Override
	protected void func_150124_c(World worldIn, int x, int y, int z, int p_150124_5_, int p_150124_6_)
    {
		if (this == BlocksGNS.diamond_leaves && worldIn.rand.nextInt(100) == 0)
		{
			dropBlockAsItem(worldIn, x, y, z, new ItemStack(Items.diamond));
		}
		else if (this == BlocksGNS.dream_leaves && worldIn.rand.nextInt(200) == 0)
		{
			dropBlockAsItem(worldIn, x, y, z, new ItemStack(Items.emerald));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return super.blockIcon;
	}

	@Override
	public String[] func_150125_e()
	{
		return new String[] { this.getUnlocalizedName() };
	}
}