package com.legacy.goodnightsleep;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommonProxy 
{

	public void preInitialization() { }

	public void initialization() { }

	public EntityPlayer getThePlayer() { return null; }

	public void sendMessage(EntityPlayer player, String message) { }

	public void spawnBlockBrokenFX(IBlockState state, BlockPos pos) { }

	public void spawnSmoke(World world, BlockPos pos) {}

	@SuppressWarnings("deprecation")
	public static void registerEvent(Object event)
	{
		FMLCommonHandler.instance().bus().register(event);
		MinecraftForge.EVENT_BUS.register(event);
	}
}