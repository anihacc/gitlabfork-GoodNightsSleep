package com.legacy.goodnightsleep.data;

import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag.Named;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraftforge.common.Tags;

@SuppressWarnings("unused")
public class GNSRecipeProv extends RecipeProvider
{
	private Consumer<FinishedRecipe> con;
	private String hasItem = "has_item";

	public GNSRecipeProv(DataGenerator generatorIn)
	{
		super(generatorIn);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer)
	{
		this.con = consumer;

		// Stone based
		simple2x2(GNSBlocks.delusion_stone, GNSBlocks.delusion_stonebrick, 4);
		slabsStairsWalls(GNSBlocks.delusion_cobblestone, GNSBlocks.delusion_cobblestone_slab, GNSBlocks.delusion_cobblestone_stairs, GNSBlocks.delusion_cobblestone_wall);
		slabsStairsWalls(GNSBlocks.delusion_stonebrick, GNSBlocks.delusion_stonebrick_slab, GNSBlocks.delusion_stonebrick_stairs, GNSBlocks.delusion_stonebrick_wall);
		slabsStairs(GNSBlocks.delusion_stone, GNSBlocks.delusion_stone_slab, GNSBlocks.delusion_stone_stairs);
		stoneCutting(GNSBlocks.delusion_cobblestone, ImmutableList.of(GNSBlocks.delusion_cobblestone_slab, GNSBlocks.delusion_cobblestone_stairs, GNSBlocks.delusion_cobblestone_wall));
		stoneCutting(GNSBlocks.delusion_stonebrick, ImmutableList.of(GNSBlocks.delusion_stonebrick_slab, GNSBlocks.delusion_stonebrick_stairs, GNSBlocks.delusion_stonebrick_wall));

		ShapelessRecipeBuilder.shapeless(GNSBlocks.delusion_button).requires(GNSBlocks.delusion_stone).unlockedBy(hasItem, has(GNSBlocks.delusion_stone)).save(con);
		ShapedRecipeBuilder.shaped(GNSBlocks.delusion_pressure_plate).define('#', GNSBlocks.delusion_stone).pattern("##").unlockedBy(hasItem, has(GNSBlocks.delusion_stone)).save(con);

		// Wood based
		//@formatter:off
		List<WoodMap> woodMapping = ImmutableList.of(
			new WoodMap(GNSItemTags.DREAM_LOGS, GNSBlocks.dream_log, GNSBlocks.dream_wood, GNSBlocks.stripped_dream_log, GNSBlocks.stripped_dream_wood, GNSBlocks.dream_planks, GNSBlocks.dream_slab, GNSBlocks.dream_stairs, GNSBlocks.dream_pressure_plate, GNSBlocks.dream_button, GNSBlocks.dream_door, GNSBlocks.dream_trapdoor, GNSBlocks.dream_fence, GNSBlocks.dream_fence_gate),
			new WoodMap(GNSItemTags.WHITE_LOGS, GNSBlocks.white_log, GNSBlocks.white_wood, GNSBlocks.stripped_white_log, GNSBlocks.stripped_white_wood, GNSBlocks.white_planks, GNSBlocks.white_slab, GNSBlocks.white_stairs, GNSBlocks.white_pressure_plate, GNSBlocks.white_button, GNSBlocks.white_door, GNSBlocks.white_trapdoor, GNSBlocks.white_fence, GNSBlocks.white_fence_gate),
			new WoodMap(GNSItemTags.DEAD_LOGS, GNSBlocks.dead_log, GNSBlocks.dead_wood, GNSBlocks.stripped_dead_log, GNSBlocks.stripped_dead_wood, GNSBlocks.dead_planks, GNSBlocks.dead_slab, GNSBlocks.dead_stairs, GNSBlocks.dead_pressure_plate, GNSBlocks.dead_button, GNSBlocks.dead_door, GNSBlocks.dead_trapdoor, GNSBlocks.dead_fence, GNSBlocks.dead_fence_gate),
			new WoodMap(GNSItemTags.BLOOD_LOGS, GNSBlocks.blood_log, GNSBlocks.blood_wood, GNSBlocks.stripped_blood_log, GNSBlocks.stripped_blood_wood, GNSBlocks.blood_planks, GNSBlocks.blood_slab, GNSBlocks.blood_stairs, GNSBlocks.blood_pressure_plate, GNSBlocks.blood_button, GNSBlocks.blood_door, GNSBlocks.blood_trapdoor, GNSBlocks.blood_fence, GNSBlocks.blood_fence_gate)
				);
		//@formatter:on
		woodMapping.forEach(wood ->
		{
			ShapedRecipeBuilder.shaped(wood.strippedWood, 3).define('#', wood.strippedLog).pattern("##").pattern("##").group("stripped_bark").unlockedBy(hasItem, has(wood.strippedLog)).save(con);
			ShapedRecipeBuilder.shaped(wood.wood, 3).define('#', wood.log).pattern("##").pattern("##").group("bark").unlockedBy(hasItem, has(wood.wood)).save(con);
			ShapelessRecipeBuilder.shapeless(wood.plank, 4).requires(wood.logTag).group("planks").unlockedBy(hasItem, has(wood.logTag)).save(con);
			slabs(wood.plank, wood.slab).group("wooden_slab").save(con);
			stairs(wood.plank, wood.stair).group("wooden_stairs").save(con);
			fencesGates(wood.plank, wood.fence, wood.gate);
			ShapedRecipeBuilder.shaped(wood.door, 3).define('#', wood.plank).pattern("##").pattern("##").pattern("##").group("wooden_door").unlockedBy(hasItem, has(wood.plank)).save(con);
			ShapedRecipeBuilder.shaped(wood.trapdoor, 2).define('#', wood.plank).pattern("###").pattern("###").group("wooden_trapdoor").unlockedBy(hasItem, has(wood.plank)).save(con);
			ShapedRecipeBuilder.shaped(wood.pressurePlate).define('#', wood.plank).pattern("##").group("wooden_pressure_plate").unlockedBy(hasItem, has(wood.plank)).save(con);
			ShapelessRecipeBuilder.shapeless(wood.button).requires(wood.plank).unlockedBy(hasItem, has(wood.plank)).save(con);
		});

		// Armor/Storage blocks
		//@formatter:off
		List<OreMap> materialMapping = ImmutableList.of(
			new OreMap(GNSItemTags.CANDY_BLOCKS, GNSBlocks.candy_block, GNSItemTags.CANDY_BLOCKS, GNSBlocks.candy_block, GNSItems.candy_chestplate, GNSItems.candy_leggings, GNSItems.candy_boots, GNSItems.candy_helmet, GNSItems.candy_sword, GNSItems.candy_pickaxe, GNSItems.candy_axe, GNSItems.candy_shovel, GNSItems.candy_hoe),
			new OreMap(GNSItemTags.RAINBOW_INGOTS, GNSItems.rainbow_ingot, GNSItemTags.RAINBOW_BLOCKS, GNSBlocks.rainbow_block, GNSItems.rainbow_chestplate, GNSItems.rainbow_leggings, GNSItems.rainbow_boots, GNSItems.rainbow_helmet, GNSItems.rainbow_sword, GNSItems.rainbow_pickaxe, GNSItems.rainbow_axe, GNSItems.rainbow_shovel, GNSItems.rainbow_hoe),
			new OreMap(GNSItemTags.POSITITE, GNSItems.positite, GNSItemTags.POSITITE_BLOCKS, GNSBlocks.positite_block, GNSItems.positite_chestplate, GNSItems.positite_leggings, GNSItems.positite_boots, GNSItems.positite_helmet, GNSItems.positite_sword, GNSItems.positite_pickaxe, GNSItems.positite_axe, GNSItems.positite_shovel, GNSItems.positite_hoe),
			new OreMap(GNSItemTags.ZITRITE_INGOTS, GNSItems.zitrite_ingot, GNSItemTags.ZITRITE_BLOCKS, GNSBlocks.zitrite_block, GNSItems.zitrite_chestplate, GNSItems.zitrite_leggings, GNSItems.zitrite_boots, GNSItems.zitrite_helmet, GNSItems.zitrite_sword, GNSItems.zitrite_pickaxe, GNSItems.zitrite_axe, GNSItems.zitrite_shovel, GNSItems.zitrite_hoe),
			new OreMap(GNSItemTags.NEGATITE, GNSItems.negatite, GNSItemTags.NEGATITE_BLOCKS, GNSBlocks.negatite_block, GNSItems.negatite_chestplate, GNSItems.negatite_leggings, GNSItems.negatite_boots, GNSItems.negatite_helmet, GNSItems.negatite_sword, GNSItems.negatite_pickaxe, GNSItems.negatite_axe, GNSItems.negatite_shovel, GNSItems.negatite_hoe)
		);
		//@formatter:on
		materialMapping.forEach(mat ->
		{
			if (mat.material != GNSBlocks.candy_block)
			{
				ShapelessRecipeBuilder.shapeless(mat.material, 9).requires(mat.blockTag).unlockedBy(hasItem, has(mat.blockTag)).save(con, GoodNightSleep.find(mat.material.asItem().getRegistryName().getPath() + "_from_block"));
				ShapedRecipeBuilder.shaped(mat.block).define('#', mat.materialTag).pattern("###").pattern("###").pattern("###").unlockedBy(hasItem, has(mat.materialTag)).save(con);
			}
			else
			{
				simple3x3(GNSItems.candy, GNSBlocks.candy_block, 1);
				ShapelessRecipeBuilder.shapeless(GNSItems.candy, 9).requires(mat.blockTag).unlockedBy(hasItem, has(mat.blockTag)).save(con, GoodNightSleep.find(mat.material.asItem().getRegistryName().getPath() + "_from_block"));
			}

			ShapedRecipeBuilder.shaped(mat.helmet).define('#', mat.materialTag).pattern("###").pattern("# #").unlockedBy(hasItem, has(mat.materialTag)).save(con);
			ShapedRecipeBuilder.shaped(mat.chestplate).define('#', mat.materialTag).pattern("# #").pattern("###").pattern("###").unlockedBy(hasItem, has(mat.materialTag)).save(con);
			ShapedRecipeBuilder.shaped(mat.leggings).define('#', mat.materialTag).pattern("###").pattern("# #").pattern("# #").unlockedBy(hasItem, has(mat.materialTag)).save(con);
			ShapedRecipeBuilder.shaped(mat.boots).define('#', mat.materialTag).pattern("# #").pattern("# #").unlockedBy(hasItem, has(mat.materialTag)).save(con);

			ShapedRecipeBuilder.shaped(mat.sword).define('#', Items.STICK).define('X', mat.materialTag).pattern("X").pattern("X").pattern("#").unlockedBy(hasItem, has(mat.materialTag)).save(con);
			ShapedRecipeBuilder.shaped(mat.pickaxe).define('#', Items.STICK).define('X', mat.materialTag).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy(hasItem, has(mat.materialTag)).save(con);
			ShapedRecipeBuilder.shaped(mat.axe).define('#', Items.STICK).define('X', mat.materialTag).pattern("XX").pattern("X#").pattern(" #").unlockedBy(hasItem, has(mat.materialTag)).save(con);
			ShapedRecipeBuilder.shaped(mat.shovel).define('#', Items.STICK).define('X', mat.materialTag).pattern("X").pattern("#").pattern("#").unlockedBy(hasItem, has(mat.materialTag)).save(con);
			ShapedRecipeBuilder.shaped(mat.hoe).define('#', Items.STICK).define('X', mat.materialTag).pattern("XX").pattern(" #").pattern(" #").unlockedBy(hasItem, has(mat.materialTag)).save(con);
		});

		ShapelessRecipeBuilder.shapeless(GNSItems.necrum, 9).requires(GNSItemTags.NECRUM_BLOCKS).unlockedBy(hasItem, has(GNSItemTags.NECRUM_BLOCKS)).save(con, GoodNightSleep.find("necrum_from_block"));
		ShapedRecipeBuilder.shaped(GNSBlocks.necrum_block).define('#', GNSItemTags.NECRUM).pattern("###").pattern("###").pattern("###").unlockedBy(hasItem, has(GNSItemTags.NECRUM)).save(con);

		ShapedRecipeBuilder.shaped(GNSItems.necrum_sword).define('#', Items.STICK).define('X', GNSItemTags.NECRUM_BLOCKS).pattern("X").pattern("X").pattern("#").unlockedBy(hasItem, has(GNSItemTags.NECRUM_BLOCKS)).save(con);
		ShapedRecipeBuilder.shaped(GNSItems.necrum_pickaxe).define('#', Items.STICK).define('X', GNSItemTags.NECRUM_BLOCKS).pattern("XXX").pattern(" # ").pattern(" # ").unlockedBy(hasItem, has(GNSItemTags.NECRUM_BLOCKS)).save(con);
		ShapedRecipeBuilder.shaped(GNSItems.necrum_axe).define('#', Items.STICK).define('X', GNSItemTags.NECRUM_BLOCKS).pattern("XX").pattern("X#").pattern(" #").unlockedBy(hasItem, has(GNSItemTags.NECRUM_BLOCKS)).save(con);
		ShapedRecipeBuilder.shaped(GNSItems.necrum_shovel).define('#', Items.STICK).define('X', GNSItemTags.NECRUM_BLOCKS).pattern("X").pattern("#").pattern("#").unlockedBy(hasItem, has(GNSItemTags.NECRUM_BLOCKS)).save(con);
		ShapedRecipeBuilder.shaped(GNSItems.necrum_hoe).define('#', Items.STICK).define('X', GNSItemTags.NECRUM_BLOCKS).pattern("XX").pattern(" #").pattern(" #").unlockedBy(hasItem, has(GNSItemTags.NECRUM_BLOCKS)).save(con);
		
		// Misc
		ImmutableMap<ItemLike, ItemLike> flowerDyeMap = ImmutableMap.of(GNSBlocks.cyan_flower, Items.CYAN_DYE, GNSBlocks.orange_flower, Items.ORANGE_DYE, GNSBlocks.dead_flower, Items.GRAY_DYE);
		flowerDyeMap.forEach((flower, dye) -> ShapelessRecipeBuilder.shapeless(dye).requires(flower).unlockedBy(hasItem, has(flower)).save(con, GoodNightSleep.find(dye.asItem().getRegistryName().getPath() + "_from_" + flower.asItem().getRegistryName().getPath())));
		ImmutableMap<ItemLike, ItemLike> cropSeedMap = ImmutableMap.of(GNSItems.rainbow_seeds, GNSItems.rainbow_berries);
		cropSeedMap.forEach((seed, crop) -> ShapelessRecipeBuilder.shapeless(seed).requires(crop).unlockedBy(hasItem, has(crop)).save(con));
		ShapedRecipeBuilder.shaped(Blocks.FURNACE).define('#', GNSItemTags.COBBLESTONES).pattern("###").pattern("# #").pattern("###").unlockedBy(hasItem, has(GNSItemTags.COBBLESTONES)).save(con, GoodNightSleep.find("furnace_compat"));

		ShapelessRecipeBuilder.shapeless(GNSItems.strange_bed).requires(ItemTags.BEDS).requires(GNSBlocks.hope_mushroom).requires(GNSBlocks.despair_mushroom).unlockedBy(hasItem, has(GNSItemTags.MUSHROOMS)).save(con);

		ShapelessRecipeBuilder.shapeless(GNSItems.luxurious_bed).requires(GNSItems.strange_bed).requires(GNSItems.positite).unlockedBy(hasItem, has(GNSItems.strange_bed)).save(con);
		ShapelessRecipeBuilder.shapeless(GNSItems.wretched_bed).requires(GNSItems.strange_bed).requires(GNSItems.negatite).unlockedBy(hasItem, has(GNSItems.strange_bed)).save(con);

		ShapedRecipeBuilder.shaped(GNSBlocks.pot_of_gold.asItem()).define('I', GNSItems.rainbow_ingot).define('G', Blocks.GOLD_BLOCK.asItem()).define('C', Blocks.CAULDRON.asItem()).pattern("III").pattern("IGI").pattern("ICI").unlockedBy(hasItem, has(GNSItems.rainbow_ingot)).save(con);

		// Cooking
		/*blasting(GNSItemTags.CANDY_ORES, GNSItems.candy_ingot, 0.2F);*/
		blasting(GNSItemTags.RAINBOW_ORES, GNSItems.rainbow_ingot, 0.7F);
		blasting(GNSItemTags.POSITITE_ORES, GNSItems.positite, 0.9F);
		blasting(GNSItemTags.NECRUM_ORES, GNSItems.necrum, 0.2F);
		blasting(GNSItemTags.ZITRITE_ORES, GNSItems.zitrite_ingot, 0.9F);
		blasting(GNSItemTags.NEGATITE_ORES, GNSItems.negatite, 1.0F);

		cooking(GNSBlocks.delusion_cobblestone, GNSBlocks.delusion_stone, 0.1F);
	}

	private void simple2x2(ItemLike item, ItemLike output, int amount)
	{
		ShapedRecipeBuilder.shaped(output, amount).define('#', item).pattern("##").pattern("##").unlockedBy(hasItem, has(item)).save(con);
	}

	private void simple2x2(ItemLike item, ItemLike output)
	{
		simple2x2(item, output, 1);
	}

	private void simple3x3(ItemLike item, ItemLike output, int amount)
	{
		ShapedRecipeBuilder.shaped(output, amount).define('#', item).pattern("###").pattern("###").pattern("###").unlockedBy(hasItem, has(item)).save(con);
	}

	private void simple3x3(ItemLike item, ItemLike output)
	{
		simple3x3(item, output, 1);
	}

	private void slabsStairs(ItemLike block, ItemLike slab, ItemLike stair)
	{
		slabs(block, slab).save(con);
		stairs(block, stair).save(con);
	}

	private void slabsStairsWalls(ItemLike block, ItemLike slab, ItemLike stair, ItemLike wall)
	{
		slabsStairs(block, slab, stair);
		walls(block, wall);
	}

	private void slabsStairs(ItemLike block, ItemLike slab, ItemLike stair, boolean withStoneCutting)
	{
		slabsStairs(block, slab, stair);
		stoneCutting(block, ImmutableList.of(slab, stair));
	}

	private void slabsStairsWalls(ItemLike block, ItemLike slab, ItemLike stair, ItemLike wall, boolean withStoneCutting)
	{
		slabsStairsWalls(block, slab, stair, wall);
		stoneCutting(block, ImmutableList.of(slab, stair, wall));
	}

	private ShapedRecipeBuilder slabs(ItemLike ingredient, ItemLike slab)
	{
		return ShapedRecipeBuilder.shaped(slab, 6).define('#', ingredient).pattern("###").unlockedBy(hasItem, has(ingredient));
	}

	private ShapedRecipeBuilder stairs(ItemLike ingredient, ItemLike stair)
	{
		return ShapedRecipeBuilder.shaped(stair, 4).define('#', ingredient).pattern("#  ").pattern("## ").pattern("###").unlockedBy(hasItem, has(ingredient));
	}

	private void walls(ItemLike ingredient, ItemLike wall)
	{
		ShapedRecipeBuilder.shaped(wall, 6).define('#', ingredient).pattern("###").pattern("###").unlockedBy(hasItem, has(ingredient)).save(con);
	}

	private void fencesGates(ItemLike plank, ItemLike fence, ItemLike gate)
	{
		fences(plank, fence);
		gates(plank, gate);
	}

	private void fences(ItemLike plank, ItemLike fence)
	{
		ShapedRecipeBuilder.shaped(fence, 3).define('P', plank).define('S', Ingredient.of(Tags.Items.RODS_WOODEN)).pattern("PSP").pattern("PSP").group("wooden_fence").unlockedBy(hasItem, has(plank)).save(con);
	}

	private void gates(ItemLike plank, ItemLike gate)
	{
		ShapedRecipeBuilder.shaped(gate).define('P', plank).define('S', Ingredient.of(Tags.Items.RODS_WOODEN)).pattern("SPS").pattern("SPS").group("wooden_fence_gate").unlockedBy(hasItem, has(plank)).save(con);
	}

	private void stoneCutting(ItemLike ingredient, ImmutableList<ItemLike> results)
	{
		results.forEach(result ->
		{
			SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), result, result instanceof SlabBlock ? 2 : 1).unlockedBy(hasItem, has(ingredient)).save(con, GoodNightSleep.find(result.asItem().getRegistryName().getPath() + "_stonecutting_" + ingredient.asItem().getRegistryName().getPath()));
		});
	}

	private void cooking(ItemLike ingredient, ItemLike result, float exp)
	{
		cooking(ingredient, result, exp, 200, RecipeSerializer.SMELTING_RECIPE);
	}

	private void cooking(ItemLike ingredient, ItemLike result, float exp, int time, SimpleCookingSerializer<?> type)
	{
		SimpleCookingRecipeBuilder.cooking(Ingredient.of(ingredient), result, exp, time, type).unlockedBy(hasItem, has(ingredient)).save(con, GoodNightSleep.find(result.asItem().getRegistryName().getPath() + "_from_" + type.getRegistryName().getPath()));
	}

	private void smoking(Named<Item> ingredient, ItemLike result, float exp)
	{
		cooking(ingredient, result, exp);
		cooking(ingredient, result, exp, 100, RecipeSerializer.SMOKING_RECIPE);
		cooking(ingredient, result, exp, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE);
	}

	private void blasting(Named<Item> ingredient, ItemLike result, float exp)
	{
		cooking(ingredient, result, exp);
		cooking(ingredient, result, exp, 100, RecipeSerializer.BLASTING_RECIPE);
	}

	private void cooking(Named<Item> ingredient, ItemLike result, float exp)
	{
		cooking(ingredient, result, exp, 200, RecipeSerializer.SMELTING_RECIPE);
	}

	private void cooking(Named<Item> ingredient, ItemLike result, float exp, int time, SimpleCookingSerializer<?> type)
	{
		SimpleCookingRecipeBuilder.cooking(Ingredient.of(ingredient), result, exp, time, type).unlockedBy(hasItem, has(ingredient)).save(con, GoodNightSleep.find(result.asItem().getRegistryName().getPath() + "_from_" + type.getRegistryName().getPath()));
	}

	private void smoking(ItemLike ingredient, ItemLike result, float exp)
	{
		cooking(ingredient, result, exp, 200, RecipeSerializer.SMELTING_RECIPE);
		cooking(ingredient, result, exp, 100, RecipeSerializer.SMOKING_RECIPE);
		cooking(ingredient, result, exp, 600, RecipeSerializer.CAMPFIRE_COOKING_RECIPE);
	}

	private void blasting(ItemLike ingredient, ItemLike result, float exp)
	{
		cooking(ingredient, result, exp, 200, RecipeSerializer.SMELTING_RECIPE);
		cooking(ingredient, result, exp, 100, RecipeSerializer.BLASTING_RECIPE);
	}

	@Override
	public String getName()
	{
		return "Good Night's Sleep Recipes";
	}

	/**
	 * Collection of wood and wood related blocks.
	 * 
	 * @author David
	 *
	 */
	protected static class WoodMap
	{
		public final Named<Item> logTag;
		public final ItemLike log, wood, strippedLog, strippedWood, plank, slab, stair, pressurePlate, button,
				door, trapdoor, fence, gate;

		public WoodMap(Named<Item> bluebrightLogs, ItemLike log, ItemLike wood, ItemLike strippedLog, ItemLike strippedWood, ItemLike plank, ItemLike slab, ItemLike stair, ItemLike pressurePlate, ItemLike button, ItemLike door, ItemLike trapdoor, ItemLike fence, ItemLike gate)
		{
			this.logTag = bluebrightLogs;
			this.log = log;
			this.wood = wood;
			this.strippedLog = strippedLog;
			this.strippedWood = strippedWood;
			this.plank = plank;
			this.slab = slab;
			this.stair = stair;
			this.pressurePlate = pressurePlate;
			this.button = button;
			this.door = door;
			this.trapdoor = trapdoor;
			this.fence = fence;
			this.gate = gate;
		}
	}

	/**
	 * Collection of armor materials
	 * 
	 * @author David
	 *
	 */
	protected static class OreMap
	{
		public final Named<Item> materialTag, blockTag;
		public final ItemLike material, block, chestplate, leggings, boots, helmet, sword, pickaxe, axe, shovel,
				hoe;

		public OreMap(Named<Item> materialTag, ItemLike material, Named<Item> blockTag, ItemLike block, ItemLike chestplate, ItemLike leggings, ItemLike boots, ItemLike helmet, ItemLike sword, ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike hoe)
		{
			this.materialTag = materialTag;
			this.material = material;
			this.blockTag = blockTag;
			this.block = block;
			this.chestplate = chestplate;
			this.leggings = leggings;
			this.boots = boots;
			this.helmet = helmet;

			this.sword = sword;
			this.pickaxe = pickaxe;
			this.axe = axe;
			this.shovel = shovel;
			this.hoe = hoe;
		}
	}
}