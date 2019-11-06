package com.huanmeng.mod.blocks.tileentity;

import com.huanmeng.mod.blocks.Food;
import com.huanmeng.mod.init.ModBlocks;
import com.huanmeng.mod.items.ItemXijunjammer;
import com.huanmeng.mod.Xijun;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSponge;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants.NBT;

public class TileEntityXijunreplace extends TileEntityXijun{

	Food replace;
	
	public TileEntityXijunreplace() {
		bacteriaBlock = ModBlocks.bacteria_replacer.getDefaultState();
		do
			colony = rand.nextInt();
		while(Xijun.jamcolonies.contains(Integer.valueOf(colony)));
	}
	
	@Override
	public void selectFood() {
		if(world.isBlockIndirectlyGettingPowered(getPos()) > 0) {
			IBlockState above = world.getBlockState(getPos().up());
			IBlockState below = world.getBlockState(getPos().down());
			if(above.getBlock() == Blocks.AIR || below.getBlock() == Blocks.AIR)
				return;
			if(above.getBlock() == ModBlocks.bacteria_replacer || below.getBlock() == ModBlocks.bacteria_replacer)
				return;
			if(above == below)
				return;
			addFood(below);
			replace = new Food(above);
			//world.setBlockToAir(getPos().up());
			world.setBlockToAir(getPos().up());
		}
	}
	
	@Override
	public boolean shouldStartInstantly() {
		return false;
	}
	
	@Override
	public void maybeEat(BlockPos pos) {
		if(isAtBorder(pos))
			return;
		if(isFood(world.getBlockState(pos))) {
			world.setBlockState(pos, bacteriaBlock);
			TileEntity newTile = world.getTileEntity(pos);
			TileEntityXijunreplace newTile2 = (TileEntityXijunreplace) newTile;
			newTile2.food = food;
			newTile2.colony = colony;
			newTile2.replace = replace;
		}
	}
	
	@Override
	public void die() {
		if(replace != null)
			world.setBlockState(getPos(), replace.state);
		else
			world.setBlockToAir(getPos());
		if(jammed)
			ItemXijunjammer.s += 1L;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		Block r = Block.getBlockById(nbt.getInteger("replace"));
		replace = new Food(r.getStateFromMeta(nbt.getInteger("replace_meta")));
//		replace = new Food(r.getStateFromMeta(getBlockMetadata()));
//		replace = new Food(r.getStateFromMeta(nbt.hashCode()));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		if(replace != null) {
			nbt.setInteger("replace", Block.getIdFromBlock(replace.state.getBlock()));
			nbt.setInteger("replace_meta", replace.state.getBlock().getMetaFromState(replace.state));
		}
		
		return nbt;
	}
}
