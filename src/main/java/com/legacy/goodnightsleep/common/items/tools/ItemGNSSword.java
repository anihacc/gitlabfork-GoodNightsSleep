package com.legacy.goodnightsleep.common.items.tools;

import com.legacy.goodnightsleep.common.registry.GNSCreativeTabs;

import net.minecraft.item.ItemSword;

public class ItemGNSSword extends ItemSword
{
    public ItemGNSSword(ToolMaterial material)
    {
        super (material);
        this.setCreativeTab(GNSCreativeTabs.tools);
    }
}
