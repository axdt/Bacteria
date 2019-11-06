package com.huanmeng.mod.init;

import java.util.ArrayList;
import java.util.List;

import com.huanmeng.mod.blocks.BlockXijun;
import com.huanmeng.mod.blocks.BlockXijunjammer;
import com.huanmeng.mod.blocks.BlockXijunrelpcer;
import com.huanmeng.mod.blocks.Blockbase;

import net.minecraft.block.Block;

public class ModBlocks {
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block bacteria = new BlockXijun("bacteria");
	public static final Block bacteria_replacer = new BlockXijunrelpcer("replacer");
	public static final Block jammer = new BlockXijunjammer("jammer");
	public static final Block must = new Blockbase("must");
	
}
