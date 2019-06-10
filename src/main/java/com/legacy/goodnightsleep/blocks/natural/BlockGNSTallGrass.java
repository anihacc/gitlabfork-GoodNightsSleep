package com.legacy.goodnightsleep.blocks.natural;

import java.util.ArrayList;
import java.util.Random;

import com.legacy.goodnightsleep.CommonProxy;
import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.ItemsGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGNSTallGrass extends BlockBush
{

	public BlockGNSTallGrass()
	{
		super();
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
		this.setStepSound(Block.soundTypeGrass);
		this.setCreativeTab(GNSCreativeTabs.blocks);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		Block soil = world.getBlock(x, y - 1, z);
		return (soil != null && this.canPlaceBlockAt(world, x, y, z));
	}

	@Override
	public IIcon getIcon(int par1, int par2)
	{
		return this.blockIcon;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
		Block ground = world.getBlock(x, y - 1, z);
		
		if (this == BlocksGNS.tall_dream_grass)
		{
			return ground == BlocksGNS.dream_grass || ground == BlocksGNS.dream_dirt;
		}
		if (this == BlocksGNS.tall_nightmare_grass)
		{
			return ground == BlocksGNS.nightmare_grass || ground == Blocks.dirt;
		}
		else
		{
			return ground == Blocks.grass;
		}
    }
	
	@Override
	protected boolean canPlaceBlockOn(Block ground)
	{
		if (this == BlocksGNS.tall_dream_grass)
		{
			return ground == BlocksGNS.dream_grass || ground == BlocksGNS.dream_dirt;
		}
		if (this == BlocksGNS.tall_nightmare_grass)
		{
			return ground == BlocksGNS.nightmare_grass || ground == Blocks.dirt;
		}
		else
		{
			return ground == Blocks.grass;
		}
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, int x, int y, int z)
	{
		return true;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, int x, int y, int z, Entity entityIn)
	{
		if (this == BlocksGNS.tall_nightmare_grass && !(entityIn instanceof IMob))
		{
			entityIn.attackEntityFrom(new DamageSource("nightmare_grass"), 1.0F);
		}
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		if (this == BlocksGNS.lolipop_bush)
		{
			return ItemsGNS.lolipop;
		}
		else
		{
			return null;
		}
	}

	@Override
	public int getRenderType()
	{
		return CommonProxy.gnsTallgrassRenderID;
	}

	@Override
	public int getDamageValue(World world, int par2, int par3, int par4)
	{
		return world.getBlockMetadata(par2, par3, par4);
	}

	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (world.rand.nextInt(8) != 0) return ret;
		ItemStack seed = new ItemStack(ItemsGNS.rainbow_seeds);
        if (seed != null) ret.add(seed);
        return ret;
    }
}