package com.legacy.goodnightsleep.registry;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.ItemsGNS;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GNSRecipes
{
	
	public static void initializeShapelessRecipes()
	{
		registerShapeless(new ItemStack(Items.DYE, 2, 6), BlocksGNS.cyan_flower);
		registerShapeless(new ItemStack(Items.DYE, 2, 14), BlocksGNS.orange_flower);
		registerShapeless(new ItemStack(Items.DYE, 2, 8), BlocksGNS.dead_flower);
		
		registerShapeless(new ItemStack(ItemsGNS.powdered_sugar, 1), ItemsGNS.candy, Items.SUGAR);
		registerShapeless(new ItemStack(ItemsGNS.necrotic_extract, 1), ItemsGNS.necrum);
		
		registerShapeless(new ItemStack(ItemsGNS.luxurious_bed_item, 1), ItemsGNS.positite_gem, Items.BED);
		registerShapeless(new ItemStack(ItemsGNS.wretched_bed_item, 1), ItemsGNS.negatite_gem, Items.BED);
		
		registerShapeless(new ItemStack(ItemsGNS.luxurious_soup, 1), Items.BOWL, ItemsGNS.powdered_sugar, BlocksGNS.hope_mushroom);
		registerShapeless(new ItemStack(ItemsGNS.wretched_soup, 1), Items.BOWL, ItemsGNS.necrotic_extract, BlocksGNS.despair_mushroom);
	}

	public static void initializeRecipes()
	{
		register(new ItemStack(BlocksGNS.dream_plank, 4), "X", 'X', BlocksGNS.dream_log);
		register(new ItemStack(BlocksGNS.white_plank, 4), "X", 'X', BlocksGNS.white_log);
		register(new ItemStack(BlocksGNS.dead_plank, 4), "X", 'X', BlocksGNS.dead_log);
		register(new ItemStack(BlocksGNS.blood_plank, 4), "X", 'X', BlocksGNS.blood_log);
		
		register(new ItemStack(Blocks.CHEST, 1), "XXX", "X X", "XXX", 'X', BlocksGNS.dream_plank);
		register(new ItemStack(Blocks.CHEST, 1), "XXX", "X X", "XXX", 'X', BlocksGNS.white_plank);
		register(new ItemStack(Blocks.CHEST, 1), "XXX", "X X", "XXX", 'X', BlocksGNS.dead_plank);
		register(new ItemStack(Blocks.CHEST, 1), "XXX", "X X", "XXX", 'X', BlocksGNS.blood_plank);

		register(new ItemStack(Items.STICK, 4), "X", "X", 'X', BlocksGNS.dream_plank);
		register(new ItemStack(Items.STICK, 4), "X", "X", 'X', BlocksGNS.white_plank);
		register(new ItemStack(Items.STICK, 4), "X", "X", 'X', BlocksGNS.dead_plank);
		register(new ItemStack(Items.STICK, 4), "X", "X", 'X', BlocksGNS.blood_plank);
		
		
		register(new ItemStack(BlocksGNS.candy_block), "XX", "XX", 'X', ItemsGNS.candy);
		register(new ItemStack(BlocksGNS.rainbow_block), "XXX", "XXX", "XXX", 'X', ItemsGNS.rainbow_ingot);
		register(new ItemStack(BlocksGNS.positite_block), "XXX", "XXX", "XXX", 'X', ItemsGNS.positite_gem);
		register(new ItemStack(BlocksGNS.necrum_block), "XX", "XX", 'X', ItemsGNS.necrum);
		register(new ItemStack(BlocksGNS.zitrite_block), "XXX", "XXX", "XXX", 'X', ItemsGNS.zitrite_ingot);
		register(new ItemStack(BlocksGNS.negatite_block), "XXX", "XXX", "XXX", 'X', ItemsGNS.negatite_gem);
		
		register(new ItemStack(ItemsGNS.candy, 4), "X", 'X', BlocksGNS.candy_block);
		register(new ItemStack(ItemsGNS.rainbow_ingot, 9), "X", 'X', BlocksGNS.rainbow_block);
		register(new ItemStack(ItemsGNS.positite_gem, 9), "X", 'X', BlocksGNS.positite_block);
		register(new ItemStack(ItemsGNS.necrum, 4), "X", 'X', BlocksGNS.necrum_block);
		register(new ItemStack(ItemsGNS.zitrite_ingot, 9), "X", 'X', BlocksGNS.zitrite_block);
		register(new ItemStack(ItemsGNS.negatite_gem, 9), "X", 'X', BlocksGNS.negatite_block);
		
		//register(new ItemStack(Blocks.JUKEBOX), "XXX", "XYX", "XXX", 'X', BlocksAether.skyroot_plank, 'Y', BlocksAether.enchanted_gravitite);

		register(new ItemStack(ItemsGNS.candy_helmet, 1), "XXX", "X X", 'X', BlocksGNS.candy_block);
		register(new ItemStack(ItemsGNS.candy_chestplate, 1), "X X", "XXX", "XXX", 'X', BlocksGNS.candy_block);
		register(new ItemStack(ItemsGNS.candy_leggings, 1), "XXX", "X X", "X X", 'X', BlocksGNS.candy_block);
		register(new ItemStack(ItemsGNS.candy_boots, 1), "X X", "X X", 'X', BlocksGNS.candy_block);
		register(new ItemStack(ItemsGNS.rainbow_helmet, 1), "XXX", "X X", 'X', ItemsGNS.rainbow_ingot);
		register(new ItemStack(ItemsGNS.rainbow_chestplate, 1), "X X", "XXX", "XXX", 'X', ItemsGNS.rainbow_ingot);
		register(new ItemStack(ItemsGNS.rainbow_leggings, 1), "XXX", "X X", "X X", 'X', ItemsGNS.rainbow_ingot);
		register(new ItemStack(ItemsGNS.rainbow_boots, 1), "X X", "X X", 'X', ItemsGNS.rainbow_ingot);
		
		register(new ItemStack(ItemsGNS.positite_helmet, 1), "XXX", "X X", 'X', ItemsGNS.positite_gem);
		register(new ItemStack(ItemsGNS.positite_chestplate, 1), "X X", "XXX", "XXX", 'X', ItemsGNS.positite_gem);
		register(new ItemStack(ItemsGNS.positite_leggings, 1), "XXX", "X X", "X X", 'X', ItemsGNS.positite_gem);
		register(new ItemStack(ItemsGNS.positite_boots, 1), "X X", "X X", 'X', ItemsGNS.positite_gem);
		
		register(new ItemStack(ItemsGNS.negatite_helmet, 1), "XXX", "X X", 'X', ItemsGNS.negatite_gem);
		register(new ItemStack(ItemsGNS.negatite_chestplate, 1), "X X", "XXX", "XXX", 'X', ItemsGNS.negatite_gem);
		register(new ItemStack(ItemsGNS.negatite_leggings, 1), "XXX", "X X", "X X", 'X', ItemsGNS.negatite_gem);
		register(new ItemStack(ItemsGNS.negatite_boots, 1), "X X", "X X", 'X', ItemsGNS.negatite_gem);

		register(new ItemStack(ItemsGNS.candy_pickaxe, 1), "ZZZ", " Y ", " Y ", 'Z', BlocksGNS.candy_block, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.candy_axe, 1), "ZZ", "ZY", " Y", 'Z', BlocksGNS.candy_block, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.candy_hoe, 1), "ZZ", " Y", " Y", 'Z', BlocksGNS.candy_block, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.candy_shovel, 1), "Z", "Y", "Y", 'Z', BlocksGNS.candy_block, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.candy_sword, 1), "Z", "Z", "Y", 'Z', BlocksGNS.candy_block, 'Y', Items.STICK);		
		register(new ItemStack(ItemsGNS.rainbow_pickaxe, 1), "ZZZ", " Y ", " Y ", 'Z', ItemsGNS.rainbow_ingot, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.rainbow_axe, 1), "ZZ", "ZY", " Y", 'Z', ItemsGNS.rainbow_ingot, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.rainbow_hoe, 1), "ZZ", " Y", " Y", 'Z', ItemsGNS.rainbow_ingot, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.rainbow_shovel, 1), "Z", "Y", "Y", 'Z', ItemsGNS.rainbow_ingot, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.rainbow_sword, 1), "Z", "Z", "Y", 'Z', ItemsGNS.rainbow_ingot, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.positite_pickaxe, 1), "ZZZ", " Y ", " Y ", 'Z', ItemsGNS.positite_gem, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.positite_axe, 1), "ZZ", "ZY", " Y", 'Z', ItemsGNS.positite_gem, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.positite_hoe, 1), "ZZ", " Y", " Y", 'Z', ItemsGNS.positite_gem, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.positite_shovel, 1), "Z", "Y", "Y", 'Z', ItemsGNS.positite_gem, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.positite_sword, 1), "Z", "Z", "Y", 'Z', ItemsGNS.positite_gem, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.zitrite_pickaxe, 1), "ZZZ", " Y ", " Y ", 'Z', ItemsGNS.zitrite_ingot, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.zitrite_axe, 1), "ZZ", "ZY", " Y", 'Z', ItemsGNS.zitrite_ingot, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.zitrite_hoe, 1), "ZZ", " Y", " Y", 'Z', ItemsGNS.zitrite_ingot, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.zitrite_shovel, 1), "Z", "Y", "Y", 'Z', ItemsGNS.zitrite_ingot, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.zitrite_sword, 1), "Z", "Z", "Y", 'Z', ItemsGNS.zitrite_ingot, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.negatite_pickaxe, 1), "ZZZ", " Y ", " Y ", 'Z', ItemsGNS.negatite_gem, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.negatite_axe, 1), "ZZ", "ZY", " Y", 'Z', ItemsGNS.negatite_gem, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.negatite_hoe, 1), "ZZ", " Y", " Y", 'Z', ItemsGNS.negatite_gem, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.negatite_shovel, 1), "Z", "Y", "Y", 'Z', ItemsGNS.negatite_gem, 'Y', Items.STICK);
		register(new ItemStack(ItemsGNS.negatite_sword, 1), "Z", "Z", "Y", 'Z', ItemsGNS.negatite_gem, 'Y', Items.STICK);

		register(new ItemStack(BlocksGNS.dream_fence, 3), "ZXZ", "ZXZ", 'Z', new ItemStack(BlocksGNS.dream_plank), 'X', new ItemStack(Items.STICK));
		//register(new ItemStack(BlocksGNS.dream_fence_gate), "ZXZ", "ZXZ", 'X', new ItemStack(BlocksGNS.dream_plank), 'Z', new ItemStack(Items.STICK));
		register(new ItemStack(BlocksGNS.white_fence, 3), "ZXZ", "ZXZ", 'Z', new ItemStack(BlocksGNS.white_plank), 'X', new ItemStack(Items.STICK));
		//register(new ItemStack(BlocksGNS.white_fence_gate), "ZXZ", "ZXZ", 'X', new ItemStack(BlocksGNS.white_plank), 'Z', new ItemStack(Items.STICK));
		register(new ItemStack(BlocksGNS.dead_fence, 3), "ZXZ", "ZXZ", 'Z', new ItemStack(BlocksGNS.dead_plank), 'X', new ItemStack(Items.STICK));
		//register(new ItemStack(BlocksGNS.dead_fence_gate), "ZXZ", "ZXZ", 'X', new ItemStack(BlocksGNS.dead_plank), 'Z', new ItemStack(Items.STICK));
		register(new ItemStack(BlocksGNS.blood_fence, 3), "ZXZ", "ZXZ", 'Z', new ItemStack(BlocksGNS.blood_plank), 'X', new ItemStack(Items.STICK));
		//register(new ItemStack(BlocksGNS.blood_fence_gate), "ZXZ", "ZXZ", 'X', new ItemStack(BlocksGNS.blood_plank), 'Z', new ItemStack(Items.STICK));
		
		register(new ItemStack(BlocksGNS.dream_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksGNS.dream_plank));
		register(new ItemStack(BlocksGNS.white_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksGNS.white_plank));
		register(new ItemStack(BlocksGNS.dead_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksGNS.dead_plank));
		register(new ItemStack(BlocksGNS.blood_slab, 6), "ZZZ", 'Z', new ItemStack(BlocksGNS.blood_plank));
		
		register(new ItemStack(BlocksGNS.dream_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksGNS.dream_plank));
		register(new ItemStack(BlocksGNS.white_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksGNS.white_plank));
		register(new ItemStack(BlocksGNS.dead_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksGNS.dead_plank));
		register(new ItemStack(BlocksGNS.blood_stairs, 4), "Z  ", "ZZ ", "ZZZ", 'Z', new ItemStack(BlocksGNS.blood_plank));
	}

	public static void register(ItemStack stack, Object... recipe)
	{
		GameRegistry.addRecipe(stack, recipe);
	}

	public static void registerShapeless(ItemStack stack, Object... recipe)
	{
		GameRegistry.addShapelessRecipe(stack, recipe);
	}

}
