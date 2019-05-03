package com.legacy.goodnightsleep.player;

import net.minecraft.nbt.NBTTagCompound;

public interface INBT 
{

	public void writeEntityToNBT(NBTTagCompound tag);

	public void readEntityFromNBT(NBTTagCompound tag);

}