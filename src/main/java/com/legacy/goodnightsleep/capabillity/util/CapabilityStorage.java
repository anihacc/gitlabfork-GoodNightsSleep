package com.legacy.goodnightsleep.capabillity.util;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class CapabilityStorage implements IStorage<IDreamPlayer>
{

	@Override
	public INBT writeNBT(Capability<IDreamPlayer> capability, IDreamPlayer instance, Direction side)
	{
		CompoundNBT compound = new CompoundNBT();
		instance.writeAdditional(compound);
		return compound;
	}

	@Override
	public void readNBT(Capability<IDreamPlayer> capability, IDreamPlayer instance, Direction side, INBT nbt)
	{
		CompoundNBT compound = (CompoundNBT) nbt;
		instance.read(compound);
	}

}