package com.legacy.goodnightsleep.client.resource_pack;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.resources.ResourceLocation;

public class GNSResourcePackHandler
{
	/**
	 * Easy registry for resource-packs.
	 */
	public static void init()
	{
		registerResourcePack(GoodNightSleep.locate("legacy_pack"), "assets/" + GoodNightSleep.MODID + "/legacy_pack/", "Good Night's Sleep Legacy");
		/*registerResourcePack(GoodNightSleep.locate("legacy_grass_pack"), "assets/" + GoodNightSleep.MODID + "/legacy_grass_pack/", "Good Night's Sleep Classic Grass");*/
	}

	private static void registerResourcePack(ResourceLocation packId, String subFolder, String packName)
	{
		Minecraft mc = Minecraft.getInstance();
		//mc.getResourcePackRepository().addPackFinder((consumer, factory) -> consumer.accept(Pack.create(packId.toString(), false, () -> new GNSResourcePack(subFolder, packName), factory, Pack.Position.TOP, PackSource.BUILT_IN)));
	}
}
