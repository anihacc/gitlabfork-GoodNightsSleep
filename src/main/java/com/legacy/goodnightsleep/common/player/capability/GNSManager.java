package com.legacy.goodnightsleep.common.player.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import com.legacy.goodnightsleep.common.player.PlayerGNS;

public class GNSManager 
{

	@CapabilityInject(PlayerGNS.class)
	public static Capability<PlayerGNS> PLAYER = null;

	public static void initialization()
	{
		CapabilityManager.INSTANCE.register(PlayerGNS.class, new GNSStorage(), new PlayerGNS(null).getClass());
	}

}