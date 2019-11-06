package com.huanmeng.mod;

import com.huanmeng.mod.init.ModBlocks;
import com.huanmeng.mod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabApple extends CreativeTabs {

	public TabApple(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.bacteriaapple);
	}
	@Override
	public boolean hasSearchBar() {
		return true;
	}
}
