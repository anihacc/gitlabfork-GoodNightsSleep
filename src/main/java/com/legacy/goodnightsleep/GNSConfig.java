package com.legacy.goodnightsleep;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = GoodNightSleep.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GNSConfig
{
	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final ForgeConfigSpec SERVER_SPEC;
	public static final ClientConfig CLIENT;
	public static final ServerConfig SERVER;

	public static boolean disableTimePassing;

	static
	{
		{
			final Pair<ClientConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
			CLIENT = pair.getLeft();
			CLIENT_SPEC = pair.getRight();
		}
		{
			final Pair<ServerConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
			SERVER = pair.getLeft();
			SERVER_SPEC = pair.getRight();
		}
	}

	private static String translate(String key)
	{
		return new String(GoodNightSleep.MODID + ".config." + key + ".name");
	}

	@SubscribeEvent
	public static void onLoadConfig(final ModConfig.ModConfigEvent event)
	{
		ModConfig config = event.getConfig();

		if (config.getSpec() == CLIENT_SPEC)
		{
			ConfigBakery.bakeClient(config);
		}
		else if (config.getSpec() == SERVER_SPEC)
		{
			ConfigBakery.bakeServer(config);
		}
	}

	private static class ClientConfig
	{
		public ClientConfig(ForgeConfigSpec.Builder builder)
		{
			builder.comment("Client side changes.").push("client");
			builder.pop();
		}
	}

	private static class ServerConfig
	{
		public final ForgeConfigSpec.ConfigValue<Boolean> disableTimePassing;

		public ServerConfig(ForgeConfigSpec.Builder builder)
		{
			builder.comment("Server and Client side changes.").push("common");
			disableTimePassing = builder.translation(translate("disableTimePassing")).comment("Disabling this will prevent time from passing in the dream dimensions, this locks it at noon/midnight. Highly recommended for servers.").define("disableTimePassing", false);
			builder.pop();
		}
	}

	@SuppressWarnings("unused")
	private static class ConfigBakery
	{
		private static ModConfig clientConfig;
		private static ModConfig serverConfig;

		public static void bakeClient(ModConfig config)
		{
			clientConfig = config;
		}

		public static void bakeServer(ModConfig config)
		{
			serverConfig = config;
			disableTimePassing = SERVER.disableTimePassing.get();
		}
	}
}
