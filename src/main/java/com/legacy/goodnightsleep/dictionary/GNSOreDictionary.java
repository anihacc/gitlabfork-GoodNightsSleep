package com.legacy.goodnightsleep.dictionary;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.ItemsGNS;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GNSOreDictionary 
{

	private static final int WILDCARD = OreDictionary.WILDCARD_VALUE;

	public static void initialization()
	{
		//tree and wood-related things
		register("logWood", new ItemStack(BlocksGNS.dream_log, 1, WILDCARD));
		register("slabWood", BlocksGNS.dream_slab);
		register("stairWood", BlocksGNS.dream_stairs);
		register("logWood", new ItemStack(BlocksGNS.white_log, 1, WILDCARD));
		register("slabWood", BlocksGNS.white_slab);
		register("stairWood", BlocksGNS.white_stairs);
		register("logWood", new ItemStack(BlocksGNS.dead_log, 1, WILDCARD));
		register("slabWood", BlocksGNS.dead_slab);
		register("stairWood", BlocksGNS.dead_stairs);
		register("logWood", new ItemStack(BlocksGNS.blood_log, 1, WILDCARD));
		register("slabWood", BlocksGNS.blood_slab);
		register("stairWood", BlocksGNS.blood_stairs);
		register("treeLeaves", new ItemStack(BlocksGNS.dream_leaves, 1, WILDCARD));
		register("treeLeaves", new ItemStack(BlocksGNS.diamond_leaves, 1, WILDCARD));
		register("treeLeaves", new ItemStack(BlocksGNS.candy_leaves, 1, WILDCARD));
		
		register("plankWood", BlocksGNS.dream_plank);
		register("plankWood", BlocksGNS.white_plank);
		register("plankWood", BlocksGNS.dead_plank);
		register("plankWood", BlocksGNS.blood_plank);

		//ores
		register("oreCandy", BlocksGNS.candy_ore);
		register("oreRainbow", BlocksGNS.rainbow_ore);
		register("orePositite", BlocksGNS.positite_ore);
		register("oreNecrum", BlocksGNS.necrum_ore);
		register("oreZitrite", BlocksGNS.zitrite_ore);
		register("oreNegatite", BlocksGNS.negatite_ore);

		register("gemPositite", ItemsGNS.positite_gem);
		register("gemPositite", ItemsGNS.negatite_gem);
		
		register("ingotCandy", ItemsGNS.candy_ingot);
		register("ingotRainbow", ItemsGNS.rainbow_ingot);
		register("ingotZitrite", ItemsGNS.zitrite_ingot);
		
        // crops
		register("cropRainbowBerries", ItemsGNS.rainbow_berries);

		//blocks
		register("blockCandy", BlocksGNS.candy_block);
		register("blockRainbow", BlocksGNS.rainbow_block);
		register("blockPositite", BlocksGNS.positite_block);
		register("blockNecrum", BlocksGNS.necrum_block);
		register("blockZitrite", BlocksGNS.zitrite_block);
		register("blockNegatite", BlocksGNS.negatite_block);
	}

	public static void register(String name, Block block)
	{
		OreDictionary.registerOre(name, block);
	}

	public static void register(String name, Item item)
	{
		OreDictionary.registerOre(name, item);
	}

	public static void register(String name, ItemStack stack)
	{
		OreDictionary.registerOre(name, stack);
	}

}