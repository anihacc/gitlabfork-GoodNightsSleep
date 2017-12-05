package com.legacy.goodnightsleep.common.entities.dream;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntityGummyBear extends EntityAnimal
{
		
	public EntityGummyBear(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 0.7F);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
