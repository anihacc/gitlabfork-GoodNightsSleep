package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.world.dream.DreamWorldManager;
import com.legacy.goodnightsleep.world.nightmare.NightmareWorldManager;

import io.netty.buffer.Unpooled;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GNSEvents
{
	
	public EntityPlayer player;

	public boolean hasTeleported = false, inPortal = false;

	public int timeInPortal;

	public float prevPortalAnimTime, portalAnimTime;
	
	@SubscribeEvent
	public void onRegisteredDimension(RegisterDimensionsEvent event)
	{
		if (DreamWorldManager.getDimensionType() == null)
		{
			DimensionManager.registerDimension(VariableConstants.locate("good_dream"), DreamWorldManager.INSTANCE, new PacketBuffer(Unpooled.buffer()));
		}
		
		if (NightmareWorldManager.getDimensionType() == null)
		{
			DimensionManager.registerDimension(VariableConstants.locate("nightmare"), NightmareWorldManager.INSTANCE, new PacketBuffer(Unpooled.buffer()));
		}
	}
	
	@SubscribeEvent
	public void onBlockRightClick(RightClickBlock event)
	{
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		IBlockState state = world.getBlockState(pos);
		EntityPlayer player = event.getEntityPlayer();
		ItemStack stack = event.getItemStack();
		if ((stack.getItem() instanceof ItemHoe) && world.isAirBlock(pos.up()))
		{
			Block block = state.getBlock();

			if (block == BlocksGNS.dream_grass_block || block == BlocksGNS.dream_dirt)
			{
				hoeDirt(stack, player, world, pos, BlocksGNS.dream_farmland.getDefaultState(), event);
			}
		}
	}

	protected void hoeDirt(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state, PlayerInteractEvent.RightClickBlock event)
	{
		worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
		if (!worldIn.isRemote)
		{
			worldIn.setBlockState(pos, state, 11);
			stack.damageItem(1, player);
		}
		player.swingArm(event.getHand());
	}

	protected void plantSeed(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			worldIn.setBlockState(pos, state, 11);

			if (!player.isCreative())
				stack.shrink(1);
		}
	}
}