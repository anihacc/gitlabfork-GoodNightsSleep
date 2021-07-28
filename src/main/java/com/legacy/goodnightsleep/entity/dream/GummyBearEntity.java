package com.legacy.goodnightsleep.entity.dream;

import com.legacy.goodnightsleep.registry.GNSEntityTypes;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

public class GummyBearEntity extends Animal
{
		
	public GummyBearEntity(EntityType<? extends GummyBearEntity> type, Level worldIn)
    {
        super(type, worldIn);
        //this.setSize(0.6F, 0.7F);
    }
	
	public GummyBearEntity(Level worldIn)
    {
        this(GNSEntityTypes.GUMMY_BEAR, worldIn);
    }

	@Override
	public AgeableMob getBreedOffspring(ServerLevel worldIn, AgeableMob ageableIn)
	{
		return null;
	}
}
