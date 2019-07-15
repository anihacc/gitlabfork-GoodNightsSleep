package com.legacy.goodnightsleep.client.audio;

import com.legacy.goodnightsleep.VariableConstants;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class GNSSounds 
{
	// Blocks
	public static SoundEvent MUSIC_GOOD_DREAM;
	public static SoundEvent MUSIC_NIGHTMARE;

    public static IForgeRegistry<SoundEvent> soundRegistry;
    
	public static void initialization()
	{
		MUSIC_GOOD_DREAM = register("music.good_dream");
		MUSIC_NIGHTMARE = register("music.tfarcenim");
	}

	private static SoundEvent register(String name)
	{
		ResourceLocation location = VariableConstants.locate(name);

		SoundEvent sound = new SoundEvent(location);

		sound.setRegistryName(location);
		
		if (soundRegistry != null)
		soundRegistry.register(sound);

		return sound;
	}

}