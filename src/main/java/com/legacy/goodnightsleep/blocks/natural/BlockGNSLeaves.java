package com.legacy.goodnightsleep.blocks.natural;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGNSLeaves extends BlockLeaves
{

	public BlockGNSLeaves()
	{
		super();
		this.setCreativeTab(GNSCreativeTabs.blocks);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
		return EnumWorldBlockLayer.CUTOUT_MIPPED;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
    	return true;
    }
	
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }

	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta & 2) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 4) > 0));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
        {
            i |= 2;
        }

        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
        {
            i |= 4;
        }

        return i;
    }

	@Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
    }

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		if (this == BlocksGNS.dream_leaves ||this == BlocksGNS.diamond_leaves)
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
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
		if (this == BlocksGNS.diamond_leaves && worldIn.rand.nextInt(100) == 0)
		{
			spawnAsEntity(worldIn, pos, new ItemStack(Items.diamond));
		}
		else if (this == BlocksGNS.dream_leaves && worldIn.rand.nextInt(200) == 0)
		{
			spawnAsEntity(worldIn, pos, new ItemStack(Items.emerald));
		}
    }

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		List<ItemStack> list = new ArrayList<ItemStack>();

		list.add(new ItemStack(this));

		return list;
	}

	@Override
	public EnumType getWoodType(int meta)
	{
		return null;
	}

}