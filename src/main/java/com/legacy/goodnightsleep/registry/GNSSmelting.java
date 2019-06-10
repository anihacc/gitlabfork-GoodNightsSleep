package com.legacy.goodnightsleep.registry;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.items.ItemsGNS;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class GNSSmelting 
{

	public static void initialization() 
	{
        // Ore recipes
		FurnaceRecipes.smelting().func_151393_a(BlocksGNS.candy_ore, new ItemStack(ItemsGNS.candy_ingot), 0.1F);
		FurnaceRecipes.smelting().func_151393_a(BlocksGNS.rainbow_ore, new ItemStack(ItemsGNS.rainbow_ingot), 0.1F);
		FurnaceRecipes.smelting().func_151393_a(BlocksGNS.positite_ore, new ItemStack(ItemsGNS.positite_gem), 0.2F);
		FurnaceRecipes.smelting().func_151393_a(BlocksGNS.zitrite_ore, new ItemStack(ItemsGNS.zitrite_ingot), 0.1F);
		FurnaceRecipes.smelting().func_151393_a(BlocksGNS.negatite_ore, new ItemStack(ItemsGNS.negatite_gem), 0.2F);
		// Charcoal
		FurnaceRecipes.smelting().func_151393_a(BlocksGNS.dream_log, new ItemStack(Items.coal, 1, 1), 0.1F);
		FurnaceRecipes.smelting().func_151393_a(BlocksGNS.white_log, new ItemStack(Items.coal, 1, 1), 0.1F);
		FurnaceRecipes.smelting().func_151393_a(BlocksGNS.dead_log, new ItemStack(Items.coal, 1, 1), 0.1F);
		FurnaceRecipes.smelting().func_151393_a(BlocksGNS.blood_log, new ItemStack(Items.coal, 1, 1), 0.1F);
	}
}