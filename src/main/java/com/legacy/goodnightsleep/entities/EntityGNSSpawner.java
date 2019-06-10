package com.legacy.goodnightsleep.entities;

import com.legacy.goodnightsleep.blocks.BlocksGNS;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGNSSpawner extends EntityLiving
{
    public EntityGNSSpawner(World worldIn)
    {
        super(worldIn);
        this.setSize(0.0F, 0.0F);
        //this.setNoAI(true);
    }
    
    /*@Override
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
	            EntityPig pig = new EntityPig(this.worldObj);
	            pig.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
	            pig.onInitialSpawn(this.worldObj.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
	            this.worldObj.spawnEntityInWorld(pig);
	        }
    	}
    	
    	if (type == 1)
    	{
	    	for (int i = 0; i < chance; ++i)
	        {
	            EntityCow cow = new EntityCow(this.worldObj);
	            cow.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
	            cow.onInitialSpawn(this.worldObj.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
	            this.worldObj.spawnEntityInWorld(cow);
	        }
    	}
    	
    	if (type == 2)
    	{
	    	for (int i = 0; i < chance; ++i)
	        {
	            EntitySheep sheep = new EntitySheep(this.worldObj);
	            sheep.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
	            sheep.onInitialSpawn(this.worldObj.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
	            this.worldObj.spawnEntityInWorld(sheep);
	        }
    	}
    	
    	if (type == 3)
    	{
	    	for (int i = 0; i < chance; ++i)
	        {
	            EntityChicken chicken = new EntityChicken(this.worldObj);
	            chicken.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
	            chicken.onInitialSpawn(this.worldObj.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
	            this.worldObj.spawnEntityInWorld(chicken);
	        }
    	}
    	
    	if (type == 4)
    	{
	    	for (int i = 0; i < chance + 2; ++i)
	        {
	            EntityHorse horse = new EntityHorse(this.worldObj);
	            horse.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
	            horse.onInitialSpawn(this.worldObj.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
	            this.worldObj.spawnEntityInWorld(horse);
	        }
    	}
    	
    	//this.setDead();
    	
        return super.onInitialSpawn(difficulty, livingdata);
    }*/
    
    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }
    
    @Override
    public void onUpdate()
    {
    	if (this.ticksExisted > 5)
    	{
    		this.setDead();
    	}
    	super.onUpdate();
    }
    
    @Override
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);
        return this.worldObj.getBlock(i, j - 1, k) == BlocksGNS.dream_grass && this.worldObj.getLightBrightness(i, j, k) > 8 && super.getCanSpawnHere();
    }
}