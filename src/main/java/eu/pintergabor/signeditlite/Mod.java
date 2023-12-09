package eu.pintergabor.signeditlite;

import eu.pintergabor.signeditlite.config.ModConfig;

import net.fabricmc.api.ModInitializer;

public class Mod implements ModInitializer {

	@Override
	public void onInitialize() {
		ModConfig.init();
	}
}
