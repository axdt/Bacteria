/*package com.huanmeng.mod.potion;

import com.huanmeng.mod.blocks.tileentity.TileEntityXijun;
import com.huanmeng.mod.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;/*
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;//
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityBacteriaPotion extends EntityPotion {


	public EntityBacteriaPotion(World worldIn, EntityLivingBase throwerIn, ItemStack potionDamageIn) {
		super(worldIn, throwerIn, potionDamageIn);
		// TODO 自动生成的构造函数存根
	}

	@Override
	protected void onImpact(MovingObjectPosition objpos) {
		BlockPos pos = objpos.getBlockPos();
		if (objpos.typeOfHit == MovingObjectType.BLOCK) { // RenderPotion
			IBlockState state = world.getBlockState(pos);

			if (TileEntityXijun.isValidFood(state)) {
				world.setBlockState(pos, ModBlocks.bacteria.getDefaultState());
				TileEntity t = world.getTileEntity(pos);

				if (t != null && t instanceof TileEntityXijun) {
					TileEntityXijun tile = (TileEntityXijun) t;
					tile.addFood(state);
					if (tile.shouldStartInstantly())
						tile.startInstantly = true;
				}
			}

			this.world.playEvent(2002, this.getPosition(), this.getPotionDamage());
			this.setDead();
		}
	}

	public int getPotionDamage() { // 6,8,12 //TODO what is this? comment this please. (magic numbers :P)
		return 12;
	}
}*/
package com.huanmeng.mod.potion;

public class EntityBacteriaPotion{}