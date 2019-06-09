package com.legacy.goodnightsleep.player.capability;

import com.legacy.goodnightsleep.player.PlayerGNS;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class GNSProvider implements ICapabilityProvider
{

	private final PlayerGNS playerAether;

	public GNSProvider(PlayerGNS playerAether)
	{
		this.playerAether = playerAether;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == GNSManager.PLAYER;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if (capability == GNSManager.PLAYER)
		{
			return (T) this.playerAether;
		}

		return null;
	}

}