package com.legacy.goodnightsleep.capabillity.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public interface IDreamPlayer
{
	void serverTick();

	void clientTick();

	void writeAdditional(CompoundNBT nbt);

	void read(CompoundNBT nbt);

	public long getEnteredDreamTime();

	public void setEnteredDreamTime(long timeIn);

	PlayerEntity getPlayer();

	void onDeath();
}
