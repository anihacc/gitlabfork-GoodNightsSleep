package com.legacy.goodnightsleep.registry;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.dictionary.GNSOreDictionary;
import com.legacy.goodnightsleep.items.ItemsGNS;
import com.legacy.goodnightsleep.world.GNSWorld;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
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

	@SubscribeEvent
	public void onRegisterBlocks(RegistryEvent.Register<Block> event)
	{
		BlocksGNS.setBlockRegistry(event.getRegistry());

		BlocksGNS.initialization();
	}

	@SubscribeEvent
	public void onRegisterItems(RegistryEvent.Register<Item> event)
	{
		BlocksGNS.setItemRegistry(event.getRegistry());
		ItemsGNS.setItemRegistry(event.getRegistry());
		
		BlocksGNS.initialization();
		ItemsGNS.initialization();
	}
	
	@SubscribeEvent
	public void onRegisterBiomes(RegistryEvent.Register<Biome> event)
	{
		event.getRegistry().register(GNSWorld.good_dream_plains.setRegistryName(VariableConstants.locate("good_dream_plains")));
		event.getRegistry().register(GNSWorld.nightmare_hills.setRegistryName(VariableConstants.locate("nightmare_hills")));
	}
	
	@SubscribeEvent
	public void onRegisterCraftingEvent(RegistryEvent.Register<IRecipe> event)
	{
		GNSOreDictionary.initialization();
	}
}