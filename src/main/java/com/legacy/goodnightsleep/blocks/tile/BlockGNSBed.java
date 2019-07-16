package com.legacy.goodnightsleep.blocks.tile;

import javax.annotation.Nullable;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.tile_entity.TileEntityLuxuriousBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityStrangeBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityWretchedBed;
import com.legacy.goodnightsleep.world.TeleporterGNS;
import com.legacy.goodnightsleep.world.dream.DreamWorldManager;
import com.legacy.goodnightsleep.world.nightmare.NightmareWorldManager;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

@SuppressWarnings("deprecation")
public class BlockGNSBed extends BlockHorizontal implements ITileEntityProvider
{

	public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

	public BlockGNSBed(Block.Properties builder)
	{
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState().with(PART, BedPart.FOOT));
	}

	public MaterialColor getMapColor(IBlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return MaterialColor.WOOL;

		//return state.get(PART) == BedPart.FOOT ? this.color.getMapColor() : MaterialColor.WOOL;
	}

	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
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
				
				player.changeDimension(transferDimension, new TeleporterGNS(ServerLifecycleHooks.getCurrentServer().getWorld(transferDimension)));	
			}
			
			else if (this == BlocksGNS.wretched_bed)
			{
				DimensionType previousDimension = player.dimension;
				DimensionType transferDimension = previousDimension == NightmareWorldManager.getDimensionType() ? DimensionType.OVERWORLD : NightmareWorldManager.getDimensionType();
				
				player.changeDimension(transferDimension, new TeleporterGNS(ServerLifecycleHooks.getCurrentServer().getWorld(transferDimension)));	
			}
			
			else if (this == BlocksGNS.strange_bed)
			{
				if (worldIn.rand.nextBoolean())
				{
					DimensionType previousDimension = player.dimension;
					DimensionType transferDimension = previousDimension == DreamWorldManager.getDimensionType() ? DimensionType.OVERWORLD : DreamWorldManager.getDimensionType();
					
					player.changeDimension(transferDimension, new TeleporterGNS(ServerLifecycleHooks.getCurrentServer().getWorld(transferDimension)));	
				}
				else
				{
					DimensionType previousDimension = player.dimension;
					DimensionType transferDimension = previousDimension == NightmareWorldManager.getDimensionType() ? DimensionType.OVERWORLD : NightmareWorldManager.getDimensionType();
					
					player.changeDimension(transferDimension, new TeleporterGNS(ServerLifecycleHooks.getCurrentServer().getWorld(transferDimension)));	
				}
			}
			
			if (player.dimension == DimensionType.OVERWORLD)
			{
				player.setSpawnPoint(pos, true, DimensionType.OVERWORLD);
				player.bedLocation = pos;
			}

			return true;
		}
	}

	public boolean isFullCube(IBlockState state)
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
		else if (entityIn.motionY < 0.0D)
		{
			entityIn.motionY = -entityIn.motionY * (double) 0.66F;
			if (!(entityIn instanceof EntityLivingBase))
			{
				entityIn.motionY *= 0.8D;
			}
		}
	}

	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
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

	private static EnumFacing func_208070_a(BedPart p_208070_0_, EnumFacing p_208070_1_)
	{
		return p_208070_0_ == BedPart.FOOT ? p_208070_1_ : p_208070_1_.getOpposite();
	}

	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
	{
		super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
	}

	public void onReplaced(IBlockState state, World worldIn, BlockPos pos, IBlockState newState, boolean isMoving)
	{
		if (state.getBlock() != newState.getBlock())
		{
			super.onReplaced(state, worldIn, pos, newState, isMoving);
			worldIn.removeTileEntity(pos);
		}
	}

	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		BedPart bedpart = state.get(PART);
		boolean flag = bedpart == BedPart.HEAD;
		BlockPos blockpos = pos.offset(func_208070_a(bedpart, state.get(HORIZONTAL_FACING)));
		IBlockState iblockstate = worldIn.getBlockState(blockpos);
		if (iblockstate.getBlock() == this && iblockstate.get(PART) != bedpart)
		{
			worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
			worldIn.playEvent(player, 2001, blockpos, Block.getStateId(iblockstate));
			if (!worldIn.isRemote && !player.isCreative())
			{
				if (flag)
				{
					state.dropBlockAsItem(worldIn, pos, 0);
				}
				else
				{
					iblockstate.dropBlockAsItem(worldIn, blockpos, 0);
				}
			}
			player.addStat(StatList.BLOCK_MINED.get(this));
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}

	@Nullable
	public IBlockState getStateForPlacement(BlockItemUseContext context)
	{
		EnumFacing enumfacing = context.getPlacementHorizontalFacing();
		BlockPos blockpos = context.getPos();
		BlockPos blockpos1 = blockpos.offset(enumfacing);
		return context.getWorld().getBlockState(blockpos1).isReplaceable(context) ? this.getDefaultState().with(HORIZONTAL_FACING, enumfacing) : null;
	}

	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune)
	{
		return (IItemProvider) (state.get(PART) == BedPart.FOOT ? Items.AIR : super.getItemDropped(state, worldIn, pos, fortune));
	}

	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return SHAPE;
	}

	@OnlyIn(Dist.CLIENT)
	public boolean hasCustomBreakingProgress(IBlockState state)
	{
		return true;
	}

	public EnumPushReaction getPushReaction(IBlockState state)
	{
		return EnumPushReaction.DESTROY;
	}

	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}

	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
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

	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, @Nullable EntityLivingBase placer, ItemStack stack)
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
	public long getPositionRandom(IBlockState state, BlockPos pos)
	{
		BlockPos blockpos = pos.offset(state.get(HORIZONTAL_FACING), state.get(PART) == BedPart.HEAD ? 0 : 1);
		return MathHelper.getCoordinateRandom(blockpos.getX(), pos.getY(), blockpos.getZ());
	}

	public boolean allowsMovement(IBlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
	{
		return false;
	}
}