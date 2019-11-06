package com.huanmeng.mod.items;

import com.huanmeng.mod.Xijun;
import com.huanmeng.mod.init.ModItems;

import net.minecraft.item.Item;

public class Itemtea extends Item{
	public Itemtea(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		
		setCreativeTab(Xijun.tabBacteria);
		
		ModItems.ITEMS.add(this);
	}
}
