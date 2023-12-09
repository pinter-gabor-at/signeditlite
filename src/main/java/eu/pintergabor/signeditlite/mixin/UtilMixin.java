package eu.pintergabor.signeditlite.mixin;

import eu.pintergabor.signeditlite.config.ModConfig;
import eu.pintergabor.signeditlite.util.StringUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.util.Util;

@Mixin(Util.class)
public abstract class UtilMixin {
	/**
	 * Rewrite {@code moveCursor} to work the same way as the original one, but skip formatting codes too.
	 */
	@Inject(method = "moveCursor", at = @At("HEAD"), cancellable = true)
	private static void newMoveCursor(
		String string, int cursor, int delta,
		CallbackInfoReturnable<Integer> cir){
		if (ModConfig.getInstance().enableSignTextFormatting) {
			cir.setReturnValue(StringUtil.moveCursor(string, cursor, delta));
		}
	}
}
