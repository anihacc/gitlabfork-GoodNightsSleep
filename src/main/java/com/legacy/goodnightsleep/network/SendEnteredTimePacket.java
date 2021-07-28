package com.legacy.goodnightsleep.network;

import java.util.function.Supplier;

import com.legacy.goodnightsleep.capabillity.DreamPlayer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class SendEnteredTimePacket
{
	private long enteredTime;

	public SendEnteredTimePacket(long timeIn)
	{
		this.enteredTime = timeIn;
	}

	public static void encoder(SendEnteredTimePacket packet, FriendlyByteBuf buffer)
	{
		buffer.writeLong(packet.enteredTime);
	}

	public static SendEnteredTimePacket decoder(FriendlyByteBuf buff)
	{
		return new SendEnteredTimePacket(buff.readLong());
	}

	public static void handler(SendEnteredTimePacket packet, Supplier<NetworkEvent.Context> context)
	{
		context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handlePacket(packet)));
		context.get().setPacketHandled(true);
	}

	@SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
	private static void handlePacket(SendEnteredTimePacket packet)
	{
		Player player = net.minecraft.client.Minecraft.getInstance().player;

		if (DreamPlayer.get(player) != null)
			DreamPlayer.get(player).setEnteredDreamTime(packet.enteredTime);
	}
}
