package com.legacy.goodnightsleep.capabillity.util;

import com.legacy.goodnightsleep.capabillity.DreamPlayer;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityProvider implements ICapabilitySerializable<CompoundTag>
{
	private final LazyOptional<IDreamPlayer> dreamPlayerHandler;

	public CapabilityProvider(IDreamPlayer dreamPlayer)
	{
		this.dreamPlayerHandler = LazyOptional.of(() ->
		{
			return dreamPlayer;
		});
	}

	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		return cap == DreamPlayer.GNS_PLAYER ? this.dreamPlayerHandler.cast() : LazyOptional.empty();
	}

	public void deserializeNBT(CompoundTag compound)
	{
		this.dreamPlayerHandler.orElse(null).read(compound);
	}

	public CompoundTag serializeNBT()
	{
		CompoundTag compound = new CompoundTag();
		this.dreamPlayerHandler.orElse(null).writeAdditional(compound);
		return compound;
	}
}