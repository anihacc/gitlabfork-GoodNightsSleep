
package com.legacy.goodnightsleep.blocks;

import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.legacy.goodnightsleep.VariableConstants;
import com.legacy.goodnightsleep.blocks.tile.BlockGNSBed;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.IForgeRegistry;

public class BlocksGNS
{
	public static Block dream_grass_block, dream_dirt, dream_farmland, nightmare_grass_block;

	public static Block tall_dream_grass, tall_nightmare_grass, lolipop_bush;

	public static Block zitrite_ore, candy_ore, rainbow_ore, positite_ore, negatite_ore, necrum_ore;
	
	public static Block coal_ore, lapis_ore;
	
	public static Block candy_leaves, dream_leaves, diamond_leaves;

	public static Block candy_block, rainbow_block, positite_block, necrum_block, zitrite_block, negatite_block; 

	public static Block dead_plank, blood_plank, white_plank, dream_plank;

	public static Block dream_sapling, candy_sapling;
	
	public static Block orange_flower, cyan_flower, dead_flower, despair_mushroom, hope_mushroom;
	
	public static Block hope_mushroom_block, despair_mushroom_block;

	public static Block rainbow_crop;

	public static Block white_log, dream_log, blood_log, dead_log;

	public static Block stripped_white_log, stripped_dream_log, stripped_blood_log, stripped_dead_log;

	public static Block delusion_stone, delusion_cobblestone;
	
	public static Block dream_fence, white_fence, dead_fence, blood_fence;
	
	public static Block dream_slab, white_slab, dead_slab, blood_slab;
	
	public static Block dead_stairs, blood_stairs, white_stairs, dream_stairs;
	
	public static Block pot_of_gold, present, rainbow;
	
	public static Block luxurious_bed, wretched_bed, strange_bed;
	
	private static IForgeRegistry<Block> iBlockRegistry;

	public static ArrayList<Block> gnsBlockList = Lists.newArrayList();

	public static void initialization()
	{		
		if (iBlockRegistry == null)
		{
			return;
		}

		luxurious_bed = registerWithoutItem("luxurious_bed", new BlockGNSBed(Block.Properties.from(Blocks.RED_BED)));//BlockGNSBed
		wretched_bed = registerWithoutItem("wretched_bed", new BlockGNSBed(Block.Properties.from(Blocks.RED_BED)));
		strange_bed = registerWithoutItem("strange_bed", new BlockGNSBed(Block.Properties.from(Blocks.RED_BED)));

		tall_dream_grass = register("tall_dream_grass", new BlockGNSTallGrass(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT)));
		tall_nightmare_grass = register("tall_nightmare_grass", new BlockGNSTallGrass(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT)));
		dream_grass_block = register("dream_grass", new BlockGNSGrass());
		dream_dirt = register("dream_dirt", new Block(Properties.create(Material.ORGANIC).hardnessAndResistance(0.5F).sound(SoundType.GROUND)));
		dream_farmland = register("dream_farmland", new BlockGNSFarmland(Properties.from(Blocks.FARMLAND)));
		delusion_stone = register("delusion_stone", new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)));
		delusion_cobblestone = register("delusion_cobblestone", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F)));
		nightmare_grass_block = register("nightmare_grass", new BlockGNSGrass());
		hope_mushroom_block = register("hope_mushroom_block", new HugeMushroomBlock(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(0.2F).sound(SoundType.WOOD)));
		despair_mushroom_block = register("despair_mushroom_block", new HugeMushroomBlock(Block.Properties.create(Material.WOOD, MaterialColor.GRAY).hardnessAndResistance(0.2F).sound(SoundType.WOOD)));
		
		candy_ore = register("candy_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
		rainbow_ore = register("rainbow_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE)));
		positite_ore = register("positite_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
		necrum_ore = register("necrum_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
		zitrite_ore = register("zitrite_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE)));
		negatite_ore = register("negatite_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
		
		coal_ore = register("coal_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)));
		lapis_ore = register("lapis_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)));
		
		dream_leaves = register("dream_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)));
		candy_leaves = register("candy_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)));
		diamond_leaves = register("diamond_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)));
		
		dream_log = register("dream_log", new LogBlock(MaterialColor.CYAN, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
		white_log = register("white_log", new LogBlock(MaterialColor.LIGHT_GRAY, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
		dead_log = register("dead_log", new LogBlock(MaterialColor.GRAY, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
		blood_log = register("blood_log", new LogBlock(MaterialColor.RED, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
		
		//stripped_dream_log = register("stripped_dream_log", new BlockLog(MaterialColor.CYAN, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
		//stripped_white_log = register("stripped_white_log", new BlockLog(MaterialColor.LIGHT_GRAY, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
		//stripped_dead_log = register("stripped_dead_log", new BlockLog(MaterialColor.GRAY, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
		//stripped_blood_log = register("stripped_blood_log", new BlockLog(MaterialColor.RED, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
		
		dream_plank = register("dream_plank", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		white_plank = register("white_plank", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		dead_plank = register("dead_plank", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		blood_plank = register("blood_plank", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

		//dream_sapling = register("dream_sapling", new BlockGNSSapling(new DreamTreeFeature(false), Block.Properties.create(Material.ROCK).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT)));
		//candy_sapling = register("candy_sapling", new BlockGNSSapling(new DreamTreeFeature(false, 5, BlocksGNS.white_log.getDefaultState(), BlocksGNS.candy_leaves.getDefaultState()), new Block.Properties.create(Material.ROCK).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT)));
		
		hope_mushroom = register("hope_mushroom", new BlockGNSFlower(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
		despair_mushroom = register("despair_mushroom", new BlockGNSFlower(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
		orange_flower = register("orange_flower", new BlockGNSFlower(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
		cyan_flower = register("cyan_flower", new BlockGNSFlower(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
		lolipop_bush = register("lolipop_bush", new BlockGNSFlower(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
		dead_flower = register("dead_flower", new BlockGNSFlower(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
		
		rainbow_crop = registerWithoutItem("rainbow_crop", new BlockGNSRainbowCrop());

		dream_fence = register("dream_fence", new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		white_fence = register("white_fence", new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		dead_fence = register("dead_fence", new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		blood_fence = register("blood_fence", new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		
		dream_stairs = register("dream_stairs", new BlockGNSStairs(BlocksGNS.dream_plank.getDefaultState(), Block.Properties.from(BlocksGNS.dream_plank)));
		white_stairs = register("white_stairs", new BlockGNSStairs(BlocksGNS.white_plank.getDefaultState(), Block.Properties.from(BlocksGNS.white_plank)));
		dead_stairs = register("dead_stairs", new BlockGNSStairs(BlocksGNS.dead_plank.getDefaultState(), Block.Properties.from(BlocksGNS.dead_plank)));
		blood_stairs = register("blood_stairs", new BlockGNSStairs(BlocksGNS.blood_plank.getDefaultState(), Block.Properties.from(BlocksGNS.blood_plank)));
		
		dream_slab = register("dream_slab", new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		white_slab = register("white_slab", new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		dead_slab = register("dead_slab", new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		blood_slab = register("blood_slab", new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		
		candy_block = register("candy_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
		rainbow_block = register("rainbow_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
		positite_block = register("positite_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
		necrum_block = register("necrum_block", new Block(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.GROUND)));
		zitrite_block = register("zitrite_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
		negatite_block = register("negatite_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
	}

	public static void setBlockRegistry(IForgeRegistry<Block> iBlockRegistry)
	{
		BlocksGNS.iBlockRegistry = iBlockRegistry;
	}
	
	public static Block register(String name, Block block)
	{
		if (iBlockRegistry != null) 
		{
			block.setRegistryName(VariableConstants.locate(name));
			gnsBlockList.add(block);
			iBlockRegistry.register(block);
		}

		return block;
	}
	
	public static Block registerWithoutItem(String name, Block block)
	{
		if (iBlockRegistry != null) 
		{
			block.setRegistryName(VariableConstants.locate(name));
			iBlockRegistry.register(block);
		}

		return block;
	}
}