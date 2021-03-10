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
	public boolean canUse()
	{
		LivingEntity var1 = this.swellingCreeper.getTarget();
		return this.swellingCreeper.getCreeperState() > 0 || var1 != null && this.swellingCreeper.distanceToSqr(var1) < 9.0D;
	}

	@Override
	public void start()
	{
		this.swellingCreeper.getNavigation().stop();
        this.creeperAttackTarget = this.swellingCreeper.getTarget();
	}

	@Override
	public void stop()
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
		else if (this.swellingCreeper.distanceToSqr(this.creeperAttackTarget) > 49.0D)
		{
			this.swellingCreeper.setCreeperState(-1);
		}
		else if (!this.swellingCreeper.getSensing().canSee(this.creeperAttackTarget))
		{
			this.swellingCreeper.setCreeperState(-1);
		}
		else
		{
			this.swellingCreeper.setCreeperState(1);
		}
	}
}
