package com.legacy.goodnightsleep.entities.nightmare;

import com.legacy.goodnightsleep.registry.GNSLootTables;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityTormenter extends EntityZombie
{
	public EntityTormenter(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
    }

	protected SoundEvent getAmbientSound()
    {
        return SoundEvents.AMBIENT_CAVE;
    }

    protected SoundEvent getHurtSound()
    {
        return SoundEvents.AMBIENT_CAVE;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_HORSE_DEATH;
    }
    
    @Override
    protected ResourceLocation getLootTable()
    {
        return GNSLootTables.tormentor;
    }
}
