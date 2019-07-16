package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.world.dream.DreamWorldManager;
import com.legacy.goodnightsleep.world.nightmare.NightmareWorldManager;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.DimensionManager;
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
}