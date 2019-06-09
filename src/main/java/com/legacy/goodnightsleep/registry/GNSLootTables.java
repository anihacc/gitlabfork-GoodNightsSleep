package com.legacy.goodnightsleep.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class GNSLootTables
{
	// Monsters
	public static ResourceLocation tormentor = register("entities/tormentor");
	public static ResourceLocation herobrine = register("entities/herobrine");	

	private static ResourceLocation register(String location)
    {
        return LootTableList.register(VariableConstants.locate(location));
    }
}