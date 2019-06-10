package com.legacy.goodnightsleep.blocks.construction;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockGNSSlab extends BlockSlab
{

	private String name;

	public BlockGNSSlab(String name, boolean double_slab, Material materialIn)
	{
		super(double_slab, materialIn);
		this.name = name;
		this.setLightOpacity(0);
		this.setStepSound(materialIn == Material.wood ? soundTypeWood : soundTypeStone);
	}

	@Override
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
	{
		return Item.getItemFromBlock(this.getDroppedSlab());
	}

	public Block getDroppedSlab()
	{
		if (this == BlocksGNS.dream_double_slab)
		{
			return BlocksGNS.dream_slab;
		}
		else if (this == BlocksGNS.white_double_slab)
		{
			return BlocksGNS.white_slab;
		}
		else if (this == BlocksGNS.dead_double_slab)
		{
			return BlocksGNS.dead_slab;
		}
		else if (this == BlocksGNS.blood_double_slab)
		{
			return BlocksGNS.blood_slab;
		}
		else
		{
			return this;
		}
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this.getDroppedSlab());
	}

	@Override
	public int damageDropped(int meta)
	{
		return 0;
	}

	@Override
	public String func_150002_b(int meta)
	{
		return this.name;
	}
}
