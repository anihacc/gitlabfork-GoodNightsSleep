package com.legacy.goodnightsleep.client.render;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;

public class GNSRenderRefs
{
	public static final Material LUXURIOUS_BED_MATERIAL = new Material(Sheets.BED_SHEET, GoodNightSleep.locate("entity/luxurious_bed"));
	public static final Material WRETCHED_BED_MATERIAL = new Material(Sheets.BED_SHEET, GoodNightSleep.locate("entity/wretched_bed"));;
	public static final Material STRANGE_BED_MATERIAL = new Material(Sheets.BED_SHEET, GoodNightSleep.locate("entity/strange_bed"));;
}
