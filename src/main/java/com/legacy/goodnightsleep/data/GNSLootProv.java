package com.legacy.goodnightsleep.data;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.blocks.GNSBedBlock;
import com.legacy.goodnightsleep.blocks.natural.GNSRainbowCropBlock;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSEntityTypes;
import com.legacy.goodnightsleep.registry.GNSItems;
import com.mojang.datafixers.util.Pair;

import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.TagLootEntry;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.EntityHasProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.conditions.TableBonus;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.loot.functions.Smelt;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class GNSLootProv extends LootTableProvider
{
	public GNSLootProv(DataGenerator dataGeneratorIn)
	{
		super(dataGeneratorIn);

	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables()
	{
		return ImmutableList.of(Pair.of(BlockLoot::new, LootParameterSets.BLOCK), Pair.of(EntityLoot::new, LootParameterSets.ENTITY), Pair.of(ChestLoot::new, LootParameterSets.CHEST));
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker)
	{
		map.forEach((location, table) ->
		{
			LootTableManager.validate(validationtracker, location, table);
		});

	}

	@Override
	public String getName()
	{
		return "Good Night's Sleep Loot Tables";
	}

	private class ChestLoot extends ChestLootTables implements LootPoolUtil
	{
		@Override
		public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer)
		{
		}

		private ResourceLocation locate(String name)
		{
			return GoodNightSleep.locate("chests/" + name);
		}
	}

	private class EntityLoot extends EntityLootTables implements LootPoolUtil
	{

		@Override
		protected void addTables()
		{
			this.add(GNSEntityTypes.UNICORN, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(Items.LEATHER).apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))));
			this.add(GNSEntityTypes.GUMMY_BEAR, LootTable.lootTable());
			this.add(GNSEntityTypes.BABY_CREEPER, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(Items.GUNPOWDER).apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F))).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().add(TagLootEntry.expandTag(ItemTags.CREEPER_DROP_MUSIC_DISCS)).when(EntityHasProperty.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)))));

			this.add(GNSEntityTypes.HEROBRINE, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(GNSItems.negatite).apply(SetCount.setCount(RandomValueRange.between(0.0F, 2.0F))))));
			this.add(GNSEntityTypes.TORMENTER, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).add(ItemLootEntry.lootTableItem(GNSItems.necrum).apply(SetCount.setCount(RandomValueRange.between(2.0F, 3.0F))))));

			this.add(GNSEntityTypes.SPAWNER_ENTITY, LootTable.lootTable());
		}

		private LootPool.Builder lootingPool(IItemProvider item, int min, int max, int minLooting, int maxLooting)
		{
			return basicPool(item, min, max).apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(minLooting, maxLooting)));
		}

		private LootPool.Builder smeltingPool(IItemProvider item, int min, int max, int minLooting, int maxLooting)
		{
			return lootingPool(item, min, max, minLooting, maxLooting).apply(Smelt.smelted().when(EntityHasProperty.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)));
		}

		private String entityName(EntityType<?> entity)
		{
			return entity.getRegistryName().getPath();
		}

		@Override
		protected Iterable<EntityType<?>> getKnownEntities()
		{
			return ForgeRegistries.ENTITIES.getValues().stream().filter(e -> e.getRegistryName().getNamespace().contains(GoodNightSleep.MODID))::iterator;
		}
	}

	private class BlockLoot extends BlockLootTables implements LootPoolUtil
	{
		private final ILootCondition.IBuilder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
		private final ILootCondition.IBuilder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
		private final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.or(SILK_TOUCH);
		private final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.invert();
		private float[] DEFAULT_SAPLING_DROP_RATES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };

		@Override
		protected void addTables()
		{
			blocks().forEach(block ->
			{
				if (block == GNSBlocks.dream_grass)
					this.add(block, this.dropRainbowSeeds(block));
				else if (block == GNSBlocks.nightmare_grass)
					this.add(block, BlockLootTables::createGrassDrops);
				else if (block == GNSBlocks.prickly_nightmare_grass)
					this.add(block, BlockLootTables::createGrassDrops);

				else if (block == GNSBlocks.dream_grass_block)
					silkOrElse(block, GNSBlocks.dream_dirt);
				else if (block == GNSBlocks.nightmare_grass_block)
					silkOrElse(block, Blocks.DIRT);
				else if (block == GNSBlocks.dream_farmland)
					dropOther(block, GNSBlocks.dream_dirt);
				else if (block == GNSBlocks.delusion_stone)
					silkOrElse(block, GNSBlocks.delusion_cobblestone);
				else if (block == GNSBlocks.dream_leaves)
					add(block, (b) -> leaves(b, GNSBlocks.dream_sapling, Items.STICK));
				else if (block == GNSBlocks.candy_leaves)
					add(block, (b) -> leaves(b, GNSBlocks.candy_sapling, Items.STICK));
				else if (block == GNSBlocks.diamond_leaves)
					add(block, (b) -> leaves(b, GNSBlocks.dream_sapling, Items.STICK));
				else if (block instanceof SlabBlock)
					add(block, BlockLootTables::createSlabItemTable);
				else if (block == GNSBlocks.candy_ore)
					add(block, (b) -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, ItemLootEntry.lootTableItem(GNSItems.candy).apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
				else if (block == GNSBlocks.necrum_ore)
					add(block, (b) -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, ItemLootEntry.lootTableItem(GNSItems.necrum).apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
				else if (block == GNSBlocks.positite_ore)
					add(block, (b) -> createOreDrop(b, GNSItems.positite));
				else if (block == GNSBlocks.negatite_ore)
					add(block, (b) -> createOreDrop(b, GNSItems.negatite));
				else if (block == GNSBlocks.rainbow_ore)
					add(block, (b) -> dropRainbow(b));
				else if (block == GNSBlocks.present)
					add(block, (b) -> dropPresent(b));
				else if (block instanceof DoorBlock)
					add(block, (b) -> createSinglePropConditionTable(b, DoorBlock.HALF, DoubleBlockHalf.LOWER));
				else if (block instanceof FlowerPotBlock)
					dropPottedContents(block);
				else if (block == GNSBlocks.lapis_ore)
					add(block, (b) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(Items.LAPIS_LAZULI).apply(SetCount.setCount(RandomValueRange.between(4.0F, 9.0F))).apply(ApplyBonus.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
				else if (block == GNSBlocks.coal_ore)
					add(block, (b) -> createOreDrop(block, Items.COAL));
				else if (block == GNSBlocks.hope_mushroom_block)
					add(block, (b) -> createMushroomBlockDrop(b, GNSBlocks.hope_mushroom));
				else if (block == GNSBlocks.despair_mushroom_block)
					add(block, (b) -> createMushroomBlockDrop(b, GNSBlocks.despair_mushroom));
				else if (block instanceof GNSBedBlock)
					add(block, (bed) ->
					{
						return createSinglePropConditionTable(bed, GNSBedBlock.PART, BedPart.HEAD);
					});
				else if (block instanceof GNSRainbowCropBlock)
				{
					ILootCondition.IBuilder growthCondition = BlockStateProperty.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GNSRainbowCropBlock.AGE, ((GNSRainbowCropBlock) block).getMaxAge()));

					if (block == GNSBlocks.rainbow_berries)
						this.add(block, (b) -> crop(growthCondition, b, GNSItems.rainbow_berries));
				}
				else
					dropSelf(block);
			});
		}

		@Override
		protected Iterable<Block> getKnownBlocks()
		{
			return blocks()::iterator;
		}

		// @formatter:off
		protected LootTable.Builder dropRainbow(Block block)
		{
			return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
					.add(ItemLootEntry.lootTableItem(block).when(SILK_TOUCH).otherwise(ItemLootEntry.lootTableItem(GNSBlocks.rainbow_ore).setWeight(50)))
					.add(ItemLootEntry.lootTableItem(block).when(SILK_TOUCH).otherwise(ItemLootEntry.lootTableItem(Blocks.GOLD_ORE).setWeight(10)))
					.add(ItemLootEntry.lootTableItem(block).when(SILK_TOUCH).otherwise(ItemLootEntry.lootTableItem(Blocks.IRON_ORE).setWeight(10)))
					.add(ItemLootEntry.lootTableItem(block).when(SILK_TOUCH).otherwise(ItemLootEntry.lootTableItem(Items.REDSTONE).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))))
					.add(ItemLootEntry.lootTableItem(block).when(SILK_TOUCH).otherwise(ItemLootEntry.lootTableItem(Items.EMERALD).setWeight(1)))
					.add(ItemLootEntry.lootTableItem(block).when(SILK_TOUCH).otherwise(ItemLootEntry.lootTableItem(GNSItems.candy).setWeight(20).apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F))))));
		}
		
		protected LootTable.Builder dropPresent(Block block)
		{
			return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1))
					.add(ItemLootEntry.lootTableItem(block).when(SILK_TOUCH).otherwise(ItemLootEntry.lootTableItem(GNSItems.rainbow_ingot).setWeight(30)))
					.add(ItemLootEntry.lootTableItem(block).when(SILK_TOUCH).otherwise(ItemLootEntry.lootTableItem(Items.GOLD_INGOT).setWeight(10)))
					.add(ItemLootEntry.lootTableItem(block).when(SILK_TOUCH).otherwise(ItemLootEntry.lootTableItem(Items.IRON_INGOT).setWeight(10)))
					.add(ItemLootEntry.lootTableItem(block).when(SILK_TOUCH).otherwise(ItemLootEntry.lootTableItem(Items.REDSTONE).setWeight(10).apply(SetCount.setCount(RandomValueRange.between(1.0F, 4.0F)))))
					.add(ItemLootEntry.lootTableItem(block).when(SILK_TOUCH).otherwise(ItemLootEntry.lootTableItem(Items.EMERALD).setWeight(5)))
					.add(ItemLootEntry.lootTableItem(block).when(SILK_TOUCH).otherwise(ItemLootEntry.lootTableItem(GNSItems.candy).setWeight(30).apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F)))))
					.add(ItemLootEntry.lootTableItem(block).when(SILK_TOUCH).otherwise(ItemLootEntry.lootTableItem(GNSItems.positite).setWeight(5))));
		}
		// @formatter:on

		private Stream<Block> blocks()
		{
			return ForgeRegistries.BLOCKS.getValues().stream().filter(b -> b.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && !b.getLootTable().equals(LootTables.EMPTY));
		}

		private void droppingSeedTag(Block block, ITag.INamedTag<Item> tag)
		{
			this.add(block, createShearsDispatchTable(block, applyExplosionDecay(block, (TagLootEntry.expandTag(tag).when(RandomChance.randomChance(0.125F))).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2)))));
		}

		protected LootTable.Builder dropRainbowSeeds(Block block)
		{
			return createShearsDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(GNSItems.rainbow_seeds).when(RandomChance.randomChance(0.125F)).apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))));
		}

		private void silkOrElse(Block withSilk, IItemProvider without)
		{
			this.add(withSilk, (b) -> createSingleItemTableWithSilkTouch(b, without));
		}

		private LootTable.Builder leaves(Block block, IItemProvider sapling, IItemProvider stick)
		{
			return createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, ItemLootEntry.lootTableItem(sapling)).when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, DEFAULT_SAPLING_DROP_RATES))).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(NOT_SILK_TOUCH_OR_SHEARS).add(applyExplosionDecay(block, ItemLootEntry.lootTableItem(stick).apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F)))).when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
		}

		private LootTable.Builder leavesFruit(Block block, IItemProvider sapling, IItemProvider stick, IItemProvider fruit)
		{
			float baseChance = 0.05F;
			float[] fortuneChances = new float[] { 1.11111114F, 1.25F, 1.6666668F, 5.0F };
			return leaves(block, sapling, stick).withPool(LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(NOT_SILK_TOUCH_OR_SHEARS).add(applyExplosionCondition(block, ItemLootEntry.lootTableItem(fruit)).when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, baseChance, baseChance * fortuneChances[0], baseChance * fortuneChances[1], baseChance * fortuneChances[2], baseChance * fortuneChances[3]))));
		}

		private LootTable.Builder crop(ILootCondition.IBuilder growthCondition, Block block, IItemProvider food)
		{
			return crop(growthCondition, block, food, food);
		}

		private LootTable.Builder crop(ILootCondition.IBuilder growthCondition, Block block, IItemProvider food, IItemProvider seed)
		{
			LootPool.Builder seedPool = LootPool.lootPool().add(ItemLootEntry.lootTableItem(seed).apply(ApplyBonus.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3).when(growthCondition)));
			LootPool.Builder foodPool = LootPool.lootPool().when(growthCondition).add(ItemLootEntry.lootTableItem(food).apply(ApplyBonus.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 1)));

			return applyExplosionDecay(block, LootTable.lootTable().withPool(seedPool).withPool(foodPool));
		}
	}

	/**
	 * Interface with basic loot table generators
	 * 
	 * @author David
	 *
	 */
	public interface LootPoolUtil
	{
		/**
		 * Creates a table from the given loot pools.
		 * 
		 * @param pools
		 * @return
		 */
		default LootTable.Builder tableOf(List<LootPool.Builder> pools)
		{
			LootTable.Builder table = LootTable.lootTable();
			pools.forEach(pool -> table.withPool(pool));
			return table;
		}

		/**
		 * Creates a table from the given loot pool.
		 * 
		 * @param pool
		 * @return
		 */
		default LootTable.Builder tableOf(LootPool.Builder pool)
		{
			return LootTable.lootTable().withPool(pool);
		}

		/**
		 * Creates a loot pool with the given item. Gives an amount between the min and
		 * max.
		 * 
		 * @param item
		 * @param min
		 * @param max
		 * @return
		 */
		default LootPool.Builder basicPool(IItemProvider item, int min, int max)
		{
			return LootPool.lootPool().add(basicEntry(item, min, max));
		}

		/**
		 * Creates a loot pool with the given item. Will only give one item.
		 * 
		 * @param item
		 * @return
		 */
		default LootPool.Builder basicPool(IItemProvider item)
		{
			return LootPool.lootPool().add(basicEntry(item));
		}

		/**
		 * Creates a loot pool that will give a random item from the list.
		 * 
		 * @param items
		 * @return
		 */
		default LootPool.Builder randItemPool(List<IItemProvider> items)
		{
			return poolOf(items.stream().map((i) -> basicEntry(i)).collect(Collectors.toList()));
		}

		/**
		 * Creates a loot pool with multiple entries. One of these entries will be
		 * picked at random each time the pool rolls.
		 * 
		 * @param lootEntries
		 * @return
		 */
		default LootPool.Builder poolOf(List<LootEntry.Builder<?>> lootEntries)
		{
			LootPool.Builder pool = LootPool.lootPool();
			lootEntries.forEach(entry -> pool.add(entry));
			return pool;
		}

		/**
		 * Creates a loot entry for the given item. Gives an amount between the min and
		 * max.
		 * 
		 * @param item
		 * @param min
		 * @param max
		 * @return
		 */
		default ItemLootEntry.Builder<?> basicEntry(IItemProvider item, int min, int max)
		{
			return basicEntry(item).apply(SetCount.setCount(RandomValueRange.between(min, max)));
		}

		/**
		 * Creates a loot entry for the given item. Will only give one item.
		 * 
		 * @param item
		 * @return
		 */
		default ItemLootEntry.Builder<?> basicEntry(IItemProvider item)
		{
			return ItemLootEntry.lootTableItem(item);
		}
	}
}
