package com.legacy.goodnightsleep.world.genfeatures;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.natural.BlockGNSFlower;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFoilage extends WorldGenerator
{

    private Block plantState;

    public WorldGenFoilage()
    {

    }

    public void setPlantBlock(Block state)
    {
    	this.plantState = state;
    }

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
        for(int l = 0; l < 64; l++)
        {
            int i1 = (x + random.nextInt(8)) - random.nextInt(8);
            int j1 = (y + random.nextInt(4)) - random.nextInt(4);
            int k1 = (z + random.nextInt(8)) - random.nextInt(8);

            if(world.isAirBlock(i1, j1, k1) && ((BlockGNSFlower)this.plantState).canBlockStay(world, i1, j1, k1))
            {
            	world.setBlock(i1, j1, k1, this.plantState);
            }
        }

		return true;
	}
}