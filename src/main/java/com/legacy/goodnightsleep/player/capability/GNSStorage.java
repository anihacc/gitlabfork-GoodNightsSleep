package com.legacy.goodnightsleep.player.capability;

import com.legacy.goodnightsleep.player.PlayerGNS;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

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