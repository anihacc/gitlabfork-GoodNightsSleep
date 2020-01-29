package com.legacy.goodnightsleep.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class TormenterEntity extends ZombieEntity
{

	public TormenterEntity(EntityType<? extends TormenterEntity> type, World worldIn)
	{
		super(type, worldIn);
		//this.setSize(0.6F, 1.95F);
	}

	public TormenterEntity(World worldIn)
	{
		this(GNSEntityTypes.TORMENTER, worldIn);
	}

	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.AMBIENT_CAVE;
	}

	protected SoundEvent getHurtSound(DamageSource source)
	{
		return SoundEvents.AMBIENT_CAVE;
	}

	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_ZOMBIE_HORSE_DEATH;
	}
	/*
	 * @Override
	 * protected ResourceLocation getLootTable()
	 * {
	 * return GNSLootTables.tormentor;
	 * }
	 */
}
