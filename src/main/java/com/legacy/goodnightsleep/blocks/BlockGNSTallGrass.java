package com.legacy.goodnightsleep.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockGNSTallGrass extends BlockBush
{

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

	 public BlockGNSTallGrass(Properties properties)
	 {
		 super(properties);

		 //this.setCreativeTab(GNSCreativeTabs.blocks);
	 }
	 
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return SHAPE;
	}

	 @Override
	 public boolean canSustainPlant(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing facing, net.minecraftforge.common.IPlantable plantable)
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
	 public boolean isReplaceable(IBlockState state, BlockItemUseContext useContext)
	 {
		 return true;
	 }
	 
	 @Override
	 public void onEntityCollision(IBlockState state, World worldIn, BlockPos pos, Entity entityIn)
	 {
		 if (this == BlocksGNS.tall_nightmare_grass && !(entityIn instanceof IMob))
		 {
			 entityIn.attackEntityFrom(new DamageSource("nightmare_grass"), 1.0F);
		 }
	 }

	 @Override
	 public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune)
	 {
		 if (this == BlocksGNS.lolipop_bush)
		 {
			 return Items.AIR;//ItemsGNS.lolipop;
		 }
		 else
		 {
			 return Items.AIR;
		 }
	 }

	 @Override
	 @OnlyIn(Dist.CLIENT)
	 public Block.EnumOffsetType getOffsetType()
	 {
		 return Block.EnumOffsetType.XYZ;
	 }
	 
	@Override
	public void getDrops(IBlockState state, net.minecraft.util.NonNullList<ItemStack> drops, World world, BlockPos pos, int fortune)
	{
		{
			if (RANDOM.nextInt(8) != 0)
				return;
			ItemStack seed = new ItemStack(Items.WHEAT_SEEDS);
			if (!seed.isEmpty())
				drops.add(seed);
		}
	}

}