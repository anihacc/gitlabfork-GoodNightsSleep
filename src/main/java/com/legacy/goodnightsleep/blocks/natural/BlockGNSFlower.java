package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.ItemsGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGNSFlower extends BlockBush 
{

	public BlockGNSFlower()
	{
		super();

		this.setStepSound(Block.soundTypeGrass);
		this.setCreativeTab(GNSCreativeTabs.blocks);
	}

	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
    	return this.canPlaceBlockOn(worldIn.getBlockState(pos.down()).getBlock());
    }
	
	@Override
	protected boolean canPlaceBlockOn(Block ground)
    {
		if (this == BlocksGNS.hope_mushroom || this == BlocksGNS.despair_mushroom)
		{
			return ground == Blocks.stone | ground == BlocksGNS.dream_grass || ground == BlocksGNS.nightmare_grass || ground == BlocksGNS.dream_dirt || ground == Blocks.dirt || ground == Blocks.grass;
		}

    	return ground == BlocksGNS.dream_grass || ground == BlocksGNS.nightmare_grass || ground == BlocksGNS.dream_dirt || ground == Blocks.dirt || ground == Blocks.grass;
    }
	
	@Override
	 public Item getItemDropped(IBlockState state, Random rand, int fortune)
	 {
		 if (this == BlocksGNS.lolipop_bush)
		 {
			 return ItemsGNS.lolipop;
		 }
		 else
		 {
			 return super.getItemDropped(state, rand, fortune);
		 }
	 }

	@Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }

}