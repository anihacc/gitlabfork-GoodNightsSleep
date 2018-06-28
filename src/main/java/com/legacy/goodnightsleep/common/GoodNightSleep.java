package com.legacy.goodnightsleep.common;

import com.legacy.goodnightsleep.common.entities.GNSEntities;
import com.legacy.goodnightsleep.common.registry.RegistryEventHandler;
import com.legacy.goodnightsleep.common.registry.VariableConstants;
import com.legacy.goodnightsleep.common.world.GNSWorld;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = VariableConstants.NAME, version = VariableConstants.VERSION, modid = VariableConstants.MODID)
public class GoodNightSleep 
{

	@Instance(VariableConstants.MODID)
	public static GoodNightSleep instance;

	@SidedProxy(modId = VariableConstants.MODID, clientSide = VariableConstants.CLIENT_PROXY_LOCATION, serverSide = VariableConstants.COMMON_PROXY_LOCATION)
	public static ServerProxy proxy;

	@EventHandler
	public void preInitialization(FMLPreInitializationEvent event)
	{
		GNSConfig.init(event.getModConfigurationDirectory());
		//AetherConfig.autoDeveloperMode(version);
		VariableConstants.registerEvent(new RegistryEventHandler());
		//AetherNetworkingManager.preInitialization();

		proxy.preInitialization();
	}

	@EventHandler
	public void initialization(FMLInitializationEvent event)
	{
		//PlayerAetherManager.initialization();
		//SoundsAether.initialization();
		GNSEntities.initialization();
		//BlocksGNS.initialization();
		//ItemsGNS.initialization();
		//AetherRecipes.initialization();
		//AchievementsAether.initialization();
		//AetherTileEntities.initialization();
		//GNSCreativeTabs.initialization();
		GNSWorld.initialization();

		proxy.initialization();

		ServerProxy.registerEvent(new GNSEventHandler());
	}

}