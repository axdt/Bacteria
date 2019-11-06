package com.huanmeng.mod;

import com.huanmeng.mod.init.ModBlocks;
import com.huanmeng.mod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabBacteria extends CreativeTabs{

	public TabBacteria(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.bacteria_replacer);
	}
	@Override
	public boolean hasSearchBar() {
		return true;
	}
}
