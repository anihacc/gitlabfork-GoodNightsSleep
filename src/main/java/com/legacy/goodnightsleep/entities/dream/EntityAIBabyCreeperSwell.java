package com.legacy.goodnightsleep.entities.dream;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIBabyCreeperSwell extends EntityAIBase
{
	EntityBabyCreeper swellingCreeper;

	EntityLivingBase creeperAttackTarget;

	public EntityAIBabyCreeperSwell(EntityBabyCreeper par1EntityCreeper)
	{
		this.swellingCreeper = par1EntityCreeper;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute()
	{
		EntityLivingBase var1 = this.swellingCreeper.getAttackTarget();
		return this.swellingCreeper.getCreeperState() > 0 || var1 != null && this.swellingCreeper.getDistanceSq(var1) < 9.0D;
	}

	@Override
	public void startExecuting()
	{
		this.swellingCreeper.getNavigator().clearPath();
        this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
	}

	@Override
	public void resetTask()
	{
		this.creeperAttackTarget = null;
	}

	@Override
	public void updateTask()
	{
		if (this.creeperAttackTarget == null)
		{
			this.swellingCreeper.setCreeperState(-1);
		}
		else if (this.swellingCreeper.getDistanceSq(this.creeperAttackTarget) > 49.0D)
		{
			this.swellingCreeper.setCreeperState(-1);
		}
		else if (!this.swellingCreeper.getEntitySenses().canSee(this.creeperAttackTarget))
		{
			this.swellingCreeper.setCreeperState(-1);
		}
		else
		{
			this.swellingCreeper.setCreeperState(1);
		}
	}
}
