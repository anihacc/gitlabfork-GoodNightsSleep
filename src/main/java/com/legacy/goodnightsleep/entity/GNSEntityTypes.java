package com.legacy.goodnightsleep.entity;

import com.legacy.goodnightsleep.entity.dream.BabyCreeperEntity;
import com.legacy.goodnightsleep.entity.dream.GummyBearEntity;
import com.legacy.goodnightsleep.entity.dream.UnicornEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("goodnightsleep")
public class GNSEntityTypes
{

	public static final EntityType<UnicornEntity> UNICORN = register("unicorn", EntityType.Builder.create(UnicornEntity::new, EntityClassification.CREATURE));

	public static final EntityType<GummyBearEntity> GUMMY_BEAR = register("gummy_bear", EntityType.Builder.create(GummyBearEntity::new, EntityClassification.CREATURE));
	
	public static final EntityType<BabyCreeperEntity> BABY_CREEPER = register("baby_creeper", EntityType.Builder.create(BabyCreeperEntity::new, EntityClassification.MONSTER));
	
	public static final EntityType<TormenterEntity> TORMENTER = register("tormenter", EntityType.Builder.create(TormenterEntity::new, EntityClassification.MONSTER));
	
	public static final EntityType<HerobrineEntity> HEROBRINE = register("herobrine", EntityType.Builder.create(HerobrineEntity::new, EntityClassification.MONSTER));

	@SuppressWarnings("deprecation")
	private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder)
	{
		return Registry.register(Registry.ENTITY_TYPE, "goodnightsleep:" + id, builder.build(id));
	}
	
	/*public static boolean animalSpawnConditions(EntityType<? extends AnimalEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random rand)
	{
		Block block = world.getBlockState(pos.down()).getBlock();
		return (block == BlocksSkies.turquoise_grass_block || block == BlocksSkies.lunar_grass_block || block == BlocksSkies.crystal_sand) && world.getLightSubtracted(pos, 0) > 8;
	}
	
	public static boolean mobSpawnConditions(EntityType<? extends MobEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random rand)
	{
		Block block = world.getBlockState(pos.down()).getBlock();
		return (block == BlocksSkies.turquoise_grass_block || block == BlocksSkies.lunar_grass_block || block == BlocksSkies.crystal_sand) && world.getLightSubtracted(pos, 0) > 8;
	}
	
	public static boolean monsterSpawnConditions(EntityType<? extends MobEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random rand)
	{
		Block block = world.getBlockState(pos.down()).getBlock();
		return (block == BlocksSkies.turquoise_grass_block || block == BlocksSkies.lunar_grass_block || block == BlocksSkies.crystal_sand || block.getRegistryName().toString().contains("stone")) && rand.nextInt(200) == 0 && world.getDifficulty() != Difficulty.PEACEFUL;
	}*/
}