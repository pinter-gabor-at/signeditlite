package eu.pintergabor.signeditlite.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import org.jetbrains.annotations.ApiStatus;


@ApiStatus.Internal
@Config(name = "signeditlite")
public class ModConfigData implements ConfigData {
	@ConfigEntry.Gui.Tooltip
	public boolean enableSignTextFormatting = true;

	@ConfigEntry.Gui.Tooltip
	public boolean enableFormattingKey = false;

	public static void init() {
		AutoConfig.register(ModConfigData.class, Toml4jConfigSerializer::new);
	}

	public static ModConfigData getInstance() {
		return AutoConfig.getConfigHolder(ModConfigData.class).getConfig();
	}
}
