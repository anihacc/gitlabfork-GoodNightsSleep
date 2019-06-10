package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.CommonProxy;
import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.ItemsGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockGNSFlower extends BlockBush 
{

	public BlockGNSFlower()
	{
		super();

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
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
		Block ground = world.getBlock(x, y - 1, z);
		
		if (this == BlocksGNS.hope_mushroom || this == BlocksGNS.despair_mushroom)
		{
			return ground == Blocks.stone | ground == BlocksGNS.dream_grass || ground == BlocksGNS.nightmare_grass || ground == BlocksGNS.dream_dirt || ground == Blocks.dirt || ground == Blocks.grass;
		}

    	return ground == BlocksGNS.dream_grass || ground == BlocksGNS.nightmare_grass || ground == BlocksGNS.dream_dirt || ground == Blocks.dirt || ground == Blocks.grass;
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
			 return super.getItemDropped(meta, rand, fortune);
		 }
	 }

	/*@Override
	public int getRenderType()
	{
		return CommonProxy.gnsFlowerRenderID;
	}*/

	public int getRenderType()
    {
        return 1;
    }
	
}