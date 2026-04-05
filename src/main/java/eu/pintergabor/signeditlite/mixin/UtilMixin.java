package eu.pintergabor.signeditlite.mixin;

import eu.pintergabor.signeditlite.config.ModConfigData;
import eu.pintergabor.signeditlite.util.StringUtil2;
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
	@Inject(method = "offsetByCodepoints", at = @At("HEAD"), cancellable = true)
	private static void newMoveCursor(
		String input, int pos, int offset,
		CallbackInfoReturnable<Integer> cir
	) {
		if (ModConfigData.enableSignTextFormatting) {
			cir.setReturnValue(StringUtil2.moveCursor(input, pos, offset));
		}
	}
}
