package com.huanmeng.mod.version;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.common.eventbus.Subscribe;
import com.huanmeng.mod.utils.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
@EventBusSubscriber(modid = "xijun")
public class Test {

	@SubscribeEvent
	public void onPlayerFirstJoin(RenderGameOverlayEvent.Post event,EntityPlayer player) {
		String version= Reference.MOD_VERSION;
		if(isLatestVersion()) {
			player.sendMessage(new TextComponentTranslation("xijun.util.versionchecker.sendmessage.now_version",version));
			player.sendMessage(new TextComponentTranslation("xijun.util.versionchecker.sendmessage.now_versionasps",version));
			//((EntityPlayerMP)event.getEntity()).sendMessage(new TextComponentTranslation("xijun.util.versionchecker.sendmessage.now_versionmp",version));
			//event.getEntity().sendMessage(new TextComponentTranslation("xijun.util.versionchecker.sendmessage.now_versionmp",version));
				
		}else
		{	
			player.sendMessage(new TextComponentTranslation("xijun.util.versionchecker.sendmessage.update_version",isLatestVersion()));
			
			
		}
		
	}
	public String update(){
		String ver=null;
		try {
			URL url = new URL("https://Rplay123.github.io/version/"+Reference.MC_VERSION+".txt");
			InputStream is = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			ver=br.readLine();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ver;
	}
	public boolean isLatestVersion() {
		boolean isLatest=false;
		String latest=update();
		String current="1.9";
		if(latest.equalsIgnoreCase(current)){
			isLatest=true;
			isLatest=true;
		}
		return isLatest;
	}
	}