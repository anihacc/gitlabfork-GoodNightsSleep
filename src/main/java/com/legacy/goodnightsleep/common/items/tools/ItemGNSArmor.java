package com.legacy.goodnightsleep.common.items.tools;

import com.legacy.goodnightsleep.common.registry.GNSCreativeTabs;
import com.legacy.goodnightsleep.common.registry.VariableConstants;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemGNSArmor extends ItemArmor
{
	private String armorName;
	
	public ItemGNSArmor(EntityEquipmentSlot armorType, ArmorMaterial material, String name)
	{
		super(material, 0, armorType);

		this.armorName = name;
		this.setUnlocalizedName(name);
		this.setCreativeTab(GNSCreativeTabs.armor);
		//this.setRegistryName(new ResourceLocation("goodnightsleep", "textures/armor/" + name));
	}
	
	@Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
    	boolean leggings = this.getUnlocalizedName().contains("leggings");
    	String type1 = leggings ? "layer_2" : "layer_1";

        return VariableConstants.MODID + ":" + "textures/armor/" + this.armorName + "_" + type1 + ".png";
    }
}