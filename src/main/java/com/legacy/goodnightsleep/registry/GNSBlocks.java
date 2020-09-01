
package com.legacy.goodnightsleep.registry;

import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.blocks.GNSFlowerPotBlock;
import com.legacy.goodnightsleep.blocks.GoldPotBlock;
import com.legacy.goodnightsleep.blocks.RainbowBlock;
import com.legacy.goodnightsleep.blocks.natural.GNSFarmlandBlock;
import com.legacy.goodnightsleep.blocks.natural.GNSFlowerBlock;
import com.legacy.goodnightsleep.blocks.natural.GNSGrassBlock;
import com.legacy.goodnightsleep.blocks.natural.GNSMushroomBlock;
import com.legacy.goodnightsleep.blocks.natural.GNSRainbowCropBlock;
import com.legacy.goodnightsleep.blocks.natural.GNSTallGrassBlock;
import com.legacy.goodnightsleep.blocks.tile.GNSBedBlock;
import com.legacy.goodnightsleep.item.sapling.CandyTree;
import com.legacy.goodnightsleep.item.sapling.DreamTree;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.StoneButtonBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class GNSBlocks
{
	public static Block dream_grass_block, dream_dirt, dream_farmland, nightmare_grass_block;

	public static Block dream_grass, lolipop_bush, nightmare_grass, prickly_nightmare_grass;

	public static Block zitrite_ore, candy_ore, rainbow_ore, positite_ore, negatite_ore, necrum_ore;

	public static Block coal_ore, lapis_ore;

	public static Block candy_leaves, dream_leaves, diamond_leaves;

	public static Block candy_block, rainbow_block, positite_block, necrum_block, zitrite_block, negatite_block;

	public static Block dead_planks, blood_planks, white_planks, dream_planks;

	public static Block dream_sapling, candy_sapling;

	public static Block orange_flower, cyan_flower, dead_flower, despair_mushroom, hope_mushroom;

	public static Block hope_mushroom_block, despair_mushroom_block;

	public static Block rainbow_berries;

	public static Block dream_log, white_log, dead_log, blood_log, dream_wood, white_wood, dead_wood, blood_wood;

	public static Block stripped_dream_log, stripped_white_log, stripped_dead_log, stripped_blood_log,
			stripped_dream_wood, stripped_white_wood, stripped_dead_wood, stripped_blood_wood;

	public static Block delusion_stone, delusion_cobblestone, delusion_stonebrick;

	public static Block dream_fence, white_fence, dead_fence, blood_fence;

	public static Block delusion_cobblestone_wall, delusion_stonebrick_wall;

	public static Block dream_fence_gate, white_fence_gate, dead_fence_gate, blood_fence_gate;

	public static Block dream_button, white_button, dead_button, blood_button, delusion_button;

	public static Block dream_pressure_plate, white_pressure_plate, dead_pressure_plate, blood_pressure_plate,
			delusion_pressure_plate;

	public static Block dream_door, white_door, dead_door, blood_door;

	public static Block dream_trapdoor, white_trapdoor, dead_trapdoor, blood_trapdoor;

	public static Block dream_slab, white_slab, dead_slab, blood_slab;

	public static Block delusion_stone_slab, delusion_cobblestone_slab, delusion_stonebrick_slab;

	public static Block dead_stairs, blood_stairs, white_stairs, dream_stairs;

	public static Block delusion_stone_stairs, delusion_cobblestone_stairs, delusion_stonebrick_stairs;

	public static Block pot_of_gold, present, rainbow;

	public static Block luxurious_bed, wretched_bed, strange_bed;

	public static Block potted_dream_sapling, potted_candy_sapling, potted_hope_mushroom, potted_despair_mushroom,
			potted_orange_flower, potted_cyan_flower, potted_dead_flower;

	private static IForgeRegistry<Block> iBlockRegistry;

	public static ArrayList<Block> gnsBlockList = Lists.newArrayList();

	// @formatter:off
	public static void init(RegistryEvent.Register<Block> event)
	{
		iBlockRegistry = event.getRegistry();

		luxurious_bed = registerBlock("luxurious_bed", new GNSBedBlock(Block.Properties.from(Blocks.CYAN_BED)));
		wretched_bed = registerBlock("wretched_bed", new GNSBedBlock(Block.Properties.from(Blocks.GRAY_BED)));
		strange_bed = registerBlock("strange_bed", new GNSBedBlock(Block.Properties.from(Blocks.RED_BED)));

		dream_grass = register("dream_grass", new GNSTallGrassBlock(Block.Properties.from(Blocks.GRASS)));
		nightmare_grass = register("nightmare_grass", new GNSTallGrassBlock(Block.Properties.from(Blocks.GRASS)));
		prickly_nightmare_grass = register("prickly_nightmare_grass", new GNSTallGrassBlock(Block.Properties.from(Blocks.GRASS)));
		dream_grass_block = register("dream_grass_block", new GNSGrassBlock());
		dream_dirt = register("dream_dirt", new Block(Block.Properties.from(Blocks.DIRT).harvestTool(ToolType.SHOVEL)));
		dream_farmland = register("dream_farmland", new GNSFarmlandBlock(Block.Properties.from(Blocks.FARMLAND)));
		delusion_stone = register("delusion_stone", new Block(Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F)));
		delusion_cobblestone = register("delusion_cobblestone", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F)));
		delusion_stonebrick = register("delusion_stonebrick", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F)));
		nightmare_grass_block = register("nightmare_grass_block", new GNSGrassBlock());
		hope_mushroom_block = register("hope_mushroom_block", new HugeMushroomBlock(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(0.2F).sound(SoundType.WOOD)));
		despair_mushroom_block = register("despair_mushroom_block", new HugeMushroomBlock(Block.Properties.create(Material.WOOD, MaterialColor.GRAY).hardnessAndResistance(0.2F).sound(SoundType.WOOD)));

		candy_ore = register("candy_ore", new Block(Block.Properties.from(Blocks.COAL_ORE).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
		rainbow_ore = register("rainbow_ore", new Block(Block.Properties.from(Blocks.IRON_ORE).harvestLevel(1).harvestTool(ToolType.PICKAXE)));
		positite_ore = register("positite_ore", new Block(Block.Properties.from(Blocks.DIAMOND_ORE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));
		necrum_ore = register("necrum_ore", new Block(Block.Properties.from(Blocks.COAL_ORE).harvestLevel(0).harvestTool(ToolType.PICKAXE)));
		zitrite_ore = register("zitrite_ore", new Block(Block.Properties.from(Blocks.IRON_ORE).harvestLevel(1).harvestTool(ToolType.PICKAXE)));
		negatite_ore = register("negatite_ore", new Block(Block.Properties.from(Blocks.DIAMOND_ORE).harvestLevel(2).harvestTool(ToolType.PICKAXE)));

		coal_ore = register("coal_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)));
		lapis_ore = register("lapis_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)));

		dream_leaves = register("dream_leaves", new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
		candy_leaves = register("candy_leaves", new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
		diamond_leaves = register("diamond_leaves", new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)));

		dream_log = register("dream_log", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));
		white_log = register("white_log", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));
		dead_log = register("dead_log", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));
		blood_log = register("blood_log", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));
		
		dream_wood = register("dream_wood", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));
		white_wood = register("white_wood", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));
		dead_wood = register("dead_wood", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));
		blood_wood = register("blood_wood", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));

		stripped_dream_log = register("stripped_dream_log", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));
		stripped_white_log = register("stripped_white_log", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));
		stripped_dead_log = register("stripped_dead_log", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));
		stripped_blood_log = register("stripped_blood_log", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));

		stripped_dream_wood = register("stripped_dream_wood", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));
		stripped_white_wood = register("stripped_white_wood", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));
		stripped_dead_wood = register("stripped_dead_wood", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));
		stripped_blood_wood = register("stripped_blood_wood", new RotatedPillarBlock(Block.Properties.from(Blocks.OAK_LOG)));

		dream_planks = register("dream_planks", new Block(Block.Properties.from(Blocks.OAK_PLANKS)));
		white_planks = register("white_planks", new Block(Block.Properties.from(Blocks.OAK_PLANKS)));
		dead_planks = register("dead_planks", new Block(Block.Properties.from(Blocks.OAK_PLANKS)));
		blood_planks = register("blood_planks", new Block(Block.Properties.from(Blocks.OAK_PLANKS)));

		dream_sapling = register("dream_sapling", new SaplingBlock(new DreamTree(), Block.Properties.from(Blocks.OAK_SAPLING)) {});
		candy_sapling = register("candy_sapling", new SaplingBlock(new CandyTree(), Block.Properties.from(Blocks.OAK_SAPLING)) {});

		hope_mushroom = register("hope_mushroom", new GNSMushroomBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
		despair_mushroom = register("despair_mushroom", new GNSMushroomBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
		orange_flower = register("orange_flower", new GNSFlowerBlock(Block.Properties.from(Blocks.POPPY)));
		cyan_flower = register("cyan_flower", new GNSFlowerBlock(Block.Properties.from(Blocks.POPPY)));
		lolipop_bush = register("lolipop_bush", new GNSFlowerBlock(Block.Properties.from(Blocks.POPPY)));
		dead_flower = register("dead_flower", new GNSFlowerBlock(Block.Properties.from(Blocks.POPPY)));

		rainbow_berries = registerBlock("rainbow_berries", new GNSRainbowCropBlock());

		dream_door = registerBlock("dream_door", new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR)) {});
		white_door = registerBlock("white_door", new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR)) {});
		dead_door = registerBlock("dead_door", new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR)) {});
		blood_door = registerBlock("blood_door", new DoorBlock(Block.Properties.from(Blocks.OAK_DOOR)) {});

		dream_trapdoor = register("dream_trapdoor", new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)) {});
		white_trapdoor = register("white_trapdoor", new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)) {});
		dead_trapdoor = register("dead_trapdoor", new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)) {});
		blood_trapdoor = register("blood_trapdoor", new TrapDoorBlock(Block.Properties.from(Blocks.OAK_TRAPDOOR)) {});
		
		dream_fence = register("dream_fence", new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)));
		white_fence = register("white_fence", new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)));
		dead_fence = register("dead_fence", new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)));
		blood_fence = register("blood_fence", new FenceBlock(Block.Properties.from(Blocks.OAK_FENCE)));

		delusion_cobblestone_wall = register("delusion_cobblestone_wall", new WallBlock(Block.Properties.from(Blocks.COBBLESTONE_WALL)));
		delusion_stonebrick_wall = register("delusion_stonebrick_wall", new WallBlock(Block.Properties.from(Blocks.STONE_BRICK_WALL)));

		dream_fence_gate = register("dream_fence_gate", new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)));
		white_fence_gate = register("white_fence_gate", new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)));
		dead_fence_gate = register("dead_fence_gate", new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)));
		blood_fence_gate = register("blood_fence_gate", new FenceGateBlock(Block.Properties.from(Blocks.OAK_FENCE_GATE)));

		dream_button = register("dream_button", new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)) {});
		white_button = register("white_button", new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)) {});
		dead_button = register("dead_button", new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)) {});
		blood_button = register("blood_button", new WoodButtonBlock(Block.Properties.from(Blocks.OAK_BUTTON)) {});

		delusion_button = register("delusion_button", new StoneButtonBlock(Block.Properties.from(Blocks.STONE_BUTTON)) {});

		dream_pressure_plate = register("dream_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_PRESSURE_PLATE)) {});
		white_pressure_plate = register("white_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_PRESSURE_PLATE)) {});
		dead_pressure_plate = register("dead_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_PRESSURE_PLATE)) {});
		blood_pressure_plate = register("blood_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(Blocks.OAK_PRESSURE_PLATE)) {});

		delusion_pressure_plate = register("delusion_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, Block.Properties.from(Blocks.STONE_PRESSURE_PLATE)) {});

		dream_stairs = register("dream_stairs", new StairsBlock(() -> GNSBlocks.dream_planks.getDefaultState(), Block.Properties.from(GNSBlocks.dream_planks)) {});
		white_stairs = register("white_stairs", new StairsBlock(() -> GNSBlocks.white_planks.getDefaultState(), Block.Properties.from(GNSBlocks.white_planks)) {});
		dead_stairs = register("dead_stairs", new StairsBlock(() -> GNSBlocks.dead_planks.getDefaultState(), Block.Properties.from(GNSBlocks.dead_planks)) {});
		blood_stairs = register("blood_stairs", new StairsBlock(() -> GNSBlocks.blood_planks.getDefaultState(), Block.Properties.from(GNSBlocks.blood_planks)) {});

		delusion_stone_stairs = register("delusion_stone_stairs", new StairsBlock(() -> GNSBlocks.delusion_stone.getDefaultState(), Block.Properties.from(GNSBlocks.delusion_stone)) {});
		delusion_cobblestone_stairs = register("delusion_cobblestone_stairs", new StairsBlock(() -> GNSBlocks.delusion_cobblestone.getDefaultState(), Block.Properties.from(GNSBlocks.delusion_cobblestone)) {});
		delusion_stonebrick_stairs = register("delusion_stonebrick_stairs", new StairsBlock(() -> GNSBlocks.delusion_stonebrick.getDefaultState(), Block.Properties.from(GNSBlocks.delusion_stonebrick)) {});

		dream_slab = register("dream_slab", new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		white_slab = register("white_slab", new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		dead_slab = register("dead_slab", new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
		blood_slab = register("blood_slab", new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

		delusion_stone_slab = register("delusion_stone_slab", new SlabBlock(Block.Properties.from(GNSBlocks.delusion_stone)));
		delusion_cobblestone_slab = register("delusion_cobblestone_slab", new SlabBlock(Block.Properties.from(GNSBlocks.delusion_cobblestone)));
		delusion_stonebrick_slab = register("delusion_stonebrick_slab", new SlabBlock(Block.Properties.from(GNSBlocks.delusion_stonebrick)));

		candy_block = register("candy_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
		rainbow_block = register("rainbow_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL).setLightLevel((state) ->
		{
			return 15;
		})));
		positite_block = register("positite_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
		necrum_block = register("necrum_block", new Block(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.GROUND)));
		zitrite_block = register("zitrite_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
		negatite_block = register("negatite_block", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));

		present = register("present", new Block(Block.Properties.create(Material.MISCELLANEOUS, MaterialColor.RED).hardnessAndResistance(0.5F, 0.0F).sound(SoundType.PLANT)));

		pot_of_gold = register("pot_of_gold", new GoldPotBlock(Block.Properties.from(Blocks.CAULDRON)));
		rainbow = registerBlock("rainbow", new RainbowBlock(Block.Properties.from(Blocks.NETHER_PORTAL)));

		potted_dream_sapling = registerBlock("potted_dream_sapling", new GNSFlowerPotBlock(() -> dream_sapling.delegate.get()));
		potted_candy_sapling = registerBlock("potted_candy_sapling", new GNSFlowerPotBlock(() -> candy_sapling.delegate.get()));
		potted_hope_mushroom = registerBlock("potted_hope_mushroom", new GNSFlowerPotBlock(() -> hope_mushroom.delegate.get()));
		potted_despair_mushroom = registerBlock("potted_despair_mushroom", new GNSFlowerPotBlock(() -> despair_mushroom.delegate.get()));
		potted_orange_flower = registerBlock("potted_orange_flower", new GNSFlowerPotBlock(() -> orange_flower.delegate.get()));
		potted_cyan_flower = registerBlock("potted_cyan_flower", new GNSFlowerPotBlock(() -> cyan_flower.delegate.get()));
		potted_dead_flower = registerBlock("potted_dead_flower", new GNSFlowerPotBlock(() -> dead_flower.delegate.get()));
	}
	// @formatter:on

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

	public static Block registerBlock(String name, Block block)
	{
		if (iBlockRegistry != null)
		{
			block.setRegistryName(GoodNightSleep.locate(name));
			iBlockRegistry.register(block);
		}

		return block;
	}
}