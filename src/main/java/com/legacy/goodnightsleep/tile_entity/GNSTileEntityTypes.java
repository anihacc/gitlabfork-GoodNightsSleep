package com.legacy.goodnightsleep.tile_entity;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("goodnightsleep")
public class GNSTileEntityTypes
{

	public static final TileEntityType<TileEntityLuxuriousBed> LUXURIOUS_BED = register("luxurious_bed", TileEntityType.Builder.create(TileEntityLuxuriousBed::new));
	public static final TileEntityType<TileEntityWretchedBed> WRETCHED_BED = register("wretched_bed", TileEntityType.Builder.create(TileEntityWretchedBed::new));
	public static final TileEntityType<TileEntityStrangeBed> STRANGE_BED = register("strange_bed", TileEntityType.Builder.create(TileEntityStrangeBed::new));

	@SuppressWarnings("deprecation")
	public static <T extends TileEntity> TileEntityType<T> register(String id, TileEntityType.Builder<T> builder)
	{
		Type<?> type = null;
		try
		{
			type = DataFixesManager.getDataFixer().getSchema(DataFixUtils.makeKey(1631)).getChoiceType(TypeReferences.BLOCK_ENTITY, id);
		}
		catch (IllegalArgumentException illegalstateexception)
		{
			if (SharedConstants.developmentMode)
			{
				throw illegalstateexception;
			}
		}
		TileEntityType<T> tileentitytype = builder.build(type);
		IRegistry.field_212626_o.put(new ResourceLocation("goodnightsleep", id), tileentitytype);
		return tileentitytype;
	}
}
