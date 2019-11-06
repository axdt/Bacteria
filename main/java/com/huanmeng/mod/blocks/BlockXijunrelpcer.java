package com.huanmeng.mod.blocks;

import com.huanmeng.mod.blocks.tileentity.TileEntityXijunreplace;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockXijunrelpcer extends Blockbase {

	public BlockXijunrelpcer(String name) {
		super(name);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World w, IBlockState state) {
		return new TileEntityXijunreplace();
	}
	
}
