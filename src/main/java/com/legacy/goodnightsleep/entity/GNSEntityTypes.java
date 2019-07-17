package com.legacy.goodnightsleep.entity;

import com.legacy.goodnightsleep.entity.dream.EntityBabyCreeper;
import com.legacy.goodnightsleep.entity.dream.EntityGummyBear;
import com.legacy.goodnightsleep.entity.dream.EntityUnicorn;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("goodnightsleep")
public class GNSEntityTypes
{

	public static final EntityType<EntityUnicorn> UNICORN = register("unicorn", EntityType.Builder.create(EntityUnicorn.class, EntityUnicorn::new));

	public static final EntityType<EntityGummyBear> GUMMY_BEAR = register("gummy_bear", EntityType.Builder.create(EntityGummyBear.class, EntityGummyBear::new));
	
	public static final EntityType<EntityBabyCreeper> BABY_CREEPER = register("baby_creeper", EntityType.Builder.create(EntityBabyCreeper.class, EntityBabyCreeper::new));
	
	public static final EntityType<EntityTormenter> TORMENTER = register("tormenter", EntityType.Builder.create(EntityTormenter.class, EntityTormenter::new));
	
	public static final EntityType<EntityHerobrine> HEROBRINE = register("herobrine", EntityType.Builder.create(EntityHerobrine.class, EntityHerobrine::new));

	@SuppressWarnings("deprecation")
	public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder)
	{
		EntityType<T> entitytype = builder.build("goodnightsleep" + id);
		IRegistry.field_212629_r.put(new ResourceLocation("goodnightsleep", id), entitytype);
		return entitytype;
	}
}