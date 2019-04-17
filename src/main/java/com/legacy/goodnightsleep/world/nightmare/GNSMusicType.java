package com.legacy.goodnightsleep.world.nightmare;

import com.legacy.goodnightsleep.registry.GNSSounds;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public enum GNSMusicType
{
	DREAM(GNSSounds.MUSIC_GOOD_DREAM, 1200, 1500), NIGHTMARE(GNSSounds.MUSIC_NIGHTMARE, 1200, 1500);

	private final SoundEvent musicLocation;

	private final int minDelay;

	private final int maxDelay;

	private GNSMusicType(SoundEvent musicLocationIn, int minDelayIn, int maxDelayIn)
	{
		this.musicLocation = musicLocationIn;
		this.minDelay = minDelayIn;
		this.maxDelay = maxDelayIn;
	}

	public SoundEvent getMusicLocation()
	{
		return this.musicLocation;
	}

	public int getMinDelay()
	{
		return this.minDelay;
	}

	public int getMaxDelay()
	{
		return this.maxDelay;
	}
}