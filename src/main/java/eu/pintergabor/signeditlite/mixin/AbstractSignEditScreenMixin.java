package eu.pintergabor.signeditlite.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import eu.pintergabor.signeditlite.config.ModConfigData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
@Mixin(AbstractSignEditScreen.class)
public abstract class AbstractSignEditScreenMixin {
	@Final
	@Shadow
	@Nullable
	private TextFieldHelper signField;

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
		if (ModConfigData.getInstance().enableFormattingKey &&
			event.key() == InputConstants.KEY_LBRACKET && ((event.modifiers() & InputConstants.MOD_CONTROL) != 0)) {
			// Global.LOGGER.info("Ctrl+[");
			if (signField != null) {
				signField.charTyped(new CharacterEvent(ChatFormatting.PREFIX_CODE));
				cir.setReturnValue(true);
			}
		}
	}
}
