package com.huanmeng.mod.blocks;

import net.minecraft.block.state.IBlockState;

public class Food {
	
	public IBlockState state;
	
	public Food(IBlockState state) {
		this.state = state;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Food))
			return false;
		Food f = (Food)o;
		return this.state == f.state;
	}
	
	public String toString() {
		return String.format("Food[%s]", state);
	}
}
