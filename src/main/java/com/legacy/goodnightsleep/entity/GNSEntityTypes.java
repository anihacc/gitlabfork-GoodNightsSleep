package com.legacy.goodnightsleep.entity;

import java.util.Random;

import com.legacy.goodnightsleep.blocks.BlocksGNS;
import com.legacy.goodnightsleep.entity.dream.BabyCreeperEntity;
import com.legacy.goodnightsleep.entity.dream.GummyBearEntity;
import com.legacy.goodnightsleep.entity.dream.UnicornEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("goodnightsleep")
public class GNSEntityTypes
{

	public static final EntityType<UnicornEntity> UNICORN = register("unicorn", EntityType.Builder.<UnicornEntity>create(UnicornEntity::new, EntityClassification.CREATURE).size(1.3964844F, 1.6F));

	public static final EntityType<GummyBearEntity> GUMMY_BEAR = register("gummy_bear", EntityType.Builder.<GummyBearEntity>create(GummyBearEntity::new, EntityClassification.CREATURE).size(0.6F, 0.7F));
	
	public static final EntityType<BabyCreeperEntity> BABY_CREEPER = register("baby_creeper", EntityType.Builder.<BabyCreeperEntity>create(BabyCreeperEntity::new, EntityClassification.MONSTER).size(0.6F, 1.3F));
	
	public static final EntityType<TormenterEntity> TORMENTER = register("tormenter", EntityType.Builder.create(TormenterEntity::new, EntityClassification.MONSTER));
	
	public static final EntityType<HerobrineEntity> HEROBRINE = register("herobrine", EntityType.Builder.create(HerobrineEntity::new, EntityClassification.MONSTER));

	public static final EntityType<GNSSpawnerEntity> SPAWNER_ENTITY = register("gns_spawner", EntityType.Builder.create(GNSSpawnerEntity::new, EntityClassification.CREATURE).size(0.1F, 0.1F));

	@SuppressWarnings("deprecation")
	private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder)
	{
		return Registry.register(Registry.ENTITY_TYPE, "goodnightsleep:" + id, builder.build(id));
	}
	
	public static boolean dreamAnimalSpawnConditions(EntityType<? extends AnimalEntity> p_223316_0_, IWorld p_223316_1_, SpawnReason p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_)
	{
		return p_223316_1_.getBlockState(p_223316_3_.down()).getBlock() == BlocksGNS.dream_grass_block && p_223316_1_.getLightSubtracted(p_223316_3_, 0) > 8;
	}
	
	public static boolean nightmareAnimalSpawnConditions(EntityType<? extends AnimalEntity> p_223316_0_, IWorld p_223316_1_, SpawnReason p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_)
	{
		return p_223316_1_.getBlockState(p_223316_3_.down()).getBlock() == BlocksGNS.nightmare_grass_block && p_223316_1_.isSkyLightMax(p_223316_3_);
	}
	
	public static boolean otherSpawnConditions(EntityType<? extends MobEntity> p_223316_0_, IWorld p_223316_1_, SpawnReason p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_)
	{
		return (p_223316_1_.getBlockState(p_223316_3_.down()).getBlock() == BlocksGNS.nightmare_grass_block || p_223316_1_.getBlockState(p_223316_3_.down()).getBlock() == BlocksGNS.dream_grass_block) && p_223316_1_.canBlockSeeSky(p_223316_3_);
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