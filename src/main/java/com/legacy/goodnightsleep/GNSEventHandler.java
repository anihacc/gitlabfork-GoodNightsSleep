package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.client.gui.GuiLoadingDreams;
import com.legacy.goodnightsleep.player.PlayerGNS;
import com.legacy.goodnightsleep.player.capability.GNSProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GNSEventHandler 
{	
	private static final ResourceLocation PLAYER_LOCATION = new ResourceLocation("goodnightsleep", "gns_player");

	private final Minecraft mc = FMLClientHandler.instance().getClient();
	
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
					//System.out.println("Entering your Dreams");
					
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
					//System.out.println("Entering your Nightmares");
					
				}
			}
	}
	
	@SubscribeEvent
	public void onOpenGui(GuiOpenEvent event)
	{
		if (event.getGui() instanceof GuiDownloadTerrain && mc.player != null) 
		{
			GuiLoadingDreams guiEnterDream = new GuiLoadingDreams(false);
			GuiLoadingDreams guiEnterNightmare = new GuiLoadingDreams(true);
			
			if (mc.player.dimension == GNSConfig.getNightmareDimensionID())
			{
				event.setGui(guiEnterNightmare);
			}
			
			if (mc.player.dimension == GNSConfig.getDreamDimensionID())
			{
				event.setGui(guiEnterDream);
			}
		}
	}
}