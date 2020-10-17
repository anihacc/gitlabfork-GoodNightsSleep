package com.legacy.goodnightsleep.world.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;

/**
 * Used to prevent other mods from messing with our biomes.
 */
public class GNSMobSpawnBuilder extends MobSpawnInfoBuilder
{
	public GNSMobSpawnBuilder(MobSpawnInfo orig)
	{
		super(orig);
	}

	// Override vanilla methods to be technically immutable
	@Override
	public Set<EntityClassification> getSpawnerTypes()
	{
		return new UnSet<>(super.getSpawnerTypes());
	}

	@Override
	public List<MobSpawnInfo.Spawners> getSpawner(EntityClassification type)
	{
		return new UnList<>(super.getSpawner(type));
	}

	@Override
	public Set<EntityType<?>> getEntityTypes()
	{
		return new UnSet<>(super.getEntityTypes());
	}

	// Our methods that reference vanilla
	public MobSpawnInfo.Builder withSkySpawner(EntityClassification classification, MobSpawnInfo.Spawners spawner)
	{
		return super.withSpawner(classification, spawner);
	}

	public MobSpawnInfo.Builder withSkySpawnCost(EntityType<?> entityType, double spawnCostPerEntity, double maxSpawnCost)
	{
		return super.withSpawnCost(entityType, spawnCostPerEntity, maxSpawnCost);
	}

	public MobSpawnInfo.Builder withSkyCreatureSpawnProbability(float probability)
	{
		return super.withCreatureSpawnProbability(probability);
	}

	// Vanilla methods forced to do nothing
	@Override
	public MobSpawnInfo.Builder withSpawner(EntityClassification classification, MobSpawnInfo.Spawners spawner)
	{
		return this;
	}

	@Override
	public MobSpawnInfo.Builder withSpawnCost(EntityType<?> entityType, double spawnCostPerEntity, double maxSpawnCost)
	{
		return this;
	}

	@Override
	public MobSpawnInfo.Builder withCreatureSpawnProbability(float probability)
	{
		return this;
	}

	public static final class UnSet<T> extends HashSet<T>
	{
		private static final long serialVersionUID = 237792144892678610L;

		public UnSet(Set<T> set)
		{
			super.addAll(set);
		}

		@Override
		public boolean add(T e)
		{
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends T> c)
		{
			return false;
		}

		@Override
		public boolean remove(Object o)
		{
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c)
		{
			return false;
		}

		@Override
		public boolean removeIf(Predicate<? super T> filter)
		{
			return false;
		}

		@Override
		public void clear()
		{
		}
	}

	public static final class UnList<T> extends ArrayList<T>
	{
		private static final long serialVersionUID = 1810699890572556698L;

		public UnList(List<T> list)
		{
			super.addAll(list);
		}

		@Override
		public boolean add(T e)
		{
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends T> c)
		{
			return false;
		}

		@Override
		public void add(int index, T element)
		{
		}

		@Override
		public boolean addAll(int index, Collection<? extends T> c)
		{
			return false;
		}

		@Override
		public boolean remove(Object o)
		{
			return false;
		}

		@Override
		public T remove(int index)
		{
			return this.get(index);
		}

		@Override
		public boolean removeAll(Collection<?> c)
		{
			return false;
		}

		@Override
		protected void removeRange(int fromIndex, int toIndex)
		{
		}

		@Override
		public boolean removeIf(Predicate<? super T> filter)
		{
			return false;
		}

		@Override
		public void clear()
		{
		}

		@Override
		public void replaceAll(UnaryOperator<T> operator)
		{
		}
		
		@Override
		public boolean retainAll(Collection<?> c)
		{
			return false;
		}
		
		@Override
		public T set(int index, T element)
		{
			return this.get(index);
		}
		
		@Override
		public void sort(Comparator<? super T> c)
		{
		}
	}
}
