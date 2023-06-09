package com.legacy.goodnightsleep.blocks.construction;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGNSSlab extends BlockSlab 
{

	private String name;

	private boolean double_slab;

	public BlockGNSSlab(String name, boolean double_slab, Material materialIn) 
	{
		super(materialIn);
		this.name = name;
		this.double_slab = double_slab;

		this.setDefaultState(double_slab ? this.getDefaultState() : this.getDefaultState().withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM));
		this.setSoundType(materialIn == Material.WOOD ? SoundType.WOOD : SoundType.STONE);
	}

	@Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
    	return new ItemStack(this.getDroppedSlab(), 1, 0);
    }

    public Block getDroppedSlab()
    {
    	if (this == BlocksGNS.dream_double_slab) { return BlocksGNS.dream_slab; }
    	else if (this == BlocksGNS.white_double_slab) { return BlocksGNS.white_slab; }
    	else if (this == BlocksGNS.dead_double_slab) { return BlocksGNS.dead_slab; }
    	else if (this == BlocksGNS.blood_double_slab) { return BlocksGNS.blood_slab; }
    	else { return this; }
    }

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this.getDroppedSlab());
    }

	@Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

	@Override
	public boolean isDouble() 
	{
		return this.double_slab && this.name.contains("double");
	}

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, ItemStack stack)
    {
        IBlockState iblockstate = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer, stack);
        return this.isDouble() ? iblockstate : (facing != EnumFacing.DOWN && (facing == EnumFacing.UP || (double)hitY <= 0.5D) ? iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM) : iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.TOP));
    }

	@Override
    public IBlockState getStateFromMeta(int meta)
    {
    	if (!this.isDouble())
    	{
    		return this.getDefaultState().withProperty(HALF, meta == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
    	}

    	return this.getDefaultState();
    }

	@Override
    public int getMetaFromState(IBlockState state)
    {
		if (!this.isDouble())
		{
			if (state.getValue(HALF) == BlockSlab.EnumBlockHalf.BOTTOM)
			{
				return 0;
			}
			else if (state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
			{
				return 1;
			}
		}

		return 0;
    }

	@Override
    protected BlockStateContainer createBlockState()
    {
    	return this.isDouble() ? new BlockStateContainer(this, new IProperty[0]): new BlockStateContainer(this, new IProperty[] {HALF});
    }

	@Override
	public String getUnlocalizedName(int meta) 
	{
		return "tile." + this.name;
	}

	@Override
	public IProperty<?> getVariantProperty()
	{
		return null;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack)
	{
		return null;
	}

}
