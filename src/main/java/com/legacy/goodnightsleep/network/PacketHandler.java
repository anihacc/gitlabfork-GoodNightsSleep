package com.legacy.goodnightsleep.network;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

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

	private static <MSG> void register(Class<MSG> packet, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer)
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
	public static void sendTo(Object packet, ServerPlayerEntity serverPlayer)
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
	public static void sendToAll(Object packet, World world)
	{
		world.players().forEach(player -> sendTo(packet, (ServerPlayerEntity) player));
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
