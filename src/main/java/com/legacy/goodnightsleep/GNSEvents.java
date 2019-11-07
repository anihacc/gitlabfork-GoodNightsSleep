package com.legacy.goodnightsleep;

import java.util.Map;

import com.google.common.collect.ImmutableMap.Builder;
import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.world.GNSDimensions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GNSEvents
{
	public static final Map<Block, Block> BLOCK_STRIPPING_MAP = (new Builder<Block, Block>()).put(GNSBlocks.dream_log, GNSBlocks.stripped_dream_log).put(GNSBlocks.dream_wood, GNSBlocks.stripped_dream_wood).put(GNSBlocks.white_log, GNSBlocks.stripped_white_log).put(GNSBlocks.white_wood, GNSBlocks.stripped_white_wood).put(GNSBlocks.dead_log, GNSBlocks.stripped_dead_log).put(GNSBlocks.dead_wood, GNSBlocks.stripped_dead_wood).put(GNSBlocks.blood_log, GNSBlocks.stripped_blood_log).put(GNSBlocks.blood_wood, GNSBlocks.stripped_blood_wood).build();

	public PlayerEntity player;

	public boolean hasTeleported = false, inPortal = false;

	public int timeInPortal;

	public float prevPortalAnimTime, portalAnimTime;

	@SubscribeEvent
	public void onRegisteredDimension(RegisterDimensionsEvent event)
	{
		GNSDimensions.initDimensions();
	}

	@SubscribeEvent
	public void onBlockRightClick(RightClickBlock event)
	{
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		BlockState state = world.getBlockState(pos);
		PlayerEntity player = event.getPlayer();
		ItemStack stack = event.getItemStack();

		if ((stack.getItem() instanceof HoeItem) && world.isAirBlock(pos.up()))
		{
			Block block = state.getBlock();

			if (block == GNSBlocks.dream_grass_block || block == GNSBlocks.dream_dirt)
			{
				hoeDirt(stack, player, world, pos, GNSBlocks.dream_farmland.getDefaultState(), event);
			}
		}

		if (stack.getItem() instanceof AxeItem)
		{
			BlockRayTraceResult rayTraceResult = (BlockRayTraceResult) rayTrace(world, player);
			BlockPos pos1 = rayTraceResult.getPos();
			BlockState blockState = world.getBlockState(pos1);
			Block block = BLOCK_STRIPPING_MAP.get(blockState.getBlock());
			if (block != null)
			{
				player.getEntityWorld().playSound(player, pos1, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
				player.swingArm(event.getHand());
				if (!world.isRemote)
				{
					world.setBlockState(pos1, block.getDefaultState().with(RotatedPillarBlock.AXIS, blockState.get(RotatedPillarBlock.AXIS)), 11);
					if (player != null)
					{
						event.getItemStack().damageItem(1, player, (playerEntity) ->
						{
							playerEntity.sendBreakAnimation(event.getHand());
						});
					}
				}
			}
		}
	}

	protected void hoeDirt(ItemStack stack, PlayerEntity player, World worldIn, BlockPos pos, BlockState state, PlayerInteractEvent.RightClickBlock event)
	{
		worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
		if (!worldIn.isRemote)
		{
			worldIn.setBlockState(pos, state, 11);
			stack.damageItem(1, player, (p_219991_1_) ->
			{
				p_219991_1_.sendBreakAnimation(player.getActiveHand());
			});
		}
		player.swingArm(event.getHand());
	}

	protected void plantSeed(ItemStack stack, PlayerEntity player, World worldIn, BlockPos pos, BlockState state)
	{
		if (!worldIn.isRemote)
		{
			worldIn.setBlockState(pos, state, 11);

			if (!player.isCreative())
				stack.shrink(1);
		}
	}

	protected static RayTraceResult rayTrace(World worldIn, PlayerEntity player)
	{
		float f = player.rotationPitch;
		float f1 = player.rotationYaw;
		Vec3d vec3d = player.getEyePosition(1.0F);
		float f2 = MathHelper.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
		float f3 = MathHelper.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
		float f4 = -MathHelper.cos(-f * ((float) Math.PI / 180F));
		float f5 = MathHelper.sin(-f * ((float) Math.PI / 180F));
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double d0 = player.getAttribute(PlayerEntity.REACH_DISTANCE).getValue();
		Vec3d vec3d1 = vec3d.add((double) f6 * d0, (double) f5 * d0, (double) f7 * d0);
		return worldIn.rayTraceBlocks(new RayTraceContext(vec3d, vec3d1, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.SOURCE_ONLY, player));
	}
}