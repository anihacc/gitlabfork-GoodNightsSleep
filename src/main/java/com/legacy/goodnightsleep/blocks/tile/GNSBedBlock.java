package com.legacy.goodnightsleep.blocks.tile;

import java.util.Optional;

import javax.annotation.Nullable;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.tile_entity.TileEntityLuxuriousBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityStrangeBed;
import com.legacy.goodnightsleep.tile_entity.TileEntityWretchedBed;
import com.legacy.goodnightsleep.world.GNSDimensions;
import com.legacy.goodnightsleep.world.GNSTeleportationUtil;
import com.legacy.goodnightsleep.world.dream.GoodDreamDimension;
import com.legacy.goodnightsleep.world.nightmare.NightmareDimension;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
public class GNSBedBlock extends HorizontalBlock implements ITileEntityProvider
{

	public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;

	protected static final VoxelShape field_220176_c = Block.makeCuboidShape(0.0D, 3.0D, 0.0D, 16.0D, 9.0D, 16.0D);
	protected static final VoxelShape field_220177_d = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 3.0D, 3.0D);
	protected static final VoxelShape field_220178_e = Block.makeCuboidShape(0.0D, 0.0D, 13.0D, 3.0D, 3.0D, 16.0D);
	protected static final VoxelShape field_220179_f = Block.makeCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 3.0D, 3.0D);
	protected static final VoxelShape field_220180_g = Block.makeCuboidShape(13.0D, 0.0D, 13.0D, 16.0D, 3.0D, 16.0D);
	protected static final VoxelShape field_220181_h = VoxelShapes.or(field_220176_c, field_220177_d, field_220179_f);
	protected static final VoxelShape field_220182_i = VoxelShapes.or(field_220176_c, field_220178_e, field_220180_g);
	protected static final VoxelShape field_220183_j = VoxelShapes.or(field_220176_c, field_220177_d, field_220178_e);
	protected static final VoxelShape field_220184_k = VoxelShapes.or(field_220176_c, field_220179_f, field_220180_g);

	public GNSBedBlock(Block.Properties properties)
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(PART, BedPart.FOOT));
	}

	public MaterialColor getMaterialColor(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return MaterialColor.WOOL;
	}

	public static Direction func_226862_h_(BlockState p_226862_0_)
	{
		Direction direction = p_226862_0_.get(HORIZONTAL_FACING);
		return p_226862_0_.get(PART) == BedPart.HEAD ? direction.getOpposite() : direction;
	}

	@OnlyIn(Dist.CLIENT)
	public static TileEntityMerger.Type func_226863_i_(BlockState p_226863_0_)
	{
		BedPart bedpart = p_226863_0_.get(PART);
		return bedpart == BedPart.HEAD ? TileEntityMerger.Type.FIRST : TileEntityMerger.Type.SECOND;
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{

		if (worldIn.isRemote)
		{
			return ActionResultType.SUCCESS;
		}
		else
		{
			if (player.dimension == DimensionType.OVERWORLD)
			{
				player.setSpawnPoint(pos, false, false, DimensionType.OVERWORLD);
				player.setBedPosition(pos);
			}

			if (this == GNSBlocks.luxurious_bed)
			{
				this.travelToDream(player, true);
			}
			else if (this == GNSBlocks.wretched_bed)
			{
				this.travelToDream(player, false);
			}
			else if (this == GNSBlocks.strange_bed)
			{
				this.travelToDream(player, worldIn.rand.nextBoolean());
			}

			return ActionResultType.SUCCESS;
		}
	}

	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
	{
		super.onFallenUpon(worldIn, pos, entityIn, fallDistance * 0.5F);
	}

	@Override
	public void onLanded(IBlockReader worldIn, Entity entityIn)
	{
		if (entityIn.isCrouching())
		{
			super.onLanded(worldIn, entityIn);
		}
		else
		{
			Vec3d vec3d = entityIn.getMotion();
			if (vec3d.y < 0.0D)
			{
				double d0 = entityIn instanceof LivingEntity ? 1.0D : 0.8D;
				entityIn.setMotion(vec3d.x, -vec3d.y * (double) 0.66F * d0, vec3d.z);
			}
		}
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		if (facing == getDirectionToOther(stateIn.get(PART), stateIn.get(HORIZONTAL_FACING)))
		{
			return facingState.getBlock() == this && facingState.get(PART) != stateIn.get(PART) ? stateIn : Blocks.AIR.getDefaultState();
		}
		else
		{
			return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	private static Direction getDirectionToOther(BedPart p_208070_0_, Direction p_208070_1_)
	{
		return p_208070_0_ == BedPart.FOOT ? p_208070_1_ : p_208070_1_.getOpposite();
	}

	@Override
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack)
	{
		super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
	{
		BedPart bedpart = state.get(PART);
		BlockPos blockpos = pos.offset(getDirectionToOther(bedpart, state.get(HORIZONTAL_FACING)));
		BlockState blockstate = worldIn.getBlockState(blockpos);
		if (blockstate.getBlock() == this && blockstate.get(PART) != bedpart)
		{
			worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
			worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
			if (!worldIn.isRemote && !player.isCreative())
			{
				ItemStack itemstack = player.getHeldItemMainhand();
				spawnDrops(state, worldIn, pos, (TileEntity) null, player, itemstack);
				spawnDrops(blockstate, worldIn, blockpos, (TileEntity) null, player, itemstack);
			}
			player.addStat(Stats.BLOCK_MINED.get(this));
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		Direction direction = context.getPlacementHorizontalFacing();
		BlockPos blockpos = context.getPos();
		BlockPos blockpos1 = blockpos.offset(direction);
		return context.getWorld().getBlockState(blockpos1).isReplaceable(context) ? this.getDefaultState().with(HORIZONTAL_FACING, direction) : null;
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		Direction direction = state.get(HORIZONTAL_FACING);
		Direction direction1 = state.get(PART) == BedPart.HEAD ? direction : direction.getOpposite();
		switch (direction1)
		{
		case NORTH:
			return field_220181_h;
		case SOUTH:
			return field_220182_i;
		case WEST:
			return field_220183_j;
		default:
			return field_220184_k;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public boolean hasCustomBreakingProgress(BlockState state)
	{
		return true;
	}

	public static Optional<Vec3d> func_220172_a(EntityType<?> p_220172_0_, IWorldReader p_220172_1_, BlockPos p_220172_2_, int p_220172_3_)
	{
		Direction direction = p_220172_1_.getBlockState(p_220172_2_).get(HORIZONTAL_FACING);
		int i = p_220172_2_.getX();
		int j = p_220172_2_.getY();
		int k = p_220172_2_.getZ();
		for (int l = 0; l <= 1; ++l)
		{
			int i1 = i - direction.getXOffset() * l - 1;
			int j1 = k - direction.getZOffset() * l - 1;
			int k1 = i1 + 2;
			int l1 = j1 + 2;
			for (int i2 = i1; i2 <= k1; ++i2)
			{
				for (int j2 = j1; j2 <= l1; ++j2)
				{
					BlockPos blockpos = new BlockPos(i2, j, j2);
					Optional<Vec3d> optional = func_220175_a(p_220172_0_, p_220172_1_, blockpos);
					if (optional.isPresent())
					{
						if (p_220172_3_ <= 0)
						{
							return optional;
						}
						--p_220172_3_;
					}
				}
			}
		}
		return Optional.empty();
	}

	protected static Optional<Vec3d> func_220175_a(EntityType<?> p_220175_0_, IWorldReader p_220175_1_, BlockPos p_220175_2_)
	{
		VoxelShape voxelshape = p_220175_1_.getBlockState(p_220175_2_).getCollisionShape(p_220175_1_, p_220175_2_);
		if (voxelshape.getEnd(Direction.Axis.Y) > 0.4375D)
		{
			return Optional.empty();
		}
		else
		{
			BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable(p_220175_2_);
			while (blockpos$mutableblockpos.getY() >= 0 && p_220175_2_.getY() - blockpos$mutableblockpos.getY() <= 2 && p_220175_1_.getBlockState(blockpos$mutableblockpos).getCollisionShape(p_220175_1_, blockpos$mutableblockpos).isEmpty())
			{
				blockpos$mutableblockpos.move(Direction.DOWN);
			}
			VoxelShape voxelshape1 = p_220175_1_.getBlockState(blockpos$mutableblockpos).getCollisionShape(p_220175_1_, blockpos$mutableblockpos);
			if (voxelshape1.isEmpty())
			{
				return Optional.empty();
			}
			else
			{
				double d0 = (double) blockpos$mutableblockpos.getY() + voxelshape1.getEnd(Direction.Axis.Y) + 2.0E-7D;
				if ((double) p_220175_2_.getY() - d0 > 2.0D)
				{
					return Optional.empty();
				}
				else
				{
					float f = p_220175_0_.getWidth() / 2.0F;
					Vec3d vec3d = new Vec3d((double) blockpos$mutableblockpos.getX() + 0.5D, d0, (double) blockpos$mutableblockpos.getZ() + 0.5D);
					return p_220175_1_.hasNoCollisions(new AxisAlignedBB(vec3d.x - (double) f, vec3d.y, vec3d.z - (double) f, vec3d.x + (double) f, vec3d.y + (double) p_220175_0_.getHeight(), vec3d.z + (double) f)) ? Optional.of(vec3d) : Optional.empty();
				}
			}
		}
	}

	@Override
	public PushReaction getPushReaction(BlockState state)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(HORIZONTAL_FACING, PART);
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn)
	{
		if (this == GNSBlocks.luxurious_bed)
		{
			return new TileEntityLuxuriousBed();
		}
		else if (this == GNSBlocks.wretched_bed)
		{
			return new TileEntityWretchedBed();
		}
		else
		{
			return new TileEntityStrangeBed();
		}

	}

	@Override
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

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
	{
		return false;
	}

	private void travelToDream(PlayerEntity player, boolean dream)
	{
		DimensionType transferDimension = player.dimension == GNSDimensions.dimensionType(dream) ? DimensionType.OVERWORLD : GNSDimensions.dimensionType(dream);

		if (transferDimension == GNSDimensions.dimensionType(true))
		{
			ServerWorld serverWorld = player.getServer().getWorld(GNSDimensions.dimensionType(true));

			if (((GoodDreamDimension) serverWorld.getDimension()).dreamPlayerList.size() <= 0)
			{
				if (!GNSConfig.disableTimePassing)
				{
					player.world.setDayTime(0L);
				}

				// serverWorld.setDayTime(0L);
			}
		}
		else if (transferDimension == GNSDimensions.dimensionType(false))
		{
			ServerWorld serverWorld = player.getServer().getWorld(GNSDimensions.dimensionType(false));

			if (((NightmareDimension) serverWorld.getDimension()).nightmarePlayerList.size() <= 0)
			{
				if (!GNSConfig.disableTimePassing)
				{
					player.world.setDayTime(0L);
				}

				// serverWorld.setDayTime(0L);
			}
		}

		try
		{
			BlockPos pos = player.dimension != DimensionType.OVERWORLD ? player.getBedLocation(DimensionType.OVERWORLD) : player.world.getSpawnPoint();
			GNSTeleportationUtil.changeDimension(transferDimension, player, pos);
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
	}
}