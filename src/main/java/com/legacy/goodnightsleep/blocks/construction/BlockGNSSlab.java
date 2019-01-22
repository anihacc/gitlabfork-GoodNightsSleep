package com.legacy.goodnightsleep.blocks.construction;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGNSSlab extends Block
{
    public static final PropertyEnum<EnumBlockHalf> TYPE = PropertyEnum.<EnumBlockHalf>create("half", EnumBlockHalf.class);
    protected static final AxisAlignedBB AABB_BOTTOM_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    protected static final AxisAlignedBB AABB_TOP_HALF = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);

	public BlockGNSSlab(Material materialIn) 
	{
		super(materialIn);
		this.setLightOpacity(255);
		this.setDefaultState(this.getDefaultState().withProperty(TYPE, EnumBlockHalf.BOTTOM));
		this.setSoundType(materialIn == Material.WOOD ? SoundType.WOOD : SoundType.STONE);
	}
	
	public boolean isFullCube(IBlockState state)
    {
        return this.isDouble(state);
    }
	
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        super.getDrops(drops, world, pos, state, 0);
        
        drops.add(new ItemStack(this, this.isDouble(state) ? 1 : 0));
    }

	public BlockFaceShape getBlockFaceShape(IBlockAccess access, IBlockState state, BlockPos pos, EnumFacing enumFacing)
	{
		if (this.isDouble(state))
		{
			return BlockFaceShape.SOLID;
		}
		else if (enumFacing == EnumFacing.UP && state.getValue(TYPE) == EnumBlockHalf.TOP)
		{
			return BlockFaceShape.SOLID;
		}
		else
		{
			return enumFacing == EnumFacing.DOWN && state.getValue(TYPE) == EnumBlockHalf.BOTTOM ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
		}
	}
	
	public boolean isTopSolid(IBlockState state)
    {
        return this.isDouble(state) || state.getValue(TYPE) == EnumBlockHalf.TOP;
    }
	
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        if (this.isDouble(state)) return true;

        EnumBlockHalf side = state.getValue(TYPE);
        return (side == EnumBlockHalf.TOP && face == EnumFacing.UP) || (side == EnumBlockHalf.BOTTOM && face == EnumFacing.DOWN);

    }
	
	@SuppressWarnings({ "unused", "deprecation" })
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing side)
    {
        if (this.isDouble(state))
        {
            return super.shouldSideBeRendered(state, access, pos, side);
        }
        else if (side != EnumFacing.UP && side != EnumFacing.DOWN && !super.shouldSideBeRendered(state, access, pos, side))
        {
            return false;
        }
        else if (false) // Forge: Additional logic breaks doesSideBlockRendering and is no longer useful.
        {
            IBlockState iblockstate = access.getBlockState(pos.offset(side));
            boolean flag = isHalfSlab(iblockstate) && iblockstate.getValue(TYPE) == EnumBlockHalf.TOP;
            boolean flag1 = isHalfSlab(state) && state.getValue(TYPE) == EnumBlockHalf.TOP;

            if (flag1)
            {
                if (side == EnumFacing.DOWN)
                {
                    return true;
                }
                else if (side == EnumFacing.UP && super.shouldSideBeRendered(state, access, pos, side))
                {
                    return true;
                }
                else
                {
                    return !isHalfSlab(iblockstate) || !flag;
                }
            }
            else if (side == EnumFacing.UP)
            {
                return true;
            }
            else if (side == EnumFacing.DOWN && super.shouldSideBeRendered(state, access, pos, side))
            {
                return true;
            }
            else
            {
                return !isHalfSlab(iblockstate) || flag;
            }
        }
        return super.shouldSideBeRendered(state, access, pos, side);
    }
	
	@SideOnly(Side.CLIENT)
    protected static boolean isHalfSlab(IBlockState state)
    {
        return !state.getValue(TYPE).equals(EnumBlockHalf.FULL);
    }
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        if (this.isDouble(state))
        {
            return FULL_BLOCK_AABB;
        }
        else
        {
            return state.getValue(TYPE) == EnumBlockHalf.TOP ? AABB_TOP_HALF : AABB_BOTTOM_HALF;
        }
    }
	 
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        @SuppressWarnings("deprecation")
		IBlockState iblockstate = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
        return (facing != EnumFacing.DOWN && (facing == EnumFacing.UP || (double)hitY <= 0.5D) ? iblockstate.withProperty(TYPE, EnumBlockHalf.BOTTOM) : iblockstate.withProperty(TYPE, EnumBlockHalf.TOP));
    }

	@Override
    public IBlockState getStateFromMeta(int meta)
    {
		switch (meta)
		{
		case 0:
			return getDefaultState().withProperty(TYPE, EnumBlockHalf.BOTTOM);
		case 1:
			return getDefaultState().withProperty(TYPE, EnumBlockHalf.TOP);
		case 2:
			return getDefaultState().withProperty(TYPE, EnumBlockHalf.FULL);
			
		}

    	return this.getDefaultState();
    }

	@Override
    public int getMetaFromState(IBlockState state)
    {
		switch (state.getValue(TYPE))
		{
		case TOP:
			return 0;
		case BOTTOM:
			return 1;
		case FULL:
			return 2;
		}

		return 0;
    }
	
	public Comparable<?> getTypeForItem(ItemStack stack)
	{
		return null;
	}

	@Override
    protected BlockStateContainer createBlockState()
    {
    	return new BlockStateContainer(this, new IProperty[] {TYPE});
    }
	
	private boolean isDouble(IBlockState state)
	{
		return state.getValue(TYPE).equals(EnumBlockHalf.FULL);
	}
	
	public static enum EnumBlockHalf implements IStringSerializable
    {
        TOP("top"),
        BOTTOM("bottom"),
		FULL("full");

        private final String name;

        private EnumBlockHalf(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
        
        public static int getAmount(IBlockState state)
        {
        	return (state.getValue(TYPE) == EnumBlockHalf.FULL) ? 2 : 1;
        }
    }
}