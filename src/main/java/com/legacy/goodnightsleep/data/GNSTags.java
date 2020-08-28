package com.legacy.goodnightsleep.data;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class GNSTags
{
	public static class Blocks
	{
		public static final ITag.INamedTag<Block> DREAM_LOGS = tag("dream_logs");
		public static final ITag.INamedTag<Block> WHITE_LOGS = tag("white_logs");
		public static final ITag.INamedTag<Block> DEAD_LOGS = tag("dead_logs");
		public static final ITag.INamedTag<Block> BLOOD_LOGS = tag("blood_logs");

		public static final ITag.INamedTag<Block> CANDY_ORES = tag("ores/candy");
		public static final ITag.INamedTag<Block> RAINBOW_ORES = tag("ores/rainbow");
		public static final ITag.INamedTag<Block> POSITITE_ORES = tag("ores/positite");
		public static final ITag.INamedTag<Block> NECRUM_ORES = tag("ores/necrum");
		public static final ITag.INamedTag<Block> ZITRITE_ORES = tag("ores/zitrite");
		public static final ITag.INamedTag<Block> NEGATITE_ORES = tag("ores/negatite");

		private static ITag.INamedTag<Block> tag(String name)
		{
			return BlockTags.makeWrapperTag(GoodNightSleep.find(name));
		}
	}

	public static class Items
	{
		public static final ITag.INamedTag<Item> DREAM_LOGS = tag("dream_logs");
		public static final ITag.INamedTag<Item> WHITE_LOGS = tag("white_logs");
		public static final ITag.INamedTag<Item> DEAD_LOGS = tag("dead_logs");
		public static final ITag.INamedTag<Item> BLOOD_LOGS = tag("blood_logs");

		public static final ITag.INamedTag<Item> CANDY_ORES = tag("ores/candy");
		public static final ITag.INamedTag<Item> RAINBOW_ORES = tag("ores/rainbow");
		public static final ITag.INamedTag<Item> POSITITE_ORES = tag("ores/positite");
		public static final ITag.INamedTag<Item> NECRUM_ORES = tag("ores/necrum");
		public static final ITag.INamedTag<Item> ZITRITE_ORES = tag("ores/zitrite");
		public static final ITag.INamedTag<Item> NEGATUTE_ORES = tag("ores/negatite");

		public static final ITag.INamedTag<Item> INGOTS_RAINBOW = tag("ingots/rainbow");
		public static final ITag.INamedTag<Item> POSITITE = tag("gems/positite");
		public static final ITag.INamedTag<Item> INGOTS_ZITRITE = tag("ingots/zitrite");
		public static final ITag.INamedTag<Item> NEGATITE = tag("gems/negatite");

		private static ITag.INamedTag<Item> tag(String name)
		{
			return ItemTags.makeWrapperTag(GoodNightSleep.find(name));
		}
	}
}
