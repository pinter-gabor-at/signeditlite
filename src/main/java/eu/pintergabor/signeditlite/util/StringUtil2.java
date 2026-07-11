package eu.pintergabor.signeditlite.util;

import org.jspecify.annotations.NonNull;

import net.minecraft.ChatFormatting;


/**
 * String manipulating utilities.
 */
public class StringUtil2 {

	/**
	 * Returns the {@code char} value at the specified index.
	 * <p>
	 * Similar to {@link String#charAt(int)}, but it returns '\0', instead of raising an exception on error.
	 *
	 * @param index the index of the {@code char} value.
	 * @return the {@code char} value at the specified index of the string, or '\0' if the {@code index} is negative or
	 * not less than the length of the string.
	 */
	public static char charAt(String string, int index) {
		return 0 <= index && index < string.length() ? string.charAt(index) : '\0';
	}

	/**
	 * Move the cursor position {@code pos} in the {@code input} string by {@code offset} amount.
	 * <p>
	 * Skip surrogate characters the same way as the original, and skip color and formatting codes.
	 *
	 * @param offset >=0.
	 * @return the new cursor position.
	 */
	public static int moveCursorForward(@NonNull String input, int pos, int offset) {
		final int len = input.length();
		int i = 0;
		while (pos < len && i < offset) {
			final char cc = charAt(input, pos);
			final char nc = charAt(input, pos + 1);
			if (Character.isHighSurrogate(cc) && Character.isLowSurrogate(nc)) {
				// Two character long UTF8 sequences count as one.
				pos += 2;
				i++;
			} else if (cc == ChatFormatting.PREFIX_CODE) {
				// Two character long formatting sequences count as zero.
				pos += 2;
			} else {
				// Normal characters count as one.
				pos++;
				i++;
			}
		}
		return Math.min(pos, len);
	}

	/**
	 * Move the cursor position {@code pos} in the {@code input} string by {@code offset} amount.
	 * <p>
	 * Skip surrogate characters the same way as the original, and skip color and formatting codes.
	 *
	 * @param offset >=0.
	 * @return the new cursor position.
	 */
	public static int moveCursorBackward(@NonNull String input, int pos, int offset) {
		int i = 0;
		while (0 < pos && i < offset) {
			final char cc = charAt(input, pos - 1);
			final char pc = charAt(input, pos - 2);
			if (Character.isLowSurrogate(cc) && Character.isHighSurrogate(pc)) {
				// Two character long UTF8 sequences count as one.
				pos -= 2;
				i++;
			} else if (pc == ChatFormatting.PREFIX_CODE) {
				// Two character long formatting sequences count as zero.
				pos -= 2;
			} else {
				// Normal characters count as one.
				pos--;
				i++;
			}
		}
		return Math.max(0, pos);
	}

	/**
	 * Move the cursor position {@code pos} in the {@code input} string by {@code offset} amount.
	 * <p>
	 * Skip surrogate characters the same way as the original, and skip color and formatting codes.
	 *
	 * @param offset if 0<=delta then move forward else move backward.
	 * @return the new cursor position.
	 */
	public static int moveCursor(@NonNull String input, int pos, int offset) {
		// Global.LOGGER.info("\"{}\", pos={}, offset={}", input, pos, offset);
		if (0 < offset) {
			// Move forward.
			pos = moveCursorForward(input, pos, offset);
		} else if (offset < 0) {
			// Move backward.
			pos = moveCursorBackward(input, pos, -offset);
		}
		// Global.LOGGER.info(" --> pos={}", pos);
		return pos;
	}
}
