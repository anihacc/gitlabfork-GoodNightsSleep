package com.legacy.goodnightsleep.world;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterHeck extends Teleporter
{
	Random random;
	
    public TeleporterHeck(WorldServer worldIn)
    {
    	super(worldIn);
    	
        this.random = new Random(worldIn.getSeed());
    }
    
    public void placeInPortal(Entity entityIn, float rotationYaw)
    {
    }

    public boolean placeInExistingPortal(Entity entityIn, float rotationYaw)
    {
		return false;
    }

    public void removeStalePortalLocations(long worldTime)
    {
    }

    public class PortalPosition extends BlockPos
    {
        /** The worldtime at which this PortalPosition was last verified */
        public long lastUpdateTime;

        public PortalPosition(BlockPos pos, long lastUpdate)
        {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.lastUpdateTime = lastUpdate;
        }
    }

}