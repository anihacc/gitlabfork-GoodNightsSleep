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
			LootTableManager.validateLootTable(validationtracker, location, table);
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
			this.registerLootTable(GNSEntityTypes.UNICORN, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.LEATHER).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));
			this.registerLootTable(GNSEntityTypes.GUMMY_BEAR, LootTable.builder());
			this.registerLootTable(GNSEntityTypes.BABY_CREEPER, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Items.GUNPOWDER).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))).addLootPool(LootPool.builder().addEntry(TagLootEntry.getBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS)).acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.create().type(EntityTypeTags.SKELETONS)))));

			this.registerLootTable(GNSEntityTypes.HEROBRINE, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(GNSItems.negatite).acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 2.0F))))));
			this.registerLootTable(GNSEntityTypes.TORMENTER, LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(GNSItems.necrum).acceptFunction(SetCount.builder(RandomValueRange.of(2.0F, 3.0F))))));

			this.registerLootTable(GNSEntityTypes.SPAWNER_ENTITY, LootTable.builder());
		}

		private LootPool.Builder lootingPool(IItemProvider item, int min, int max, int minLooting, int maxLooting)
		{
			return basicPool(item, min, max).acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(minLooting, maxLooting)));
		}

		private LootPool.Builder smeltingPool(IItemProvider item, int min, int max, int minLooting, int maxLooting)
		{
			return lootingPool(item, min, max, minLooting, maxLooting).acceptFunction(Smelt.func_215953_b().acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS, ON_FIRE)));
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
		private final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
		private final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
		private final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
		private final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();
		private float[] DEFAULT_SAPLING_DROP_RATES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };

		@Override
		protected void addTables()
		{
			blocks().forEach(block ->
			{
				if (block == GNSBlocks.dream_grass)
					this.registerLootTable(block, this.dropRainbowSeeds(block));

				else if (block == GNSBlocks.dream_grass_block)
					silkOrElse(block, GNSBlocks.dream_dirt);
				else if (block == GNSBlocks.nightmare_grass_block)
					silkOrElse(block, Blocks.DIRT);
				else if (block == GNSBlocks.dream_farmland)
					registerDropping(block, GNSBlocks.dream_dirt);
				else if (block == GNSBlocks.delusion_stone)
					silkOrElse(block, GNSBlocks.delusion_cobblestone);
				else if (block == GNSBlocks.dream_leaves)
					registerLootTable(block, (b) -> leaves(b, GNSBlocks.dream_sapling, Items.STICK));
				else if (block == GNSBlocks.candy_leaves)
					registerLootTable(block, (b) -> leaves(b, GNSBlocks.candy_sapling, Items.STICK));
				else if (block == GNSBlocks.diamond_leaves)
					registerLootTable(block, (b) -> leaves(b, GNSBlocks.dream_sapling, Items.STICK));
				else if (block instanceof SlabBlock)
					registerLootTable(block, BlockLootTables::droppingSlab);
				else if (block == GNSBlocks.candy_ore)
					registerLootTable(block, (b) -> droppingWithSilkTouch(b, withExplosionDecay(b, ItemLootEntry.builder(GNSItems.candy).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
				else if (block == GNSBlocks.necrum_ore)
					registerLootTable(block, (b) -> droppingWithSilkTouch(b, withExplosionDecay(b, ItemLootEntry.builder(GNSItems.necrum).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));
				else if (block == GNSBlocks.positite_ore)
					registerLootTable(block, (b) -> droppingItemWithFortune(b, GNSItems.positite));
				else if (block == GNSBlocks.negatite_ore)
					registerLootTable(block, (b) -> droppingItemWithFortune(b, GNSItems.negatite));
				else if (block == GNSBlocks.rainbow_ore)
					registerLootTable(block, (b) -> dropRainbow(b));
				else if (block == GNSBlocks.present)
					registerLootTable(block, (b) -> dropPresent(b));
				else if (block instanceof DoorBlock)
					registerLootTable(block, (b) -> droppingWhen(b, DoorBlock.HALF, DoubleBlockHalf.LOWER));
				else if (block instanceof FlowerPotBlock)
					registerFlowerPot(block);
				else if (block == GNSBlocks.lapis_ore)
					registerLootTable(block, (b) -> droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(Items.LAPIS_LAZULI).acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 9.0F))).acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)))));
				else if (block == GNSBlocks.coal_ore)
					registerLootTable(block, (b) -> droppingItemWithFortune(block, Items.COAL));
				else if (block instanceof GNSBedBlock)
					registerLootTable(block, (bed) ->
					{
						return droppingWhen(bed, GNSBedBlock.PART, BedPart.HEAD);
					});
				else if (block instanceof GNSRainbowCropBlock)
				{
					ILootCondition.IBuilder growthCondition = BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(GNSRainbowCropBlock.AGE, ((GNSRainbowCropBlock) block).getMaxAge()));

					if (block == GNSBlocks.rainbow_berries)
						this.registerLootTable(block, (b) -> crop(growthCondition, b, GNSItems.rainbow_berries));
				}
				else
					registerDropSelfLootTable(block);
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
			return LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
					.addEntry(ItemLootEntry.builder(block).acceptCondition(SILK_TOUCH).alternatively(ItemLootEntry.builder(GNSBlocks.rainbow_ore).weight(50)))
					.addEntry(ItemLootEntry.builder(block).acceptCondition(SILK_TOUCH).alternatively(ItemLootEntry.builder(Blocks.GOLD_ORE).weight(10)))
					.addEntry(ItemLootEntry.builder(block).acceptCondition(SILK_TOUCH).alternatively(ItemLootEntry.builder(Blocks.IRON_ORE).weight(10)))
					.addEntry(ItemLootEntry.builder(block).acceptCondition(SILK_TOUCH).alternatively(ItemLootEntry.builder(Items.REDSTONE).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F)))))
					.addEntry(ItemLootEntry.builder(block).acceptCondition(SILK_TOUCH).alternatively(ItemLootEntry.builder(Items.EMERALD).weight(1)))
					.addEntry(ItemLootEntry.builder(block).acceptCondition(SILK_TOUCH).alternatively(ItemLootEntry.builder(GNSItems.candy).weight(20).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F))))));
		}
		
		protected LootTable.Builder dropPresent(Block block)
		{
			return LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1))
					.addEntry(ItemLootEntry.builder(block).acceptCondition(SILK_TOUCH).alternatively(ItemLootEntry.builder(GNSItems.rainbow_ingot).weight(30)))
					.addEntry(ItemLootEntry.builder(block).acceptCondition(SILK_TOUCH).alternatively(ItemLootEntry.builder(Items.GOLD_INGOT).weight(10)))
					.addEntry(ItemLootEntry.builder(block).acceptCondition(SILK_TOUCH).alternatively(ItemLootEntry.builder(Items.IRON_INGOT).weight(10)))
					.addEntry(ItemLootEntry.builder(block).acceptCondition(SILK_TOUCH).alternatively(ItemLootEntry.builder(Items.REDSTONE).weight(10).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 4.0F)))))
					.addEntry(ItemLootEntry.builder(block).acceptCondition(SILK_TOUCH).alternatively(ItemLootEntry.builder(Items.EMERALD).weight(5)))
					.addEntry(ItemLootEntry.builder(block).acceptCondition(SILK_TOUCH).alternatively(ItemLootEntry.builder(GNSItems.candy).weight(30).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 3.0F)))))
					.addEntry(ItemLootEntry.builder(block).acceptCondition(SILK_TOUCH).alternatively(ItemLootEntry.builder(GNSItems.positite).weight(5))));
		}
		// @formatter:on

		private Stream<Block> blocks()
		{
			return ForgeRegistries.BLOCKS.getValues().stream().filter(b -> b.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && !b.getLootTable().equals(LootTables.EMPTY));
		}

		private void droppingSeedTag(Block block, ITag.INamedTag<Item> tag)
		{
			this.registerLootTable(block, droppingWithShears(block, withExplosionDecay(block, (TagLootEntry.getBuilder(tag).acceptCondition(RandomChance.builder(0.125F))).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE, 2)))));
		}

		protected LootTable.Builder dropRainbowSeeds(Block block)
		{
			return droppingWithShears(block, withExplosionDecay(block, ItemLootEntry.builder(GNSItems.rainbow_seeds).acceptCondition(RandomChance.builder(0.125F)).acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE, 2))));
		}

		private void silkOrElse(Block withSilk, IItemProvider without)
		{
			this.registerLootTable(withSilk, (b) -> droppingWithSilkTouch(b, without));
		}

		private LootTable.Builder leaves(Block block, IItemProvider sapling, IItemProvider stick)
		{
			return droppingWithSilkTouchOrShears(block, withSurvivesExplosion(block, ItemLootEntry.builder(sapling)).acceptCondition(TableBonus.builder(Enchantments.FORTUNE, DEFAULT_SAPLING_DROP_RATES))).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).acceptCondition(NOT_SILK_TOUCH_OR_SHEARS).addEntry(withExplosionDecay(block, ItemLootEntry.builder(stick).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F)))).acceptCondition(TableBonus.builder(Enchantments.FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
		}

		private LootTable.Builder leavesFruit(Block block, IItemProvider sapling, IItemProvider stick, IItemProvider fruit)
		{
			float baseChance = 0.05F;
			float[] fortuneChances = new float[] { 1.11111114F, 1.25F, 1.6666668F, 5.0F };
			return leaves(block, sapling, stick).addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).acceptCondition(NOT_SILK_TOUCH_OR_SHEARS).addEntry(withSurvivesExplosion(block, ItemLootEntry.builder(fruit)).acceptCondition(TableBonus.builder(Enchantments.FORTUNE, baseChance, baseChance * fortuneChances[0], baseChance * fortuneChances[1], baseChance * fortuneChances[2], baseChance * fortuneChances[3]))));
		}

		private LootTable.Builder crop(ILootCondition.IBuilder growthCondition, Block block, IItemProvider food)
		{
			return crop(growthCondition, block, food, food);
		}

		private LootTable.Builder crop(ILootCondition.IBuilder growthCondition, Block block, IItemProvider food, IItemProvider seed)
		{
			LootPool.Builder seedPool = LootPool.builder().addEntry(ItemLootEntry.builder(seed).acceptFunction(ApplyBonus.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286F, 3).acceptCondition(growthCondition)));
			LootPool.Builder foodPool = LootPool.builder().acceptCondition(growthCondition).addEntry(ItemLootEntry.builder(food).acceptFunction(ApplyBonus.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286F, 1)));

			return withExplosionDecay(block, LootTable.builder().addLootPool(seedPool).addLootPool(foodPool));
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
			LootTable.Builder table = LootTable.builder();
			pools.forEach(pool -> table.addLootPool(pool));
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
			return LootTable.builder().addLootPool(pool);
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
			return LootPool.builder().addEntry(basicEntry(item, min, max));
		}

		/**
		 * Creates a loot pool with the given item. Will only give one item.
		 * 
		 * @param item
		 * @return
		 */
		default LootPool.Builder basicPool(IItemProvider item)
		{
			return LootPool.builder().addEntry(basicEntry(item));
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
			LootPool.Builder pool = LootPool.builder();
			lootEntries.forEach(entry -> pool.addEntry(entry));
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
			return basicEntry(item).acceptFunction(SetCount.builder(RandomValueRange.of(min, max)));
		}

		/**
		 * Creates a loot entry for the given item. Will only give one item.
		 * 
		 * @param item
		 * @return
		 */
		default ItemLootEntry.Builder<?> basicEntry(IItemProvider item)
		{
			return ItemLootEntry.builder(item);
		}
	}
}
