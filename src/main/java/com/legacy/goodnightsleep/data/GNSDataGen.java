package com.legacy.goodnightsleep.data;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = GoodNightSleep.MODID, bus = Bus.MOD)
public class GNSDataGen
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event)
	{
		DataGenerator gen = event.getGenerator();
		
		//gen.addProvider(new GNSBiomeProv(gen));
		
		BlockTagsProvider blockProv = new GNSTagProv.BlockTagProv(gen, event.getExistingFileHelper());
		gen.addProvider(blockProv);
		gen.addProvider(new GNSTagProv.ItemTagProv(gen, blockProv, event.getExistingFileHelper()));
		gen.addProvider(new GNSRecipeProv(gen));
		gen.addProvider(new GNSLootProv(gen));
		//gen.addProvider(new GNSAdvancementProv(gen));
	}
}
