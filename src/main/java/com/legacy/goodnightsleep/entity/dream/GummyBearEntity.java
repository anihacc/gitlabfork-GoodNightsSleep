package com.legacy.goodnightsleep.entity.dream;

import com.legacy.goodnightsleep.entity.GNSEntityTypes;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

public class GummyBearEntity extends AnimalEntity
{
		
	public GummyBearEntity(EntityType<? extends GummyBearEntity> type, World worldIn)
    {
        super(type, worldIn);
        //this.setSize(0.6F, 0.7F);
    }
	
	public GummyBearEntity(World worldIn)
    {
        this(GNSEntityTypes.GUMMY_BEAR, worldIn);
    }

	@Override
	public AgeableEntity createChild(AgeableEntity ageable)
	{
		return null;
	}
}