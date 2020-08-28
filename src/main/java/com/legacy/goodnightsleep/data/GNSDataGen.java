package com.legacy.goodnightsleep.data;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = GoodNightSleep.MODID, bus = Bus.MOD)
public class GNSDataGen
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event)
	{
		DataGenerator gen = event.getGenerator();
		
		gen.addProvider(new GNSBiomeGen(gen));
	}
}
