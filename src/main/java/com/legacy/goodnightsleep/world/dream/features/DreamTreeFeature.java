package com.legacy.goodnightsleep.world.dream.features;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class DreamTreeFeature extends AbstractTreeFeature<NoFeatureConfig>
{

	private static final BlockState DEFAULT_TRUNK = GNSBlocks.dream_log.getDefaultState();

	private static final BlockState DEFAULT_LEAF = GNSBlocks.dream_leaves.getDefaultState();

	protected final int minTreeHeight;

	private final BlockState trunk;

	private final BlockState leaf;

	public DreamTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn, boolean doBlockNotifyOnPlace)
	{
		this(configFactoryIn, doBlockNotifyOnPlace, 4, DEFAULT_TRUNK, DEFAULT_LEAF);
	}

	public DreamTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn, boolean doBlockNotifyOnPlace, int minTreeHeightIn, BlockState trunkState, BlockState leafState)
	{
		super(configFactoryIn, doBlockNotifyOnPlace);
		this.minTreeHeight = minTreeHeightIn;
		this.trunk = trunkState;
		this.leaf = leafState;
	}

	public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox p_208519_5_)
	{
		int i = this.getHeight(rand);
		boolean flag = true;
		if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getMaxHeight())
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
						if (j >= 0 && j < worldIn.getMaxHeight())
						{
							if (!func_214587_a(worldIn, blockpos$mutableblockpos.setPos(l, j, i1)))
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
			else if (isSoil(worldIn, position.down(), getSapling()) && position.getY() < worldIn.getMaxHeight() - i - 1)
			{
				this.setDirtAt(worldIn, position.down(), position);
				for (int l2 = position.getY() - 3 + i; l2 <= position.getY() + i; ++l2)
				{
					int l3 = l2 - (position.getY() + i);
					int j4 = 1 - l3 / 2;
					for (int j1 = position.getX() - j4; j1 <= position.getX() + j4; ++j1)
					{
						int k1 = j1 - position.getX();
						for (int l1 = position.getZ() - j4; l1 <= position.getZ() + j4; ++l1)
						{
							int i2 = l1 - position.getZ();
							if (Math.abs(k1) != j4 || Math.abs(i2) != j4 || rand.nextInt(2) != 0 && l3 != 0)
							{
								BlockPos blockpos = new BlockPos(j1, l2, l1);
								if (isAirOrLeaves(worldIn, blockpos) || isAir(worldIn, blockpos))
								{
									this.setLogState(changedBlocks, worldIn, blockpos, this.leaf, p_208519_5_);
								}
							}
						}
					}
				}
				for (int i3 = 0; i3 < i; ++i3)
				{
					if (isAirOrLeaves(worldIn, position.up(i3)) || isAir(worldIn, position.up(i3)))
					{
						this.setLogState(changedBlocks, worldIn, position.up(i3), this.trunk, p_208519_5_);
					}
				}
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	protected int getHeight(Random random)
	{
		return this.minTreeHeight + random.nextInt(3);
	}
	
	@SuppressWarnings("deprecation")
	protected static boolean func_214587_a(IWorldGenerationBaseReader p_214587_0_, BlockPos p_214587_1_)
	{
		if (!(p_214587_0_ instanceof net.minecraft.world.IWorldReader))
			return p_214587_0_.hasBlockState(p_214587_1_, (p_214573_0_) -> {
				Block block = p_214573_0_.getBlock();
				return p_214573_0_.isAir() || p_214573_0_.isIn(BlockTags.LEAVES) || block == GNSBlocks.dream_grass_block || block == GNSBlocks.nightmare_grass_block || block == GNSBlocks.nightmare_grass_block || Block.isDirt(block) || block.isIn(BlockTags.LOGS) || block.isIn(BlockTags.SAPLINGS) || block == Blocks.VINE;
			});
		else
			return p_214587_0_.hasBlockState(p_214587_1_, state -> state.canBeReplacedByLogs((net.minecraft.world.IWorldReader) p_214587_0_, p_214587_1_));
	}

	protected static boolean isSoil(IWorldGenerationBaseReader reader, BlockPos pos, net.minecraftforge.common.IPlantable sapling)
	{
		return reader.hasBlockState(pos, state -> state.canSustainPlant((net.minecraft.world.IBlockReader) reader, pos, Direction.UP, sapling));
	}
	
	protected static boolean isDirtOrGrassBlock(IWorldGenerationBaseReader worldIn, BlockPos pos)
	{
		return worldIn.hasBlockState(pos, (p_214582_0_) -> {
			Block block = p_214582_0_.getBlock();
			return Block.isDirt(block) || block == GNSBlocks.dream_grass_block || block == GNSBlocks.dream_dirt || block == GNSBlocks.nightmare_grass_block;
		});
	}
}