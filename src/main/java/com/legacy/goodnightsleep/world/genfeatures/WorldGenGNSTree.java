package com.legacy.goodnightsleep.world.genfeatures;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class WorldGenGNSTree extends WorldGenTrees
{

    /** The minimum height of a generated tree. */
    private final int minTreeHeight;

    /** The metadata value of the wood to use in tree generation. */
    private final Block metaWood;

    /** The metadata value of the leaves to use in tree generation. */
    private final Block metaLeaves;

	public WorldGenGNSTree(boolean p_i46446_1_, int p_i46446_2_, Block wood, Block leaves, boolean p_i46446_5_) 
	{
		super(p_i46446_1_, p_i46446_2_, 0, 0, p_i46446_5_);

        this.minTreeHeight = p_i46446_2_;
        this.metaWood = wood;
        this.metaLeaves = leaves;
	}

	public boolean generate(World worldIn, Random rand, int x, int y, int z)
    {
        int i = rand.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        if (y >= 1 && y + i + 1 <= worldIn.getHeight())
        {
            for (int j = y; j <= y + 1 + i; ++j)
            {
                int k = 1;

                if (j == y)
                {
                    k = 0;
                }

                if (j >= y + 1 + i - 2)
                {
                    k = 2;
                }

                for (int l = z - k; l <= x + k && flag; ++l)
                {
                    for (int i1 = z - k; i1 <= z + k && flag; ++i1)
                    {
                        if (j >= 0 && j < worldIn.getHeight())
                        {
                            if (!this.isReplaceable(worldIn, l, j, i1))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                Block state = worldIn.getBlock(x, y - 1, z);

                if (this.canGrowInto(state))
                {
                    for (int i3 = y - 3 + i; i3 <= y + i; ++i3)
                    {
                        int i4 = i3 - (y + i);
                        int j1 = 1 - i4 / 2;

                        for (int k1 = x - j1; k1 <= x + j1; ++k1)
                        {
                            int l1 = k1 - x;

                            for (int i2 = z - j1; i2 <= z + j1; ++i2)
                            {
                                int j2 = i2 - z;

                                if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0)
                                {
                                    state = worldIn.getBlock(k1, i3, i2);

                                    if (state.isAir(worldIn, k1, i3, i2) || state.isLeaves(worldIn, k1, i3, i2) || state.getMaterial() == Material.vine)
                                    {
                                        this.setBlockAndNotifyAdequately(worldIn, k1, i3, i2, this.metaLeaves, 0);
                                    }
                                }
                            }
                        }
                    }

                    for (int j3 = 0; j3 < i; ++j3)
                    {
                        state = worldIn.getBlock(x, y - 1 + j3, z);

                        if (state.isAir(worldIn, x, y - 1 + j3, z) || state.isLeaves(worldIn, x, y - 1 + j3, z) || state.getMaterial() == Material.plants || state.getMaterial() == Material.vine)
                        {
                            this.setBlockAndNotifyAdequately(worldIn, x, y - 1 + j3, z, this.metaWood, 0);
                        }
                    }
                }
            }
        }

       return false;
    }

    protected boolean canGrowInto(Block blockType)
    {
        Material material = blockType.getMaterial();
        return material == Material.air || material == Material.leaves || material == Material.vine ||  blockType ==  BlocksGNS.dream_grass || blockType == BlocksGNS.nightmare_grass || blockType == BlocksGNS.dream_dirt || blockType == Blocks.dirt;
    }
}