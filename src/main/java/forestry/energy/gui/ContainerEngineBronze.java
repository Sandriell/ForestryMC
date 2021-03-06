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
package forestry.energy.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import forestry.core.gui.ContainerLiquidTanks;
import forestry.core.gui.slots.SlotFiltered;
import forestry.energy.gadgets.EngineBronze;

public class ContainerEngineBronze extends ContainerLiquidTanks {

	protected final EngineBronze engine;

	public ContainerEngineBronze(InventoryPlayer player, EngineBronze engine) {
		super(engine);

		this.engine = engine;

		this.addSlotToContainer(new SlotFiltered(engine, EngineBronze.SLOT_CAN, 143, 40));

		int i;
		for (i = 0; i < 3; ++i) {
			for (int var4 = 0; var4 < 9; ++var4) {
				this.addSlotToContainer(new Slot(player, var4 + i * 9 + 9, 8 + var4 * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
		}

	}

}
