package com.legacy.goodnightsleep.blocks.natural;

import java.util.List;
import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.ItemsGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
		public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
		{
		 return this.canPlaceBlockOn(worldIn.getBlockState(pos.down()).getBlock());
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
	 public boolean isReplaceable(World worldIn, BlockPos pos)
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