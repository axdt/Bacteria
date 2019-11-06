package com.huanmeng.mod.blocks.tileentity;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import com.huanmeng.mod.blocks.Food;
import com.huanmeng.mod.init.ModBlocks;
import com.huanmeng.mod.items.ItemXijunjammer;
import com.huanmeng.mod.Xijun;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import scala.util.Random;

public class TileEntityXijun extends TileEntity implements ITickable {

	IBlockState bacteriaBlock;
	ArrayList<Food> food;
	Random rand = new Random();
	int colony;
	int tick = 0;
	public boolean startInstantly;
	boolean jammed;
	
	public TileEntityXijun() {
		if(food==null)
			food = new ArrayList<Food>();
		bacteriaBlock = ModBlocks.bacteria.getDefaultState();
		do
			colony = rand.nextInt();
		while(Xijun.jamcolonies.contains(Integer.valueOf(colony)));
	}
	
	@Override
	public void update() {
		if(world.isRemote)
			return;
		if(Xijun.jamcolonies.contains(Integer.valueOf(colony)) || Xijun.jam_all) {
			jammed = true;
			die();
			return;
		}
		if(food.size() == 0) {
			if(world.isBlockIndirectlyGettingPowered(this.getPos()) <= 0)
				return;
			selectFood();
			if(food.size() == 0)
				return;
			if(shouldStartInstantly())
				startInstantly = true;
		}
		if(!startInstantly) {
			if(Xijun.randomize)
				tick = rand.nextInt(Xijun.speed + 1);
			if(tick < Xijun.speed) {
				tick++;
				return;
			}
			tick = 0;
		}
		
		eatEverything();
		
	}
	
	public boolean shouldStartInstantly() {
		return true;
	}
	
	public void selectFood() {
		IBlockState state;
		BlockPos pos = this.getPos().up();
		while((state = world.getBlockState(pos)).getBlock() != Blocks.AIR) {
			addFood(state);
			pos = pos.up();
		}
	}
	
	public void addFood(IBlockState state) {
		if(isValidFood(state))
			food.add(new Food(state));
	}
	
	public static boolean isValidFood(IBlockState state) {
		if(state.getBlock() == Blocks.BEDROCK || state.getBlock() == ModBlocks.bacteria || state.getBlock() == ModBlocks.bacteria_replacer || state.getBlock() == Blocks.BRICK_BLOCK || state.getBlock() == ModBlocks.jammer )
			return false;
		return true;
	}
	
	public void eatEverything() {
		maybeEat(getPos().north());
		maybeEat(getPos().south());
		maybeEat(getPos().east());
		maybeEat(getPos().west());
		maybeEat(getPos().up());
		maybeEat(getPos().down());
		
		die();
	}
	
	public void maybeEat(BlockPos pos) {
		if(isAtBorder(pos))
			return;
		if(isFood(world.getBlockState(pos))) {
			world.setBlockState(pos, bacteriaBlock);
			((TileEntityXijun) world.getTileEntity(pos)).food = food;
			((TileEntityXijun) world.getTileEntity(pos)).colony = colony;
		}
	}
	
	public boolean isAtBorder(BlockPos pos) {
		while(world.getBlockState(pos).getBlock() != Block.getBlockFromName(Xijun.isolation)) {
			if(pos.getY() >= world.getActualHeight())
				return false;
			pos = pos.up();
		}
		return true;
	}
	
	Food grassFood = new Food(Blocks.GRASS.getDefaultState());
	Food dirtFood = new Food(Blocks.DIRT.getDefaultState());
	Food waterFood = new Food(Blocks.WATER.getDefaultState());
	Food flowingWaterFood = new Food(Blocks.FLOWING_WATER.getDefaultState());
	
	public boolean isFood(IBlockState state) {
		if(Xijun.jamcolonies.contains(Integer.valueOf(colony)))
			return false;
		if(state.getBlock() == ModBlocks.jammer) {
			Xijun.jamcolonies.add(Integer.valueOf(colony));
			jammed = true;
			return false;
		}
		if(Xijun.blacklist.size() > 0)
			for(Food f : Xijun.blacklist)
				if(isFood2(f,state))
					return false;
		if(food.size() > 0)
			for(Food f : food)
				if(isFood2(f,state))
					return true;
		return false;
	}
	
	private boolean isFood2(Food f, IBlockState state) {
		if(state == f.state)
			return true;
		
		else if(state == Blocks.DIRT.getDefaultState())
			return f.state == Blocks.GRASS.getDefaultState();
		else if(state == Blocks.GRASS.getDefaultState())
			return f.state == Blocks.DIRT.getDefaultState();
		
		else if(state == Blocks.WATER.getDefaultState())
			return f.state == Blocks.FLOWING_WATER.getDefaultState();
		else if(state == Blocks.FLOWING_WATER.getDefaultState())
			return f.state == Blocks.WATER.getDefaultState();
		
		else if(state == Blocks.LAVA.getDefaultState())
			return f.state == Blocks.FLOWING_LAVA.getDefaultState();
		else if(state == Blocks.FLOWING_LAVA.getDefaultState())
			return f.state == Blocks.LAVA.getDefaultState();
		else if(state == Blocks.WHITE_SHULKER_BOX.getBlockState())
			return f.state == Blocks.WHITE_SHULKER_BOX.getBlockState();
		
		return false;
	}
	
	public void die() {
		world.setBlockToAir(getPos());
		if(jammed)
			ItemXijunjammer.s += 1L;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if(food == null)
			food = new ArrayList<Food>();
		colony = nbt.getInteger("colony");
		int i = nbt.getInteger("numfood");
		
		for(int j = 0; j < i; j++) {
			int id = nbt.getInteger("food"+j);
			int meta = nbt.getInteger("food_meta"+j+"");
			Block b = Block.getBlockById(id);
			food.add(new Food(b.getStateFromMeta(meta)));
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("colony", colony);
		nbt.setInteger("numfod", food.size());
		for(int j = 0; j < food.size(); j++) {
			IBlockState bs = food.get(j).state;
			int id = Block.getIdFromBlock(bs.getBlock());
			nbt.setInteger("food"+j, id);
			nbt.setInteger("food_meta"+j, bs.getBlock().getMetaFromState(bs));
		}
		return nbt;
	}
	
}
