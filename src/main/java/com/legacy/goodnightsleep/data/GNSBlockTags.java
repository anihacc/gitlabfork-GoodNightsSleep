package com.legacy.goodnightsleep.data;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.world.level.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;

public class GNSBlockTags
{
	public static void init()
	{
	}

	public static final Tag.Named<Block> PLANKS = tag("planks");

	public static final Tag.Named<Block> DREAM_LOGS = tag("logs/dream_logs");
	public static final Tag.Named<Block> WHITE_LOGS = tag("logs/white_logs");
	public static final Tag.Named<Block> DEAD_LOGS = tag("logs/dead_logs");
	public static final Tag.Named<Block> BLOOD_LOGS = tag("logs/blood_logs");

	public static final Tag.Named<Block> COBBLESTONES = tag("cobblestone");
	public static final Tag.Named<Block> STONES = tag("stone");
	public static final Tag.Named<Block> MUSHROOMS = tag("mushrooms");

	public static final Tag.Named<Block> CANDY_ORES = tag("ores/candy");
	public static final Tag.Named<Block> RAINBOW_ORES = tag("ores/rainbow");
	public static final Tag.Named<Block> POSITITE_ORES = tag("ores/positite");
	public static final Tag.Named<Block> NECRUM_ORES = tag("ores/necrum");
	public static final Tag.Named<Block> ZITRITE_ORES = tag("ores/zitrite");
	public static final Tag.Named<Block> NEGATITE_ORES = tag("ores/negatite");

	public static final Tag.Named<Block> CANDY_BLOCKS = tag("storage_blocks/candy");
	public static final Tag.Named<Block> RAINBOW_BLOCKS = tag("storage_blocks/rainbow");
	public static final Tag.Named<Block> POSITITE_BLOCKS = tag("storage_blocks/positite");
	public static final Tag.Named<Block> NECRUM_BLOCKS = tag("storage_blocks/necrum");
	public static final Tag.Named<Block> ZITRITE_BLOCKS = tag("storage_blocks/zitrite");
	public static final Tag.Named<Block> NEGATITE_BLOCKS = tag("storage_blocks/negatite");

	private static Tag.Named<Block> tag(String key)
	{
		return BlockTags.createOptional(GoodNightSleep.locate(key));
	}
}