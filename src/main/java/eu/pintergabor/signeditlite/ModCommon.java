package eu.pintergabor.signeditlite;

import eu.pintergabor.signeditlite.config.ModConfigData;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.jspecify.annotations.NonNull;


/**
 * Common startup.
 */
@Mod(Global.MODID)
public final class ModCommon {

	@SuppressWarnings("unused")
	public ModCommon(
		final @NonNull IEventBus modEventBus,
		final @NonNull ModContainer modContainer,
		final Dist dist
	) {
		// Use configuration parameters on both sides and load them on startup.
		modContainer.registerConfig(ModConfig.Type.LOCAL, ModConfigData.SPEC);
		modEventBus.addListener(ModConfigData::onLoad);
	}
}
