package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.entities.GNSEntities;
import com.legacy.goodnightsleep.entities.GNSEntityEvents;
import com.legacy.goodnightsleep.registry.GNSSmelting;
import com.legacy.goodnightsleep.registry.RegistryEventHandler;
import com.legacy.goodnightsleep.registry.VariableConstants;
import com.legacy.goodnightsleep.world.GNSWorld;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(name = VariableConstants.NAME, version = VariableConstants.VERSION, modid = VariableConstants.MODID)
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
		VariableConstants.registerEvent(new GNSEntityEvents());
		proxy.preInitialization();
	}

	@EventHandler
	public void initialization(FMLInitializationEvent event)
	{
		EntityRegistry.registerEgg(new ResourceLocation("minecraft:giant"), 1598464, 30652);

		GNSEntities.initialization();
		GNSWorld.initialization();
		GNSSmelting.initialization();

		proxy.initialization();
		CommonProxy.registerEvent(new GNSEventHandler());
	}

}