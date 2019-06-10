package com.legacy.goodnightsleep.client.audio;

import com.legacy.goodnightsleep.GNSConfig;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;

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
					musicTicker.tick();
				}
			}
		}
	}

	@SubscribeEvent
	public void onMusicControl(PlaySoundEvent17 event)
	{
		ISound sound = event.result;
		
		if (sound == null)
		{
			return;
		}

		SoundCategory category = event.category;

		if (category == SoundCategory.MUSIC)
		{
			if (this.mc.thePlayer != null && this.mc.thePlayer.dimension == GNSConfig.getDreamDimensionID() || this.mc.thePlayer != null && this.mc.thePlayer.dimension == GNSConfig.getNightmareDimensionID())
			{
				if (!sound.getPositionedSoundLocation().toString().contains("goodnightsleep") && (this.musicTicker.playingMusic() || !this.musicTicker.playingMusic()))
				{
					event.result = null;

					return;
				}
			}
		}

		else if (category == SoundCategory.RECORDS)
		{
			this.musicTicker.stopMusic();
			this.mc.getSoundHandler().stopSounds();

			this.musicTicker.ambientMusic = null;
			
			this.musicTicker.playingRecord = event.result;
			return;
		}
	}

}