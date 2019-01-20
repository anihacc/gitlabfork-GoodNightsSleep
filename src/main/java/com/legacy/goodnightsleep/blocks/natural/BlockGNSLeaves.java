package com.legacy.goodnightsleep.blocks.natural;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
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
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

	@Override
    public boolean isOpaqueCube(IBlockState state)
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
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
    }

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
		/*if (this == BlocksSkies.turquoise_leaves)
		{
			return Item.getItemFromBlock(BlocksSkies.bluebright_sapling);
		}
		
		if (this == BlocksSkies.lunar_leaves)
		{
			return Item.getItemFromBlock(BlocksSkies.lunar_sapling);
		}
		
		if (this == BlocksSkies.cherry_leaves)
		{
			return Item.getItemFromBlock(BlocksSkies.cherry_sapling);
		}
		
		if (this == BlocksSkies.blue_leaves)
		{
			return Item.getItemFromBlock(BlocksSkies.old_blue_sapling);
		}
		
		if (this == BlocksSkies.purple_leaves)
		{
			return Item.getItemFromBlock(BlocksSkies.old_purple_sapling);
		}
		
		else
		{
			return Item.getItemFromBlock(Blocks.SAPLING);
		}*/
		
		return Item.getItemFromBlock(Blocks.SAPLING);
		
    }

	@Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
    {
		if (this == BlocksGNS.diamond_leaves && RANDOM.nextInt(5) == 0)
		{
			spawnAsEntity(worldIn, pos, new ItemStack(Items.DIAMOND));
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