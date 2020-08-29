package com.legacy.goodnightsleep.registry;

import java.util.Random;

import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.entity.GNSSpawnerEntity;
import com.legacy.goodnightsleep.entity.HerobrineEntity;
import com.legacy.goodnightsleep.entity.TormenterEntity;
import com.legacy.goodnightsleep.entity.dream.BabyCreeperEntity;
import com.legacy.goodnightsleep.entity.dream.GummyBearEntity;
import com.legacy.goodnightsleep.entity.dream.UnicornEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(GoodNightSleep.MODID)
public class GNSEntityTypes
{
	public static final EntityType<UnicornEntity> UNICORN = buildEntity("unicorn", EntityType.Builder.<UnicornEntity>create(UnicornEntity::new, EntityClassification.CREATURE).size(1.3964844F, 1.6F));
	public static final EntityType<GummyBearEntity> GUMMY_BEAR = buildEntity("gummy_bear", EntityType.Builder.<GummyBearEntity>create(GummyBearEntity::new, EntityClassification.CREATURE).size(0.6F, 0.7F));
	public static final EntityType<BabyCreeperEntity> BABY_CREEPER = buildEntity("baby_creeper", EntityType.Builder.<BabyCreeperEntity>create(BabyCreeperEntity::new, EntityClassification.MONSTER).size(0.6F, 1.3F));
	public static final EntityType<TormenterEntity> TORMENTER = buildEntity("tormenter", EntityType.Builder.create(TormenterEntity::new, EntityClassification.MONSTER));
	public static final EntityType<HerobrineEntity> HEROBRINE = buildEntity("herobrine", EntityType.Builder.create(HerobrineEntity::new, EntityClassification.MONSTER));
	public static final EntityType<GNSSpawnerEntity> SPAWNER_ENTITY = buildEntity("gns_spawner", EntityType.Builder.create(GNSSpawnerEntity::new, EntityClassification.CREATURE).size(1.0F, 1.0F));

	public static void init(Register<EntityType<?>> event)
	{
		GNSRegistry.register(event.getRegistry(), "unicorn", GNSEntityTypes.UNICORN);
		GNSRegistry.register(event.getRegistry(), "gummy_bear", GNSEntityTypes.GUMMY_BEAR);
		GNSRegistry.register(event.getRegistry(), "baby_creeper", GNSEntityTypes.BABY_CREEPER);
		GNSRegistry.register(event.getRegistry(), "tormenter", GNSEntityTypes.TORMENTER);
		GNSRegistry.register(event.getRegistry(), "herobrine", GNSEntityTypes.HEROBRINE);
		GNSRegistry.register(event.getRegistry(), "gns_spawner", GNSEntityTypes.SPAWNER_ENTITY);

		EntitySpawnPlacementRegistry.register(GNSEntityTypes.TORMENTER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
		EntitySpawnPlacementRegistry.register(GNSEntityTypes.HEROBRINE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
		EntitySpawnPlacementRegistry.register(GNSEntityTypes.BABY_CREEPER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);

		EntitySpawnPlacementRegistry.register(GNSEntityTypes.UNICORN, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GNSEntityTypes::animalSpawnConditions);
		EntitySpawnPlacementRegistry.register(GNSEntityTypes.GUMMY_BEAR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GNSEntityTypes::animalSpawnConditions);
		EntitySpawnPlacementRegistry.register(GNSEntityTypes.SPAWNER_ENTITY, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GNSEntityTypes::otherSpawnConditions);

		registerAttributes();
	}
	
	private static void registerAttributes()
	{
		GlobalEntityTypeAttributes.put(GNSEntityTypes.UNICORN, UnicornEntity.func_234237_fg_().create());
		GlobalEntityTypeAttributes.put(GNSEntityTypes.GUMMY_BEAR, MobEntity.func_233666_p_().create());
		GlobalEntityTypeAttributes.put(GNSEntityTypes.BABY_CREEPER, CreeperEntity.func_234278_m_().create());
		GlobalEntityTypeAttributes.put(GNSEntityTypes.TORMENTER, TormenterEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(GNSEntityTypes.HEROBRINE, HerobrineEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(GNSEntityTypes.SPAWNER_ENTITY, MobEntity.func_233666_p_().create());
	}

	public static boolean animalSpawnConditions(EntityType<? extends AnimalEntity> entityIn, IWorld worldIn, SpawnReason reasonIn, BlockPos posIn, Random randIn)
	{
		return worldIn.getBlockState(posIn.down()).getBlock() == GNSBlocks.dream_grass_block && worldIn.getLightSubtracted(posIn, 0) > 8;
	}

	public static boolean otherSpawnConditions(EntityType<? extends MobEntity> entityIn, IWorld worldIn, SpawnReason reasonIn, BlockPos posIn, Random randIn)
	{
		return (worldIn.getBlockState(posIn.down()).getBlock() == GNSBlocks.nightmare_grass_block || worldIn.getBlockState(posIn.down()).getBlock() == GNSBlocks.dream_grass_block) && worldIn.canBlockSeeSky(posIn);
	}

	private static <T extends Entity> EntityType<T> buildEntity(String key, EntityType.Builder<T> builder)
	{
		return builder.build(GoodNightSleep.find(key));
	}
}