package com.huanmeng.mod.blocks;

import java.util.ArrayList;

import com.huanmeng.mod.blocks.tileentity.TileEntityXijun;
import com.huanmeng.mod.blocks.tileentity.TileEntityXijunreplace;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockXijun extends Blockbase{

	ArrayList<Integer> food = new ArrayList<Integer>();
	
	public BlockXijun(String name) {
		super(name);
		setHardness(0.07f);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World w, IBlockState state) {
		return new TileEntityXijun();
	}
	
}
