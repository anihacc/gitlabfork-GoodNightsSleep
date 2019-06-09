package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.dictionary.GNSOreDictionary;
import com.legacy.goodnightsleep.entities.GNSEntities;
import com.legacy.goodnightsleep.entities.GNSEntityEvents;
import com.legacy.goodnightsleep.entities.nightmare.EntityGiant;
import com.legacy.goodnightsleep.items.ItemsGNS;
import com.legacy.goodnightsleep.registry.GNSRecipes;
import com.legacy.goodnightsleep.registry.GNSSmelting;
import com.legacy.goodnightsleep.registry.GNSSounds;
import com.legacy.goodnightsleep.registry.RegistryEventHandler;
import com.legacy.goodnightsleep.registry.VariableConstants;
import com.legacy.goodnightsleep.world.GNSOverworldWorldEvent;
import com.legacy.goodnightsleep.world.GNSWorld;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(name = VariableConstants.NAME, version = VariableConstants.VERSION, modid = VariableConstants.MODID, updateJSON = "https://gist.githubusercontent.com/Lachney/30ab83d5fe6bd534880ba6efb8f958a3/raw/good-nights-sleep-changelog.json")
public class GoodNightSleep 
{

	@Instance(VariableConstants.MODID)
	public static GoodNightSleep instance;

	@SidedProxy(modId = VariableConstants.MODID, clientSide = VariableConstants.CLIENT_PROXY_LOCATION, serverSide = VariableConstants.COMMON_PROXY_LOCATION)
	public static CommonProxy proxy;

	@EventHandler
	public void preInitialization(FMLPreInitializationEvent event)
	{
		GNSConfig.init(event.getModConfigurationDirectory());
		VariableConstants.registerEvent(new RegistryEventHandler());
		GNSSounds.initialization();
		BlocksGNS.initialization();
		ItemsGNS.initialization();
		VariableConstants.registerEvent(new GNSEntityEvents());
		proxy.preInitialization();
	}

	@EventHandler
	public void initialization(FMLInitializationEvent event)
	{
		EntityRegistry.registerEgg(EntityGiant.class, 1598464, 30652);

		GNSRecipes.initializeRecipes();
		GNSRecipes.initializeShapelessRecipes();
		GNSOreDictionary.initialization();
		GNSEntities.initialization();
		GNSWorld.initialization();
		GNSSmelting.initialization();
		
		proxy.initialization();
		CommonProxy.registerEvent(new GNSOverworldWorldEvent());
		CommonProxy.registerEvent(new GNSEventHandler());
	}

}