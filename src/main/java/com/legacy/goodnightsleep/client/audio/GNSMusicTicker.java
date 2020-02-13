package com.legacy.goodnightsleep.client.audio;

import java.util.Random;

import com.legacy.goodnightsleep.world.GNSDimensions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GNSMusicTicker implements ITickable
{

	private final Random rand = new Random();

	private final Minecraft mc;

	public ISound ambientMusic, playingRecord;

	private int timeUntilNextMusic = 100;

	public GNSMusicTicker(Minecraft mcIn)
	{
		this.mc = mcIn;
	}

	public void tick()
	{
		TrackType tracktypeB = this.getRandomTrackDream();
		TrackType tracktypeD = this.getRandomTrackNightmare();

		try
		{
			if (this.mc.player != null && !this.mc.getSoundHandler().isPlaying(this.playingRecord) && GNSDimensions.dimensionType(true) != null && GNSDimensions.dimensionType(false) != null)
			{
				if (this.mc.player.dimension == GNSDimensions.dimensionType(true))
				{
					if (this.ambientMusic != null)
					{
						if (!this.mc.getSoundHandler().isPlaying(this.ambientMusic))
						{
							this.ambientMusic = null;
							this.timeUntilNextMusic = Math.min(MathHelper.nextInt(this.rand, tracktypeB.getMinDelay(), tracktypeB.getMaxDelay()), this.timeUntilNextMusic);
						}
					}
					this.timeUntilNextMusic = Math.min(this.timeUntilNextMusic, tracktypeB.getMaxDelay());
					if (this.ambientMusic == null && this.timeUntilNextMusic-- <= 0)
					{
						this.playMusic(tracktypeB);
					}
				}
				else if (this.mc.player.dimension == GNSDimensions.dimensionType(false))
				{
					if (this.ambientMusic != null)
					{
						if (!this.mc.getSoundHandler().isPlaying(this.ambientMusic))
						{
							this.ambientMusic = null;
							this.timeUntilNextMusic = Math.min(MathHelper.nextInt(this.rand, tracktypeD.getMinDelay(), tracktypeD.getMaxDelay()), this.timeUntilNextMusic);
						}
					}
					this.timeUntilNextMusic = Math.min(this.timeUntilNextMusic, tracktypeD.getMaxDelay());
					if (this.ambientMusic == null && this.timeUntilNextMusic-- <= 0)
					{
						this.playMusic(tracktypeD);
					}
				}
				else
				{
					this.stopMusic();
				}
			}
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
	}

	public boolean playingMusic()
	{
		return this.ambientMusic != null;
	}

	public boolean playingRecord()
	{
		return this.playingRecord != null;
	}

	public GNSMusicTicker.TrackType getRandomTrackDream()
	{
		return this.rand.nextBoolean() ? TrackType.SKY_BLUE : TrackType.DREAM;
	}

	public GNSMusicTicker.TrackType getRandomTrackNightmare()
	{
		return TrackType.NIGHTMARE;
	}

	public void playMusic(TrackType requestedMusicType)
	{
		this.ambientMusic = SimpleSound.music(requestedMusicType.getMusicLocation());
		this.mc.getSoundHandler().play(this.ambientMusic);
		this.timeUntilNextMusic = Integer.MAX_VALUE;
	}

	public void stopMusic()
	{
		if (this.ambientMusic != null)
		{
			this.mc.getSoundHandler().stop(this.ambientMusic);
			this.ambientMusic = null;
			this.timeUntilNextMusic = 0;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static enum TrackType
	{
		// @formatter:off
    	DREAM(GNSSounds.MUSIC_GOOD_DREAM, 1200, 1500),
    	SKY_BLUE(GNSSounds.MUSIC_SKY_BLUE, 1200, 1500),
    	NIGHTMARE(GNSSounds.MUSIC_NIGHTMARE, 1200, 1500);
    	// @formatter:on

		private final SoundEvent musicLocation;
		private final int minDelay;
		private final int maxDelay;

		private TrackType(SoundEvent musicLocationIn, int minDelayIn, int maxDelayIn)
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

}