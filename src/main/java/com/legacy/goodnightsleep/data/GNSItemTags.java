package com.legacy.goodnightsleep.data;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class GNSItemTags
{
	public static void init()
	{
	}

	public static final ITag.INamedTag<Item> PLANKS = tag("planks");

	public static final ITag.INamedTag<Item> DREAM_LOGS = tag("dream_logs");
	public static final ITag.INamedTag<Item> WHITE_LOGS = tag("white_logs");
	public static final ITag.INamedTag<Item> DEAD_LOGS = tag("dead_logs");
	public static final ITag.INamedTag<Item> BLOOD_LOGS = tag("blood_logs");

	public static final ITag.INamedTag<Item> COBBLESTONES = tag("cobblestone");
	public static final ITag.INamedTag<Item> STONES = tag("stone");
	public static final ITag.INamedTag<Item> MUSHROOMS = tag("mushrooms");

	public static final ITag.INamedTag<Item> CANDY_ORES = tag("ores/candy");
	public static final ITag.INamedTag<Item> RAINBOW_ORES = tag("ores/rainbow");
	public static final ITag.INamedTag<Item> POSITITE_ORES = tag("ores/positite");
	public static final ITag.INamedTag<Item> NECRUM_ORES = tag("ores/necrum");
	public static final ITag.INamedTag<Item> ZITRITE_ORES = tag("ores/zitrite");
	public static final ITag.INamedTag<Item> NEGATITE_ORES = tag("ores/negatite");

	public static final ITag.INamedTag<Item> CANDY_MATERIALS = tag("materials/candy");
	public static final ITag.INamedTag<Item> RAINBOW_INGOTS = tag("ingots/rainbow");
	public static final ITag.INamedTag<Item> POSITITE = tag("gems/positite");
	public static final ITag.INamedTag<Item> ZITRITE_INGOTS = tag("materials/necrum");
	public static final ITag.INamedTag<Item> NECRUM = tag("ingots/zitrite");
	public static final ITag.INamedTag<Item> NEGATITE = tag("gems/negatite");

	public static final ITag.INamedTag<Item> CANDY_BLOCKS = tag("storage_blocks/candy");
	public static final ITag.INamedTag<Item> RAINBOW_BLOCKS = tag("storage_blocks/rainbow");
	public static final ITag.INamedTag<Item> POSITITE_BLOCKS = tag("storage_blocks/positite");
	public static final ITag.INamedTag<Item> NECRUM_BLOCKS = tag("storage_blocks/necrum");
	public static final ITag.INamedTag<Item> ZITRITE_BLOCKS = tag("storage_blocks/zitrite");
	public static final ITag.INamedTag<Item> NEGATITE_BLOCKS = tag("storage_blocks/negatite");

	private static ITag.INamedTag<Item> tag(String key)
	{
		return ItemTags.createOptional(GoodNightSleep.locate(key));
	}
}