package eu.pintergabor.signeditlite;

import eu.pintergabor.signeditlite.config.ModConfigData;

import net.fabricmc.api.ModInitializer;


public class Mod implements ModInitializer {

	@Override
	public void onInitialize() {
		ModConfigData.init();
	}
}
