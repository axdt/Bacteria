package com.huanmeng.mod.version;

import org.apache.logging.log4j.core.config.Configurator;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public final class AdaptorNotifier {

	private AdaptorNotifier() {}

	private static boolean triedToWarnPlayer;

	@SubscribeEvent
	public static void onTick(ClientTickEvent event) {
		if(!triedToWarnPlayer && Minecraft.getMinecraft().player != null) {
			EntityPlayer player = Minecraft.getMinecraft().player;
			ConfigHandler.adaptor.tellChanges(player);

			triedToWarnPlayer = true;
		}
	}

}