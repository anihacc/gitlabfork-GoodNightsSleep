package com.legacy.goodnightsleep.world.dream;

import java.util.ArrayList;
import java.util.Iterator;

import com.legacy.goodnightsleep.GNSConfig;
import com.legacy.goodnightsleep.blocks.GNSBlocks;
import com.legacy.goodnightsleep.world.GNSBiomes;
import com.legacy.goodnightsleep.world.GNSDimensions;
import com.legacy.goodnightsleep.world.GNSTeleportationUtil;

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
import net.minecraftforge.client.IRenderHandler;

public class GoodDreamDimension extends Dimension
{
	public ArrayList<ServerPlayerEntity> dreamPlayerList = new ArrayList<ServerPlayerEntity>();

	public GoodDreamDimension(World world, DimensionType type)
	{
		super(world, type, 0.0F);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator()
	{
		OverworldGenSettings genSettings = ChunkGeneratorType.SURFACE.createSettings();
		genSettings.setDefaultBlock(GNSBlocks.delusion_stone.getDefaultState());
		return ChunkGeneratorType.SURFACE.create(this.world, BiomeProviderType.FIXED.create(BiomeProviderType.FIXED.func_226840_a_(this.world.getWorldInfo()).setBiome(GNSBiomes.GOOD_DREAM_PLAINS)), genSettings);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public IRenderHandler getSkyRenderer()
	{
		return DreamSkyRenderer.INSTANCE;
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
		return false;
	}

	@Override
	public float getCurrentMoonPhaseFactor(long time)
	{
		return 2.0F;
	}

	@OnlyIn(Dist.CLIENT)
	// @Override
	public float getStarBrightness(float brightness)
	{
		return 1.0F;
	}

	@OnlyIn(Dist.CLIENT)
	public float getCloudHeight()
	{
		return 150F;
	}

	@OnlyIn(Dist.CLIENT)
	public String getWelcomeMessage()
	{
		return "You dream of peaceful lands...";
	}

	@Override
	public DimensionType getType()
	{
		return GNSDimensions.dimensionType(true);
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
						if (playerMP.dimension == GNSDimensions.dimensionType(true))
						{
							dreamPlayerList.add(playerMP);
						}
					}
				}

				for (int var14 = 0; var14 < dreamPlayerList.size(); ++var14)
				{
					playerMP = (ServerPlayerEntity) dreamPlayerList.get(var14);

					BlockPos pos = playerMP.getBedLocation(DimensionType.OVERWORLD) != null ? playerMP.getBedLocation(DimensionType.OVERWORLD) : playerMP.world.getSpawnPoint();
					GNSTeleportationUtil.changeDimension(DimensionType.OVERWORLD, playerMP, pos);
				}
			}

			return f1;
		}
		else
		{
			return 1.0F;
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public Vec3d getFogColor(float par1, float par2)
	{
		return new Vec3d(0.843172549D, 1.0D, 1.0D);
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