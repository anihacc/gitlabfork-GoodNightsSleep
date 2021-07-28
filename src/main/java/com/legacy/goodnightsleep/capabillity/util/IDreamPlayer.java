package com.legacy.goodnightsleep.capabillity.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;

public interface IDreamPlayer
{
	void serverTick();

	void clientTick();

	void writeAdditional(CompoundTag nbt);

	void read(CompoundTag nbt);

	public long getEnteredDreamTime();

	public void setEnteredDreamTime(long timeIn);

	Player getPlayer();

	void onDeath();
}
