package com.legacy.goodnightsleep.world.structures.nightmare;

import java.util.Random;

import com.legacy.goodnightsleep.world.structures.TreePlacer;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DeadTreeGenerator extends TreePlacer
{
	public DeadTreeGenerator(boolean notify) 
	{
		super(notify);
	}

	public boolean generate (World worldIn, Random rand, BlockPos pos) 
	{
		pos = worldIn.getPrecipitationHeight(pos);
		
		NightmareTreePieces tree = new NightmareTreePieces(worldIn, rand);
		
		if (worldIn.getBlockState(pos.down()).getMaterial() == Material.GRASS)
		{
			int variant = rand.nextInt(5);
			
			if (variant == 0)
			{
				placeStructure(worldIn, tree.tree1, pos);
			}
			if (variant == 1)
			{
				placeStructure(worldIn, tree.tree2, pos);
			}
			if (variant == 3)
			{
				placeStructure(worldIn, tree.tree3, pos);
			}
			else
			{
				placeStructure(worldIn, tree.tree4, pos);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public void handleDataMarker(String function, BlockPos pos, World worldIn, Random rand) {}
	
}