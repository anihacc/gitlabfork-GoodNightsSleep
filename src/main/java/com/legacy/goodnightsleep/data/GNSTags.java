package com.legacy.goodnightsleep.data;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;

public class GNSTags
{
	public static class Blocks
	{
		public static final Tag<Block> DREAM_LOGS = tag("dream_logs");
		public static final Tag<Block> WHITE_LOGS = tag("white_logs");
		public static final Tag<Block> DEAD_LOGS = tag("dead_logs");
		public static final Tag<Block> BLOOD_LOGS = tag("blood_logs");

		public static final Tag<Block> CANDY_ORES = tag("ores/candy");
		public static final Tag<Block> RAINBOW_ORES = tag("ores/rainbow");
		public static final Tag<Block> POSITITE_ORES = tag("ores/positite");
		public static final Tag<Block> NECRUM_ORES = tag("ores/necrum");
		public static final Tag<Block> ZITRITE_ORES = tag("ores/zitrite");
		public static final Tag<Block> NEGATITE_ORES = tag("ores/negatite");

		private static Tag<Block> tag(String name)
		{
			return new BlockTags.Wrapper(GoodNightSleep.locate(name));
		}
	}

	public static class Items
	{
		public static final Tag<Item> DREAM_LOGS = tag("dream_logs");
		public static final Tag<Item> WHITE_LOGS = tag("white_logs");
		public static final Tag<Item> DEAD_LOGS = tag("dead_logs");
		public static final Tag<Item> BLOOD_LOGS = tag("blood_logs");

		public static final Tag<Item> CANDY_ORES = tag("ores/candy");
		public static final Tag<Item> RAINBOW_ORES = tag("ores/rainbow");
		public static final Tag<Item> POSITITE_ORES = tag("ores/positite");
		public static final Tag<Item> NECRUM_ORES = tag("ores/necrum");
		public static final Tag<Item> ZITRITE_ORES = tag("ores/zitrite");
		public static final Tag<Item> NEGATUTE_ORES = tag("ores/negatite");

		public static final Tag<Item> INGOTS_RAINBOW = tag("ingots/rainbow");
		public static final Tag<Item> POSITITE = tag("gems/positite");
		public static final Tag<Item> INGOTS_ZITRITE = tag("ingots/zitrite");
		public static final Tag<Item> NEGATITE = tag("gems/negatite");

		private static Tag<Item> tag(String name)
		{
			return new ItemTags.Wrapper(GoodNightSleep.locate(name));
		}
	}
}
