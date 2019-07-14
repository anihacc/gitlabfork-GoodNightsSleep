package com.legacy.goodnightsleep.world.dream.features;

import java.util.Random;
import java.util.Set;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class DreamTreeFeature extends AbstractTreeFeature<NoFeatureConfig>
{

	private static final IBlockState DEFAULT_TRUNK = BlocksGNS.dream_log.getDefaultState();

	private static final IBlockState DEFAULT_LEAF = BlocksGNS.dream_leaves.getDefaultState();

	/** The minimum height of a generated tree. */
	protected final int minTreeHeight;

	/** The metadata value of the wood to use in tree generation. */
	private final IBlockState metaWood;

	/** The metadata value of the leaves to use in tree generation. */
	private final IBlockState metaLeaves;

	protected net.minecraftforge.common.IPlantable sapling = (net.minecraftforge.common.IPlantable) Blocks.OAK_SAPLING;

	public DreamTreeFeature(boolean p_i2027_1_)
	{
		this(p_i2027_1_, 4, DEFAULT_TRUNK, DEFAULT_LEAF);
	}

	public DreamTreeFeature(boolean notify, int minTreeHeightIn, IBlockState woodMeta, IBlockState p_i46446_4_)
	{
		super(notify);
		this.minTreeHeight = minTreeHeightIn;
		this.metaWood = woodMeta;
		this.metaLeaves = p_i46446_4_;
	}

	public boolean place(Set<BlockPos> changedBlocks, IWorld worldIn, Random rand, BlockPos position)
	{
		int i = this.func_208534_a(rand);
		boolean flag = true;
		if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getWorld().getHeight())
		{
			for (int j = position.getY(); j <= position.getY() + 1 + i; ++j)
			{
				int k = 1;
				if (j == position.getY())
				{
					k = 0;
				}
				if (j >= position.getY() + 1 + i - 2)
				{
					k = 2;
				}
				BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
				for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l)
				{
					for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1)
					{
						if (j >= 0 && j < worldIn.getWorld().getHeight())
						{
							if (!this.canGrowInto(worldIn, blockpos$mutableblockpos.setPos(l, j, i1)))
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
				if (worldIn.getBlockState(position.down()).getBlock() == BlocksGNS.dream_grass && position.getY() < worldIn.getWorld().getHeight() - i - 1)
				{
					this.setDirtAt(worldIn, position.down(), position);
					int k2 = 3;
					int l2 = 0;
					for (int i3 = position.getY() - 3 + i; i3 <= position.getY() + i; ++i3)
					{
						int i4 = i3 - (position.getY() + i);
						int j1 = 1 - i4 / 2;
						for (int k1 = position.getX() - j1; k1 <= position.getX() + j1; ++k1)
						{
							int l1 = k1 - position.getX();
							for (int i2 = position.getZ() - j1; i2 <= position.getZ() + j1; ++i2)
							{
								int j2 = i2 - position.getZ();
								if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0)
								{
									BlockPos blockpos = new BlockPos(k1, i3, i2);
									IBlockState iblockstate = worldIn.getBlockState(blockpos);
									Material material = iblockstate.getMaterial();
									if (iblockstate.canBeReplacedByLeaves(worldIn, blockpos) || material == Material.VINE)
									{
										this.setBlockState(worldIn, blockpos, this.metaLeaves);
									}
								}
							}
						}
					}
					for (int j3 = 0; j3 < i; ++j3)
					{
						IBlockState iblockstate1 = worldIn.getBlockState(position.up(j3));
						Material material1 = iblockstate1.getMaterial();
						if (iblockstate1.canBeReplacedByLeaves(worldIn, position.up(j3)) || material1 == Material.VINE)
						{
							this.func_208520_a(changedBlocks, worldIn, position.up(j3), this.metaWood);
						}
					}
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
	}

	protected int func_208534_a(Random p_208534_1_)
	{
		return this.minTreeHeight + p_208534_1_.nextInt(3);
	}

	public DreamTreeFeature setSapling(net.minecraftforge.common.IPlantable sapling)
	{
		this.sapling = sapling;
		return this;
	}
}