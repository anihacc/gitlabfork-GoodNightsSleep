package com.legacy.goodnightsleep;

import com.legacy.goodnightsleep.entities.GNSEntities;
import com.legacy.goodnightsleep.entities.GNSEntityEvents;
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
		//AetherConfig.autoDeveloperMode(version);
		VariableConstants.registerEvent(new RegistryEventHandler());
		//AetherNetworkingManager.preInitialization();
		VariableConstants.registerEvent(new GNSEntityEvents());
		proxy.preInitialization();
	}

	@EventHandler
	public void initialization(FMLInitializationEvent event)
	{
		EntityRegistry.registerEgg(new ResourceLocation("minecraft:giant"), 1598464, 30652);

		
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

		CommonProxy.registerEvent(new GNSEventHandler());
	}

}