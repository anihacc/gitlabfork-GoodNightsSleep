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
		if (!(this.level instanceof ServerWorld))
			return;

		ServerWorld worldIn = (ServerWorld) this.level;

		DifficultyInstance difficultyIn = this.level.getCurrentDifficultyAt(this.blockPosition());
		int type = random.nextInt(4);
		int chance = random.nextInt(4) + 1;

		BlockPos blockpos = this.level.getHeightmapPos(Type.MOTION_BLOCKING_NO_LEAVES, this.blockPosition()).offset(-2 + this.random.nextInt(4), 0, -2 + this.random.nextInt(4));

		if (worldIn.dimension() != GNSDimensions.getDimensionKeys(false))
		{
			if (type == 0)
			{
				for (int i = 0; i < chance; ++i)
				{
					PigEntity pig = new PigEntity(EntityType.PIG, this.level);
					pig.moveTo(blockpos, 0.0F, 0.0F);
					pig.finalizeSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData) null, (CompoundNBT) null);
					this.level.addFreshEntity(pig);
				}
			}

			if (type == 1)
			{
				for (int i = 0; i < chance; ++i)
				{
					CowEntity cow = new CowEntity(EntityType.COW, this.level);
					cow.moveTo(blockpos, 0.0F, 0.0F);
					cow.finalizeSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData) null, (CompoundNBT) null);
					this.level.addFreshEntity(cow);
				}
			}

			if (type == 2)
			{
				for (int i = 0; i < chance; ++i)
				{
					SheepEntity sheep = new SheepEntity(EntityType.SHEEP, this.level);
					sheep.moveTo(blockpos, 0.0F, 0.0F);
					sheep.finalizeSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData) null, (CompoundNBT) null);

					sheep.setColor(DyeColor.byId(random.nextInt(15)));
					this.level.addFreshEntity(sheep);
				}
			}

			if (type == 3)
			{
				for (int i = 0; i < chance; ++i)
				{
					ChickenEntity chicken = new ChickenEntity(EntityType.CHICKEN, this.level);
					chicken.moveTo(blockpos, 0.0F, 0.0F);
					chicken.finalizeSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData) null, (CompoundNBT) null);
					this.level.addFreshEntity(chicken);
				}
			}
		}
		else if (random.nextBoolean() && worldIn.dimension() == GNSDimensions.getDimensionKeys(false))
		{
			int nType = random.nextInt(2);
			int nChance = random.nextInt(4) + 1;

			if (nType == 0)
			{
				for (int i = 0; i < nChance; ++i)
				{
					ZombieHorseEntity zombhorse = new ZombieHorseEntity(EntityType.ZOMBIE_HORSE, this.level);
					zombhorse.moveTo(blockpos, 0.0F, 0.0F);
					zombhorse.finalizeSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData) null, (CompoundNBT) null);
					this.level.addFreshEntity(zombhorse);
				}
			}

			if (nType == 1)
			{
				for (int i = 0; i < nChance; ++i)
				{
					SkeletonHorseEntity skelehorse = new SkeletonHorseEntity(EntityType.SKELETON_HORSE, this.level);
					skelehorse.moveTo(blockpos, 0.0F, 0.0F);
					skelehorse.finalizeSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData) null, (CompoundNBT) null);
					this.level.addFreshEntity(skelehorse);
				}
			}
		}
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 1;
	}

	@Override
	public void tick()
	{
		super.tick();
		if (this.tickCount > 50 || this.spawnedMobs)
		{
			this.remove();
		}

		if (this.tickCount == 3)
		{
			this.spawnMobs();
			this.spawnedMobs = true;
		}
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld worldIn, AgeableEntity ageableIn)
	{
		return null;
	}
}