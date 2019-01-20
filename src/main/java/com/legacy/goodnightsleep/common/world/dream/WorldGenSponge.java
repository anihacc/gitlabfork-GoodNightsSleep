package com.legacy.goodnightsleep.common.world.dream;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSponge extends WorldGenerator 
{
   private IBlockState spongeBlockId;

   public WorldGenSponge()
   {
      this.spongeBlockId = Blocks.SPONGE.getStateFromMeta(1);
   }

   @Override
   public boolean generate(World worldIn, Random random, BlockPos pos) 
   {
	   
	   if((worldIn.getBlockState(pos).getMaterial() == Material.WATER || worldIn.getBlockState(pos.down()).getMaterial() == Material.WATER || worldIn.getBlockState(pos.up()).getMaterial() == Material.WATER) && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR) 	    
      {
         int varSize = random.nextInt(6);
         int varSpace = 1 + random.nextInt(3);
         this.generateStalk(worldIn, pos.getX(), pos.getY(), pos.getZ() + varSpace, varSize);
         this.generateStalk(worldIn, pos.getX() + varSpace, pos.getY(), pos.getZ(), varSize - 1);
         this.generateStalk(worldIn, pos.getX() + varSpace, pos.getY(), pos.getZ() + varSpace, varSize - 2);
         this.generateStalk(worldIn, pos.getX(), pos.getY(), pos.getZ(), varSize - 3);
         return true;
         
      }
	   else
	   {
		   return false;
	   }
   }

   private void generateStalk(World world, int j, int k, int l, int varSize) 
   {
      //if(!world.isAirBlock(new BlockPos(j, k - 1, l))) 
    		  {
         for(int i = 0; i < varSize; ++i) 
         {
        	 world.setBlockState(new BlockPos(j, k + i, l), this.spongeBlockId);
         }
      }
   }
}
