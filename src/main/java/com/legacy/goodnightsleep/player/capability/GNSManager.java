package com.legacy.goodnightsleep.player.capability;

import com.legacy.goodnightsleep.player.PlayerGNS;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class GNSManager 
{

	@CapabilityInject(PlayerGNS.class)
	public static Capability<PlayerGNS> PLAYER = null;

	public static void initialization()
	{
		CapabilityManager.INSTANCE.register(PlayerGNS.class, new GNSStorage(), new PlayerGNS(null).getClass());
	}

}