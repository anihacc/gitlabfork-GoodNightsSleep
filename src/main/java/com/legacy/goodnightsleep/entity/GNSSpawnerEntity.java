package com.legacy.goodnightsleep.entity;

import javax.annotation.Nullable;

import com.legacy.goodnightsleep.world.nightmare.NightmareWorldManager;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.horse.SkeletonHorseEntity;
import net.minecraft.entity.passive.horse.ZombieHorseEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap.Type;

public class GNSSpawnerEntity extends MobEntity
{
	private boolean spawnedMobs;

    public GNSSpawnerEntity(EntityType<? extends GNSSpawnerEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.setInvisible(true);
        //this.setSize(0.0F, 0.0F);
    }
    
    @Override
    protected void registerGoals()
    {
    }

    //@Override
    //public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
    public void spawnMobs()
    {
    	World worldIn = this.world;
    	
    	DifficultyInstance difficultyIn = this.world.getDifficultyForLocation(this.getPosition());
    	int type = rand.nextInt(4);
    	int chance =  rand.nextInt(4) + 1;
    	
    	BlockPos blockpos = this.world.getHeight(Type.MOTION_BLOCKING_NO_LEAVES, this.getPosition()).add(-2 + this.rand.nextInt(4), 0, -2 + this.rand.nextInt(4));//(new BlockPos(this)).add(-1 + this.rand.nextInt(1), 1, -1 + this.rand.nextInt(1));
    	
    	//BlockPos blockpos = (new BlockPos(this));
    	
    	if (worldIn.getDimension().getType() != NightmareWorldManager.getDimensionType())
    	{
	    	if (type == 0)
	    	{
		    	for (int i = 0; i < chance; ++i)
		        {
		    		PigEntity pig = new PigEntity(EntityType.PIG, this.world);
		            pig.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
		            pig.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData)null, (CompoundNBT)null);
		            this.world.addEntity(pig);
		        }
	    	}
	    	
	    	if (type == 1)
	    	{
		    	for (int i = 0; i < chance; ++i)
		        {
		            CowEntity cow = new CowEntity(EntityType.COW, this.world);
		            cow.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
		            cow.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData)null, (CompoundNBT)null);
		            this.world.addEntity(cow);
		        }
	    	}
	    	
	    	if (type == 2)
	    	{
		    	for (int i = 0; i < chance; ++i)
		        {
		            SheepEntity sheep = new SheepEntity(EntityType.SHEEP, this.world);
		            sheep.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
		            sheep.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData)null, (CompoundNBT)null);
		            
		            sheep.setFleeceColor(DyeColor.byId(rand.nextInt(15)));
		            this.world.addEntity(sheep);
		        }
	    	}
	    	
	    	if (type == 3)
	    	{
		    	for (int i = 0; i < chance; ++i)
		        {
		            ChickenEntity chicken = new ChickenEntity(EntityType.CHICKEN, this.world);
		            chicken.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
		            chicken.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData)null, (CompoundNBT)null);
		            this.world.addEntity(chicken);
		        }
	    	}
    	}
    	else
    	{
    		int nType = rand.nextInt(2);
    		int nChance = rand.nextInt(4) + 1;

    		if (nType == 0)
	    	{
		    	for (int i = 0; i < nChance; ++i)
		        {
		    		ZombieHorseEntity zombhorse = new ZombieHorseEntity(EntityType.ZOMBIE_HORSE, this.world);
		    		zombhorse.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
		            zombhorse.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData)null, (CompoundNBT)null);
		            zombhorse.setHorseTamed(true);
		            this.world.addEntity(zombhorse);
		        }
	    	}
	    	
	    	if (nType == 1)
	    	{
		    	for (int i = 0; i < nChance; ++i)
		        {
		            SkeletonHorseEntity skelehorse = new SkeletonHorseEntity(EntityType.SKELETON_HORSE, this.world);
		            skelehorse.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
		            skelehorse.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData)null, (CompoundNBT)null);
		            skelehorse.setHorseTamed(true);
		            this.world.addEntity(skelehorse);
		        }
	    	}
    	}
    	
    	//this.setDead();
    	
        //return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }
    
    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }
    
    @Override
    public void tick()
    {
    	super.tick();
    	if (this.ticksExisted > 20 || this.spawnedMobs)
    	{
    		this.remove();
    	}

    	if (this.ticksExisted == 3)
    	{
    		this.spawnMobs();
    		this.spawnedMobs = true;
    	}
    }
}