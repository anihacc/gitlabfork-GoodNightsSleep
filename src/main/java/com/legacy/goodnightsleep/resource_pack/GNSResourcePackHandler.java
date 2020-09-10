package com.legacy.goodnightsleep.resource_pack;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.util.ResourceLocation;

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
		mc.getResourcePackList().addPackFinder((consumer, factory) -> consumer.accept(ResourcePackInfo.createResourcePack(packId.toString(), false, () -> new GNSResourcePack(subFolder, packName), factory, ResourcePackInfo.Priority.TOP, IPackNameDecorator.BUILTIN)));
	}
}
