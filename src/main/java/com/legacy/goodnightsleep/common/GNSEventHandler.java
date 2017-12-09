package com.legacy.goodnightsleep.common;

import com.legacy.goodnightsleep.common.blocks.BlocksGNS;
import com.legacy.goodnightsleep.common.player.PlayerGNS;
import com.legacy.goodnightsleep.common.player.capability.GNSProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GNSEventHandler 
{	
	private static final ResourceLocation PLAYER_LOCATION = new ResourceLocation("goodnightsleep", "gns_player");

	@SubscribeEvent
	public void PlayerConstructingEvent(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getObject();
			GNSProvider provider = new GNSProvider(new PlayerGNS(player));

			if (PlayerGNS.get(player) == null)
			{
				event.addCapability(PLAYER_LOCATION,  provider);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof EntityPlayer)
		{
			PlayerGNS.get((EntityPlayer) event.getEntityLiving()).onUpdate();
		}
	}
	
	@SubscribeEvent
	public void haveGoodDream(RightClickBlock event)
	{					
		if (event.getWorld().getBlockState(event.getPos()).getBlock() == BlocksGNS.luxurious_bed)
			{
				if (event.getEntityLiving() instanceof EntityPlayer)
				{
					PlayerGNS.get((EntityPlayer) event.getEntityLiving()).teleportPlayer(true);
					System.out.println("Entering your Dreams");
					
				}
			}
	}
	
	@SubscribeEvent
	public void haveBadDream(RightClickBlock event)
	{
		if (event.getWorld().getBlockState(event.getPos()).getBlock() == BlocksGNS.wretched_bed)
			{
				if (event.getEntityLiving() instanceof EntityPlayer)
				{
					PlayerGNS.get((EntityPlayer) event.getEntityLiving()).teleportPlayerNightmare(true);
					System.out.println("Entering your Nightmares");
					
				}
			}
	}
}