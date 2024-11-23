package eu.pintergabor.signeditlite;

import eu.pintergabor.signeditlite.util.FormatButtonsHandler;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Screen handler
		FormatButtonsHandler.init();
	}
}
