package com.huanmeng.mod.version;

import com.huanmeng.mod.utils.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value=Side.CLIENT,modid=Reference.MOD_ID)
public final class VersionChecker {
	private VersionChecker() {}
	private static final int FLAVOUR_MESSAGES = 65;

	public static volatile boolean doneChecking = false;
	public static volatile String onlineVersion = "";
	private static boolean triedToWarnPlayer = false;

	public static volatile boolean startedDownload = false;
	public static volatile boolean downloadedFile = false;

	public static void init() {
		new ThreadVersionChecker();
	}

	@SubscribeEvent
	public static void onTick(ClientTickEvent event) {
		if(event.phase == Phase.END && Minecraft.getMinecraft().player != null && !triedToWarnPlayer && doneChecking) {
			if(!onlineVersion.isEmpty()) {
				EntityPlayer player = Minecraft.getMinecraft().player;
				int onlineBuild = Integer.parseInt(onlineVersion.split("-")[1]);
				int clientBuild = "GRADLE:BUILD".contains("GRADLE") ? Integer.MAX_VALUE : Integer.parseInt("GRADLE:BUILD");
				if(onlineBuild > clientBuild) {
					player.sendMessage(new TextComponentTranslation("botania.versioning.flavour" + player.world.rand.nextInt(FLAVOUR_MESSAGES)).setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
					player.sendMessage(new TextComponentTranslation("botania.versioning.outdated", clientBuild, onlineBuild));

					ITextComponent component = ITextComponent.Serializer.fromJsonLenient(I18n.translateToLocal("botania.versioning.updateMessage").replaceAll("%version%", onlineVersion));
					player.sendMessage(component);
				}
			}

			triedToWarnPlayer = true;
		}
	}

}