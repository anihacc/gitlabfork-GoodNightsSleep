package com.legacy.goodnightsleep.data;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSItems;

import net.minecraft.block.ChestBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unchecked")
public class GNSTagProv
{
	public static class BlockTagProv extends BlockTagsProvider
	{
		public BlockTagProv(DataGenerator generatorIn, ExistingFileHelper existingFileHelper)
		{
			super(generatorIn, GoodNightSleep.MODID, existingFileHelper);
		}

		@Override
		protected void addTags()
		{
			goodNightsSleep();
			vanilla();
			forge();
		}

		void goodNightsSleep()
		{
			this.tag(GNSBlockTags.PLANKS).add(GNSBlocks.dream_planks, GNSBlocks.white_planks, GNSBlocks.dead_planks, GNSBlocks.blood_planks);

			this.tag(GNSBlockTags.DREAM_LOGS).add(GNSBlocks.dream_log, GNSBlocks.dream_wood, GNSBlocks.stripped_dream_log, GNSBlocks.stripped_dream_wood);
			this.tag(GNSBlockTags.WHITE_LOGS).add(GNSBlocks.white_log, GNSBlocks.white_wood, GNSBlocks.stripped_white_log, GNSBlocks.stripped_white_wood);
			this.tag(GNSBlockTags.DEAD_LOGS).add(GNSBlocks.dead_log, GNSBlocks.dead_wood, GNSBlocks.stripped_dead_log, GNSBlocks.stripped_dead_wood);
			this.tag(GNSBlockTags.BLOOD_LOGS).add(GNSBlocks.blood_log, GNSBlocks.blood_wood, GNSBlocks.stripped_blood_log, GNSBlocks.stripped_blood_wood);

			this.tag(GNSBlockTags.COBBLESTONES).add(GNSBlocks.delusion_cobblestone);
			this.tag(GNSBlockTags.STONES).add(GNSBlocks.delusion_stone);
			this.tag(GNSBlockTags.MUSHROOMS).add(GNSBlocks.hope_mushroom, GNSBlocks.despair_mushroom);

			this.tag(GNSBlockTags.CANDY_ORES).add(GNSBlocks.candy_ore);
			this.tag(GNSBlockTags.RAINBOW_ORES).add(GNSBlocks.rainbow_ore);
			this.tag(GNSBlockTags.POSITITE_ORES).add(GNSBlocks.positite_ore);
			this.tag(GNSBlockTags.NECRUM_ORES).add(GNSBlocks.necrum_ore);
			this.tag(GNSBlockTags.ZITRITE_ORES).add(GNSBlocks.zitrite_ore);
			this.tag(GNSBlockTags.NEGATITE_ORES).add(GNSBlocks.negatite_ore);

			this.tag(GNSBlockTags.CANDY_BLOCKS).add(GNSBlocks.candy_block);
			this.tag(GNSBlockTags.RAINBOW_BLOCKS).add(GNSBlocks.rainbow_block);
			this.tag(GNSBlockTags.POSITITE_BLOCKS).add(GNSBlocks.positite_block);
			this.tag(GNSBlockTags.NECRUM_BLOCKS).add(GNSBlocks.necrum_block);
			this.tag(GNSBlockTags.ZITRITE_BLOCKS).add(GNSBlocks.zitrite_block);
			this.tag(GNSBlockTags.NEGATITE_BLOCKS).add(GNSBlocks.negatite_block);
		}

		void vanilla()
		{
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof FlowerPotBlock).forEach(this.tag(BlockTags.FLOWER_POTS)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof LeavesBlock).forEach(this.tag(BlockTags.LEAVES)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof SaplingBlock).forEach(this.tag(BlockTags.SAPLINGS)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof SlabBlock && !block.defaultBlockState().getMaterial().equals(Material.WOOD)).forEach(this.tag(BlockTags.SLABS)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof FlowerBlock).forEach(this.tag(BlockTags.SMALL_FLOWERS)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof StairsBlock && !block.defaultBlockState().getMaterial().equals(Material.WOOD)).forEach(this.tag(BlockTags.STAIRS)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof GrassBlock).forEach(this.tag(BlockTags.VALID_SPAWN)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof WallBlock).forEach(this.tag(BlockTags.WALLS)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof WoodButtonBlock).forEach(this.tag(BlockTags.WOODEN_BUTTONS)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof DoorBlock && block.defaultBlockState().getMaterial().equals(Material.WOOD)).forEach(this.tag(BlockTags.WOODEN_DOORS)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof FenceBlock && block.defaultBlockState().getMaterial().equals(Material.WOOD)).forEach(this.tag(BlockTags.WOODEN_FENCES)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof PressurePlateBlock && block.defaultBlockState().getMaterial().equals(Material.WOOD)).forEach(this.tag(BlockTags.WOODEN_PRESSURE_PLATES)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof SlabBlock && block.defaultBlockState().getMaterial().equals(Material.WOOD)).forEach(this.tag(BlockTags.WOODEN_SLABS)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof StairsBlock && block.defaultBlockState().getMaterial().equals(Material.WOOD)).forEach(this.tag(BlockTags.WOODEN_STAIRS)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof TrapDoorBlock && block.defaultBlockState().getMaterial().equals(Material.WOOD)).forEach(this.tag(BlockTags.WOODEN_TRAPDOORS)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof RotatedPillarBlock && block.defaultBlockState().getMaterial().equals(Material.WOOD)).forEach(this.tag(BlockTags.LOGS)::add);

			this.tag(BlockTags.BEACON_BASE_BLOCKS).addTags(GNSBlockTags.RAINBOW_BLOCKS, GNSBlockTags.POSITITE_BLOCKS, GNSBlockTags.ZITRITE_BLOCKS, GNSBlockTags.NEGATITE_BLOCKS);
			/*this.getOrCreateBuilder(BlockTags.LOGS).add(GNSBlocks.blood_log);*/

			this.tag(BlockTags.PLANKS).addTag(GNSBlockTags.PLANKS);
			this.tag(BlockTags.BEDS).add(GNSBlocks.luxurious_bed, GNSBlocks.wretched_bed, GNSBlocks.strange_bed);
		}

		void forge()
		{
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof ChestBlock && block.defaultBlockState().getMaterial().equals(Material.WOOD)).forEach(this.tag(Tags.Blocks.CHESTS_WOODEN)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof FenceBlock && block.defaultBlockState().getMaterial().equals(Material.WOOD)).forEach(this.tag(Tags.Blocks.FENCES_WOODEN)::add);
			ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && block instanceof FenceGateBlock && block.defaultBlockState().getMaterial().equals(Material.WOOD)).forEach(this.tag(Tags.Blocks.FENCE_GATES_WOODEN)::add);
			this.tag(Tags.Blocks.COBBLESTONE).addTag(GNSBlockTags.COBBLESTONES);
			this.tag(Tags.Blocks.STONE).addTag(GNSBlockTags.STONES);
			this.tag(Tags.Blocks.DIRT).add(GNSBlocks.dream_grass_block, GNSBlocks.dream_dirt, GNSBlocks.nightmare_grass_block);
			this.tag(Tags.Blocks.ORES).addTags(GNSBlockTags.CANDY_ORES, GNSBlockTags.RAINBOW_ORES, GNSBlockTags.POSITITE_ORES, GNSBlockTags.NECRUM_ORES, GNSBlockTags.ZITRITE_ORES, GNSBlockTags.NEGATITE_ORES);
			this.tag(Tags.Blocks.STORAGE_BLOCKS).addTags(GNSBlockTags.CANDY_BLOCKS, GNSBlockTags.RAINBOW_BLOCKS, GNSBlockTags.POSITITE_BLOCKS, GNSBlockTags.NECRUM_BLOCKS, GNSBlockTags.ZITRITE_BLOCKS, GNSBlockTags.NEGATITE_BLOCKS);

			this.tag(Tags.Blocks.ORES_COAL).add(GNSBlocks.coal_ore);
			this.tag(Tags.Blocks.ORES_LAPIS).add(GNSBlocks.lapis_ore);
		}

		@Override
		public String getName()
		{
			return "Good Night's Sleep Block Tags";
		}
	}

	public static class ItemTagProv extends ItemTagsProvider
	{
		public ItemTagProv(DataGenerator generatorIn, BlockTagsProvider blocktagProvIn, ExistingFileHelper existingFileHelper)
		{
			super(generatorIn, blocktagProvIn, GoodNightSleep.MODID, existingFileHelper);
		}

		@Override
		protected void addTags()
		{
			goodNightsSleep();
			vanilla();
			forge();
		}

		void goodNightsSleep()
		{
			this.copy(GNSBlockTags.PLANKS, GNSItemTags.PLANKS);

			this.copy(GNSBlockTags.DREAM_LOGS, GNSItemTags.DREAM_LOGS);
			this.copy(GNSBlockTags.WHITE_LOGS, GNSItemTags.WHITE_LOGS);
			this.copy(GNSBlockTags.DEAD_LOGS, GNSItemTags.DEAD_LOGS);
			this.copy(GNSBlockTags.BLOOD_LOGS, GNSItemTags.BLOOD_LOGS);

			this.copy(GNSBlockTags.COBBLESTONES, GNSItemTags.COBBLESTONES);
			this.copy(GNSBlockTags.STONES, GNSItemTags.STONES);
			this.copy(GNSBlockTags.MUSHROOMS, GNSItemTags.MUSHROOMS);

			this.copy(GNSBlockTags.CANDY_ORES, GNSItemTags.CANDY_ORES);
			this.copy(GNSBlockTags.RAINBOW_ORES, GNSItemTags.RAINBOW_ORES);
			this.copy(GNSBlockTags.POSITITE_ORES, GNSItemTags.POSITITE_ORES);
			this.copy(GNSBlockTags.NECRUM_ORES, GNSItemTags.NECRUM_ORES);
			this.copy(GNSBlockTags.ZITRITE_ORES, GNSItemTags.ZITRITE_ORES);
			this.copy(GNSBlockTags.NEGATITE_ORES, GNSItemTags.NEGATITE_ORES);

			this.copy(GNSBlockTags.CANDY_BLOCKS, GNSItemTags.CANDY_BLOCKS);
			this.copy(GNSBlockTags.RAINBOW_BLOCKS, GNSItemTags.RAINBOW_BLOCKS);
			this.copy(GNSBlockTags.POSITITE_BLOCKS, GNSItemTags.POSITITE_BLOCKS);
			this.copy(GNSBlockTags.NECRUM_BLOCKS, GNSItemTags.NECRUM_BLOCKS);
			this.copy(GNSBlockTags.ZITRITE_BLOCKS, GNSItemTags.ZITRITE_BLOCKS);
			this.copy(GNSBlockTags.NEGATITE_BLOCKS, GNSItemTags.NEGATITE_BLOCKS);

			this.tag(GNSItemTags.CANDY_MATERIALS).add(GNSBlocks.candy_block.asItem());
			this.tag(GNSItemTags.RAINBOW_INGOTS).add(GNSItems.rainbow_ingot);
			this.tag(GNSItemTags.POSITITE).add(GNSItems.positite);
			this.tag(GNSItemTags.NECRUM).add(GNSItems.necrum);
			this.tag(GNSItemTags.ZITRITE_INGOTS).add(GNSItems.zitrite_ingot);
			this.tag(GNSItemTags.NEGATITE).add(GNSItems.negatite);
		}

		void vanilla()
		{
			this.copy(BlockTags.BEDS, ItemTags.BEDS);
			this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
			this.copy(BlockTags.LOGS, ItemTags.LOGS);
			this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
			this.copy(BlockTags.SAND, ItemTags.SAND);
			this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
			this.copy(BlockTags.SLABS, ItemTags.SLABS);
			this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
			this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
			this.copy(BlockTags.WALL_CORALS, ItemTags.WALLS);
			this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
			this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
			this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
			this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
			this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
			this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
			this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);

			this.tag(ItemTags.STONE_TOOL_MATERIALS).addTag(GNSItemTags.COBBLESTONES);

			this.tag(ItemTags.PIGLIN_LOVED).add(GNSBlocks.pot_of_gold.asItem());

			/*ForgeRegistries.ITEMS.getValues().stream().filter(item -> item.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && item instanceof MusicDiscItem).forEach(this.getOrCreateBuilder(ItemTags.MUSIC_DISCS)::add);*/
		}

		void forge()
		{
			this.copy(Tags.Blocks.CHESTS_WOODEN, Tags.Items.CHESTS_WOODEN);
			this.copy(Tags.Blocks.COBBLESTONE, Tags.Items.COBBLESTONE);
			this.copy(Tags.Blocks.STONE, Tags.Items.STONE);
			this.copy(Tags.Blocks.ORES, Tags.Items.ORES);
			this.copy(Tags.Blocks.ORES_COAL, Tags.Items.ORES_COAL);
			this.copy(Tags.Blocks.ORES_LAPIS, Tags.Items.ORES_LAPIS);
			this.copy(Tags.Blocks.SANDSTONE, Tags.Items.SANDSTONE);
			this.copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
			this.tag(Tags.Items.CROPS).add(GNSItems.rainbow_berries);
			this.tag(Tags.Items.GEMS).addTags(GNSItemTags.POSITITE, GNSItemTags.NEGATITE);
			this.tag(Tags.Items.INGOTS).addTags(GNSItemTags.RAINBOW_INGOTS, GNSItemTags.ZITRITE_INGOTS);
			this.tag(Tags.Items.SEEDS).add(GNSItems.rainbow_seeds);
		}

		@Override
		public String getName()
		{
			return "Good Night's Sleep Item Tags";
		}
	}
}
