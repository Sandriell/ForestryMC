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
package forestry.plugins;

import net.minecraftforge.fluids.Fluid;

import cpw.mods.fml.common.Optional;

import forestry.core.GameMode;
import forestry.core.config.Defaults;
import forestry.core.fluids.Fluids;
import forestry.core.proxy.Proxies;

import buildcraft.api.fuels.BuildcraftFuelRegistry;

@Plugin(pluginID = "BC6|Fuels", name = "BuildCraft 6 Fuels", author = "mezz", url = Defaults.URL, unlocalizedDescription = "for.plugin.buildcraft6.description")
public class PluginBuildCraftFuels extends ForestryPlugin {

	@Override
	public boolean isAvailable() {
		return Proxies.common.isAPILoaded("buildcraft.api.fuels", "[2.0, 3.0)");
	}

	@Override
	public String getFailMessage() {
		return "Compatible BuildCraftAPI|fuels version not found";
	}

	@Optional.Method(modid = "BuildCraftAPI|fuels")
	@Override
	public void doInit() {
		BuildcraftFuelRegistry.coolant.addCoolant(Fluids.ICE.getFluid(), 10.0f);

		Fluid ethanol = Fluids.ETHANOL.getFluid();
		if (ethanol != null) {
			int ethanolPower = Math.round(GameMode.getGameMode().getFloatSetting("fuel.ethanol.combustionPower"));
			int ethanolBurnTime = Math.round(Defaults.ENGINE_CYCLE_DURATION_ETHANOL * GameMode.getGameMode().getFloatSetting("fuel.ethanol.combustion"));
			BuildcraftFuelRegistry.fuel.addFuel(ethanol, ethanolPower, ethanolBurnTime);
		}
	}

}
