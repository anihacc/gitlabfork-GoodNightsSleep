package com.legacy.goodnightsleep.entities.nightmare;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class EntityTormenter extends EntityZombie
{
	public EntityTormenter(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
    }

	protected String getAmbientSound()
    {
        return "ambient.cave.cave";
    }

    protected String getHurtSound()
    {
        return "ambient.cave.cave";
    }

    protected String getDeathSound()
    {
        return "mob.horse.zombie.death";
    }
}
