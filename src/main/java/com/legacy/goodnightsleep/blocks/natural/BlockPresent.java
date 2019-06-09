package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockPresent extends Block
{
	private int quantityDropped;

	private int randomItemSwitch;

	protected BlockPresent()
	{
		super(Material.GRASS);
		this.setCreativeTab(GNSCreativeTabs.blocks);
	}

	public int func_71885_a(int par1, Random par2Random, int par3)
	{
		/*switch (this.randomItemSwitch)
		{
			case 0:
				return ItemsGNS.rainbow_ingot;
			case 1:
				return ItemsGNS.positite_gem;
			case 2:
				return BlocksGNS.candy_block;
			case 3:
				return Items.field_77702_n.field_77779_bT;
			case 4:
				return Items.field_77817_bH.field_77779_bT;
			case 5:
				return Items.field_77717_p.field_77779_bT;
			case 6:
				return Items.field_77746_aZ.field_77779_bT;
			case 7:
				return Items.field_77705_m.field_77779_bT;
			default:
				return ItemsGNS.rainbow_berries;
		}*/
		return 1;
	}

	public int func_71925_a(Random random)
	{
		this.randomItemSwitch = random.nextInt(12);
		this.quantityDropped = 1;
		if (this.randomItemSwitch > 7)
		{
			this.quantityDropped = 4;
		}
		return this.quantityDropped;
	}
}
