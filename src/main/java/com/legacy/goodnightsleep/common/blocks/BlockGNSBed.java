package com.legacy.goodnightsleep.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGNSBed extends BlockHorizontal
{
    public static final PropertyEnum<BlockGNSBed.EnumPartType> PART = PropertyEnum.<BlockGNSBed.EnumPartType>create("part", BlockGNSBed.EnumPartType.class);
    protected static final AxisAlignedBB BED_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D);

    public BlockGNSBed()
    {
        super(Material.CLOTH);
        this.setDefaultState(this.blockState.getBaseState().withProperty(PART, BlockGNSBed.EnumPartType.FOOT));
        this.setCreativeTab(null);
    }
    
    /**
     * Called when the block is right clicked by a player.
     */

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

        if (state.getValue(PART) == BlockGNSBed.EnumPartType.HEAD)
        {
            if (worldIn.getBlockState(pos.offset(enumfacing.getOpposite())).getBlock() != this)
            {
                worldIn.setBlockToAir(pos);
            }
        }
        else if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() != this)
        {
            worldIn.setBlockToAir(pos);

            if (!worldIn.isRemote)
            {
                this.dropBlockAsItem(worldIn, pos, state, 0);
            }
        }
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state.getValue(PART) == BlockGNSBed.EnumPartType.HEAD ? Items.AIR : Items.BED;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BED_AABB;
    }

    /**
     * Spawns this Block's drops into the World as EntityItems.
     */
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        if (state.getValue(PART) == BlockGNSBed.EnumPartType.FOOT)
        {
            super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
        }
    }

    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.DESTROY;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(Items.BED);
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (player.capabilities.isCreativeMode && state.getValue(PART) == BlockGNSBed.EnumPartType.HEAD)
        {
            BlockPos blockpos = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());

            if (worldIn.getBlockState(blockpos).getBlock() == this)
            {
                worldIn.setBlockToAir(blockpos);
            }
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getHorizontal(meta);
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(PART, BlockGNSBed.EnumPartType.HEAD).withProperty(FACING, enumfacing) : this.getDefaultState().withProperty(PART, BlockGNSBed.EnumPartType.FOOT).withProperty(FACING, enumfacing);
    }
    
    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();

        return i;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, PART});
    }

    public static enum EnumPartType implements IStringSerializable
    {
        HEAD("head"),
        FOOT("foot");

        private final String name;

        private EnumPartType(String name)
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
    }

}
