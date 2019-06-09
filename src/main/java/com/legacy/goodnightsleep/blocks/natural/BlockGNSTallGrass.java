package com.legacy.goodnightsleep.blocks.natural;

import java.util.List;
import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.ItemsGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGNSTallGrass extends BlockBush
{

	 protected static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

	 public BlockGNSTallGrass()
	 {
		 super();

		 this.setSoundType(SoundType.PLANT);
		 this.setCreativeTab(GNSCreativeTabs.blocks);
	 }

	 @Override
	 protected boolean canSustainBush(IBlockState state)
	 { 
		 if (this == BlocksGNS.tall_dream_grass)
			{
			 return state.getBlock() == BlocksGNS.dream_grass || state.getBlock() == BlocksGNS.dream_dirt;
			}
		 
		 if (this == BlocksGNS.tall_nightmare_grass)
			{
			 return state.getBlock() == BlocksGNS.nightmare_grass || state.getBlock() == Blocks.DIRT;
			}
		 
		 else
			{
			 return state.getBlock() == Blocks.GRASS;
			}
	 }
	 
	 @Override
	 public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
	 {
		 return true;
	 }
	 
	 @Override
	 public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	 {
		 if (this == BlocksGNS.tall_nightmare_grass && !(entityIn instanceof IMob))
		 {
			 entityIn.attackEntityFrom(new DamageSource("nightmare_grass"), 1.0F);
		 }
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
			 return null;
		 }
	 }
	   
	 

	 @Override
	 public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	 {
		 return BUSH_AABB;
	 }

	 @Override
	 @SideOnly(Side.CLIENT)
	 public Block.EnumOffsetType getOffsetType()
	 {
		 return Block.EnumOffsetType.XYZ;
	 }
	 
	 @Override
	    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	    {
	        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
	        if (RANDOM.nextInt(8) != 0) return ret;
			ItemStack seed = new ItemStack(ItemsGNS.rainbow_seeds);
	        if (seed != null) ret.add(seed);
	        return ret;
	    }

}