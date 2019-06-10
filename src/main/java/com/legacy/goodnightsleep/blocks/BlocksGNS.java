
package com.legacy.goodnightsleep.blocks;

import com.legacy.goodnightsleep.blocks.construction.BlockGNSFence;
import com.legacy.goodnightsleep.blocks.construction.BlockGNSOreBlock;
import com.legacy.goodnightsleep.blocks.construction.BlockGNSPlank;
import com.legacy.goodnightsleep.blocks.construction.BlockGNSSlab;
import com.legacy.goodnightsleep.blocks.construction.BlockGNSStairs;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSDirt;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSFarmland;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSFlower;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSGrass;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSLeaves;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSLog;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSMushroom;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSRainbowCrop;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSSapling;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSTallGrass;
import com.legacy.goodnightsleep.blocks.natural.ores.BlockGNSOre;
import com.legacy.goodnightsleep.blocks.util.ItemGNSSlab;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;
import com.legacy.goodnightsleep.registry.VariableConstants;
import com.legacy.goodnightsleep.world.genfeatures.WorldGenGNSTree;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlocksGNS
{
	public static Block dream_grass, dream_dirt, dream_farmland, nightmare_grass;

	public static Block tall_dream_grass, tall_nightmare_grass, lolipop_bush;

	public static Block zitrite_ore, candy_ore, rainbow_ore, positite_ore, negatite_ore, necrum_ore;

	public static Block candy_leaves, dream_leaves, diamond_leaves;

	public static Block candy_block, rainbow_block, positite_block, necrum_block, zitrite_block, negatite_block; 

	public static Block dead_plank, blood_plank, white_plank, dream_plank;

	public static Block dream_sapling, candy_sapling;
	
	public static Block orange_flower, cyan_flower, dead_flower, despair_mushroom, hope_mushroom;
	
	public static Block hope_mushroom_cap, despair_mushroom_cap;

	public static Block rainbow_crop;

	public static Block white_log, dream_log, blood_log, dead_log;

	public static Block dream_fence, white_fence, dead_fence, blood_fence;
	
	public static Block dream_double_slab, white_double_slab, dead_double_slab, blood_double_slab;
	
	public static Block dream_slab, white_slab, dead_slab, blood_slab;
	
	public static Block dead_stairs, blood_stairs, white_stairs, dream_stairs;
	
	public static Block pot_of_gold, present, rainbow;
	
	public static Block luxurious_bed, wretched_bed;

	public static void initialization()
	{		
		luxurious_bed = register("luxurious_bed", new BlockGNSBed().setBlockTextureName(VariableConstants.find("luxurious_bed"))).setCreativeTab(null);
		wretched_bed = register("wretched_bed", new BlockGNSBed().setBlockTextureName(VariableConstants.find("wretched_bed"))).setCreativeTab(null);

		tall_dream_grass = register("tall_dream_grass", new BlockGNSTallGrass().setBlockTextureName(VariableConstants.find("tall_dream_grass")));
		tall_nightmare_grass = register("tall_nightmare_grass", new BlockGNSTallGrass().setBlockTextureName(VariableConstants.find("tall_nightmare_grass")));
		dream_grass = register("dream_grass", new BlockGNSGrass());
		dream_dirt = register("dream_dirt", new BlockGNSDirt());
		dream_farmland = register("dream_farmland", new BlockGNSFarmland()).setCreativeTab(null);
		nightmare_grass = register("nightmare_grass", new BlockGNSGrass());
		hope_mushroom_cap = register("hope_mushroom_cap", new BlockGNSMushroom().setBlockTextureName(VariableConstants.find("hope_mushroom_cap")));
		despair_mushroom_cap = register("despair_mushroom_cap", new BlockGNSMushroom().setBlockTextureName(VariableConstants.find("despair_mushroom_cap")));
		
		candy_ore = register("candy_ore", new BlockGNSOre().setBlockTextureName(VariableConstants.find("candy_ore")));
		rainbow_ore = register("rainbow_ore", new BlockGNSOre().setBlockTextureName(VariableConstants.find("rainbow_ore")));
		positite_ore = register("positite_ore", new BlockGNSOre().setBlockTextureName(VariableConstants.find("positite_ore")));
		necrum_ore = register("necrum_ore", new BlockGNSOre().setBlockTextureName(VariableConstants.find("necrum_ore")));
		zitrite_ore = register("zitrite_ore", new BlockGNSOre().setBlockTextureName(VariableConstants.find("zitrite_ore")));
		negatite_ore = register("negatite_ore", new BlockGNSOre().setBlockTextureName(VariableConstants.find("negatite_ore")));
		
		dream_leaves = register("dream_leaves", new BlockGNSLeaves().setBlockTextureName(VariableConstants.find("dream_leaves")));
		candy_leaves = register("candy_leaves", new BlockGNSLeaves().setBlockTextureName(VariableConstants.find("candy_leaves")));
		diamond_leaves = register("diamond_leaves", new BlockGNSLeaves().setBlockTextureName(VariableConstants.find("diamond_leaves")));
		
		dream_log = register("dream_log", new BlockGNSLog().setBlockTextureName(VariableConstants.find("dream_log")));
		white_log = register("white_log", new BlockGNSLog().setBlockTextureName(VariableConstants.find("white_log")));
		dead_log = register("dead_log", new BlockGNSLog().setBlockTextureName(VariableConstants.find("dead_log")));
		blood_log = register("blood_log", new BlockGNSLog().setBlockTextureName(VariableConstants.find("blood_log")));
		
		dream_plank = register("dream_plank", new BlockGNSPlank().setBlockTextureName(VariableConstants.find("dream_plank")));
		white_plank = register("white_plank", new BlockGNSPlank().setBlockTextureName(VariableConstants.find("white_plank")));
		dead_plank = register("dead_plank", new BlockGNSPlank().setBlockTextureName(VariableConstants.find("dead_plank")));
		blood_plank = register("blood_plank", new BlockGNSPlank().setBlockTextureName(VariableConstants.find("blood_plank")));

		dream_sapling = register("dream_sapling", new BlockGNSSapling(new WorldGenGNSTree(false, 6, BlocksGNS.dream_log, 0, BlocksGNS.dream_leaves, 0, false)).setBlockTextureName(VariableConstants.find("dream_sapling")));
		candy_sapling = register("candy_sapling", new BlockGNSSapling(new WorldGenGNSTree(false, 6, BlocksGNS.white_log, 0, BlocksGNS.candy_leaves, 0, false)).setBlockTextureName(VariableConstants.find("candy_sapling")));
		
		hope_mushroom = register("hope_mushroom", new BlockGNSFlower().setBlockTextureName(VariableConstants.find("hope_mushroom")));
		despair_mushroom = register("despair_mushroom", new BlockGNSFlower().setBlockTextureName(VariableConstants.find("despair_mushroom")));
		orange_flower = register("orange_flower", new BlockGNSFlower().setBlockTextureName(VariableConstants.find("orange_flower")));
		cyan_flower = register("cyan_flower", new BlockGNSFlower().setBlockTextureName(VariableConstants.find("cyan_flower")));
		lolipop_bush = register("lolipop_bush", new BlockGNSFlower().setBlockTextureName(VariableConstants.find("lolipop_bush")));
		dead_flower = register("dead_flower", new BlockGNSFlower().setBlockTextureName(VariableConstants.find("dead_flower")));
		
		rainbow_crop = register("rainbow_crop", new BlockGNSRainbowCrop().setBlockTextureName(VariableConstants.find("rainbow_crop"))).setCreativeTab(null);

		dream_fence = register("dream_fence", new BlockGNSFence("dream_plank"));
		white_fence = register("white_fence", new BlockGNSFence("white_plank"));
		dead_fence = register("dead_fence", new BlockGNSFence("dead_plank"));
		blood_fence = register("blood_fence", new BlockGNSFence("blood_plank"));
		
		dream_stairs = register("dream_stairs", new BlockGNSStairs(dream_plank));
		white_stairs = register("white_stairs", new BlockGNSStairs(white_plank));
		dead_stairs = register("dead_stairs", new BlockGNSStairs(dead_plank));
		blood_stairs = register("blood_stairs", new BlockGNSStairs(blood_plank));

		dream_double_slab = register("dream_double_slab", new BlockGNSSlab("dream_double_slab", true, Material.wood).setHardness(2.0F).setResistance(5.0F)).setCreativeTab(null).setBlockTextureName(VariableConstants.find("dream_plank"));
		white_double_slab = register("white_double_slab", new BlockGNSSlab("white_double_slab", true, Material.wood).setHardness(2.0F).setResistance(5.0F)).setCreativeTab(null).setBlockTextureName(VariableConstants.find("white_plank"));
		dead_double_slab = register("dead_double_slab", new BlockGNSSlab("dead_double_slab", true, Material.wood).setHardness(2.0F).setResistance(5.0F)).setCreativeTab(null).setBlockTextureName(VariableConstants.find("dead_plank"));
		blood_double_slab = register("blood_double_slab", new BlockGNSSlab("blood_double_slab", true, Material.wood).setHardness(2.0F).setResistance(5.0F)).setCreativeTab(null).setBlockTextureName(VariableConstants.find("blood_plank"));
				
		dream_slab = registerSlab("dream_slab", new BlockGNSSlab("dream_slab", false, Material.wood).setHardness(2.0F).setResistance(5.0F), dream_double_slab).setBlockTextureName(VariableConstants.find("dream_plank"));
		white_slab = registerSlab("white_slab", new BlockGNSSlab("white_slab", false, Material.wood).setHardness(2.0F).setResistance(5.0F), white_double_slab).setBlockTextureName(VariableConstants.find("white_plank"));
		dead_slab = registerSlab("dead_slab", new BlockGNSSlab("dead_slab", false, Material.wood).setHardness(2.0F).setResistance(5.0F), dead_double_slab).setBlockTextureName(VariableConstants.find("dead_plank"));
		blood_slab = registerSlab("blood_slab", new BlockGNSSlab("blood_slab", false, Material.wood).setHardness(2.0F).setResistance(5.0F), blood_double_slab).setBlockTextureName(VariableConstants.find("blood_plank"));
		
		candy_block = register("candy_block", new BlockGNSOreBlock().setBlockTextureName(VariableConstants.find("candy_block")));
		rainbow_block = register("rainbow_block", new BlockGNSOreBlock().setBlockTextureName(VariableConstants.find("rainbow_block")));
		positite_block = register("positite_block", new BlockGNSOreBlock().setBlockTextureName(VariableConstants.find("positite_block")));
		necrum_block = register("necrum_block", new BlockGNSOreBlock().setBlockTextureName(VariableConstants.find("necrum_block")));
		zitrite_block = register("zitrite_block", new BlockGNSOreBlock().setBlockTextureName(VariableConstants.find("zitrite_block")));
		negatite_block = register("negatite_block", new BlockGNSOreBlock().setBlockTextureName(VariableConstants.find("negatite_block")));
		
		candy_ore.setHarvestLevel("pickaxe", 0);
		rainbow_ore.setHarvestLevel("pickaxe", 1);
		positite_ore.setHarvestLevel("pickaxe", 2);
		necrum_ore.setHarvestLevel("pickaxe", 0);
		zitrite_ore.setHarvestLevel("pickaxe", 1);
		negatite_ore.setHarvestLevel("pickaxe", 2);
	}

	public static Block register(String name, Block block)
	{
		return register(name, block, new ItemBlock(block));
	}
	
	public static Block registerNoTab(String name, Block block)
	{
		return registerNoTab(name, block, new ItemBlock(block));
	}

	public static Block register(String name, Block block, ItemBlock item)
	{
		block.setBlockName(name);

		//block.setRegistryName(VariableConstants.locate(name));
		//item.setRegistryName(VariableConstants.locate(name));

		block.setCreativeTab(GNSCreativeTabs.blocks);
		
		GameRegistry.registerBlock(block, name);
		//GameRegistry.registerItem(new ItemBlock(block).setRegistryName(VariableConstants.locate(name)));

		return block;
	}
	
	public static Block registerNoTab(String name, Block block, ItemBlock item)
	{
		block.setBlockName(name);

		//block.setRegistryName(VariableConstants.locate(name));
		//item.setRegistryName(VariableConstants.locate(name));

		block.setCreativeTab(null);
		
		GameRegistry.registerBlock(block, name);
		//GameRegistry.register(new ItemBlock(block).setRegistryName(VariableConstants.locate(name)));

		return block;
	}
	
	public static Block registerSlab(String name, Block singleSlab, Block doubleSlab)
	{
		singleSlab.setCreativeTab(GNSCreativeTabs.blocks);
		doubleSlab.setCreativeTab(null);

		GameRegistry.registerBlock(singleSlab, ItemGNSSlab.class, name, (BlockGNSSlab) singleSlab, (BlockGNSSlab) doubleSlab, false);		
		//GameRegistry.register(new ItemGNSSlab(singleSlab, (BlockSlab) singleSlab, (BlockSlab) doubleSlab).setRegistryName(VariableConstants.locate(name)));

		return singleSlab;
	}
}