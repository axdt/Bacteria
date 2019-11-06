package com.huanmeng.mod.blocks;

import java.util.Random;

import com.huanmeng.mod.Xijun;
import com.huanmeng.mod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockA extends Blockbase{

	public static final int MAX_GROW_INDEX = 7;
	public static final PropertyInteger GROWN = PropertyInteger.create("grown", 0, MAX_GROW_INDEX);
	
	public int tick;
	public boolean drop = true;
	
	public BlockA(String name) {
		super(name);
		setTickRandomly(true);
	}
	@Override
	public int quantityDropped(Random random) {
		return 1 + random.nextInt(2);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if((Integer)state.getValue(GROWN) >= MAX_GROW_INDEX)
			return ModItems.bacteria;
		return Item.getItemFromBlock(this);
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {GROWN});
	}
	
	public int getMetaFromState(IBlockState state) {
		return (Integer)state.getValue(GROWN);
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(GROWN, meta);
	}

}
