package eu.pintergabor.signeditlite.mixin;

import java.util.function.Function;
import java.util.stream.Stream;

import eu.pintergabor.signeditlite.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.server.network.ServerPlayNetworkHandler;


@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {
	@Redirect(method = "onUpdateSign",
		at = @At(value = "INVOKE",
			target = "Ljava/util/stream/Stream;map(Ljava/util/function/Function;)Ljava/util/stream/Stream;"))
	private Stream<String> nothing(
		Stream<String> instance, Function<String, String> function) {
		// function === Formatting::strip
		// Strip formatting only if text formatting is disabled in config
		return ModConfig.getInstance().enableSignTextFormatting ?
			instance :
			instance.map(function);
	}
}
