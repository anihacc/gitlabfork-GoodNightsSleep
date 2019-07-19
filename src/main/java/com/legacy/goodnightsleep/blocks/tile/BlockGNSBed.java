package com.legacy.goodnightsleep.blocks.tile;

import javax.annotation.Nullable;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.tile_entity.TileEntityLuxuriousBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityStrangeBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityWretchedBed;
import com.legacy.goodnightsleep.world.dream.DreamWorldManager;
import com.legacy.goodnightsleep.world.nightmare.NightmareWorldManager;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
public class BlockGNSBed extends HorizontalBlock implements ITileEntityProvider
{

	public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

	public BlockGNSBed(Block.Properties builder)
	{
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState().with(PART, BedPart.FOOT));
	}

	public MaterialColor getMapColor(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return MaterialColor.WOOL;

		//return state.get(PART) == BedPart.FOOT ? this.color.getMapColor() : MaterialColor.WOOL;
	}

	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, Direction side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
		{
			return true;
		}
		else
		{
			if (this == BlocksGNS.luxurious_bed)
			{
				DimensionType previousDimension = player.dimension;
				DimensionType transferDimension = previousDimension == DreamWorldManager.getDimensionType() ? DimensionType.OVERWORLD : DreamWorldManager.getDimensionType();
				
				//player.changeDimension(transferDimension, new TeleporterGNS(ServerLifecycleHooks.getCurrentServer().getWorld(transferDimension)));	
			}
			
			else if (this == BlocksGNS.wretched_bed)
			{
				DimensionType previousDimension = player.dimension;
				DimensionType transferDimension = previousDimension == NightmareWorldManager.getDimensionType() ? DimensionType.OVERWORLD : NightmareWorldManager.getDimensionType();
				
				//player.changeDimension(transferDimension, new TeleporterGNS(ServerLifecycleHooks.getCurrentServer().getWorld(transferDimension)));	
			}
			
			else if (this == BlocksGNS.strange_bed)
			{
				if (worldIn.rand.nextBoolean())
				{
					DimensionType previousDimension = player.dimension;
					DimensionType transferDimension = previousDimension == DreamWorldManager.getDimensionType() ? DimensionType.OVERWORLD : DreamWorldManager.getDimensionType();
					
					//player.changeDimension(transferDimension, new TeleporterGNS(ServerLifecycleHooks.getCurrentServer().getWorld(transferDimension)));	
				}
				else
				{
					DimensionType previousDimension = player.dimension;
					DimensionType transferDimension = previousDimension == NightmareWorldManager.getDimensionType() ? DimensionType.OVERWORLD : NightmareWorldManager.getDimensionType();
					
					//player.changeDimension(transferDimension, new TeleporterGNS(ServerLifecycleHooks.getCurrentServer().getWorld(transferDimension)));	
				}
			}
			
			if (player.dimension == DimensionType.OVERWORLD)
			{
				player.setSpawnPoint(pos, true, DimensionType.OVERWORLD);
				player.setBedPosition(pos);
			}

			return true;
		}
	}

	public boolean isFullCube(BlockState state)
	{
		return false;
	}

	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
	{
		super.onFallenUpon(worldIn, pos, entityIn, fallDistance * 0.5F);
	}

	public void onLanded(IBlockReader worldIn, Entity entityIn)
	{
		if (entityIn.isSneaking())
		{
			super.onLanded(worldIn, entityIn);
		}
		else if (entityIn.getMotion().y < 0.0D)
		{
			//entityIn.getMotion().y = -entityIn.getMotion().y * (double) 0.66F;
			if (!(entityIn instanceof LivingEntity))
			{
				//entityIn.getMotion().y *= 0.8D;
			}
		}
	}

	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		if (facing == func_208070_a(stateIn.get(PART), stateIn.get(HORIZONTAL_FACING)))
		{
			return facingState.getBlock() == this && facingState.get(PART) != stateIn.get(PART) ? stateIn : Blocks.AIR.getDefaultState();
		}
		else
		{
			return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	private static Direction func_208070_a(BedPart p_208070_0_, Direction p_208070_1_)
	{
		return p_208070_0_ == BedPart.FOOT ? p_208070_1_ : p_208070_1_.getOpposite();
	}

	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack)
	{
		super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
	}

	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
	{
		if (state.getBlock() != newState.getBlock())
		{
			super.onReplaced(state, worldIn, pos, newState, isMoving);
			worldIn.removeTileEntity(pos);
		}
	}

	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
	{
		BedPart bedpart = state.get(PART);
		boolean flag = bedpart == BedPart.HEAD;
		BlockPos blockpos = pos.offset(func_208070_a(bedpart, state.get(HORIZONTAL_FACING)));
		BlockState iblockstate = worldIn.getBlockState(blockpos);
		if (iblockstate.getBlock() == this && iblockstate.get(PART) != bedpart)
		{
			worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
			worldIn.playEvent(player, 2001, blockpos, Block.getStateId(iblockstate));
			if (!worldIn.isRemote && !player.isCreative())
			{
				if (flag)
				{
					//state.dropBlockAsItem(worldIn, pos, 0);
				}
				else
				{
					//iblockstate.dropBlockAsItem(worldIn, blockpos, 0);
				}
			}
			//player.addStat(StatList.BLOCK_MINED.get(this));
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		Direction enumfacing = context.getPlacementHorizontalFacing();
		BlockPos blockpos = context.getPos();
		BlockPos blockpos1 = blockpos.offset(enumfacing);
		return context.getWorld().getBlockState(blockpos1).isReplaceable(context) ? this.getDefaultState().with(HORIZONTAL_FACING, enumfacing) : null;
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return SHAPE;
	}

	@OnlyIn(Dist.CLIENT)
	public boolean hasCustomBreakingProgress(BlockState state)
	{
		return true;
	}

	public PushReaction getPushReaction(BlockState state)
	{
		return PushReaction.DESTROY;
	}

	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	//public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, BlockState state, BlockPos pos, Direction face)
	{
		//return BlockFaceShape.UNDEFINED;
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(HORIZONTAL_FACING, PART);
	}

	public TileEntity createNewTileEntity(IBlockReader worldIn)
	{
		if (this == BlocksGNS.luxurious_bed)
		{
			return new TileEntityLuxuriousBed();
		}
		else if (this == BlocksGNS.wretched_bed)
		{
			return new TileEntityWretchedBed();
		}
		else
		{
			return new TileEntityStrangeBed();
		}
		
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
	{
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

		if (!worldIn.isRemote)
		{
			BlockPos blockpos = pos.offset(state.get(HORIZONTAL_FACING));
			worldIn.setBlockState(blockpos, state.with(PART, BedPart.HEAD), 3);
			worldIn.notifyNeighbors(pos, Blocks.AIR);
			state.updateNeighbors(worldIn, pos, 3);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public long getPositionRandom(BlockState state, BlockPos pos)
	{
		BlockPos blockpos = pos.offset(state.get(HORIZONTAL_FACING), state.get(PART) == BedPart.HEAD ? 0 : 1);
		return MathHelper.getCoordinateRandom(blockpos.getX(), pos.getY(), blockpos.getZ());
	}

	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
	{
		return false;
	}
}