package com.legacy.goodnightsleep.entity;

import com.legacy.goodnightsleep.registry.GNSDimensions;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
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
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.server.ServerWorld;

public class GNSSpawnerEntity extends AnimalEntity
{
	private boolean spawnedMobs;

	public GNSSpawnerEntity(EntityType<? extends GNSSpawnerEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.setInvisible(true);
	}

	@Override
	protected void registerGoals()
	{
	}

	public void spawnMobs()
	{
		if (!(this.world instanceof ServerWorld))
			return;

		ServerWorld worldIn = (ServerWorld) this.world;

		DifficultyInstance difficultyIn = this.world.getDifficultyForLocation(this.getPosition());
		int type = rand.nextInt(4);
		int chance = rand.nextInt(4) + 1;

		BlockPos blockpos = this.world.getHeight(Type.MOTION_BLOCKING_NO_LEAVES, this.getPosition()).add(-2 + this.rand.nextInt(4), 0, -2 + this.rand.nextInt(4));

		if (worldIn.getDimensionKey() != GNSDimensions.getDimensionKeys(false))
		{
			if (type == 0)
			{
				for (int i = 0; i < chance; ++i)
				{
					PigEntity pig = new PigEntity(EntityType.PIG, this.world);
					pig.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
					pig.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData) null, (CompoundNBT) null);
					this.world.addEntity(pig);
				}
			}

			if (type == 1)
			{
				for (int i = 0; i < chance; ++i)
				{
					CowEntity cow = new CowEntity(EntityType.COW, this.world);
					cow.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
					cow.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData) null, (CompoundNBT) null);
					this.world.addEntity(cow);
				}
			}

			if (type == 2)
			{
				for (int i = 0; i < chance; ++i)
				{
					SheepEntity sheep = new SheepEntity(EntityType.SHEEP, this.world);
					sheep.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
					sheep.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData) null, (CompoundNBT) null);

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
					chicken.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData) null, (CompoundNBT) null);
					this.world.addEntity(chicken);
				}
			}
		}
		else if (rand.nextBoolean() && worldIn.getDimensionKey() == GNSDimensions.getDimensionKeys(false))
		{
			int nType = rand.nextInt(2);
			int nChance = rand.nextInt(4) + 1;

			if (nType == 0)
			{
				for (int i = 0; i < nChance; ++i)
				{
					ZombieHorseEntity zombhorse = new ZombieHorseEntity(EntityType.ZOMBIE_HORSE, this.world);
					zombhorse.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
					zombhorse.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData) null, (CompoundNBT) null);
					this.world.addEntity(zombhorse);
				}
			}

			if (nType == 1)
			{
				for (int i = 0; i < nChance; ++i)
				{
					SkeletonHorseEntity skelehorse = new SkeletonHorseEntity(EntityType.SKELETON_HORSE, this.world);
					skelehorse.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
					skelehorse.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData) null, (CompoundNBT) null);
					this.world.addEntity(skelehorse);
				}
			}
		}
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
		if (this.ticksExisted > 50 || this.spawnedMobs)
		{
			this.remove();
		}

		if (this.ticksExisted == 3)
		{
			this.spawnMobs();
			this.spawnedMobs = true;
		}
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld worldIn, AgeableEntity ageableIn)
	{
		return null;
	}
}