/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.core.gadgets;

import forestry.core.CreativeTabForestry;
import forestry.core.interfaces.IOwnable;
import forestry.core.proxy.Proxies;
import forestry.core.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

public abstract class BlockForestry extends BlockContainer {

	public BlockForestry(Material material) {
		super(material);
		setHardness(1.5f);
		setCreativeTab(CreativeTabForestry.tabForestry);
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
		IOwnable tile = (IOwnable) world.getTileEntity(x, y, z);
		if (!tile.isOwnable() || tile.allowsRemoval(player))
			return super.removedByPlayer(world, player, x, y, z, willHarvest);
		else
			return false;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {

		if (!Proxies.common.isSimulating(world))
			return;

		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileForestry) {
			TileForestry tileForestry = (TileForestry) tile;
			Utils.dropInventory(tileForestry, world, x, y, z);
			tileForestry.onRemoval();
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemstack) {

		if (!Proxies.common.isSimulating(world))
			return;

		TileForestry tile = (TileForestry) world.getTileEntity(i, j, k);
		if (entityliving instanceof EntityPlayer)
			tile.setOwner(((EntityPlayer) entityliving));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		try {
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile instanceof TileForestry)
				((TileForestry) tile).onNeighborBlockChange(block);
		} catch (StackOverflowError error) {
			Proxies.log.logThrowable(Level.ERROR, "Stack Overflow Error in BlockMachine.onNeighborBlockChange()", 10, error);
			throw error;
		}
	}

}
