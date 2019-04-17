package com.legacy.goodnightsleep.client.audio;

import com.legacy.goodnightsleep.GNSConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class GNSMusicHandler 
{

	private Minecraft mc = Minecraft.getMinecraft();

	private GNSMusicTicker musicTicker = new GNSMusicTicker(mc);
	
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) throws Exception
	{
		TickEvent.Phase phase = event.phase;
		TickEvent.Type type = event.type;

		if (phase == TickEvent.Phase.END)
		{
			if (type.equals(TickEvent.Type.CLIENT))
			{
				if (!mc.isGamePaused())
				{
					musicTicker.update();
				}
			}
		}
	}

	@SubscribeEvent
	public void onMusicControl(PlaySoundEvent event)
	{
		ISound sound = event.getSound();
		SoundCategory category = sound.getCategory();

		if (category == SoundCategory.MUSIC)
		{
			if (this.mc.player != null && this.mc.player.dimension == GNSConfig.getDreamDimensionID() || this.mc.player != null && this.mc.player.dimension == GNSConfig.getNightmareDimensionID())
			{
				if (!sound.getSoundLocation().toString().contains("goodnightsleep") && (this.musicTicker.playingMusic() || !this.musicTicker.playingMusic()))
				{
					event.setResultSound(null);

					return;
				}
			}
		}

		else if (category == SoundCategory.RECORDS)
		{
			this.musicTicker.stopMusic();
			this.mc.getSoundHandler().stopSounds();

			this.musicTicker.ambientMusic = null;
			
			this.musicTicker.playingRecord = event.getSound();
			return;
		}
	}

}