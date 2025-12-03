package eu.pintergabor.signeditlite.mixin;

import eu.pintergabor.signeditlite.config.ModConfigData;
import eu.pintergabor.signeditlite.util.FormatButtonsHandler;

import net.minecraft.client.input.CharacterEvent;

import net.minecraft.client.input.KeyEvent;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;


@Mixin(AbstractSignEditScreen.class)
public abstract class AbstractSignEditScreenMixin {
	@Shadow
	@Nullable
	private TextFieldHelper signField;

	/**
	 * Create a hook at the end of init to add more screen widgets to the screen.
	 */
	@Inject(method = "init", at = @At("TAIL"))
	private void init(CallbackInfo ci) {
		FormatButtonsHandler.onScreenOpened((AbstractSignEditScreen) (Object) this);
	}

	/**
	 * Insert '§' when 'Ctrl+[' is pressed.
	 * <p>
	 * Undocumented extra feature for nerds.
	 */
	@Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
	private void keyPessed(
		KeyEvent event, CallbackInfoReturnable<Boolean> cir
	) {
		// Global.LOGGER.info("Keycode: {}, Modifiers: {}", event.key(), event.modifiers());
		if (ModConfigData.enableSignTextFormatting && ModConfigData.enableFormattingKey &&
			event.key() == GLFW.GLFW_KEY_LEFT_BRACKET && ((event.modifiers() & GLFW.GLFW_MOD_CONTROL) != 0)) {
			// Global.LOGGER.info("Ctrl+[");
			if (signField != null) {
				signField.charTyped(new CharacterEvent(ChatFormatting.PREFIX_CODE, 0));
				cir.setReturnValue(true);
			}
		}
	}
}
