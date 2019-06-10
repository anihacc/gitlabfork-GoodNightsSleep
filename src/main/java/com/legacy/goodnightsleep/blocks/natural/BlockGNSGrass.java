package com.legacy.goodnightsleep.blocks.natural;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockGNSGrass extends Block
{

	@SideOnly(Side.CLIENT)
	private IIcon blockIconTop;

	public BlockGNSGrass()
	{
		super(Material.grass);

		this.setTickRandomly(true);
		this.setHardness(0.6F);
		this.setStepSound(Block.soundTypeGrass);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry)
	{
		if (this == BlocksGNS.dream_grass)
		{
			this.blockIcon = registry.registerIcon("goodnightsleep:dream_grass_side");
			this.blockIconTop = registry.registerIcon("goodnightsleep:dream_grass_top");
		}
		else
		{
			this.blockIcon = registry.registerIcon("goodnightsleep:nightmare_grass_side");
			this.blockIconTop = registry.registerIcon("goodnightsleep:nightmare_grass_top");
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (this == BlocksGNS.dream_grass)
		{
			return side == 1 ? this.blockIconTop : (side == 0 ? BlocksGNS.dream_dirt.getBlockTextureFromSide(side) : this.blockIcon);
		}
		else
		{
			return side == 1 ? this.blockIconTop : (side == 0 ? Blocks.dirt.getBlockTextureFromSide(side) : this.blockIcon);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		if (side == 1)
		{
			return this.blockIconTop;
		}
		else if (side == 0 && this == BlocksGNS.dream_grass)
		{
			return BlocksGNS.dream_dirt.getBlockTextureFromSide(side);
		}
		else if (side == 0)
		{
			return Blocks.dirt.getBlockTextureFromSide(side);
		}
		return this.blockIcon;
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
	{
		EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
		return plantType == EnumPlantType.Plains;
	}

	public Item getItemDropped(int meta, Random random, int fortune)
    {
		if (this == BlocksGNS.dream_grass)
		{
			return Item.getItemFromBlock(BlocksGNS.dream_dirt);
		}
		else
		{
			return Item.getItemFromBlock(Blocks.dirt);
		}
    }

}
