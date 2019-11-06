package com.huanmeng.mod.potion;

import com.huanmeng.mod.items.ItemBase;
import com.huanmeng.mod.utils.IHasModel;

import ibxm.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBacteriaPotion extends ItemBase{

	public ItemBacteriaPotion(String name) {
		super(name);
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack,World world,EntityPlayer player) {
		if (!player.capabilities.isCreativeMode) {
			itemstack.getMaxStackSize();
		}
		//world.playSound(player, "random.bow", 0.5F, 0.4F/(itemRand.nextFloat() * 0.4F + 0.8F));
		if (!world.isRemote) {
			//world.spawnEntity(new EntityBacteriaPotion(world,player,itemstack));//
		}
		return itemstack;
		
	}
}
