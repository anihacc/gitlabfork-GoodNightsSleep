package com.legacy.goodnightsleep.entity;

import com.legacy.goodnightsleep.client.audio.GNSSounds;
import com.legacy.goodnightsleep.registry.GNSEntityTypes;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public class TormenterEntity extends Zombie
{
	public TormenterEntity(EntityType<? extends TormenterEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}

	public TormenterEntity(Level worldIn)
	{
		this(GNSEntityTypes.TORMENTER, worldIn);
	}

	public static AttributeSupplier.Builder registerAttributes()
	{
		return TormenterEntity.createAttributes().add(Attributes.MAX_HEALTH, 30.0D);
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
	public boolean doHurtTarget(Entity entityIn)
	{
		if (!level.isClientSide() && entityIn instanceof LivingEntity && random.nextBoolean() && !((LivingEntity) entityIn).hasEffect(MobEffects.BLINDNESS) && !((LivingEntity) entityIn).hasEffect(MobEffects.CONFUSION))
		{
			this.playSound(GNSSounds.ENTITY_TORMENTER_TORMENT, 0.5F, 1.0F);
			((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 80));
			((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.CONFUSION, 160));

			if (this.random.nextBoolean())
				((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.WITHER, 60, this.level.getDifficulty().getId()));
		}

		return super.doHurtTarget(entityIn);
	}
}
