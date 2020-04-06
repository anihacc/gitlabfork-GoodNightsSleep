package com.legacy.goodnightsleep.entity.dream;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;

public class BabyCreeperSwellGoal extends Goal
{
	BabyCreeperEntity swellingCreeper;

	LivingEntity creeperAttackTarget;

	public BabyCreeperSwellGoal(BabyCreeperEntity par1EntityCreeper)
	{
		this.swellingCreeper = par1EntityCreeper;
	}

	@Override
	public boolean shouldExecute()
	{
		LivingEntity var1 = this.swellingCreeper.getAttackTarget();
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
	public void tick()
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
