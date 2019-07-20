package com.legacy.goodnightsleep.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class HerobrineEntity extends MonsterEntity
{

	public HerobrineEntity(EntityType<? extends HerobrineEntity> type, World worldIn)
	{
		super(type, worldIn);
		//this.setSize(0.6F, 1.95F);
	}
	
	public HerobrineEntity(World worldIn)
	{
		this(GNSEntityTypes.HEROBRINE, worldIn);
	}

	protected void registerGoals()
	{
		super.registerGoals();
		//this.tasks.addTask(0, new EntityAISwimming(this));
		//this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
		//this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D, 0.0F));
		//this.tasks.addTask(8, new EntityAIWatchClosest(this, PlayerEntity.class, 8.0F));
		//this.tasks.addTask(8, new EntityAILookIdle(this));
		// this.targetTasks.addTask(1, new EntityHerobrine.AIFindPlayer(this));
		//this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
	}

	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
	}

	protected SoundEvent getAmbientSound()
	{
		return null;
	}

	protected SoundEvent getHurtSound(DamageSource source)
	{
		return SoundEvents.ENTITY_WITHER_HURT;
	}

	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_WITHER_DEATH;
	}
	
	/*@Override
    protected ResourceLocation getLootTable()
    {
        return GNSLootTables.herobrine;
    }*/
}
