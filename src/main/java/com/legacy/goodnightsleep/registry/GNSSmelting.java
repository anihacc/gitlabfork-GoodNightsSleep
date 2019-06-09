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
		FurnaceRecipes.instance().addSmeltingRecipeForBlock(BlocksGNS.candy_ore, new ItemStack(ItemsGNS.candy_ingot), 0.1F);
		FurnaceRecipes.instance().addSmeltingRecipeForBlock(BlocksGNS.rainbow_ore, new ItemStack(ItemsGNS.rainbow_ingot), 0.1F);
		FurnaceRecipes.instance().addSmeltingRecipeForBlock(BlocksGNS.positite_ore, new ItemStack(ItemsGNS.positite_gem), 0.2F);
		FurnaceRecipes.instance().addSmeltingRecipeForBlock(BlocksGNS.zitrite_ore, new ItemStack(ItemsGNS.zitrite_ingot), 0.1F);
		FurnaceRecipes.instance().addSmeltingRecipeForBlock(BlocksGNS.negatite_ore, new ItemStack(ItemsGNS.negatite_gem), 0.2F);
		// Charcoal
		FurnaceRecipes.instance().addSmeltingRecipeForBlock(BlocksGNS.dream_log, new ItemStack(Items.coal, 1, 1), 0.1F);
		FurnaceRecipes.instance().addSmeltingRecipeForBlock(BlocksGNS.white_log, new ItemStack(Items.coal, 1, 1), 0.1F);
		FurnaceRecipes.instance().addSmeltingRecipeForBlock(BlocksGNS.dead_log, new ItemStack(Items.coal, 1, 1), 0.1F);
		FurnaceRecipes.instance().addSmeltingRecipeForBlock(BlocksGNS.blood_log, new ItemStack(Items.coal, 1, 1), 0.1F);
	}
}