package com.legacy.goodnightsleep.data;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;

public class GNSBlockTags
{
	public static void init()
	{
	}

	public static final ITag.INamedTag<Block> PLANKS = tag("planks");

	public static final ITag.INamedTag<Block> DREAM_LOGS = tag("logs/dream_logs");
	public static final ITag.INamedTag<Block> WHITE_LOGS = tag("logs/white_logs");
	public static final ITag.INamedTag<Block> DEAD_LOGS = tag("logs/dead_logs");
	public static final ITag.INamedTag<Block> BLOOD_LOGS = tag("logs/blood_logs");

	public static final ITag.INamedTag<Block> COBBLESTONES = tag("cobblestone");
	public static final ITag.INamedTag<Block> STONES = tag("stone");
	public static final ITag.INamedTag<Block> MUSHROOMS = tag("mushrooms");

	public static final ITag.INamedTag<Block> CANDY_ORES = tag("ores/candy");
	public static final ITag.INamedTag<Block> RAINBOW_ORES = tag("ores/rainbow");
	public static final ITag.INamedTag<Block> POSITITE_ORES = tag("ores/positite");
	public static final ITag.INamedTag<Block> NECRUM_ORES = tag("ores/necrum");
	public static final ITag.INamedTag<Block> ZITRITE_ORES = tag("ores/zitrite");
	public static final ITag.INamedTag<Block> NEGATITE_ORES = tag("ores/negatite");

	public static final ITag.INamedTag<Block> CANDY_BLOCKS = tag("storage_blocks/candy");
	public static final ITag.INamedTag<Block> RAINBOW_BLOCKS = tag("storage_blocks/rainbow");
	public static final ITag.INamedTag<Block> POSITITE_BLOCKS = tag("storage_blocks/positite");
	public static final ITag.INamedTag<Block> NECRUM_BLOCKS = tag("storage_blocks/necrum");
	public static final ITag.INamedTag<Block> ZITRITE_BLOCKS = tag("storage_blocks/zitrite");
	public static final ITag.INamedTag<Block> NEGATITE_BLOCKS = tag("storage_blocks/negatite");

	private static ITag.INamedTag<Block> tag(String key)
	{
		return BlockTags.createOptional(GoodNightSleep.locate(key));
	}
}