package com.legacy.goodnightsleep.data;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.world.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.tags.ItemTags;

public class GNSItemTags
{
	public static void init()
	{
	}

	public static final Tag.Named<Item> PLANKS = tag("planks");

	public static final Tag.Named<Item> DREAM_LOGS = tag("dream_logs");
	public static final Tag.Named<Item> WHITE_LOGS = tag("white_logs");
	public static final Tag.Named<Item> DEAD_LOGS = tag("dead_logs");
	public static final Tag.Named<Item> BLOOD_LOGS = tag("blood_logs");

	public static final Tag.Named<Item> COBBLESTONES = tag("cobblestone");
	public static final Tag.Named<Item> STONES = tag("stone");
	public static final Tag.Named<Item> MUSHROOMS = tag("mushrooms");

	public static final Tag.Named<Item> CANDY_ORES = tag("ores/candy");
	public static final Tag.Named<Item> RAINBOW_ORES = tag("ores/rainbow");
	public static final Tag.Named<Item> POSITITE_ORES = tag("ores/positite");
	public static final Tag.Named<Item> NECRUM_ORES = tag("ores/necrum");
	public static final Tag.Named<Item> ZITRITE_ORES = tag("ores/zitrite");
	public static final Tag.Named<Item> NEGATITE_ORES = tag("ores/negatite");

	public static final Tag.Named<Item> CANDY_MATERIALS = tag("materials/candy");
	public static final Tag.Named<Item> RAINBOW_INGOTS = tag("ingots/rainbow");
	public static final Tag.Named<Item> POSITITE = tag("gems/positite");
	public static final Tag.Named<Item> NECRUM = tag("materials/necrum");
	public static final Tag.Named<Item> ZITRITE_INGOTS = tag("ingots/zitrite");
	public static final Tag.Named<Item> NEGATITE = tag("gems/negatite");

	public static final Tag.Named<Item> CANDY_BLOCKS = tag("storage_blocks/candy");
	public static final Tag.Named<Item> RAINBOW_BLOCKS = tag("storage_blocks/rainbow");
	public static final Tag.Named<Item> POSITITE_BLOCKS = tag("storage_blocks/positite");
	public static final Tag.Named<Item> NECRUM_BLOCKS = tag("storage_blocks/necrum");
	public static final Tag.Named<Item> ZITRITE_BLOCKS = tag("storage_blocks/zitrite");
	public static final Tag.Named<Item> NEGATITE_BLOCKS = tag("storage_blocks/negatite");

	private static Tag.Named<Item> tag(String key)
	{
		return ItemTags.createOptional(GoodNightSleep.locate(key));
	}
}