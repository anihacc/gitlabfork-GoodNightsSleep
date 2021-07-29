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

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;

public class GNSLootProv extends LootTableProvider
{
	public GNSLootProv(DataGenerator dataGeneratorIn)
	{
		super(dataGeneratorIn);

	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables()
	{
		return ImmutableList.of(Pair.of(GNSBlockLoot::new, LootContextParamSets.BLOCK), Pair.of(GNSEntityLoot::new, LootContextParamSets.ENTITY), Pair.of(GNSChestLoot::new, LootContextParamSets.CHEST));
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker)
	{
		map.forEach((location, table) ->
		{
			LootTables.validate(validationtracker, location, table);
		});

	}

	@Override
	public String getName()
	{
		return "Good Night's Sleep Loot Tables";
	}

	private class GNSChestLoot extends ChestLoot implements LootPoolUtil
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

	private class GNSEntityLoot extends EntityLoot implements LootPoolUtil
	{

		@Override
		protected void addTables()
		{
			this.add(GNSEntityTypes.UNICORN, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.LEATHER).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))));
			this.add(GNSEntityTypes.GUMMY_BEAR, LootTable.lootTable());
			this.add(GNSEntityTypes.BABY_CREEPER, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().add(TagEntry.expandTag(ItemTags.CREEPER_DROP_MUSIC_DISCS)).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)))));

			this.add(GNSEntityTypes.HEROBRINE, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(GNSItems.negatite).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))))));
			this.add(GNSEntityTypes.TORMENTER, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(GNSItems.necrum).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))));
		}

		private LootPool.Builder lootingPool(ItemLike item, int min, int max, int minLooting, int maxLooting)
		{
			return basicPool(item, min, max).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(minLooting, maxLooting)));
		}

		private LootPool.Builder smeltingPool(ItemLike item, int min, int max, int minLooting, int maxLooting)
		{
			return lootingPool(item, min, max, minLooting, maxLooting).apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)));
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

	private class GNSBlockLoot extends BlockLoot implements LootPoolUtil
	{
		private final LootItemCondition.Builder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
		private final LootItemCondition.Builder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
		private final LootItemCondition.Builder SILK_TOUCH_OR_SHEARS = SHEARS.or(SILK_TOUCH);
		private final LootItemCondition.Builder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.invert();
		private float[] DEFAULT_SAPLING_DROP_RATES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };

		@Override
		protected void addTables()
		{
			blocks().forEach(block ->
			{
				if (block == GNSBlocks.dream_grass)
					this.add(block, this.dropRainbowSeeds(block));
				else if (block == GNSBlocks.nightmare_grass)
					this.add(block, BlockLoot::createGrassDrops);
				else if (block == GNSBlocks.prickly_nightmare_grass)
					this.add(block, BlockLoot::createGrassDrops);

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
					add(block, BlockLoot::createSlabItemTable);
				else if (block == GNSBlocks.candy_ore)
					add(block, (b) -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(GNSItems.candy).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
				else if (block == GNSBlocks.necrum_ore)
					add(block, (b) -> createSilkTouchDispatchTable(b, applyExplosionDecay(b, LootItem.lootTableItem(GNSItems.necrum).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
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
					add(block, (b) -> createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(Items.LAPIS_LAZULI).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));
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
					LootItemCondition.Builder growthCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GNSRainbowCropBlock.AGE, ((GNSRainbowCropBlock) block).getMaxAge()));

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
			return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
					.add(LootItem.lootTableItem(block).when(SILK_TOUCH).otherwise(LootItem.lootTableItem(GNSBlocks.rainbow_ore).setWeight(50)))
					.add(LootItem.lootTableItem(block).when(SILK_TOUCH).otherwise(LootItem.lootTableItem(Blocks.GOLD_ORE).setWeight(10)))
					.add(LootItem.lootTableItem(block).when(SILK_TOUCH).otherwise(LootItem.lootTableItem(Blocks.IRON_ORE).setWeight(10)))
					.add(LootItem.lootTableItem(block).when(SILK_TOUCH).otherwise(LootItem.lootTableItem(Items.REDSTONE).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))))
					.add(LootItem.lootTableItem(block).when(SILK_TOUCH).otherwise(LootItem.lootTableItem(Items.EMERALD).setWeight(1)))
					.add(LootItem.lootTableItem(block).when(SILK_TOUCH).otherwise(LootItem.lootTableItem(GNSItems.candy).setWeight(20).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))));
		}
		
		protected LootTable.Builder dropPresent(Block block)
		{
			return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
					.add(LootItem.lootTableItem(block).when(SILK_TOUCH).otherwise(LootItem.lootTableItem(GNSItems.rainbow_ingot).setWeight(30)))
					.add(LootItem.lootTableItem(block).when(SILK_TOUCH).otherwise(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(10)))
					.add(LootItem.lootTableItem(block).when(SILK_TOUCH).otherwise(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(10)))
					.add(LootItem.lootTableItem(block).when(SILK_TOUCH).otherwise(LootItem.lootTableItem(Items.REDSTONE).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))))
					.add(LootItem.lootTableItem(block).when(SILK_TOUCH).otherwise(LootItem.lootTableItem(Items.EMERALD).setWeight(5)))
					.add(LootItem.lootTableItem(block).when(SILK_TOUCH).otherwise(LootItem.lootTableItem(GNSItems.candy).setWeight(30).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))))
					.add(LootItem.lootTableItem(block).when(SILK_TOUCH).otherwise(LootItem.lootTableItem(GNSItems.positite).setWeight(5))));
		}
		// @formatter:on

		private Stream<Block> blocks()
		{
			return ForgeRegistries.BLOCKS.getValues().stream().filter(b -> b.getRegistryName().getNamespace().equals(GoodNightSleep.MODID) && !b.getLootTable().equals(BuiltInLootTables.EMPTY));
		}

		private void droppingSeedTag(Block block, Tag.Named<Item> tag)
		{
			this.add(block, createShearsDispatchTable(block, applyExplosionDecay(block, (TagEntry.expandTag(tag).when(LootItemRandomChanceCondition.randomChance(0.125F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2)))));
		}

		protected LootTable.Builder dropRainbowSeeds(Block block)
		{
			return createShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(GNSItems.rainbow_seeds).when(LootItemRandomChanceCondition.randomChance(0.125F)).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))));
		}

		private void silkOrElse(Block withSilk, ItemLike without)
		{
			this.add(withSilk, (b) -> createSingleItemTableWithSilkTouch(b, without));
		}

		private LootTable.Builder leaves(Block block, ItemLike sapling, ItemLike stick)
		{
			return createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(sapling)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, DEFAULT_SAPLING_DROP_RATES))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(NOT_SILK_TOUCH_OR_SHEARS).add(applyExplosionDecay(block, LootItem.lootTableItem(stick).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
		}

		private LootTable.Builder leavesFruit(Block block, ItemLike sapling, ItemLike stick, ItemLike fruit)
		{
			float baseChance = 0.05F;
			float[] fortuneChances = new float[] { 1.11111114F, 1.25F, 1.6666668F, 5.0F };
			return leaves(block, sapling, stick).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(NOT_SILK_TOUCH_OR_SHEARS).add(applyExplosionCondition(block, LootItem.lootTableItem(fruit)).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, baseChance, baseChance * fortuneChances[0], baseChance * fortuneChances[1], baseChance * fortuneChances[2], baseChance * fortuneChances[3]))));
		}

		private LootTable.Builder crop(LootItemCondition.Builder growthCondition, Block block, ItemLike food)
		{
			return crop(growthCondition, block, food, food);
		}

		private LootTable.Builder crop(LootItemCondition.Builder growthCondition, Block block, ItemLike food, ItemLike seed)
		{
			LootPool.Builder seedPool = LootPool.lootPool().add(LootItem.lootTableItem(seed).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3).when(growthCondition)));
			LootPool.Builder foodPool = LootPool.lootPool().when(growthCondition).add(LootItem.lootTableItem(food).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 1)));

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
		default LootPool.Builder basicPool(ItemLike item, int min, int max)
		{
			return LootPool.lootPool().add(basicEntry(item, min, max));
		}

		/**
		 * Creates a loot pool with the given item. Will only give one item.
		 * 
		 * @param item
		 * @return
		 */
		default LootPool.Builder basicPool(ItemLike item)
		{
			return LootPool.lootPool().add(basicEntry(item));
		}

		/**
		 * Creates a loot pool that will give a random item from the list.
		 * 
		 * @param items
		 * @return
		 */
		default LootPool.Builder randItemPool(List<ItemLike> items)
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
		default LootPool.Builder poolOf(List<LootPoolEntryContainer.Builder<?>> lootEntries)
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
		default LootItem.Builder<?> basicEntry(ItemLike item, int min, int max)
		{
			return basicEntry(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
		}

		/**
		 * Creates a loot entry for the given item. Will only give one item.
		 * 
		 * @param item
		 * @return
		 */
		default LootItem.Builder<?> basicEntry(ItemLike item)
		{
			return LootItem.lootTableItem(item);
		}
	}
}
