package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.world.GNSDimensions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GNSEvents
{
	
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
	}

	protected void hoeDirt(ItemStack stack, PlayerEntity player, World worldIn, BlockPos pos, BlockState state, PlayerInteractEvent.RightClickBlock event)
	{
		worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
		if (!worldIn.isRemote)
		{
			worldIn.setBlockState(pos, state, 11);
			//stack.damageItem(1, player);
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
}