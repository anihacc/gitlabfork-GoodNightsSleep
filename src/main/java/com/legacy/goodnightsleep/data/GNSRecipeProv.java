package com.legacy.goodnightsleep.data;

import static net.minecraft.item.Items.BOOK;

import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.registry.GNSBlocks;
import com.legacy.goodnightsleep.registry.GNSItems;

import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.data.SingleItemRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;

@SuppressWarnings("unused")
public class GNSRecipeProv extends RecipeProvider
{
	private Consumer<IFinishedRecipe> con;
	private String hasItem = "has_item";

	public GNSRecipeProv(DataGenerator generatorIn)
	{
		super(generatorIn);
	}

	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
	{
		this.con = consumer;

		// Stone based
		simple2x2(GNSBlocks.delusion_stone, GNSBlocks.delusion_stonebrick, 4);
		slabsStairsWalls(GNSBlocks.delusion_cobblestone, GNSBlocks.delusion_cobblestone_slab, GNSBlocks.delusion_cobblestone_stairs, GNSBlocks.delusion_cobblestone_wall);
		slabsStairsWalls(GNSBlocks.delusion_stonebrick, GNSBlocks.delusion_stonebrick_slab, GNSBlocks.delusion_stonebrick_stairs, GNSBlocks.delusion_stonebrick_wall);
		slabsStairs(GNSBlocks.delusion_stone, GNSBlocks.delusion_stone_slab, GNSBlocks.delusion_stone_stairs);
		stoneCutting(GNSBlocks.delusion_cobblestone, ImmutableList.of(GNSBlocks.delusion_cobblestone_slab, GNSBlocks.delusion_cobblestone_stairs, GNSBlocks.delusion_cobblestone_wall));
		stoneCutting(GNSBlocks.delusion_stonebrick, ImmutableList.of(GNSBlocks.delusion_stonebrick_slab, GNSBlocks.delusion_stonebrick_stairs, GNSBlocks.delusion_stonebrick_wall));

		ShapelessRecipeBuilder.shapelessRecipe(GNSBlocks.delusion_button).addIngredient(GNSBlocks.delusion_stone).addCriterion(hasItem, hasItem(GNSBlocks.delusion_stone)).build(con);
		ShapedRecipeBuilder.shapedRecipe(GNSBlocks.delusion_pressure_plate).key('#', GNSBlocks.delusion_stone).patternLine("##").addCriterion(hasItem, hasItem(GNSBlocks.delusion_stone)).build(con);

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
			ShapedRecipeBuilder.shapedRecipe(wood.strippedWood, 3).key('#', wood.strippedLog).patternLine("##").patternLine("##").setGroup("stripped_bark").addCriterion(hasItem, hasItem(wood.strippedLog)).build(con);
			ShapedRecipeBuilder.shapedRecipe(wood.wood, 3).key('#', wood.log).patternLine("##").patternLine("##").setGroup("bark").addCriterion(hasItem, hasItem(wood.wood)).build(con);
			ShapelessRecipeBuilder.shapelessRecipe(wood.plank, 4).addIngredient(wood.logTag).setGroup("planks").addCriterion(hasItem, hasItem(wood.logTag)).build(con);
			slabs(wood.plank, wood.slab).setGroup("wooden_slab").build(con);
			stairs(wood.plank, wood.stair).setGroup("wooden_stairs").build(con);
			fencesGates(wood.plank, wood.fence, wood.gate);
			ShapedRecipeBuilder.shapedRecipe(wood.door, 3).key('#', wood.plank).patternLine("##").patternLine("##").patternLine("##").setGroup("wooden_door").addCriterion(hasItem, hasItem(wood.plank)).build(con);
			ShapedRecipeBuilder.shapedRecipe(wood.trapdoor, 2).key('#', wood.plank).patternLine("###").patternLine("###").setGroup("wooden_trapdoor").addCriterion(hasItem, hasItem(wood.plank)).build(con);
			ShapedRecipeBuilder.shapedRecipe(wood.pressurePlate).key('#', wood.plank).patternLine("##").setGroup("wooden_pressure_plate").addCriterion(hasItem, hasItem(wood.plank)).build(con);
			ShapelessRecipeBuilder.shapelessRecipe(wood.button).addIngredient(wood.plank).addCriterion(hasItem, hasItem(wood.plank)).build(con);
		});

		// Armor/Storage blocks
		//@formatter:off
		List<OreMap> materialMapping = ImmutableList.of(
			new OreMap(GNSItemTags.CANDY_BLOCKS, GNSBlocks.candy_block, GNSItemTags.CANDY_ORES, GNSBlocks.candy_block, GNSItems.candy_chestplate, GNSItems.candy_leggings, GNSItems.candy_boots, GNSItems.candy_helmet, GNSItems.candy_sword, GNSItems.candy_pickaxe, GNSItems.candy_axe, GNSItems.candy_shovel, GNSItems.candy_hoe),
			new OreMap(GNSItemTags.RAINBOW_INGOTS, GNSItems.rainbow_ingot, GNSItemTags.RAINBOW_ORES, GNSBlocks.rainbow_block, GNSItems.rainbow_chestplate, GNSItems.rainbow_leggings, GNSItems.rainbow_boots, GNSItems.rainbow_helmet, GNSItems.rainbow_sword, GNSItems.rainbow_pickaxe, GNSItems.rainbow_axe, GNSItems.rainbow_shovel, GNSItems.rainbow_hoe),
			new OreMap(GNSItemTags.POSITITE, GNSItems.positite, GNSItemTags.POSITITE_ORES, GNSBlocks.positite_block, GNSItems.positite_chestplate, GNSItems.positite_leggings, GNSItems.positite_boots, GNSItems.positite_helmet, GNSItems.positite_sword, GNSItems.positite_pickaxe, GNSItems.positite_axe, GNSItems.positite_shovel, GNSItems.positite_hoe),
			new OreMap(GNSItemTags.ZITRITE_INGOTS, GNSItems.zitrite_ingot, GNSItemTags.ZITRITE_ORES, GNSBlocks.zitrite_block, GNSItems.zitrite_chestplate, GNSItems.zitrite_leggings, GNSItems.zitrite_boots, GNSItems.zitrite_helmet, GNSItems.zitrite_sword, GNSItems.zitrite_pickaxe, GNSItems.zitrite_axe, GNSItems.zitrite_shovel, GNSItems.zitrite_hoe),
			new OreMap(GNSItemTags.NEGATITE, GNSItems.negatite, GNSItemTags.NEGATITE_ORES, GNSBlocks.negatite_block, GNSItems.negatite_chestplate, GNSItems.negatite_leggings, GNSItems.negatite_boots, GNSItems.negatite_helmet, GNSItems.negatite_sword, GNSItems.negatite_pickaxe, GNSItems.negatite_axe, GNSItems.negatite_shovel, GNSItems.negatite_hoe)
		);
		//@formatter:on
		materialMapping.forEach(mat ->
		{
			if (mat.material != GNSBlocks.candy_block)
			{
				ShapelessRecipeBuilder.shapelessRecipe(mat.material, 9).addIngredient(mat.blockTag).addCriterion(hasItem, hasItem(mat.blockTag)).build(con, GoodNightSleep.find(mat.material.asItem().getRegistryName().getPath() + "_from_block"));
				ShapedRecipeBuilder.shapedRecipe(mat.block).key('#', mat.materialTag).patternLine("###").patternLine("###").patternLine("###").addCriterion(hasItem, hasItem(mat.materialTag)).build(con);
			}
			else
			{
				simple3x3(GNSItems.candy, GNSBlocks.candy_block, 1);
				ShapelessRecipeBuilder.shapelessRecipe(GNSItems.candy, 9).addIngredient(mat.blockTag).addCriterion(hasItem, hasItem(mat.blockTag)).build(con, GoodNightSleep.find(mat.material.asItem().getRegistryName().getPath() + "_from_block"));
			}

			ShapedRecipeBuilder.shapedRecipe(mat.helmet).key('#', mat.materialTag).patternLine("###").patternLine("# #").addCriterion(hasItem, hasItem(mat.materialTag)).build(con);
			ShapedRecipeBuilder.shapedRecipe(mat.chestplate).key('#', mat.materialTag).patternLine("# #").patternLine("###").patternLine("###").addCriterion(hasItem, hasItem(mat.materialTag)).build(con);
			ShapedRecipeBuilder.shapedRecipe(mat.leggings).key('#', mat.materialTag).patternLine("###").patternLine("# #").patternLine("# #").addCriterion(hasItem, hasItem(mat.materialTag)).build(con);
			ShapedRecipeBuilder.shapedRecipe(mat.boots).key('#', mat.materialTag).patternLine("# #").patternLine("# #").addCriterion(hasItem, hasItem(mat.materialTag)).build(con);

			ShapedRecipeBuilder.shapedRecipe(mat.sword).key('#', Items.STICK).key('X', mat.materialTag).patternLine("X").patternLine("X").patternLine("#").addCriterion(hasItem, hasItem(mat.materialTag)).build(con);
			ShapedRecipeBuilder.shapedRecipe(mat.pickaxe).key('#', Items.STICK).key('X', mat.materialTag).patternLine("XXX").patternLine(" # ").patternLine(" # ").addCriterion(hasItem, hasItem(mat.materialTag)).build(con);
			ShapedRecipeBuilder.shapedRecipe(mat.axe).key('#', Items.STICK).key('X', mat.materialTag).patternLine("XX").patternLine("X#").patternLine(" #").addCriterion(hasItem, hasItem(mat.materialTag)).build(con);
			ShapedRecipeBuilder.shapedRecipe(mat.shovel).key('#', Items.STICK).key('X', mat.materialTag).patternLine("X").patternLine("#").patternLine("#").addCriterion(hasItem, hasItem(mat.materialTag)).build(con);
			ShapedRecipeBuilder.shapedRecipe(mat.hoe).key('#', Items.STICK).key('X', mat.materialTag).patternLine("XX").patternLine(" #").patternLine(" #").addCriterion(hasItem, hasItem(mat.materialTag)).build(con);

		});

		ShapelessRecipeBuilder.shapelessRecipe(GNSItems.necrum, 9).addIngredient(GNSItemTags.NECRUM_BLOCKS).addCriterion(hasItem, hasItem(GNSItemTags.NECRUM_BLOCKS)).build(con, GoodNightSleep.find("necrum_from_block"));
		ShapedRecipeBuilder.shapedRecipe(GNSBlocks.necrum_block).key('#', GNSItemTags.NECRUM).patternLine("###").patternLine("###").patternLine("###").addCriterion(hasItem, hasItem(GNSItemTags.NECRUM)).build(con);

		// Misc
		ImmutableMap<IItemProvider, IItemProvider> flowerDyeMap = ImmutableMap.of(GNSBlocks.cyan_flower, Items.CYAN_DYE, GNSBlocks.orange_flower, Items.ORANGE_DYE, GNSBlocks.dead_flower, Items.GRAY_DYE);
		flowerDyeMap.forEach((flower, dye) -> ShapelessRecipeBuilder.shapelessRecipe(dye).addIngredient(flower).addCriterion(hasItem, hasItem(flower)).build(con, GoodNightSleep.find(dye.asItem().getRegistryName().getPath() + "_from_" + flower.asItem().getRegistryName().getPath())));
		ImmutableMap<IItemProvider, IItemProvider> cropSeedMap = ImmutableMap.of(GNSItems.rainbow_seeds, GNSItems.rainbow_berries);
		cropSeedMap.forEach((seed, crop) -> ShapelessRecipeBuilder.shapelessRecipe(seed).addIngredient(crop).addCriterion(hasItem, hasItem(crop)).build(con));
		ShapedRecipeBuilder.shapedRecipe(Blocks.FURNACE).key('#', GNSItemTags.COBBLESTONES).patternLine("###").patternLine("# #").patternLine("###").addCriterion(hasItem, hasItem(GNSItemTags.COBBLESTONES)).build(con, GoodNightSleep.find("furnace_compat"));

		ShapelessRecipeBuilder.shapelessRecipe(GNSItems.strange_bed).addIngredient(ItemTags.BEDS).addIngredient(GNSBlocks.hope_mushroom).addIngredient(GNSBlocks.despair_mushroom).addCriterion(hasItem, hasItem(GNSItemTags.MUSHROOMS)).build(con);

		ShapelessRecipeBuilder.shapelessRecipe(GNSItems.luxurious_bed).addIngredient(GNSItems.strange_bed).addIngredient(GNSItems.positite).addCriterion(hasItem, hasItem(GNSItems.strange_bed)).build(con);
		ShapelessRecipeBuilder.shapelessRecipe(GNSItems.wretched_bed).addIngredient(GNSItems.strange_bed).addIngredient(GNSItems.negatite).addCriterion(hasItem, hasItem(GNSItems.strange_bed)).build(con);

		ShapedRecipeBuilder.shapedRecipe(GNSBlocks.pot_of_gold.asItem()).key('I', GNSItems.rainbow_ingot).key('G', Blocks.GOLD_BLOCK.asItem()).key('C', Blocks.CAULDRON.asItem()).patternLine("III").patternLine("IGI").patternLine("ICI").addCriterion(hasItem, hasItem(GNSItems.rainbow_ingot)).build(con);

		// Cooking
		/*blasting(GNSItemTags.CANDY_ORES, GNSItems.candy_ingot, 0.2F);*/
		blasting(GNSItemTags.RAINBOW_ORES, GNSItems.rainbow_ingot, 0.7F);
		blasting(GNSItemTags.POSITITE_ORES, GNSItems.positite, 0.9F);
		blasting(GNSItemTags.NECRUM_ORES, GNSItems.necrum, 0.2F);
		blasting(GNSItemTags.ZITRITE_ORES, GNSItems.zitrite_ingot, 0.9F);
		blasting(GNSItemTags.NEGATITE_ORES, GNSItems.negatite, 1.0F);

		cooking(GNSBlocks.delusion_cobblestone, GNSBlocks.delusion_stone, 0.1F);

	}

	private void simple2x2(IItemProvider item, IItemProvider output, int amount)
	{
		ShapedRecipeBuilder.shapedRecipe(output, amount).key('#', item).patternLine("##").patternLine("##").addCriterion(hasItem, hasItem(item)).build(con);
	}

	private void simple2x2(IItemProvider item, IItemProvider output)
	{
		simple2x2(item, output, 1);
	}

	private void simple3x3(IItemProvider item, IItemProvider output, int amount)
	{
		ShapedRecipeBuilder.shapedRecipe(output, amount).key('#', item).patternLine("###").patternLine("###").patternLine("###").addCriterion(hasItem, hasItem(item)).build(con);
	}

	private void simple3x3(IItemProvider item, IItemProvider output)
	{
		simple3x3(item, output, 1);
	}

	private void slabsStairs(IItemProvider block, IItemProvider slab, IItemProvider stair)
	{
		slabs(block, slab).build(con);
		stairs(block, stair).build(con);
	}

	private void slabsStairsWalls(IItemProvider block, IItemProvider slab, IItemProvider stair, IItemProvider wall)
	{
		slabsStairs(block, slab, stair);
		walls(block, wall);
	}

	private void slabsStairs(IItemProvider block, IItemProvider slab, IItemProvider stair, boolean withStoneCutting)
	{
		slabsStairs(block, slab, stair);
		stoneCutting(block, ImmutableList.of(slab, stair));
	}

	private void slabsStairsWalls(IItemProvider block, IItemProvider slab, IItemProvider stair, IItemProvider wall, boolean withStoneCutting)
	{
		slabsStairsWalls(block, slab, stair, wall);
		stoneCutting(block, ImmutableList.of(slab, stair, wall));
	}

	private ShapedRecipeBuilder slabs(IItemProvider ingredient, IItemProvider slab)
	{
		return ShapedRecipeBuilder.shapedRecipe(slab, 6).key('#', ingredient).patternLine("###").addCriterion(hasItem, hasItem(ingredient));
	}

	private ShapedRecipeBuilder stairs(IItemProvider ingredient, IItemProvider stair)
	{
		return ShapedRecipeBuilder.shapedRecipe(stair, 4).key('#', ingredient).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion(hasItem, hasItem(ingredient));
	}

	private void walls(IItemProvider ingredient, IItemProvider wall)
	{
		ShapedRecipeBuilder.shapedRecipe(wall, 6).key('#', ingredient).patternLine("###").patternLine("###").addCriterion(hasItem, hasItem(ingredient)).build(con);
	}

	private void fencesGates(IItemProvider plank, IItemProvider fence, IItemProvider gate)
	{
		fences(plank, fence);
		gates(plank, gate);
	}

	private void fences(IItemProvider plank, IItemProvider fence)
	{
		ShapedRecipeBuilder.shapedRecipe(fence, 3).key('P', plank).key('S', Ingredient.fromTag(Tags.Items.RODS_WOODEN)).patternLine("PSP").patternLine("PSP").setGroup("wooden_fence").addCriterion(hasItem, hasItem(plank)).build(con);
	}

	private void gates(IItemProvider plank, IItemProvider gate)
	{
		ShapedRecipeBuilder.shapedRecipe(gate).key('P', plank).key('S', Ingredient.fromTag(Tags.Items.RODS_WOODEN)).patternLine("SPS").patternLine("SPS").setGroup("wooden_fence_gate").addCriterion(hasItem, hasItem(plank)).build(con);
	}

	private void stoneCutting(IItemProvider ingredient, ImmutableList<IItemProvider> results)
	{
		results.forEach(result ->
		{
			SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ingredient), result, result instanceof SlabBlock ? 2 : 1).addCriterion(hasItem, hasItem(ingredient)).build(con, GoodNightSleep.find(result.asItem().getRegistryName().getPath() + "_stonecutting_" + ingredient.asItem().getRegistryName().getPath()));
		});
	}

	private void cooking(IItemProvider ingredient, IItemProvider result, float exp)
	{
		cooking(ingredient, result, exp, 200, IRecipeSerializer.SMELTING);
	}

	private void cooking(IItemProvider ingredient, IItemProvider result, float exp, int time, CookingRecipeSerializer<?> type)
	{
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ingredient), result, exp, time, type).addCriterion(hasItem, hasItem(ingredient)).build(con, GoodNightSleep.find(result.asItem().getRegistryName().getPath() + "_from_" + type.getRegistryName().getPath()));
	}

	private void smoking(INamedTag<Item> ingredient, IItemProvider result, float exp)
	{
		cooking(ingredient, result, exp);
		cooking(ingredient, result, exp, 100, IRecipeSerializer.SMOKING);
		cooking(ingredient, result, exp, 600, IRecipeSerializer.CAMPFIRE_COOKING);
	}

	private void blasting(INamedTag<Item> ingredient, IItemProvider result, float exp)
	{
		cooking(ingredient, result, exp);
		cooking(ingredient, result, exp, 100, IRecipeSerializer.BLASTING);
	}

	private void cooking(INamedTag<Item> ingredient, IItemProvider result, float exp)
	{
		cooking(ingredient, result, exp, 200, IRecipeSerializer.SMELTING);
	}

	private void cooking(INamedTag<Item> ingredient, IItemProvider result, float exp, int time, CookingRecipeSerializer<?> type)
	{
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromTag(ingredient), result, exp, time, type).addCriterion(hasItem, hasItem(ingredient)).build(con, GoodNightSleep.find(result.asItem().getRegistryName().getPath() + "_from_" + type.getRegistryName().getPath()));
	}

	private void smoking(IItemProvider ingredient, IItemProvider result, float exp)
	{
		cooking(ingredient, result, exp, 200, IRecipeSerializer.SMELTING);
		cooking(ingredient, result, exp, 100, IRecipeSerializer.SMOKING);
		cooking(ingredient, result, exp, 600, IRecipeSerializer.CAMPFIRE_COOKING);
	}

	private void blasting(IItemProvider ingredient, IItemProvider result, float exp)
	{
		cooking(ingredient, result, exp, 200, IRecipeSerializer.SMELTING);
		cooking(ingredient, result, exp, 100, IRecipeSerializer.BLASTING);
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
		public final INamedTag<Item> logTag;
		public final IItemProvider log, wood, strippedLog, strippedWood, plank, slab, stair, pressurePlate, button,
				door, trapdoor, fence, gate;

		public WoodMap(INamedTag<Item> bluebrightLogs, IItemProvider log, IItemProvider wood, IItemProvider strippedLog, IItemProvider strippedWood, IItemProvider plank, IItemProvider slab, IItemProvider stair, IItemProvider pressurePlate, IItemProvider button, IItemProvider door, IItemProvider trapdoor, IItemProvider fence, IItemProvider gate)
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
		public final INamedTag<Item> materialTag, blockTag;
		public final IItemProvider material, block, chestplate, leggings, boots, helmet, sword, pickaxe, axe, shovel,
				hoe;

		public OreMap(INamedTag<Item> materialTag, IItemProvider material, INamedTag<Item> blockTag, IItemProvider block, IItemProvider chestplate, IItemProvider leggings, IItemProvider boots, IItemProvider helmet, IItemProvider sword, IItemProvider pickaxe, IItemProvider axe, IItemProvider shovel, IItemProvider hoe)
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