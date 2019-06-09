package com.legacy.goodnightsleep.blocks.natural;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGNSLog extends BlockLog
{

    public static PropertyEnum<BlockLog.EnumAxis> PROPERTY_LOG_AXIS = PropertyEnum.create("axis", BlockLog.EnumAxis.class);

    public BlockGNSLog()
    {
        super();

        if (this != BlocksGNS.dead_log && this != BlocksGNS.blood_log)
        {
        	Blocks.fire.setFireInfo(this, 5, 5);	
        }
        else
        {
        	Blocks.fire.setFireInfo(this, 0, 0);
        }

        this.setHardness(2.0F);
        this.setStepSound(soundTypeWood);
        this.setCreativeTab(GNSCreativeTabs.blocks);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(PROPERTY_LOG_AXIS, BlockLog.EnumAxis.Y));
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        int i = 4;
        int j = i + 1;

        if (worldIn.isAreaLoaded(pos.add(-j, -j, -j), pos.add(j, j, j)))
        {
            for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-i, -i, -i), pos.add(i, i, i)))
            {
                IBlockState iblockstate = worldIn.getBlockState(blockpos);

                if (iblockstate.getBlock().isLeaves(worldIn, blockpos))
                {
                    iblockstate.getBlock().beginLeavesDecay(worldIn, blockpos);
                }
            }
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        BlockLog.EnumAxis axis = BlockLog.EnumAxis.NONE;

        switch (meta & 7)
        {
            case 1:
                axis = BlockLog.EnumAxis.Y;
                break;
            case 2:
                axis = BlockLog.EnumAxis.X;
                break;
            case 3:
                axis = BlockLog.EnumAxis.Z;
                break;
        }

        return this.getDefaultState().withProperty(PROPERTY_LOG_AXIS, axis);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int meta = 0;

        switch (state.getValue(PROPERTY_LOG_AXIS))
        {
            case Y:
                meta |= 1;
                break;
            case X:
                meta |= 2;
                break;
            case Z:
                meta |= 3;
                break;
		default:
			break;
        }

        return meta;
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, PROPERTY_LOG_AXIS);
    }
}
