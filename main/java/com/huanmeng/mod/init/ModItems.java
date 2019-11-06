package com.huanmeng.mod.init;

import java.util.ArrayList;

import java.util.List;

import com.huanmeng.mod.items.ItemXijunjammer;
import com.huanmeng.mod.items.Itemtea;
import com.huanmeng.mod.items.food.Itembronzeapple;
import com.huanmeng.mod.items.food.Itemcopperapple;
import com.huanmeng.mod.items.food.Itemdiamondapple;
import com.huanmeng.mod.items.food.Itemgoldapple;
import com.huanmeng.mod.items.food.Itemiridiumapple;
import com.huanmeng.mod.items.food.Itemironapple;
import com.huanmeng.mod.items.food.Itemleadapple;
import com.huanmeng.mod.items.food.Itemnickelapple;
import com.huanmeng.mod.items.food.Itemplatinumapple;
import com.huanmeng.mod.items.food.Itemsilverapple;
import com.huanmeng.mod.items.food.Itemsteelapple;
import com.huanmeng.mod.items.food.Itemtinapple;
import com.huanmeng.mod.items.food.Itemtungstenapple;
import com.huanmeng.mod.items.food.Itemuraniumapple;
import com.huanmeng.mod.items.food.Itemxijunapple;
import com.huanmeng.mod.potion.ItemBacteriaPotion;
import com.huanmeng.mod.items.ItemBase;

import net.minecraft.item.Item;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item bacteria = new ItemBase("bunch");
	public static final Item jammerItem = new ItemXijunjammer();
	public static final Item bacteriaapple = new Itemxijunapple(0,false);
	public static final Item ironapple = new Itemironapple(0,false);
	public static final Item goldapple = new Itemgoldapple(0,false);
	public static final Item diamondapple = new Itemdiamondapple(0,false);
	public static final Item copperapple = new Itemcopperapple(0,false);
	public static final Item steelapple = new Itemsteelapple(0,false);
	public static final Item tinapple = new Itemtinapple(0,false);
	public static final Item tungstenapple = new Itemtungstenapple(0,false);
	public static final Item bronze = new Itembronzeapple(0,false);
	public static final Item iridium = new Itemiridiumapple(0,false);
	public static final Item leadapple = new Itemleadapple(0,false);
	public static final Item nickelapple = new Itemnickelapple(0,false);
	public static final Item platinumapple = new Itemplatinumapple(0,false);
	public static final Item silverapple = new Itemsilverapple(0,false);
	public static final Item uraniumapple = new Itemuraniumapple(0,false);
	//public static final Item bacteriapotion = new ItemBacteriaPotion("bacteria_potion");
}
