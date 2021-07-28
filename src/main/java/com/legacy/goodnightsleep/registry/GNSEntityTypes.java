package com.legacy.goodnightsleep.registry;

import java.util.Random;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

public class GNSEntityTypes
{
	public static final EntityType<Pig> UNICORN = EntityType.PIG; //buildEntity("unicorn", EntityType.Builder.<UnicornEntity>of(UnicornEntity::new, MobCategory.CREATURE).sized(1.3964844F, 1.6F));
	public static final EntityType<Pig> GUMMY_BEAR = EntityType.PIG; //buildEntity("gummy_bear", EntityType.Builder.<GummyBearEntity>of(GummyBearEntity::new, MobCategory.CREATURE).sized(0.6F, 0.7F));
	public static final EntityType<Pig> BABY_CREEPER = EntityType.PIG; //buildEntity("baby_creeper", EntityType.Builder.<BabyCreeperEntity>of(BabyCreeperEntity::new, MobCategory.MONSTER).sized(0.6F, 1.3F));
	public static final EntityType<Pig> TORMENTER = EntityType.PIG; //buildEntity("tormenter", EntityType.Builder.of(TormenterEntity::new, MobCategory.MONSTER));
	public static final EntityType<Pig> HEROBRINE = EntityType.PIG; //buildEntity("herobrine", EntityType.Builder.of(HerobrineEntity::new, MobCategory.MONSTER));

	public static void init(Register<EntityType<?>> event)
	{
		/*GNSRegistry.register(event.getRegistry(), "unicorn", GNSEntityTypes.UNICORN);
		GNSRegistry.register(event.getRegistry(), "gummy_bear", GNSEntityTypes.GUMMY_BEAR);
		GNSRegistry.register(event.getRegistry(), "baby_creeper", GNSEntityTypes.BABY_CREEPER);
		GNSRegistry.register(event.getRegistry(), "tormenter", GNSEntityTypes.TORMENTER);
		GNSRegistry.register(event.getRegistry(), "herobrine", GNSEntityTypes.HEROBRINE);
		
		SpawnPlacements.register(GNSEntityTypes.TORMENTER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(GNSEntityTypes.HEROBRINE, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(GNSEntityTypes.BABY_CREEPER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		
		SpawnPlacements.register(GNSEntityTypes.UNICORN, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GNSEntityTypes::animalSpawnConditions);
		SpawnPlacements.register(GNSEntityTypes.GUMMY_BEAR, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GNSEntityTypes::animalSpawnConditions);*/
	}

	public static void onAttributesRegistered(EntityAttributeCreationEvent event)
	{
		/*event.put(GNSEntityTypes.UNICORN, UnicornEntity.createBaseHorseAttributes().build());
		event.put(GNSEntityTypes.GUMMY_BEAR, Mob.createMobAttributes().build());
		event.put(GNSEntityTypes.BABY_CREEPER, Creeper.createAttributes().build());
		event.put(GNSEntityTypes.TORMENTER, TormenterEntity.registerAttributes().build());
		event.put(GNSEntityTypes.HEROBRINE, HerobrineEntity.registerAttributes().build());*/
	}

	public static boolean animalSpawnConditions(EntityType<? extends Animal> entityIn, LevelAccessor worldIn, MobSpawnType reasonIn, BlockPos posIn, Random randIn)
	{
		return worldIn.getBlockState(posIn.below()).getBlock() == GNSBlocks.dream_grass_block && worldIn.getRawBrightness(posIn, 0) > 8;
	}

	public static boolean otherSpawnConditions(EntityType<? extends Mob> entityIn, LevelAccessor worldIn, MobSpawnType reasonIn, BlockPos posIn, Random randIn)
	{
		return (worldIn.getBlockState(posIn.below()).getBlock() == GNSBlocks.nightmare_grass_block || worldIn.getBlockState(posIn.below()).getBlock() == GNSBlocks.dream_grass_block) && worldIn.canSeeSkyFromBelowWater(posIn);
	}

	private static <T extends Entity> EntityType<T> buildEntity(String key, EntityType.Builder<T> builder)
	{
		return builder.build(GoodNightSleep.find(key));
	}
}