package com.huanmeng.mod.items;

import com.huanmeng.mod.Xijun;
import com.huanmeng.mod.version.Test;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState.ModState;

public class ItemXijunjammer extends ItemBase{

	int tick;
	public static long s;
	
	public ItemXijunjammer() {
		super("jammeritem");
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int i, boolean flag) {
		if(tick > 0) {
			tick--;
			if(tick == 0) {
				Xijun.jam_all = false;
				((EntityPlayer)entity).sendMessage(new TextComponentTranslation("xijun.item.xijunjammer.sendmessage.clear",s));
				s = 0L;
			}
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		if(worldIn.isRemote)
			return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
		Xijun.jam_all = true;
		tick = 30;
		playerIn.sendMessage(new TextComponentTranslation("xijun.item.xijunjammer.sendmessage.ready"));
		//playerIn.sendMessage(new TextComponentString("clering bacteria..."));
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}	
}
