package com.legacy.goodnightsleep.items.tools;

import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.item.ItemSword;

public class ItemGNSSword extends ItemSword
{
    public ItemGNSSword(ToolMaterial material)
    {
        super (material);
        this.setCreativeTab(GNSCreativeTabs.tools);
    }
}
