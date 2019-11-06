package com.huanmeng.mod.blocks;

import com.huanmeng.mod.init.ModBlocks;
import com.huanmeng.mod.init.ModItems;
import com.huanmeng.mod.utils.IHasModel;
import com.huanmeng.mod.Xijun;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;

public class Blockbase extends Block implements IHasModel{

	public Blockbase(String name) {
		super(Material.SPONGE);
		setUnlocalizedName(I18n.translateToLocal("xijun."+name));
		setRegistryName("xijun",name);
		setCreativeTab(Xijun.tabBacteria);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels() {
		Xijun.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
}
