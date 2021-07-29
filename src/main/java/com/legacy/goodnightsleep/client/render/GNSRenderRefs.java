package com.legacy.goodnightsleep.client.render;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;

// ModelLayers
public class GNSRenderRefs
{
	public static final Material LUXURIOUS_BED_MATERIAL = new Material(Sheets.BED_SHEET, GoodNightSleep.locate("entity/luxurious_bed"));
	public static final Material WRETCHED_BED_MATERIAL = new Material(Sheets.BED_SHEET, GoodNightSleep.locate("entity/wretched_bed"));
	public static final Material STRANGE_BED_MATERIAL = new Material(Sheets.BED_SHEET, GoodNightSleep.locate("entity/strange_bed"));

	public static final ModelLayerLocation UNICORN = layer("unicorn");
	public static final ModelLayerLocation GUMMY_BEAR = layer("gummy_bear");
	public static final ModelLayerLocation BABY_CREEPER = layer("baby_creeper");

	public static final ModelLayerLocation TORMENTER = layer("tormenter");
	public static final ModelLayerLocation HEROBRINE = layer("herobrine");

	private static ModelLayerLocation layer(String name)
	{
		return layer(name, "main");
	}

	private static ModelLayerLocation layer(String name, String layer)
	{
		return new ModelLayerLocation(GoodNightSleep.locate(name), layer);
	}
}
