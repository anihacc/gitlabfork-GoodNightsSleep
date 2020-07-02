package com.legacy.goodnightsleep.data;

import java.util.HashMap;
import java.util.Map;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.entity.GNSEntityTypes;
import com.legacy.goodnightsleep.item.GNSItems;
import com.legacy.goodnightsleep.tile_entity.GNSTileEntityTypes;
import com.legacy.goodnightsleep.world.GNSBiomes;
import com.legacy.goodnightsleep.world.GNSDimensions;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class GNSMappingChanges
{
	private static final Map<ResourceLocation, Block> blockRemappings = new HashMap<ResourceLocation, Block>()
	{
		private static final long serialVersionUID = 2729763913422843325L;

		{
			put(GoodNightSleep.locateOld("tall_dream_grass"), GNSBlocks.dream_grass);
			put(GoodNightSleep.locateOld("tall_nightmare_grass"), GNSBlocks.nightmare_grass);
			put(GoodNightSleep.locateOld("dream_grass"), GNSBlocks.dream_grass_block);
			put(GoodNightSleep.locateOld("nightmare_grass"), GNSBlocks.nightmare_grass_block);
			put(GoodNightSleep.locateOld("dream_plank"), GNSBlocks.dream_planks);
			put(GoodNightSleep.locateOld("white_plank"), GNSBlocks.white_planks);
			put(GoodNightSleep.locateOld("dead_plank"), GNSBlocks.dead_planks);
			put(GoodNightSleep.locateOld("blood_plank"), GNSBlocks.blood_planks);
			put(GoodNightSleep.locateOld("rainbow_crop"), GNSBlocks.rainbow_berries);

			put(GoodNightSleep.locateOld("dream_dirt"), GNSBlocks.dream_dirt);
			put(GoodNightSleep.locateOld("dream_farmland"), GNSBlocks.dream_farmland);
		}
	};

	private static final Map<ResourceLocation, Item> itemRemappings = new HashMap<ResourceLocation, Item>()
	{
		private static final long serialVersionUID = 8446754316909741871L;

		{
			blockRemappings.forEach((r, b) -> put(r, b.asItem()));

			put(GoodNightSleep.locateOld("luxurious_bed_item"), GNSItems.luxurious_bed);
			put(GoodNightSleep.locateOld("wretched_bed_item"), GNSItems.wretched_bed);
			put(GoodNightSleep.locateOld("strange_bed_item"), GNSItems.strange_bed);
			put(GoodNightSleep.locateOld("candy_ingot"), GNSItems.candy_ingot);
			put(GoodNightSleep.locateOld("rainbow_ingot"), GNSItems.rainbow_ingot);
			put(GoodNightSleep.locateOld("positite_gem"), GNSItems.positite_gem);
			put(GoodNightSleep.locateOld("necrum"), GNSItems.necrum);
			put(GoodNightSleep.locateOld("zitrite_ingot"), GNSItems.zitrite_ingot);
			put(GoodNightSleep.locateOld("negatite_gem"), GNSItems.negatite_gem);
			put(GoodNightSleep.locateOld("candy_pickaxe"), GNSItems.candy_pickaxe);
			put(GoodNightSleep.locateOld("candy_axe"), GNSItems.candy_axe);
			put(GoodNightSleep.locateOld("candy_shovel"), GNSItems.candy_shovel);
			put(GoodNightSleep.locateOld("candy_hoe"), GNSItems.candy_hoe);
			put(GoodNightSleep.locateOld("candy_sword"), GNSItems.candy_sword);
			put(GoodNightSleep.locateOld("necrum_pickaxe"), GNSItems.necrum_pickaxe);
			put(GoodNightSleep.locateOld("necrum_axe"), GNSItems.necrum_axe);
			put(GoodNightSleep.locateOld("necrum_shovel"), GNSItems.necrum_shovel);
			put(GoodNightSleep.locateOld("necrum_hoe"), GNSItems.necrum_hoe);
			put(GoodNightSleep.locateOld("necrum_sword"), GNSItems.necrum_sword);
			put(GoodNightSleep.locateOld("zitrite_pickaxe"), GNSItems.zitrite_pickaxe);
			put(GoodNightSleep.locateOld("zitrite_axe"), GNSItems.zitrite_axe);
			put(GoodNightSleep.locateOld("zitrite_shovel"), GNSItems.zitrite_shovel);
			put(GoodNightSleep.locateOld("zitrite_hoe"), GNSItems.zitrite_hoe);
			put(GoodNightSleep.locateOld("zitrite_sword"), GNSItems.zitrite_sword);
			put(GoodNightSleep.locateOld("rainbow_pickaxe"), GNSItems.rainbow_pickaxe);
			put(GoodNightSleep.locateOld("rainbow_axe"), GNSItems.rainbow_axe);
			put(GoodNightSleep.locateOld("rainbow_shovel"), GNSItems.rainbow_shovel);
			put(GoodNightSleep.locateOld("rainbow_hoe"), GNSItems.rainbow_hoe);
			put(GoodNightSleep.locateOld("rainbow_sword"), GNSItems.rainbow_sword);
			put(GoodNightSleep.locateOld("positite_pickaxe"), GNSItems.positite_pickaxe);
			put(GoodNightSleep.locateOld("positite_axe"), GNSItems.positite_axe);
			put(GoodNightSleep.locateOld("positite_shovel"), GNSItems.positite_shovel);
			put(GoodNightSleep.locateOld("positite_hoe"), GNSItems.positite_hoe);
			put(GoodNightSleep.locateOld("positite_sword"), GNSItems.positite_sword);
			put(GoodNightSleep.locateOld("negatite_pickaxe"), GNSItems.negatite_pickaxe);
			put(GoodNightSleep.locateOld("negatite_axe"), GNSItems.negatite_axe);
			put(GoodNightSleep.locateOld("negatite_shovel"), GNSItems.negatite_shovel);
			put(GoodNightSleep.locateOld("negatite_hoe"), GNSItems.negatite_hoe);
			put(GoodNightSleep.locateOld("negatite_sword"), GNSItems.negatite_sword);
			put(GoodNightSleep.locateOld("candy_helmet"), GNSItems.candy_helmet);
			put(GoodNightSleep.locateOld("candy_chestplate"), GNSItems.candy_chestplate);
			put(GoodNightSleep.locateOld("candy_leggings"), GNSItems.candy_leggings);
			put(GoodNightSleep.locateOld("candy_boots"), GNSItems.candy_boots);
			put(GoodNightSleep.locateOld("rainbow_helmet"), GNSItems.rainbow_helmet);
			put(GoodNightSleep.locateOld("rainbow_chestplate"), GNSItems.rainbow_chestplate);
			put(GoodNightSleep.locateOld("rainbow_leggings"), GNSItems.rainbow_leggings);
			put(GoodNightSleep.locateOld("rainbow_boots"), GNSItems.rainbow_boots);
			put(GoodNightSleep.locateOld("positite_helmet"), GNSItems.positite_helmet);
			put(GoodNightSleep.locateOld("positite_chestplate"), GNSItems.positite_chestplate);
			put(GoodNightSleep.locateOld("positite_leggings"), GNSItems.positite_leggings);
			put(GoodNightSleep.locateOld("positite_boots"), GNSItems.positite_boots);
			put(GoodNightSleep.locateOld("zitrite_helmet"), GNSItems.zitrite_helmet);
			put(GoodNightSleep.locateOld("zitrite_chestplate"), GNSItems.zitrite_chestplate);
			put(GoodNightSleep.locateOld("zitrite_leggings"), GNSItems.zitrite_leggings);
			put(GoodNightSleep.locateOld("zitrite_boots"), GNSItems.zitrite_boots);
			put(GoodNightSleep.locateOld("negatite_helmet"), GNSItems.negatite_helmet);
			put(GoodNightSleep.locateOld("negatite_chestplate"), GNSItems.negatite_chestplate);
			put(GoodNightSleep.locateOld("negatite_leggings"), GNSItems.negatite_leggings);
			put(GoodNightSleep.locateOld("negatite_boots"), GNSItems.negatite_boots);
			put(GoodNightSleep.locateOld("candy"), GNSItems.candy);
			put(GoodNightSleep.locateOld("lolipop"), GNSItems.lolipop);
			put(GoodNightSleep.locateOld("rainbow_berries"), GNSItems.rainbow_berries);
			put(GoodNightSleep.locateOld("luxurious_soup"), GNSItems.luxurious_soup);
			put(GoodNightSleep.locateOld("wretched_soup"), GNSItems.wretched_soup);
			put(GoodNightSleep.locateOld("powdered_sugar"), GNSItems.powdered_sugar);
			put(GoodNightSleep.locateOld("necrotic_extract"), GNSItems.necrotic_extract);
			put(GoodNightSleep.locateOld("rainbow_seeds"), GNSItems.rainbow_seeds);
			put(GoodNightSleep.locateOld("unicorn_spawn_egg"), GNSItems.unicorn_spawn_egg);
			put(GoodNightSleep.locateOld("gummy_bear_spawn_egg"), GNSItems.gummy_bear_spawn_egg);
			put(GoodNightSleep.locateOld("baby_creeper_spawn_egg"), GNSItems.baby_creeper_spawn_egg);
			put(GoodNightSleep.locateOld("tormenter_spawn_egg"), GNSItems.tormenter_spawn_egg);
			put(GoodNightSleep.locateOld("herobrine_spawn_egg"), GNSItems.herobrine_spawn_egg);
			put(GoodNightSleep.locateOld("giant_spawn_egg"), GNSItems.giant_spawn_egg);
		}
	};

	private static final Map<ResourceLocation, Biome> biomeRemappings = new HashMap<ResourceLocation, Biome>()
	{
		private static final long serialVersionUID = 3505784855800524223L;

		{
			put(GoodNightSleep.locateOld("good_dream_plains"), GNSBiomes.GOOD_DREAM_PLAINS);
			put(GoodNightSleep.locateOld("nightmare_hills"), GNSBiomes.NIGHTMARE_HILLS);
		}
	};

	private static final Map<ResourceLocation, EntityType<?>> entityTypeRemappings = new HashMap<ResourceLocation, EntityType<?>>()
	{
		private static final long serialVersionUID = -3518059723027038971L;

		{
			put(GoodNightSleep.locateOld("baby_creeper"), GNSEntityTypes.BABY_CREEPER);
			put(GoodNightSleep.locateOld("gummy_bear"), GNSEntityTypes.GUMMY_BEAR);
			put(GoodNightSleep.locateOld("herobrine"), GNSEntityTypes.HEROBRINE);
			put(GoodNightSleep.locateOld("tormenter"), GNSEntityTypes.TORMENTER);
			put(GoodNightSleep.locateOld("unicorn"), GNSEntityTypes.UNICORN);
			put(GoodNightSleep.locateOld("gns_spawner"), GNSEntityTypes.SPAWNER_ENTITY);

		}
	};

	private static final Map<ResourceLocation, TileEntityType<?>> tileEntityTypeRemappings = new HashMap<ResourceLocation, TileEntityType<?>>()
	{
		private static final long serialVersionUID = -3518059723027038971L;

		{
			put(GoodNightSleep.locateOld("luxurious_bed"), GNSTileEntityTypes.LUXURIOUS_BED);
			put(GoodNightSleep.locateOld("wretched_bed"), GNSTileEntityTypes.WRETCHED_BED);
			put(GoodNightSleep.locateOld("strange_bed"), GNSTileEntityTypes.STRANGE_BED);
		}
	};

	private static final Map<ResourceLocation, DimensionType> dimTypeRemappings = new HashMap<ResourceLocation, DimensionType>()
	{
		private static final long serialVersionUID = -9007380852518195235L;

		{
			put(GoodNightSleep.locateOld("good_dream"), GNSDimensions.dimensionType(true));
			put(GoodNightSleep.locateOld("nightmare"), GNSDimensions.dimensionType(false));
		}
	};

	private static final Map<ResourceLocation, ModDimension> modDimRemappings = new HashMap<ResourceLocation, ModDimension>()
	{
		private static final long serialVersionUID = -9002350852518195235L;

		{
			put(GoodNightSleep.locateOld("good_dream"), GNSDimensions.dreamDim);
			put(GoodNightSleep.locateOld("nightmare"), GNSDimensions.nightmareDim);
		}
	};

	@SubscribeEvent
	public void blockMapping(RegistryEvent.MissingMappings<Block> event)
	{
		if (event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).findAny().isPresent())
		{
			GoodNightSleep.LOGGER.warn("Found missing block mappings. Attempting to replace them...");
			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(m -> remap(m, blockRemappings));
		}
	}

	@SubscribeEvent
	public void itemMapping(RegistryEvent.MissingMappings<Item> event)
	{
		if (event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).findAny().isPresent())
		{
			GoodNightSleep.LOGGER.warn("Found missing item mappings. Attempting to replace them...");

			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(mapping ->
			{
				if (itemRemappings.containsKey(mapping.key))
					remap(mapping, itemRemappings);
			});

			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(m -> remap(m, itemRemappings));
		}
	}

	@SubscribeEvent
	public void entityTypeMapping(RegistryEvent.MissingMappings<EntityType<?>> event)
	{
		if (event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).findAny().isPresent())
		{
			GoodNightSleep.LOGGER.warn("Found missing entity type mappings. Attempting to replace them...");

			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(mapping ->
			{
				if (entityTypeRemappings.containsKey(mapping.key))
					remap(mapping, entityTypeRemappings);
			});

			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(m -> remap(m, entityTypeRemappings));
		}
	}

	@SubscribeEvent
	public void tileEntityTypeMapping(RegistryEvent.MissingMappings<TileEntityType<?>> event)
	{
		if (event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).findAny().isPresent())
		{
			GoodNightSleep.LOGGER.warn("Found missing tile entity type mappings. Attempting to replace them...");

			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(mapping ->
			{
				if (tileEntityTypeRemappings.containsKey(mapping.key))
					remap(mapping, tileEntityTypeRemappings);
			});

			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(m -> remap(m, tileEntityTypeRemappings));
		}
	}

	@SubscribeEvent
	public void biomeMapping(RegistryEvent.MissingMappings<Biome> event)
	{
		if (event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).findAny().isPresent())
		{
			GoodNightSleep.LOGGER.warn("Found missing biome mappings. Attempting to replace them...");

			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(mapping ->
			{
				if (biomeRemappings.containsKey(mapping.key))
					remap(mapping, biomeRemappings);
			});

			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(m -> remap(m, biomeRemappings));
		}
	}

	@SubscribeEvent
	public void dimTypeMapping(RegistryEvent.MissingMappings<DimensionType> event)
	{
		if (event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).findAny().isPresent())
		{
			GoodNightSleep.LOGGER.warn("Found missing dimension mappings. Attempting to replace them...");

			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(mapping ->
			{
				if (dimTypeRemappings.containsKey(mapping.key))
					remap(mapping, dimTypeRemappings);
			});

			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(m -> remap(m, dimTypeRemappings));
		}
	}

	@SubscribeEvent
	public void modDimMapping(RegistryEvent.MissingMappings<ModDimension> event)
	{
		if (event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).findAny().isPresent())
		{
			GoodNightSleep.LOGGER.warn("Found missing mod dimension mappings. Attempting to replace them...");

			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(mapping ->
			{
				if (modDimRemappings.containsKey(mapping.key))
					remap(mapping, modDimRemappings);
			});

			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(m -> remap(m, modDimRemappings));
		}
	}

	@SubscribeEvent
	public void soundMapping(RegistryEvent.MissingMappings<SoundEvent> event)
	{
		if (event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).findAny().isPresent())
		{
			GoodNightSleep.LOGGER.warn("Found missing sound event mappings. They do not need to be remapped, ignoring them.");
			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(m -> m.ignore());
		}
	}

	@SubscribeEvent
	public void featureMapping(RegistryEvent.MissingMappings<Feature<?>> event)
	{
		if (event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).findAny().isPresent())
		{
			GoodNightSleep.LOGGER.warn("Found missing feature mappings. They do not need to be remapped, ignoring them.");
			event.getAllMappings().stream().filter(m -> m.key.getNamespace().equals(GoodNightSleep.OLD_MODID)).forEach(m -> m.ignore());
		}
	}

	private <T extends IForgeRegistryEntry<T>> void remap(RegistryEvent.MissingMappings.Mapping<T> mapping, Map<ResourceLocation, T> remappings)
	{
		ResourceLocation key = mapping.key;
		if (remappings.containsKey(key))
		{
			mapping.remap(remappings.get(key));
			GoodNightSleep.LOGGER.warn("Replaced " + key + " with " + remappings.get(key).getRegistryName());
		}
		else
		{
			mapping.ignore();
			GoodNightSleep.LOGGER.warn("Could not find a mapping replacement for " + key + ". It was likely intentionally removed in an update.");
		}
	}
}
