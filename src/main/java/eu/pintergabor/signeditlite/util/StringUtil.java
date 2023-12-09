package eu.pintergabor.signeditlite.util;

import net.minecraft.util.Formatting;

/**
 * String manipulating utilities
 */
public class StringUtil {
	private StringUtil() {
	}

	/**
	 * Moves the {@code cursor} in the {@code string} by a {@code delta} amount.
	 * <p>
	 * Skips surrogate characters the same way as the original, and skips color and formatting codes.
	 */
	public static int moveCursor(String string, int cursor, int delta) {
		// Global.LOGGER.info("\"{}\", cursor={}, delta={}", string, cursor, delta);
		int len = string.length();
		if (delta >= 0) {
			// Move forward
			for (int i = 0; cursor < len && i < delta; ) {
				final char cc = string.charAt(cursor);
				final char nc = cursor + 1 >= len ? '\0' : string.charAt(cursor + 1);
				if (Character.isHighSurrogate(cc) && Character.isLowSurrogate(nc)) {
					// Two character long UTF8 sequences count as one
					cursor += 2;
					i++;
				} else if (cc == Formatting.FORMATTING_CODE_PREFIX) {
					// Two character long formatting sequences count as zero
					cursor += 2;
				} else {
					// Normal characters count as one
					cursor++;
					i++;
				}
			}
			if (cursor >= len) {
				cursor = len;
			}
		} else {
			// Move backward
			for (int i = delta; cursor > 0 && i < 0; ) {
				final char cc = string.charAt(cursor - 1);
				final char pc = cursor - 2 < 0 ? '\0' : string.charAt(cursor - 2);
				if (Character.isLowSurrogate(cc) && Character.isHighSurrogate(pc)) {
					// Two character long UTF8 sequences count as one
					cursor -= 2;
					i++;
				} else if (pc == Formatting.FORMATTING_CODE_PREFIX) {
					// Two character long formatting sequences count as zero
					cursor -= 2;
				} else {
					// Normal characters count as one
					cursor--;
					i++;
				}
			}
			if (cursor < 0) {
				cursor = 0;
			}
		}
		// Global.LOGGER.info(" --> cursor={}", cursor);
		return cursor;
	}
}
