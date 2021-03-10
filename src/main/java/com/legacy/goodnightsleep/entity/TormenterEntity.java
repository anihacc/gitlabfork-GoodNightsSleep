package com.legacy.goodnightsleep.entity;

import com.legacy.goodnightsleep.client.audio.GNSSounds;
import com.legacy.goodnightsleep.registry.GNSEntityTypes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
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

	public static AttributeModifierMap.MutableAttribute registerAttributes()
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
		if (!level.isClientSide() && entityIn instanceof LivingEntity && random.nextBoolean() && !((LivingEntity) entityIn).hasEffect(Effects.BLINDNESS) && !((LivingEntity) entityIn).hasEffect(Effects.CONFUSION))
		{
			this.playSound(GNSSounds.ENTITY_TORMENTER_TORMENT, 0.5F, 1.0F);
			((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.BLINDNESS, 80));
			((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.CONFUSION, 160));

			if (this.random.nextBoolean())
				((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.WITHER, 60, this.level.getDifficulty().getId()));
		}

		return super.doHurtTarget(entityIn);
	}
}
