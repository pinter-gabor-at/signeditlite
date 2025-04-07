package eu.pintergabor.signeditlite.config;

import eu.pintergabor.signeditlite.Global;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;


public class ModConfigData {
	// Builder.
	private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	// Enable Sign Text Formatting.
	private static final ModConfigSpec.BooleanValue ENABLE_SIGN_TEXT_FORMATTING = BUILDER
		.translation(Global.modName("config.enableSignTextFormatting"))
		.define("enableSignTextFormatting", true);
	public static boolean enableSignTextFormatting;
	// Enable Formatting Key.
	private static final ModConfigSpec.BooleanValue ENABLE_FORMATTING_KEY = BUILDER
		.translation(Global.modName("config.enableFormattingKey"))
		.define("enableFormattingKey", false);
	public static boolean enableFormattingKey;
	// Build and prepare for registration.
	public static final ModConfigSpec SPEC = BUILDER.build();

	/**
	 * Load parameters from TOML.
	 */
	@SuppressWarnings("unused")
	public static void onLoad(final ModConfigEvent event) {
		enableSignTextFormatting = ENABLE_SIGN_TEXT_FORMATTING.get();
		enableFormattingKey = ENABLE_FORMATTING_KEY.get();
	}
}
