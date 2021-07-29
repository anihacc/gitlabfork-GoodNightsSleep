package com.legacy.goodnightsleep.registry;

import com.legacy.goodnightsleep.tile_entity.DreamBedBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent.Register;

public class GNSBlockEntityTypes
{
	public static final BlockEntityType<DreamBedBlockEntity> DREAM_BED = BlockEntityType.Builder.of(DreamBedBlockEntity::new, GNSBlocks.luxurious_bed, GNSBlocks.wretched_bed, GNSBlocks.strange_bed).build(null);
	/*public static final BlockEntityType<TileEntityLuxuriousBed> LUXURIOUS_BED = null;
	public static final BlockEntityType<TileEntityWretchedBed> WRETCHED_BED = null;
	public static final BlockEntityType<TileEntityStrangeBed> STRANGE_BED = null;*/

	public static void init(Register<BlockEntityType<?>> event)
	{
		GNSRegistry.register(event.getRegistry(), "dream_bed", DREAM_BED);
		/*GNSRegistry.register(event.getRegistry(), "luxurious_bed", BlockEntityType.Builder.of(TileEntityLuxuriousBed::new, GNSBlocks.luxurious_bed).build(null));
		GNSRegistry.register(event.getRegistry(), "wretched_bed", BlockEntityType.Builder.of(TileEntityWretchedBed::new, GNSBlocks.wretched_bed).build(null));
		GNSRegistry.register(event.getRegistry(), "strange_bed", BlockEntityType.Builder.of(TileEntityStrangeBed::new, GNSBlocks.strange_bed).build(null));*/
	}
}
