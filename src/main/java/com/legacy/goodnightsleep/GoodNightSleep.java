package com.legacy.goodnightsleep;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("goodnightsleep")
public class GoodNightSleep
{
	public GoodNightSleep()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::initialization);
		//FMLJavaModLoadingContext.get().getModEventBus().addListener(GoodNightSleepClient::initialization);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void initialization(final FMLCommonSetupEvent event)
    {
		//MinecraftForge.EVENT_BUS.register(new GNSEntityEvents());
		//MinecraftForge.EVENT_BUS.register(new GNSPlayerEvents());
    }

	public static ResourceLocation locate(String name)
	{
		return new ResourceLocation("goodnightsleep", name);
	}
}
