package com.legacy.goodnightsleep.client;

import java.util.Random;

import com.legacy.goodnightsleep.CommonProxy;
import com.legacy.goodnightsleep.client.audio.GNSMusicHandler;
import com.legacy.goodnightsleep.client.renders.GNSEntityRenderingRegistry;
import com.legacy.goodnightsleep.client.renders.blocks.BlockRendering;
import com.legacy.goodnightsleep.client.renders.items.ItemRendering;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ClientProxy extends CommonProxy
{

	@Override
	public void preInitialization()
	{
		registerEvent(new BlockRendering());
		registerEvent(new ItemRendering());
		GNSEntityRenderingRegistry.initialize();

		registerEvent(new GNSMusicHandler());
		
		MinecraftForge.EVENT_BUS.register(new GNSClientEventHandler());
	}

	@Override
	public void initialization()
	{
	}

	@Override
	public void sendMessage(EntityPlayer reciever, String message)
	{
		if (this.getThePlayer() == reciever)
		{
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new TextComponentString(message));
		}
	}

	@Override
	public EntityPlayer getThePlayer()
	{
		return Minecraft.getMinecraft().player;
	}

	@Override
	public void spawnSmoke(World world, BlockPos pos)
	{
		Random rand = new Random();
		double a, b, c;
		a = pos.getX() + 0.5D + ((rand.nextFloat() - rand.nextFloat()) * 0.375D);
		b = pos.getY() + 0.5D + ((rand.nextFloat() - rand.nextFloat()) * 0.375D);
		c = pos.getZ() + 0.5D + ((rand.nextFloat() - rand.nextFloat()) * 0.375D);
		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, a, b, c, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public void spawnBlockBrokenFX(IBlockState state, BlockPos pos)
	{
		FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(pos, state);
	}

}