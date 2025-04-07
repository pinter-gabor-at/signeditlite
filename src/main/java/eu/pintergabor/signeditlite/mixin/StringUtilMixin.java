package eu.pintergabor.signeditlite.mixin;

import eu.pintergabor.signeditlite.config.ModConfigData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.ChatFormatting;
import net.minecraft.util.StringUtil;


@Mixin(StringUtil.class)
public abstract class StringUtilMixin {

	/**
	 * Unfortunately the easiest way to keep '§' in sign texts is to enable it everywhere.
	 * <p>
	 * This may lead to incompatibilities.
	 */
	@Inject(method = "isAllowedChatCharacter", at = @At("HEAD"), cancellable = true)
	private static void isAllowedChatCharacter(char c, CallbackInfoReturnable<Boolean> cir) {
		if (ModConfigData.getInstance().enableSignTextFormatting) {
			// Allow sign texts to contain the formatting code prefix.
			if (c == ChatFormatting.PREFIX_CODE) {
				cir.setReturnValue(true);
			}
		}
	}
}
