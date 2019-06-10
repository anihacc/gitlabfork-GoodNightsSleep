package com.legacy.goodnightsleep.client.audio;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSSounds;
import com.legacy.goodnightsleep.world.dream.GoodDreamWorldProvider;
import com.legacy.goodnightsleep.world.nightmare.NightmareWorldProvider;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
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
       
      	if (this.mc.thePlayer != null && !this.mc.getSoundHandler().isSoundPlaying(this.playingRecord))
      	{
      		
      		if (this.mc.thePlayer.worldObj.provider instanceof GoodDreamWorldProvider)
          	{
      			
      			if (this.ambientMusic != null)
                {
                    if (!this.mc.getSoundHandler().isSoundPlaying(this.ambientMusic))
                    {
                        this.ambientMusic = null;
                        this.timeUntilNextMusic = Math.min(MathHelper.getRandomIntegerInRange(this.rand, tracktypeB.getMinDelay(), tracktypeB.getMaxDelay()), this.timeUntilNextMusic);
                    }
                }

                this.timeUntilNextMusic = Math.min(this.timeUntilNextMusic, tracktypeB.getMaxDelay());

                if (this.ambientMusic == null && this.timeUntilNextMusic-- <= 0)
                {
             	   this.playMusic(tracktypeB);
                }
          	}
          	
          	else if (this.mc.thePlayer.worldObj.provider instanceof NightmareWorldProvider)
          	{
      			if (this.ambientMusic != null)
                {
                    if (!this.mc.getSoundHandler().isSoundPlaying(this.ambientMusic))
                    {
                        this.ambientMusic = null;
                        this.timeUntilNextMusic = Math.min(MathHelper.getRandomIntegerInRange(this.rand, tracktypeD.getMinDelay(), tracktypeD.getMaxDelay()), this.timeUntilNextMusic);
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
		return TrackType.DREAM;
    }
    
    public GNSMusicTicker.TrackType getRandomTrackNightmare()
    {
		return TrackType.NIGHTMARE;
    }

    public void playMusic(TrackType requestedMusicType)
    {
        this.ambientMusic = PositionedSoundRecord.func_147673_a(requestedMusicType.getMusicLocation());
        this.mc.getSoundHandler().playSound(this.ambientMusic);
        this.timeUntilNextMusic = Integer.MAX_VALUE;
    }

    public void stopMusic()
    {
        if (this.ambientMusic != null)
        {
            this.mc.getSoundHandler().stopSound(this.ambientMusic);
            this.ambientMusic = null;
            this.timeUntilNextMusic = 0;
        }
    }

    @SideOnly(Side.CLIENT)
    public static enum TrackType
    {
    	DREAM(GNSSounds.MUSIC_GOOD_DREAM, 1200, 1500),
    	NIGHTMARE(GNSSounds.MUSIC_NIGHTMARE, 1200, 1500);
    	
        private final ResourceLocation musicLocation;
        private final int minDelay;
        private final int maxDelay;

        private TrackType(ResourceLocation musicLocationIn, int minDelayIn, int maxDelayIn)
        {
            this.musicLocation = musicLocationIn;
            this.minDelay = minDelayIn;
            this.maxDelay = maxDelayIn;
        }

        public ResourceLocation getMusicLocation()
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