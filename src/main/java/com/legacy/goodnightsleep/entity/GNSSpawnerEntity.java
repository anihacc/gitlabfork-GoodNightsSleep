package com.legacy.goodnightsleep.entity;

public class GNSSpawnerEntity// extends Animal
{
	/*private boolean spawnedMobs;
	
	public GNSSpawnerEntity(EntityType<? extends GNSSpawnerEntity> type, Level worldIn)
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
		if (!(this.level instanceof ServerLevel))
			return;
	
		ServerLevel worldIn = (ServerLevel) this.level;
	
		DifficultyInstance difficultyIn = this.level.getCurrentDifficultyAt(this.blockPosition());
		int type = random.nextInt(4);
		int chance = random.nextInt(4) + 1;
	
		BlockPos blockpos = this.level.getHeightmapPos(Types.MOTION_BLOCKING_NO_LEAVES, this.blockPosition()).offset(-2 + this.random.nextInt(4), 0, -2 + this.random.nextInt(4));
	
		if (worldIn.dimension() != GNSDimensions.getDimensionKeys(false))
		{
			if (type == 0)
			{
				for (int i = 0; i < chance; ++i)
				{
					Pig pig = new Pig(EntityType.PIG, this.level);
					pig.moveTo(blockpos, 0.0F, 0.0F);
					pig.finalizeSpawn(worldIn, difficultyIn, MobSpawnType.NATURAL, (SpawnGroupData) null, (CompoundTag) null);
					this.level.addFreshEntity(pig);
				}
			}
	
			if (type == 1)
			{
				for (int i = 0; i < chance; ++i)
				{
					Cow cow = new Cow(EntityType.COW, this.level);
					cow.moveTo(blockpos, 0.0F, 0.0F);
					cow.finalizeSpawn(worldIn, difficultyIn, MobSpawnType.NATURAL, (SpawnGroupData) null, (CompoundTag) null);
					this.level.addFreshEntity(cow);
				}
			}
	
			if (type == 2)
			{
				for (int i = 0; i < chance; ++i)
				{
					Sheep sheep = new Sheep(EntityType.SHEEP, this.level);
					sheep.moveTo(blockpos, 0.0F, 0.0F);
					sheep.finalizeSpawn(worldIn, difficultyIn, MobSpawnType.NATURAL, (SpawnGroupData) null, (CompoundTag) null);
	
					sheep.setColor(DyeColor.byId(random.nextInt(15)));
					this.level.addFreshEntity(sheep);
				}
			}
	
			if (type == 3)
			{
				for (int i = 0; i < chance; ++i)
				{
					Chicken chicken = new Chicken(EntityType.CHICKEN, this.level);
					chicken.moveTo(blockpos, 0.0F, 0.0F);
					chicken.finalizeSpawn(worldIn, difficultyIn, MobSpawnType.NATURAL, (SpawnGroupData) null, (CompoundTag) null);
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
					ZombieHorse zombhorse = new ZombieHorse(EntityType.ZOMBIE_HORSE, this.level);
					zombhorse.moveTo(blockpos, 0.0F, 0.0F);
					zombhorse.finalizeSpawn(worldIn, difficultyIn, MobSpawnType.NATURAL, (SpawnGroupData) null, (CompoundTag) null);
					this.level.addFreshEntity(zombhorse);
				}
			}
	
			if (nType == 1)
			{
				for (int i = 0; i < nChance; ++i)
				{
					SkeletonHorse skelehorse = new SkeletonHorse(EntityType.SKELETON_HORSE, this.level);
					skelehorse.moveTo(blockpos, 0.0F, 0.0F);
					skelehorse.finalizeSpawn(worldIn, difficultyIn, MobSpawnType.NATURAL, (SpawnGroupData) null, (CompoundTag) null);
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
	public AgableMob getBreedOffspring(ServerLevel worldIn, AgableMob ageableIn)
	{
		return null;
	}*/
}