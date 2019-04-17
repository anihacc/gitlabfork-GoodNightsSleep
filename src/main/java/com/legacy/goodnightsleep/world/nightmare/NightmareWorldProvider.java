package com.legacy.goodnightsleep.world.nightmare;

import com.legacy.goodnightsleep.world.GNSWorld;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NightmareWorldProvider extends WorldProvider
{

	@Override
	protected void init()
	{
		//this.doesWaterVaporize = true;
		this.hasSkyLight = true;
		this.biomeProvider = new WorldChunkManagerNightmare();
	}

	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new ChunkProviderNightmare(world, this.getSeed());
	}

	@Override
	public boolean canRespawnHere()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int x, int z)
	{
        return true;
	}

	@Override
    public WorldSleepResult canSleepAt(EntityPlayer player, BlockPos pos)
    {
        return WorldSleepResult.DENY;
    }

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks)
	{
		return 0.5F;
	}

	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage()
	{
		return "You dream of horrible things...";
	}

	@Override
	public DimensionType getDimensionType()
	{
		return GNSWorld.nightmare_dimension_type;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float celestialAngle, float partialTicks)
	{
		 return new Vec3d(0.1D, 0.0D, 0.0D);
	}
}