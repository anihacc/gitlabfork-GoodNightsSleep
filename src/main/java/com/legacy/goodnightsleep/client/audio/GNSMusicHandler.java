package com.legacy.goodnightsleep.client.audio;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.registry.GNSDimensions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GNSMusicHandler
{

	private Minecraft mc = Minecraft.getInstance();

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
				if (!mc.isPaused())
				{
					musicTicker.tick();
				}
			}
		}
	}

	@SubscribeEvent
	public void onMusicControl(PlaySoundEvent event)
	{
		ISound sound = event.getSound();
		SoundCategory category = sound.getSource();

		if (category == SoundCategory.MUSIC)
		{
			if (this.mc.player != null && this.mc.player.level.dimension().location() == GNSDimensions.getDimensionLocations(true) || this.mc.player != null && this.mc.player.level.dimension().location() == GNSDimensions.getDimensionLocations(false))
			{
				if (!sound.getLocation().toString().contains(GoodNightSleep.MODID) && (this.musicTicker.playingMusic() || !this.musicTicker.playingMusic()))
				{
					event.setResultSound(null);

					return;
				}
			}
		}

		/*else if (category == SoundCategory.RECORDS)
		{
			this.musicTicker.stopMusic();
			this.mc.getSoundHandler().stop();
		
			this.musicTicker.ambientMusic = null;
			
			this.musicTicker.playingRecord = event.getSound();
			return;
		}*/
	}

}