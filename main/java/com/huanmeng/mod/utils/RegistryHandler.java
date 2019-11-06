package com.huanmeng.mod.utils;

import com.huanmeng.mod.blocks.tileentity.TileEntityXijun;
import com.huanmeng.mod.blocks.tileentity.TileEntityXijunreplace;
import com.huanmeng.mod.init.ModBlocks;
import com.huanmeng.mod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		registerTileEntities();
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item : ModItems.ITEMS)
			if(item instanceof IHasModel)
				((IHasModel)item).registerModels();
		for(Block block : ModBlocks.BLOCKS)
			if(block instanceof IHasModel)
				((IHasModel)block).registerModels();
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityXijun.class, new ResourceLocation(Reference.MOD_ID + ":bacteria"));
		GameRegistry.registerTileEntity(TileEntityXijunreplace.class, new ResourceLocation(Reference.MOD_ID + ":replacer"));
	}
	
}
