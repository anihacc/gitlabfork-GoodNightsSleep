package com.legacy.goodnightsleep.registry;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegistryEventHandler 
{	
	@SubscribeEvent
	public void onRegisterSounds(RegistryEvent.Register<SoundEvent> event)
	{	
		GNSSounds.soundRegistry = event.getRegistry();

		GNSSounds.initialization();
	}
}