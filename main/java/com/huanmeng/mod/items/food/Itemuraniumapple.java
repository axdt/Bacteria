package com.huanmeng.mod.items.food;

import java.util.List;

import com.huanmeng.mod.Xijun;
import com.huanmeng.mod.init.ModItems;
import com.huanmeng.mod.utils.IHasModel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Itemuraniumapple extends ItemFood implements IHasModel{
	public static ItemStack setLore(ItemStack itemStack, List<String>lore) {
		final NBTTagList list = new NBTTagList();
		for(String line : lore) {
			list.appendTag(new NBTTagString(line));
		}
		itemStack.getOrCreateSubCompound("display").setTag("Lore", list);
		return itemStack;
	}
	public Itemuraniumapple(int amount, boolean isWolfFood) {
		super(amount, isWolfFood);
		setUnlocalizedName("xijun.uraniumapple");
		setRegistryName("uraniumapple");
		setCreativeTab(Xijun.tabApple);
		ModItems.ITEMS.add(this);
		}
		@Override
		public void registerModels() {
			Xijun.proxy.registerItemRenderer(this,0,"inventory");
		}
    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(ItemStack stack)
    {
        return super.hasEffect(stack) || stack.getMetadata() > 0;
    }

    public EnumRarity func_77613_e(ItemStack stack)
    {
        return stack.getMetadata() == 0 ? EnumRarity.COMMON : EnumRarity.COMMON ;
    }

    protected void func_77849_c(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
            if (stack.getMetadata() > 0)
            {
            	player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 6000, 6));
                player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 6000, 6));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 1200, 6));
                player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 6000, 6));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 6));
                player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 1200, 6));
            }
            else
            {
            	player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 6000, 6));
                player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 6000, 6));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 1200, 6));
                player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 6000, 6));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 6));
                player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 1200, 6));
            }
        }
    }
}
