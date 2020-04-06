package com.legacy.goodnightsleep.client.audio;

import com.legacy.goodnightsleep.GNSRegistry;
import com.legacy.goodnightsleep.GoodNightSleep;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class GNSSounds
{
	// Music
	public static SoundEvent MUSIC_GOOD_DREAM;
	public static SoundEvent MUSIC_SKY_BLUE;

	public static SoundEvent MUSIC_NIGHTMARE;

	// Mobs
	public static SoundEvent ENTITY_UNICORN_IDLE;
	public static SoundEvent ENTITY_UNICORN_HURT;
	public static SoundEvent ENTITY_UNICORN_DEATH;

	public static SoundEvent ENTITY_TORMENTER_IDLE;
	public static SoundEvent ENTITY_TORMENTER_HURT;
	public static SoundEvent ENTITY_TORMENTER_DEATH;
	public static SoundEvent ENTITY_TORMENTER_TORMENT;

	public static SoundEvent ENTITY_HEROBRINE_HURT;
	public static SoundEvent ENTITY_HEROBRINE_DEATH;

	public static SoundEvent ITEM_ARMOR_EQUIP_CANDY = new SoundEvent(GoodNightSleep.locate("item.armor.equip_candy"));
	public static SoundEvent ITEM_ARMOR_EQUIP_RAINBOW = new SoundEvent(GoodNightSleep.locate("item.armor.equip_rainbow"));
	public static SoundEvent ITEM_ARMOR_EQUIP_POSITITE = new SoundEvent(GoodNightSleep.locate("item.armor.equip_positite"));
	public static SoundEvent ITEM_ARMOR_EQUIP_ZITRITE = new SoundEvent(GoodNightSleep.locate("item.armor.equip_zitrite"));
	public static SoundEvent ITEM_ARMOR_EQUIP_NEGATITE = new SoundEvent(GoodNightSleep.locate("item.armor.equip_negatite"));

	public static IForgeRegistry<SoundEvent> soundRegistry;

	public static void init()
	{
		MUSIC_GOOD_DREAM = register("music.good_dream");
		MUSIC_SKY_BLUE = register("music.sky_blue");

		MUSIC_NIGHTMARE = register("music.tfarcenim");

		ENTITY_UNICORN_IDLE = register("entity.unicorn.idle");
		ENTITY_UNICORN_HURT = register("entity.unicorn.hurt");
		ENTITY_UNICORN_DEATH = register("entity.unicorn.death");

		ENTITY_TORMENTER_IDLE = register("entity.tormenter.idle");
		ENTITY_TORMENTER_HURT = register("entity.tormenter.hurt");
		ENTITY_TORMENTER_DEATH = register("entity.tormenter.death");
		ENTITY_TORMENTER_TORMENT = register("entity.tormenter.torment");

		ENTITY_HEROBRINE_HURT = register("entity.herobrine.hurt");
		ENTITY_HEROBRINE_DEATH = register("entity.herobrine.death");
		
		GNSRegistry.register(soundRegistry, "item.armor.equip_candy", ITEM_ARMOR_EQUIP_CANDY);
		GNSRegistry.register(soundRegistry, "item.armor.equip_rainbow", ITEM_ARMOR_EQUIP_RAINBOW);
		GNSRegistry.register(soundRegistry, "item.armor.equip_positite", ITEM_ARMOR_EQUIP_POSITITE);
		GNSRegistry.register(soundRegistry, "item.armor.equip_zitrite", ITEM_ARMOR_EQUIP_ZITRITE);
		GNSRegistry.register(soundRegistry, "item.armor.equip_negatite", ITEM_ARMOR_EQUIP_NEGATITE);
	}

	private static SoundEvent register(String name)
	{
		ResourceLocation location = GoodNightSleep.locate(name);

		SoundEvent sound = new SoundEvent(location);

		sound.setRegistryName(location);

		if (soundRegistry != null)
			soundRegistry.register(sound);

		return sound;
	}

}