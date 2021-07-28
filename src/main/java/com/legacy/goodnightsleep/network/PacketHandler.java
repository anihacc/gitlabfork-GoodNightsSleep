package com.legacy.goodnightsleep.network;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

public class PacketHandler
{
	private static final String PROTOCOL_VERSION = "1";
	private static int index = 0;
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(GoodNightSleep.locate("main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

	public static void register()
	{
		// Server -> Client
		register(SendEnteredTimePacket.class, SendEnteredTimePacket::encoder, SendEnteredTimePacket::decoder, SendEnteredTimePacket::handler);
		
		// Client -> Server
		/*register(GetClientMotionPacket.class, GetClientMotionPacket::encoder, GetClientMotionPacket::decoder, GetClientMotionPacket::handler);*/
	}

	private static <MSG> void register(Class<MSG> packet, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer)
	{
		INSTANCE.registerMessage(index, packet, encoder, decoder, messageConsumer);
		index++;
	}

	/**
	 * Server -> Client
	 * 
	 * @param packet
	 * @param serverPlayer
	 */
	public static void sendTo(Object packet, ServerPlayer serverPlayer)
	{
		if (!(serverPlayer instanceof FakePlayer))
			INSTANCE.sendTo(packet, serverPlayer.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
	}

	/**
	 * Server -> Clients in same world
	 * 
	 * @param packet
	 * @param world
	 */
	public static void sendToAll(Object packet, Level world)
	{
		world.players().forEach(player -> sendTo(packet, (ServerPlayer) player));
	}

	/**
	 * Client -> Server
	 * 
	 * @param packet
	 */
	public static void sendToServer(Object packet)
	{
		INSTANCE.sendToServer(packet);
	}
}
