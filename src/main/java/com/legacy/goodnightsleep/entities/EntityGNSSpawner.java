package com.legacy.goodnightsleep.entities;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityGNSSpawner extends EntityLiving
{
    public EntityGNSSpawner(World worldIn)
    {
        super(worldIn);
        this.setSize(0.0F, 0.0F);
        this.setNoAI(true);
    }
    
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata)
    {
    	int type = rand.nextInt(5);
    	int chance = rand.nextInt(4) + 1;
    	
    	BlockPos blockpos = (new BlockPos(this)).add(-1 + this.rand.nextInt(1), 1, -1 + this.rand.nextInt(1));
    	
    	//BlockPos blockpos = (new BlockPos(this));
    	
    	if (type == 0)
    	{
	    	for (int i = 0; i < chance; ++i)
	        {
	            EntityPig pig = new EntityPig(this.world);
	            pig.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
	            pig.onInitialSpawn(this.world.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
	            this.world.spawnEntity(pig);
	        }
    	}
    	
    	if (type == 1)
    	{
	    	for (int i = 0; i < chance; ++i)
	        {
	            EntityCow cow = new EntityCow(this.world);
	            cow.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
	            cow.onInitialSpawn(this.world.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
	            this.world.spawnEntity(cow);
	        }
    	}
    	
    	if (type == 2)
    	{
	    	for (int i = 0; i < chance; ++i)
	        {
	            EntitySheep sheep = new EntitySheep(this.world);
	            sheep.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
	            sheep.onInitialSpawn(this.world.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
	            this.world.spawnEntity(sheep);
	        }
    	}
    	
    	if (type == 3)
    	{
	    	for (int i = 0; i < chance; ++i)
	        {
	            EntityChicken chicken = new EntityChicken(this.world);
	            chicken.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
	            chicken.onInitialSpawn(this.world.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
	            this.world.spawnEntity(chicken);
	        }
    	}
    	
    	if (type == 4)
    	{
	    	for (int i = 0; i < chance + 2; ++i)
	        {
	            EntityHorse horse = new EntityHorse(this.world);
	            horse.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
	            horse.onInitialSpawn(this.world.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
	            this.world.spawnEntity(horse);
	        }
    	}
    	
    	this.setDead();
    	
        return super.onInitialSpawn(difficulty, livingdata);
    }
    
    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(i, j, k);
        return this.world.getBlockState(blockpos.down()).getBlock() == BlocksGNS.dream_grass && this.world.getLight(blockpos) > 8 && super.getCanSpawnHere();
    }
}