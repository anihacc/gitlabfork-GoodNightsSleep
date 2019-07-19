package com.legacy.goodnightsleep.tile_entity;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("goodnightsleep")
public class GNSTileEntityTypes
{

	public static final TileEntityType<TileEntityLuxuriousBed> LUXURIOUS_BED = register("luxurious_bed", TileEntityType.Builder.create(TileEntityLuxuriousBed::new));
	public static final TileEntityType<TileEntityWretchedBed> WRETCHED_BED = register("wretched_bed", TileEntityType.Builder.create(TileEntityWretchedBed::new));
	public static final TileEntityType<TileEntityStrangeBed> STRANGE_BED = register("strange_bed", TileEntityType.Builder.create(TileEntityStrangeBed::new));

	@SuppressWarnings("deprecation")
	private static <T extends TileEntity> TileEntityType<T> register(String key, TileEntityType.Builder<T> builder)
	{
		Type<?> type = null;
		try
		{
			type = DataFixesManager.getDataFixer().getSchema(DataFixUtils.makeKey(SharedConstants.getVersion().getWorldVersion())).getChoiceType(TypeReferences.BLOCK_ENTITY, key);
		}
		catch (IllegalArgumentException illegalstateexception)
		{
			if (SharedConstants.developmentMode)
			{
				throw illegalstateexception;
			}
		}
		return Registry.register(Registry.BLOCK_ENTITY_TYPE, "goodnightsleep:" + key, builder.build(type));
	}
}
