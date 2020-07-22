package com.legacy.goodnightsleep.world.nightmare;

import java.util.ArrayList;
import java.util.Iterator;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.world.GNSBiomes;
import com.legacy.goodnightsleep.world.GNSDimensions;
import com.legacy.goodnightsleep.world.GNSTeleportationUtil;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeDimension;

public class NightmareDimension extends Dimension implements IForgeDimension
{
	public ArrayList<ServerPlayerEntity> nightmarePlayerList = new ArrayList<ServerPlayerEntity>();

	public NightmareDimension(World world, DimensionType type)
	{
		super(world, type, 0.0F);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator()
	{
		OverworldGenSettings genSettings = ChunkGeneratorType.SURFACE.createSettings();
		genSettings.setDefaultBlock(Blocks.STONE.getDefaultState());
		genSettings.setDefaultFluid(Blocks.LAVA.getDefaultState());
		return ChunkGeneratorType.SURFACE.create(this.world, BiomeProviderType.FIXED.create(BiomeProviderType.FIXED.createSettings(this.world.getWorldInfo()).setBiome(GNSBiomes.NIGHTMARE_HILLS)), genSettings);
	}

	@Override
	public boolean canRespawnHere()
	{
		return false;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean doesXZShowFog(int x, int z)
	{
		return true;
	}

	@Override
	public float getCurrentMoonPhaseFactor(long time)
	{
		return 2.0F;
	}

	@OnlyIn(Dist.CLIENT)
	public String getWelcomeMessage()
	{
		return "You dream of horrible things...";
	}

	@OnlyIn(Dist.CLIENT)
	public float getStarBrightness(float brightness)
	{
		return 1.0F;
	}

	@OnlyIn(Dist.CLIENT)
	public float getCloudHeight()
	{
		return 150F;
	}

	@Override
	public DimensionType getType()
	{
		return GNSDimensions.dimensionType(false);
	}

	@Override
	public BlockPos findSpawn(ChunkPos p_206920_1_, boolean checkValid)
	{
		return null;
	}

	@Override
	public BlockPos findSpawn(int p_206921_1_, int p_206921_2_, boolean checkValid)
	{
		return null;
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return true;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks)
	{
		// kick the player out after 20 minutes
		if (!GNSConfig.disableTimePassing)
		{
			int j = (int) (worldTime % 48000L);
			float f1 = ((float) j + partialTicks) / 48000.0F - 0.25F;
			if (f1 < 0.0F)
			{
				++f1;
			}

			if (f1 > 1.0F)
			{
				--f1;
			}

			float f2 = f1;
			f1 = 1.0F - (float) ((Math.cos((double) f1 * Math.PI) + 1.0D) / 2.0D);
			f1 = f2 + (f1 - f2) / 3.0F;
			f1 += 0.5F;
			if (worldTime > 25000L)
			{
				Iterator<?> iterator = this.world.getPlayers().iterator();

				ServerPlayerEntity playerMP;
				while (iterator.hasNext())
				{
					Object i = iterator.next();
					if (i instanceof ServerPlayerEntity)
					{
						playerMP = (ServerPlayerEntity) i;

						if (playerMP.dimension == GNSDimensions.dimensionType(false))
						{
							nightmarePlayerList.add(playerMP);
						}
					}
				}

				for (int var14 = 0; var14 < nightmarePlayerList.size(); ++var14)
				{
					playerMP = (ServerPlayerEntity) nightmarePlayerList.get(var14);

					BlockPos pos = playerMP.getBedLocation(DimensionType.OVERWORLD) != null ? playerMP.getBedLocation(DimensionType.OVERWORLD) : playerMP.world.getSpawnPoint();
					GNSTeleportationUtil.changeDimension(DimensionType.OVERWORLD, playerMP, pos);
				}
			}

			return f1;
		}
		else
		{
			return 0.5F;
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public Vec3d getFogColor(float celestialAngle, float partialTicks)
	{
		return new Vec3d(0.1D, 0.0D, 0.0D);
	}

	@Override
	public boolean hasSkyLight()
	{
		return true;
	}

	@Override
	public SleepResult canSleepAt(PlayerEntity player, BlockPos pos)
	{
		return SleepResult.DENY;
	}
}