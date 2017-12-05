package com.legacy.goodnightsleep.common.entities.dream;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBabyCreeper extends EntityCreeper
{
	
	private int explosionRadius = 0;
	
	public EntityBabyCreeper(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 0.7F);
    }
	
	public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        compound.setByte("ExplosionRadius", (byte)this.explosionRadius);
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("ExplosionRadius", 99))
        {
            this.explosionRadius = compound.getByte("ExplosionRadius");
        }
    }

}
