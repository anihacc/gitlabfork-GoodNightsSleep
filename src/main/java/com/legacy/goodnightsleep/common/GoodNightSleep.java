package com.legacy.goodnightsleep.common;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.legacy.goodnightsleep.common.blocks.BlocksGNS;
import com.legacy.goodnightsleep.common.entities.GNSEntities;
import com.legacy.goodnightsleep.common.registry.GNSCreativeTabs;

@Mod(name = "Good Night Sleep", modid = GoodNightSleep.modid, version = GoodNightSleep.version, acceptedMinecraftVersions = "1.11.2")
public class GoodNightSleep 
{

	public static final String modid = "goodnightsleep";
	public static final String version = "v1.0.1";

	@Instance(GoodNightSleep.modid)
	public static GoodNightSleep instance;

	@SidedProxy(modId = GoodNightSleep.modid, clientSide = "com.legacy.goodnightsleep.client.ClientProxy", serverSide = "com.legacy.goodnightsleep.common.ServerProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void preInitialization(FMLPreInitializationEvent event)
	{
		//AetherConfig.init(event.getModConfigurationDirectory());
		//AetherConfig.autoDeveloperMode(version);

		//AetherNetworkingManager.preInitialization();

		proxy.preInitialization();
	}

	@EventHandler
	public void initialization(FMLInitializationEvent event)
	{
		//PlayerAetherManager.initialization();
		//SoundsAether.initialization();
		GNSEntities.initialization();
		BlocksGNS.initialization();
		//ItemsAether.initialization();
		//AetherRecipes.initialization();
		//AchievementsAether.initialization();
		//AetherTileEntities.initialization();
		GNSCreativeTabs.initialization();
		//AetherWorld.initialization();

		proxy.initialization();

		//ServerProxy.registerEvent(new AetherEventHandler());
	}

	public static ResourceLocation locate(String location)
	{
		return new ResourceLocation(modid, location);
	}

	public static String modAddress()
	{
		return modid + ":";
	}

}