package eu.pintergabor.signeditlite.mixin;

import eu.pintergabor.signeditlite.config.ModConfig;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import net.minecraft.client.util.SelectionManager;
import net.minecraft.util.Formatting;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
@Mixin(AbstractSignEditScreen.class)
public abstract class AbstractSignEditScreenMixin {
	@Shadow
	private @Nullable SelectionManager selectionManager;

	/**
	 * Insert '§' when 'Ctrl+[' is pressed
	 * <p>
	 * Extra feature for nerds.
	 */
	@Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
	private void keyPessed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
		// Global.LOGGER.info("Keycode: {}, Modifiers: {}", keyCode, modifiers);
		if (ModConfig.getInstance().enableFormattingKey &&
			keyCode == GLFW.GLFW_KEY_LEFT_BRACKET && ((modifiers & GLFW.GLFW_MOD_CONTROL) != 0)) {
			// Global.LOGGER.info("Ctrl+[");
			if (selectionManager != null) {
				selectionManager.insert(Formatting.FORMATTING_CODE_PREFIX);
				cir.setReturnValue(true);
			}
		}
	}
}
