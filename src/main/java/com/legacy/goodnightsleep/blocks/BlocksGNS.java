
package com.legacy.goodnightsleep.blocks;

import com.legacy.goodnightsleep.blocks.construction.BlockGNSFence;
import com.legacy.goodnightsleep.blocks.construction.BlockGNSPlank;
import com.legacy.goodnightsleep.blocks.construction.BlockGNSSlab;
import com.legacy.goodnightsleep.blocks.construction.BlockGNSStairs;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSDirt;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSFlower;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSGrass;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSLeaves;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSLog;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSMushroom;
import com.legacy.goodnightsleep.blocks.natural.BlockGNSTallGrass;
import com.legacy.goodnightsleep.blocks.natural.ores.BlockGNSOre;
import com.legacy.goodnightsleep.blocks.util.ItemGNSSlab;
import com.legacy.goodnightsleep.items.ItemsGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;
import com.legacy.goodnightsleep.registry.VariableConstants;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

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

	public static Block rainbow_berries;

	public static Block white_log, dream_log, blood_log, dead_log;

	public static Block dream_fence, white_fence, dead_fence, blood_fence;
	
	public static Block dream_slab, white_slab, dead_slab, blood_slab;
	
	public static Block dead_stairs, blood_stairs, white_stairs, dream_stairs;
	
	public static Block pot_of_gold, present;
	
	public static Block luxurious_bed, wretched_bed;
	
	private static IForgeRegistry<Block> iBlockRegistry;

	private static IForgeRegistry<Item> iItemRegistry;

	public static void initialization()
	{		
		if (iBlockRegistry == null || iItemRegistry == null)
		{
			return;
		}
		
		tall_dream_grass = register("tall_dream_grass", new BlockGNSTallGrass());
		tall_nightmare_grass = register("tall_nightmare_grass", new BlockGNSTallGrass());
		dream_grass = register("dream_grass", new BlockGNSGrass());
		dream_dirt = register("dream_dirt", new BlockGNSDirt());
		nightmare_grass = register("nightmare_grass", new BlockGNSGrass());
		hope_mushroom_cap = register("hope_mushroom_cap", new BlockGNSMushroom());
		despair_mushroom_cap = register("despair_mushroom_cap", new BlockGNSMushroom());
		
		zitrite_ore = register("zitrite_ore", new BlockGNSOre(ItemsGNS.zitrite_ingot));
		necrum_ore = register("necrum_ore", new BlockGNSOre(ItemsGNS.necrum));
		candy_ore = register("candy_ore", new BlockGNSOre(Items.APPLE));
		rainbow_ore = register("rainbow_ore", new BlockGNSOre(ItemsGNS.rainbow_ingot));
		positite_ore = register("positite_ore", new BlockGNSOre(ItemsGNS.positite_gem));
		negatite_ore = register("negatite_ore", new BlockGNSOre(ItemsGNS.negatite_gem));
		
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
				
		orange_flower = register("orange_flower", new BlockGNSFlower());
		cyan_flower = register("cyan_flower", new BlockGNSFlower());
		lolipop_bush = register("lolipop_bush", new BlockGNSFlower());
		dead_flower = register("dead_flower", new BlockGNSFlower());
		
		luxurious_bed = register("luxurious_bed", new BlockGNSBed().setCreativeTab(null));
		wretched_bed = register("wretched_bed", new BlockGNSBed().setCreativeTab(null));

		dream_fence = register("dream_fence", new BlockGNSFence());
		white_fence = register("white_fence", new BlockGNSFence());
		dead_fence = register("dead_fence", new BlockGNSFence());
		blood_fence = register("blood_fence", new BlockGNSFence());
		
		dream_stairs = register("dream_stairs", new BlockGNSStairs(dream_plank.getDefaultState()));
		white_stairs = register("white_stairs", new BlockGNSStairs(white_plank.getDefaultState()));
		dead_stairs = register("dead_stairs", new BlockGNSStairs(dead_plank.getDefaultState()));
		blood_stairs = register("blood_stairs", new BlockGNSStairs(blood_plank.getDefaultState()));
		
		dream_slab = registerSlab("dream_slab", new BlockGNSSlab(Material.WOOD).setHardness(2.0F).setResistance(5.0F), GNSCreativeTabs.blocks);
		white_slab = registerSlab("white_slab", new BlockGNSSlab(Material.WOOD).setHardness(2.0F).setResistance(5.0F), GNSCreativeTabs.blocks);
		dead_slab = registerSlab("dead_slab", new BlockGNSSlab(Material.WOOD).setHardness(2.0F).setResistance(5.0F), GNSCreativeTabs.blocks);
		blood_slab = registerSlab("blood_slab", new BlockGNSSlab(Material.WOOD).setHardness(2.0F).setResistance(5.0F), GNSCreativeTabs.blocks);
		
		/*bluebright_fence_gate = registerBright("bluebright_fence_gate", new BlockSkyFenceGate());
		lunar_fence_gate = registerDawn("lunar_fence_gate", new BlockSkyFenceGate());
		cherry_fence_gate = register("cherry_fence_gate", new BlockSkyFenceGate());
		starlit_fence_gate = registerBright("starlit_fence_gate", new BlockSkyFenceGate());
		dusk_fence_gate = registerDawn("dusk_fence_gate", new BlockSkyFenceGate());*/
	}

	/*public static Block registerSlab(String name, Block slab1, Block slab2)
	{
		slab1.setCreativeTab(AetherCreativeTabs.blocks);

		GameRegistry.register(slab1.setRegistryName(Aether.locate(name)));
		GameRegistry.register(new ItemAetherSlab(slab1, (BlockSlab) slab1, (BlockSlab) slab2).setRegistryName(Aether.locate(name)));

		return slab1;
	}*/
	
	public static void setItemRegistry(IForgeRegistry<Item> iItemRegistry)
	{
		BlocksGNS.iItemRegistry = iItemRegistry;
	}

	public static void setBlockRegistry(IForgeRegistry<Block> iBlockRegistry)
	{
		BlocksGNS.iBlockRegistry = iBlockRegistry;
	}
	
	public static Block register(String name, Block block)
	{
		return register(name, block, new ItemBlock(block));
	}
	
	public static Block registerSlab(String name, Block block, CreativeTabs tab)
	{
		return registerSlab(name, block, new ItemGNSSlab(block), tab);
	}

	public static Block register(String name, Block block, ItemBlock item)
	{
		block.setUnlocalizedName(name);

		block.setRegistryName(VariableConstants.locate(name));
		item.setRegistryName(VariableConstants.locate(name));

		iBlockRegistry.register(block);
		iItemRegistry.register(item);

		if (name != "luxurious_bed" || name != "wretched_bed")
		{
			block.setCreativeTab(GNSCreativeTabs.blocks);
		}

		return block;
	}
	
	public static Block registerSlab(String name, Block block, ItemBlock item, CreativeTabs tab)
	{
		block.setUnlocalizedName(name);

		block.setRegistryName(VariableConstants.locate(name));
		item.setRegistryName(VariableConstants.locate(name));

		iBlockRegistry.register(block);
		iItemRegistry.register(item);

		block.setCreativeTab(tab);

		return block;
	}

	public static boolean isEarth(IBlockState state)
	{
		Block block = state.getBlock();
        return block == dream_dirt || block == dream_grass || block == nightmare_grass || block == Blocks.DIRT || block == Blocks.STONE;
    }

}