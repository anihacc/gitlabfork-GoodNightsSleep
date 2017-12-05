
package com.legacy.goodnightsleep.common.blocks;

import com.legacy.goodnightsleep.common.blocks.depricated.BlockAetherPortal;
import com.legacy.goodnightsleep.common.GoodNightSleep;
import com.legacy.goodnightsleep.common.blocks.construction.BlockGNSPlank;
import com.legacy.goodnightsleep.common.blocks.natural.BlockGNSDirt;
import com.legacy.goodnightsleep.common.blocks.natural.BlockGNSFlower;
import com.legacy.goodnightsleep.common.blocks.natural.BlockGNSGrass;
import com.legacy.goodnightsleep.common.blocks.natural.BlockGNSLeaves;
import com.legacy.goodnightsleep.common.blocks.natural.BlockGNSLog;
import com.legacy.goodnightsleep.common.blocks.natural.BlockGNSMushroom;
import com.legacy.goodnightsleep.common.blocks.natural.BlockGNSTallGrass;
import com.legacy.goodnightsleep.common.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlocksGNS
{

	public static Block dream_grass, dream_dirt, nightmare_grass;

	public static Block tall_dream_grass, tall_nightmare_grass, lolipop_bush;

	public static Block zitrite_ore, candy_ore, rainbow_ore, positite_ore, negatite_ore, necrum_ore;

	public static Block candy_leaves, dream_leaves, diamond_leaves;

	public static Block candy_block, rainbow_block, negatite_block, zitrite_block, necrum_block;

	public static Block dead_plank, blood_plank, white_plank, dream_plank;

	public static Block nightmare_test_portal, dream_test_portal;

	public static Block orange_flower, cyan_flower, dead_flower, despair_mushroom, hope_mushroom;
	
	public static Block hope_mushroom_cap, despair_mushroom_cap;

	//public static Block skyroot_sapling, golden_oak_sapling;

	//public static Block rainbow_berries;

	public static Block white_log, dream_log, blood_log, dead_log;

	//public static Block dream_fence, white_fence, dead_fence, blood_fence;

	//public static Block carved_stairs, angelic_stairs, hellfire_stairs, skyroot_stairs, mossy_holystone_stairs, holystone_stairs, holystone_brick_stairs, aerogel_stairs;

	//public static Block carved_slab, angelic_slab, hellfire_slab, skyroot_slab, holystone_slab, holystone_brick_slab, mossy_holystone_slab, aerogel_slab;

	//public static Block carved_double_slab, angelic_double_slab, hellfire_double_slab, skyroot_double_slab, holystone_double_slab, holystone_brick_double_slab, mossy_holystone_double_slab, aerogel_double_slab;

	//public static Block holystone_wall, mossy_holystone_wall, holystone_brick_wall, carved_wall, angelic_wall, hellfire_wall, aerogel_wall;

	public static Block pot_of_gold, present;
	
	public static Block luxurious_bed, wretched_bed;

	public static void initialization()
	{
		tall_dream_grass = register("tall_dream_grass", new BlockGNSTallGrass());
		tall_nightmare_grass = register("tall_nightmare_grass", new BlockGNSTallGrass());
		dream_grass = register("dream_grass", new BlockGNSGrass());
		dream_dirt = register("dream_dirt", new BlockGNSDirt());
		nightmare_grass = register("nightmare_grass", new BlockGNSGrass());
		hope_mushroom = register("hope_mushroom", new BlockGNSMushroom());
		despair_mushroom = register("despair_mushroom", new BlockGNSMushroom());
		
		//zitrite_ore = register("zitrite_ore", new BlockZirtiteOre());
		//necrum_ore = register("necrum_ore", new BlockNecrumOre());
		//candy_ore = register("candy_ore", new BlockCandyOre());
		//rainbow_ore = register("rainbow_ore", new BlockRainbowOre());
		//positite_ore = register("positite_ore", new BlockPosititeOre());
		//negatite_ore = register("negatite_ore", new BlockNegatiteOre());
		
		dream_leaves = register("dream_leaves", new BlockGNSLeaves());
		candy_leaves = register("candy_leaves", new BlockGNSLeaves());
		diamond_leaves = register("diamond_leaves", new BlockGNSLeaves());
		
		dream_log = register("dream_log", new BlockGNSLog());
		white_log = register("white_log", new BlockGNSLog());
		dead_log = register("dead_log", new BlockGNSLog());
		blood_log = register("blood_log", new BlockGNSLog());
		
		dream_plank = register("dream_plank", new BlockGNSPlank());
		white_plank = register("white_plank", new BlockGNSPlank());
		dead_plank = register("dead_plank", new BlockGNSPlank());
		blood_plank = register("blood_plank", new BlockGNSPlank());
		
		//test_portal = register("test_portal", new BlockAetherPortal()).setCreativeTab(GNSCreativeTabs.blocks);
		
		orange_flower = register("orange_flower", new BlockGNSFlower());
		cyan_flower = register("cyan_flower", new BlockGNSFlower());
		dead_flower = register("dead_flower", new BlockGNSFlower());
		
		//present = register("present", new BlockPresent());

		//skyroot_fence = register("skyroot_fence", new BlockAetherFence());

		//skyroot_fence_gate = register("skyroot_fence_gate", new BlockAetherFenceGate());

		//carved_wall = register("carved_wall", new BlockAetherWall(dungeon_block.getDefaultState()));

		//carved_stairs = register("carved_stairs", new BlockAetherStairs(dungeon_block.getDefaultState()));

		//skyroot_double_slab = register("skyroot_double_slab", new BlockAetherSlab("skyroot_double_slab", true, Material.WOOD).setHardness(2.0F).setResistance(5.0F)).setCreativeTab(null);
		
		//skyroot_slab = registerSlab("skyroot_slab", new BlockAetherSlab("skyroot_slab", false, Material.WOOD).setHardness(2.0F).setResistance(5.0F), skyroot_double_slab);
	}

	/*public static Block registerSlab(String name, Block slab1, Block slab2)
	{
		slab1.setCreativeTab(AetherCreativeTabs.blocks);

		GameRegistry.register(slab1.setRegistryName(Aether.locate(name)));
		GameRegistry.register(new ItemAetherSlab(slab1, (BlockSlab) slab1, (BlockSlab) slab2).setRegistryName(Aether.locate(name)));

		return slab1;
	}*/

	public static Block register(String name, Block block)
	{
		block.setUnlocalizedName(name);
		block.setCreativeTab(GNSCreativeTabs.blocks);

		GameRegistry.register(block.setRegistryName(GoodNightSleep.locate(name)));
		GameRegistry.register(new ItemBlock(block).setRegistryName(GoodNightSleep.locate(name)));

		return block;
	}

	public static boolean isEarth(IBlockState state)
	{
		Block block = state.getBlock();
        return block == dream_dirt || block == dream_grass || block == nightmare_grass || block == Blocks.DIRT || block == Blocks.STONE;
    }

}