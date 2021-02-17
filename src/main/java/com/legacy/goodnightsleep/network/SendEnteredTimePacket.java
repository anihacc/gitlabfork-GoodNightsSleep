package com.legacy.goodnightsleep.network;

import java.util.function.Supplier;

import com.legacy.goodnightsleep.capabillity.DreamPlayer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

public class SendEnteredTimePacket
{
	private long enteredTime;

	public SendEnteredTimePacket(long timeIn)
	{
		this.enteredTime = timeIn;
	}

	public static void encoder(SendEnteredTimePacket packet, PacketBuffer buffer)
	{
		buffer.writeLong(packet.enteredTime);
	}

	public static SendEnteredTimePacket decoder(PacketBuffer buff)
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
		PlayerEntity player = net.minecraft.client.Minecraft.getInstance().player;

		if (DreamPlayer.get(player) != null)
			DreamPlayer.get(player).setEnteredDreamTime(packet.enteredTime);
	}
}
