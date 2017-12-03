package com.legacy.goodnightsleep.common.player.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

import com.legacy.goodnightsleep.common.player.PlayerGNS;

public class GNSStorage implements IStorage<PlayerGNS>
{

	@Override
	public NBTBase writeNBT(Capability<PlayerGNS> capability, PlayerGNS instance, EnumFacing side) 
	{
		return new NBTTagCompound();
	}

	@Override
	public void readNBT(Capability<PlayerGNS> capability, PlayerGNS instance, EnumFacing side, NBTBase nbt) 
	{

	}

}