package com.legacy.goodnightsleep.world;

import java.util.function.BiFunction;

import com.legacy.goodnightsleep.GNSRegistry;
import com.legacy.goodnightsleep.GoodNightSleep;
import com.legacy.goodnightsleep.world.dream.GoodDreamDimension;
import com.legacy.goodnightsleep.world.nightmare.NightmareDimension;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.registries.IForgeRegistry;

public class GNSDimensions
{

	public static final ModDimension dreamDim = new ModDimension()
	{

		@Override
		public BiFunction<World, DimensionType, ? extends Dimension> getFactory()
		{
			return GoodDreamDimension::new;
		}
	};

	public static final ModDimension nightmareDim = new ModDimension()
	{

		@Override
		public BiFunction<World, DimensionType, ? extends Dimension> getFactory()
		{
			return NightmareDimension::new;
		}
	};

	public static void initModDimensions(IForgeRegistry<ModDimension> registry)
	{
		GNSRegistry.register(registry, "good_dream", dreamDim);
		DimensionManager.registerDimension(GoodNightSleep.locate("good_dream"), dreamDim, new PacketBuffer(Unpooled.buffer()), true);
		GNSRegistry.register(registry, "nightmare", nightmareDim);
		DimensionManager.registerDimension(GoodNightSleep.locate("nightmare"), nightmareDim, new PacketBuffer(Unpooled.buffer()), true);
	}

	public static void initDimensions()
	{
		if (GNSDimensions.dimensionType(true) == null)
		{
			DimensionManager.registerDimension(GoodNightSleep.locate("good_dream"), dreamDim, new PacketBuffer(Unpooled.buffer()), true);
		}
		if (GNSDimensions.dimensionType(false) == null)
		{
			DimensionManager.registerDimension(GoodNightSleep.locate("nightmare"), nightmareDim, new PacketBuffer(Unpooled.buffer()), true);
		}
	}

	public static DimensionType dimensionType(boolean dream)
	{
		DimensionType dimension;
		if (dream)
		{
			dimension = DimensionType.byName(new ResourceLocation(GoodNightSleep.MODID, "good_dream"));
		}
		else
		{
			dimension = DimensionType.byName(new ResourceLocation(GoodNightSleep.MODID, "nightmare"));
		}
		return dimension;
	}

	/*static
	{
		try
		{
			Field channelField = FMLNetworkConstants.class.getDeclaredField("handshakeChannel");
			channelField.setAccessible(true);
			SimpleChannel handshakeChannel = (SimpleChannel) channelField.get(null);
			handshakeChannel.messageBuilder(S2CDimensionSync.class, 100).loginIndex(S2CDimensionSync::getLoginIndex, S2CDimensionSync::setLoginIndex).decoder(S2CDimensionSync::decode).encoder(S2CDimensionSync::encode).buildLoginPacketList(isLocal ->
			{
				if (isLocal)
				{
					return ImmutableList.of();
				}
	
				return ImmutableList.of(Pair.of("Dream Dimension Sync", new S2CDimensionSync(GNSDimensions.dimensionType(true))));
			}).consumer((msg, ctx) ->
			{
				if (DimensionManager.getRegistry().getByValue(msg.id) == null)
				{
					DimensionManager.registerDimensionInternal(msg.id, msg.name, msg.dimension, null, msg.skyLight);
				}
				ctx.get().setPacketHandled(true);
				handshakeChannel.reply(new FMLHandshakeMessages.C2SAcknowledge(), ctx.get());
			}).add();
		}
		catch (ReflectiveOperationException e)
		{
			e.printStackTrace();
		}
	}
	
	public static class S2CDimensionSync implements IntSupplier
	{
	
		final int id;
	
		final ResourceLocation name;
	
		final ModDimension dimension;
	
		final boolean skyLight;
	
		private int loginIndex;
	
		public S2CDimensionSync(DimensionType dimensionType)
		{
			this.id = dimensionType.getId() + 1;
			this.name = DimensionType.getKey(dimensionType);
			this.dimension = dimensionType.getModType();
			this.skyLight = dimensionType.hasSkyLight();
		}
	
		S2CDimensionSync(int id, ResourceLocation name, ModDimension dimension, boolean skyLight)
		{
			this.id = id;
			this.name = name;
			this.dimension = dimension;
			this.skyLight = skyLight;
		}
	
		@Override
		public int getAsInt()
		{
			return this.loginIndex;
		}
	
		void setLoginIndex(final int loginIndex)
		{
			this.loginIndex = loginIndex;
		}
	
		int getLoginIndex()
		{
			return this.loginIndex;
		}
	
		void encode(PacketBuffer buffer)
		{
			buffer.writeInt(this.id);
			buffer.writeResourceLocation(this.name);
			buffer.writeResourceLocation(this.dimension.getRegistryName());
			buffer.writeBoolean(this.skyLight);
		}
	
		public static S2CDimensionSync decode(PacketBuffer buffer)
		{
			int id = buffer.readInt();
			ResourceLocation name = buffer.readResourceLocation();
			ModDimension dimension = ForgeRegistries.MOD_DIMENSIONS.getValue(buffer.readResourceLocation());
			boolean skyLight = buffer.readBoolean();
			return new S2CDimensionSync(id, name, dimension, skyLight);
		}
	}*/
}
