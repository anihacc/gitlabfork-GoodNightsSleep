package com.legacy.goodnightsleep.blocks.construction;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockGNSFence extends BlockFence
{

	public BlockGNSFence()
	{
		super(Material.wood, MapColor.woodColor);

		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeWood);
	}

	/*public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
		if (this == BlocksSkies.bluebright_fence)
		{
			return MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY;
		}
		
		if (this == BlocksSkies.starlit_fence)
		{
			return MapColor.DIAMOND;
		}

		if (this == BlocksSkies.lunar_fence)
		{
			return MapColor.MAGENTA;
		}
		
		if (this == BlocksSkies.dusk_fence)
		{
			return MapColor.PURPLE;
		}

		if (this == BlocksSkies.cherry_fence)
		{
			return MapColor.PINK_STAINED_HARDENED_CLAY;
		}
		
		else
		{
			return MapColor.STONE;
		}
    }*/
}