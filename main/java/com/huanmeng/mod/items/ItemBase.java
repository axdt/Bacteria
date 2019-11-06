package com.huanmeng.mod.items;

import com.huanmeng.mod.init.ModItems;
import com.huanmeng.mod.utils.IHasModel;
import com.huanmeng.mod.Xijun;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name) {
		setUnlocalizedName(I18n.translateToLocal("xijun."+name));
		setRegistryName(name);
		setFull3D();
		setCreativeTab(Xijun.tabBacteria);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Xijun.proxy.registerItemRenderer(this,0,"inventory");
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer plyaer) {
		return null;
	}
	
}
