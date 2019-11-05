
package com.legacy.goodnightsleep.blocks;

import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.blocks.natural.GNSFarmlandBlock;
import com.legacy.goodnightsleep.blocks.natural.GNSFlowerBlock;
import com.legacy.goodnightsleep.blocks.natural.GNSGrassBlock;
import com.legacy.goodnightsleep.blocks.natural.GNSRainbowCropBlock;
import com.legacy.goodnightsleep.blocks.natural.GNSTallGrassBlock;
import com.legacy.goodnightsleep.blocks.tile.GNSBedBlock;
import com.legacy.goodnightsleep.item.sapling.CandyTree;
import com.legacy.goodnightsleep.item.sapling.DreamTree;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.IForgeRegistry;

public class GNSBlocks
{
	public static Block dream_grass_block, dream_dirt, dream_farmland, nightmare_grass_block;

	public static Block tall_dream_grass, lolipop_bush, tall_nightmare_grass, prickly_nightmare_grass;

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

	public static Block delusion_stone, delusion_cobblestone, delusion_stonebrick;
	
	public static Block dream_fence, white_fence, dead_fence, blood_fence;
	
	public static Block dream_slab, white_slab, dead_slab, blood_slab;
	
	public static Block delusion_stone_slab, delusion_cobblestone_slab, delusion_stonebrick_slab;

	public static Block dead_stairs, blood_stairs, white_stairs, dream_stairs;

	public static Block delusion_stone_stairs, delusion_cobblestone_stairs, delusion_stonebrick_stairs;

	public static Block pot_of_gold, present, rainbow;
	
	public static Block luxurious_bed, wretched_bed, strange_bed;
	
	private static IForgeRegistry<Block> iBlockRegistry;

	public static ArrayList<Block> gnsBlockList = Lists.newArrayList();

	@SuppressWarnings("deprecation")
	public static void initialization()
	{		
		if (iBlockRegistry == null)
		{
			return;
		}

		luxurious_bed = registerWithoutItem("luxurious_bed", new GNSBedBlock(Block.Properties.from(Blocks.CYAN_BED)));
		wretched_bed = registerWithoutItem("wretched_bed", new GNSBedBlock(Block.Properties.from(Blocks.GRAY_BED)));
		strange_bed = registerWithoutItem("strange_bed", new GNSBedBlock(Block.Properties.from(Blocks.RED_BED)));

		tall_dream_grass = register("tall_dream_grass", new GNSTallGrassBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT)));
		tall_nightmare_grass = register("tall_nightmare_grass", new GNSTallGrassBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT)));
		prickly_nightmare_grass = register("prickly_nightmare_grass", new GNSTallGrassBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT)));
		dream_grass_block = register("dream_grass", new GNSGrassBlock());
		dream_dirt = register("dream_dirt", new Block(Properties.create(Material.ORGANIC).hardnessAndResistance(0.5F).sound(SoundType.GROUND)));
		dream_farmland = register("dream_farmland", new GNSFarmlandBlock(Properties.from(Blocks.FARMLAND)));
		delusion_stone = register("delusion_stone", new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)));
		delusion_cobblestone = register("delusion_cobblestone", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F)));
		delusion_stonebrick = register("delusion_stonebrick", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F)));
		nightmare_grass_block = register("nightmare_grass", new GNSGrassBlock());
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
		
		stripped_dream_log = register("stripped_dream_log", new LogBlock(MaterialColor.CYAN, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
		stripped_white_log = register("stripped_white_log", new LogBlock(MaterialColor.LIGHT_GRAY, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
		stripped_dead_log = register("stripped_dead_log", new LogBlock(MaterialColor.GRAY, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
		stripped_blood_log = register("stripped_blood_log", new LogBlock(MaterialColor.RED, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
		
		dream_plank = register("dream_plank", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		white_plank = register("white_plank", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		dead_plank = register("dead_plank", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		blood_plank = register("blood_plank", new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

		dream_sapling = register("dream_sapling", new SaplingBlock(new DreamTree(), Block.Properties.create(Material.ROCK).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT)) {});
		candy_sapling = register("candy_sapling", new SaplingBlock(new CandyTree(), Block.Properties.create(Material.ROCK).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT)) {});
		
		hope_mushroom = register("hope_mushroom", new GNSFlowerBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
		despair_mushroom = register("despair_mushroom", new GNSFlowerBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
		orange_flower = register("orange_flower", new GNSFlowerBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
		cyan_flower = register("cyan_flower", new GNSFlowerBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
		lolipop_bush = register("lolipop_bush", new GNSFlowerBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
		dead_flower = register("dead_flower", new GNSFlowerBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));

		rainbow_crop = registerWithoutItem("rainbow_crop", new GNSRainbowCropBlock());

		dream_fence = register("dream_fence", new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		white_fence = register("white_fence", new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		dead_fence = register("dead_fence", new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		blood_fence = register("blood_fence", new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

		dream_stairs = register("dream_stairs", new StairsBlock(GNSBlocks.dream_plank.getDefaultState(), Block.Properties.from(GNSBlocks.dream_plank)) {});
		white_stairs = register("white_stairs", new StairsBlock(GNSBlocks.white_plank.getDefaultState(), Block.Properties.from(GNSBlocks.white_plank)) {});
		dead_stairs = register("dead_stairs", new StairsBlock(GNSBlocks.dead_plank.getDefaultState(), Block.Properties.from(GNSBlocks.dead_plank)) {});
		blood_stairs = register("blood_stairs", new StairsBlock(GNSBlocks.blood_plank.getDefaultState(), Block.Properties.from(GNSBlocks.blood_plank)) {});

		delusion_stone_stairs = register("delusion_stone_stairs", new StairsBlock(GNSBlocks.delusion_stone.getDefaultState(), Block.Properties.from(GNSBlocks.delusion_stone)) {});
		delusion_cobblestone_stairs = register("delusion_cobblestone_stairs", new StairsBlock(GNSBlocks.delusion_cobblestone.getDefaultState(), Block.Properties.from(GNSBlocks.delusion_cobblestone)) {});
		delusion_stonebrick_stairs = register("delusion_stonebrick_stairs", new StairsBlock(GNSBlocks.delusion_stonebrick.getDefaultState(), Block.Properties.from(GNSBlocks.delusion_stonebrick)) {});

		dream_slab = register("dream_slab", new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		white_slab = register("white_slab", new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		dead_slab = register("dead_slab", new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		blood_slab = register("blood_slab", new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		
		delusion_stone_slab = register("delusion_stone_slab", new SlabBlock(Block.Properties.from(GNSBlocks.delusion_stone)));
		delusion_cobblestone_slab = register("delusion_cobblestone_slab", new SlabBlock(Block.Properties.from(GNSBlocks.delusion_cobblestone)));
		delusion_stonebrick_slab = register("delusion_stonebrick_slab", new SlabBlock(Block.Properties.from(GNSBlocks.delusion_stonebrick)));
		
		candy_block = register("candy_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
		rainbow_block = register("rainbow_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL).lightValue(15)));
		positite_block = register("positite_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
		necrum_block = register("necrum_block", new Block(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.GROUND)));
		zitrite_block = register("zitrite_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
		negatite_block = register("negatite_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
		
		present = register("present", new Block(Block.Properties.create(Material.MISCELLANEOUS, MaterialColor.RED).hardnessAndResistance(0.5F, 0.0F).sound(SoundType.PLANT)));
		
		pot_of_gold = register("pot_of_gold", new GoldPotBlock(Block.Properties.from(Blocks.CAULDRON)));
		
		rainbow = register("rainbow", new RainbowBlock(Block.Properties.from(Blocks.NETHER_PORTAL)));
	}

	public static void setBlockRegistry(IForgeRegistry<Block> iBlockRegistry)
	{
		GNSBlocks.iBlockRegistry = iBlockRegistry;
	}
	
	public static Block register(String name, Block block)
	{
		if (iBlockRegistry != null) 
		{
			block.setRegistryName(GoodNightSleep.locate(name));
			gnsBlockList.add(block);
			iBlockRegistry.register(block);
		}

		return block;
	}
	
	public static Block registerWithoutItem(String name, Block block)
	{
		if (iBlockRegistry != null)
		{
			block.setRegistryName(GoodNightSleep.locate(name));
			iBlockRegistry.register(block);
		}

		return block;
	}
}