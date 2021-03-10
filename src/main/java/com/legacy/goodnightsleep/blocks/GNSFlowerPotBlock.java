package com.legacy.goodnightsleep.blocks;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.material.Material;

public class GNSFlowerPotBlock extends FlowerPotBlock
{
	public GNSFlowerPotBlock(@Nullable Supplier<FlowerPotBlock> emptyPot, Supplier<? extends Block> flower, Block.Properties properties)
	{
		super(emptyPot, flower, properties);
		((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(flower.get().getRegistryName(), () -> this);
	}

	public GNSFlowerPotBlock(Supplier<? extends Block> flower, Block.Properties properties)
	{
		this(() -> (FlowerPotBlock) Blocks.FLOWER_POT.delegate.get(), flower, properties);
	}

	public GNSFlowerPotBlock(java.util.function.Supplier<? extends Block> flower)
	{
		this(flower, Block.Properties.of(Material.DECORATION).strength(0.0F));
	}
}
