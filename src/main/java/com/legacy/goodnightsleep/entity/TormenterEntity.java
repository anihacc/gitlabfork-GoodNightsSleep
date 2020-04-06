package com.legacy.goodnightsleep.entity;

import com.legacy.goodnightsleep.client.audio.GNSSounds;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class TormenterEntity extends ZombieEntity
{
	public TormenterEntity(EntityType<? extends TormenterEntity> type, World worldIn)
	{
		super(type, worldIn);
	}

	public TormenterEntity(World worldIn)
	{
		this(GNSEntityTypes.TORMENTER, worldIn);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GNSSounds.ENTITY_TORMENTER_IDLE;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GNSSounds.ENTITY_TORMENTER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return GNSSounds.ENTITY_TORMENTER_DEATH;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		if (!world.isRemote() && entityIn instanceof LivingEntity && rand.nextBoolean() && !((LivingEntity) entityIn).isPotionActive(Effects.BLINDNESS) && !((LivingEntity) entityIn).isPotionActive(Effects.NAUSEA))
		{
			this.playSound(GNSSounds.ENTITY_TORMENTER_TORMENT, 0.5F, 1.0F);
			((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 80));
			((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.NAUSEA, 160));

			if (this.rand.nextBoolean())
				((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.WITHER, 60, this.world.getDifficulty().getId()));
		}

		return super.attackEntityAsMob(entityIn);
	}
}
