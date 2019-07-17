package com.legacy.goodnightsleep.entity.dream;

import com.legacy.goodnightsleep.entity.GNSEntityTypes;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntityGummyBear extends EntityAnimal
{
		
	public EntityGummyBear(EntityType<?> type, World worldIn)
    {
        super(type, worldIn);
        this.setSize(0.6F, 0.7F);
    }
	
	public EntityGummyBear(World worldIn)
    {
        this(GNSEntityTypes.GUMMY_BEAR, worldIn);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return null;
	}
}
