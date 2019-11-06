package com.huanmeng.mod.proxies;

import java.util.ArrayList;

import com.huanmeng.mod.blocks.Food;
import com.huanmeng.mod.gen.MustGen;
import com.huanmeng.mod.gen.SpongeGen;
import com.huanmeng.mod.Xijun;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	public void registerItemRenderer(Item item, int meta, String name) {}
	
	public void preInit(FMLPreInitializationEvent event) {
		Gen();
		FMLCommonHandler.instance().bus().register(Xijun.instance);
		MinecraftForge.EVENT_BUS.register(Xijun.instance);
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		Xijun.isolation = config.get("General", "isolation block", "BEDROCK").getString();
		Xijun.speed = config.get("General","bacteria speed",50).getInt();
		Xijun.randomize = config.get("General","randomize bacteria spread",true).getBoolean();
		String blacklist1 = config.get("General","blacklist","").getString();
		config.save();
		
		Xijun.blacklist = new ArrayList<Food>();
		if(blacklist1.length() > 0) {
			for(String s : blacklist1.split(",")) {
				try {
					int meta = 0;
					if(s.contains(":")) {
						String[] s2 = s.split(":");
						s = s2[0];
						meta = Integer.parseInt(s2[1]);
					}
					int id = Integer.parseInt(s);
					Block block = Block.getBlockById(id);
					if(block == Blocks.AIR) {
						Xijun.logger.error("Error while parsing blacklist: ID " + id + " is not a valid block!");
					}else {
						Xijun.blacklist.add(new Food(block.getStateFromMeta(meta)));
					}
				}catch(NumberFormatException e) {
					Xijun.logger.error("Error while parsing blacklist: " + s + " is not a valid number");
					continue;
				}
			}
		}
	}

	public String getLastVersion() {
		return null;
	}
	public static void Gen() {
		GameRegistry.registerWorldGenerator(new SpongeGen(), 2);
		GameRegistry.registerWorldGenerator(new MustGen(), 2);
	}
}
