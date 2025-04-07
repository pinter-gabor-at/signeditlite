package eu.pintergabor.signeditlite.util;

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
	 * Move the {@code cursor} in the {@code string} by a {@code delta} amount.
	 * <p>
	 * Skip surrogate characters the same way as the original, and skip color and formatting codes.
	 *
	 * @param delta >=0.
	 * @return the new cursor position.
	 */
	public static int moveCursorForward(String string, int cursor, int delta) {
		final int len = string.length();
		int i = 0;
		while (cursor < len && i < delta) {
			final char cc = charAt(string, cursor);
			final char nc = charAt(string, cursor + 1);
			if (Character.isHighSurrogate(cc) && Character.isLowSurrogate(nc)) {
				// Two character long UTF8 sequences count as one.
				cursor += 2;
				i++;
			} else if (cc == ChatFormatting.PREFIX_CODE) {
				// Two character long formatting sequences count as zero.
				cursor += 2;
			} else {
				// Normal characters count as one.
				cursor++;
				i++;
			}
		}
		return Math.min(cursor, len);
	}

	/**
	 * Move the {@code cursor} in the {@code string} by a {@code delta} amount.
	 * <p>
	 * Skip surrogate characters the same way as the original, and skip color and formatting codes.
	 *
	 * @param delta >=0.
	 * @return the new cursor position.
	 */
	public static int moveCursorBackward(String string, int cursor, int delta) {
		int i = 0;
		while (0 < cursor && i < delta) {
			final char cc = charAt(string, cursor - 1);
			final char pc = charAt(string, cursor - 2);
			if (Character.isLowSurrogate(cc) && Character.isHighSurrogate(pc)) {
				// Two character long UTF8 sequences count as one
				cursor -= 2;
				i++;
			} else if (pc == ChatFormatting.PREFIX_CODE) {
				// Two character long formatting sequences count as zero
				cursor -= 2;
			} else {
				// Normal characters count as one
				cursor--;
				i++;
			}
		}
		return Math.max(0, cursor);
	}

	/**
	 * Move the {@code cursor} in the {@code string} by a {@code delta} amount.
	 * <p>
	 * Skip surrogate characters the same way as the original, and skip color and formatting codes.
	 *
	 * @param delta if 0<=delta then move forward else move backward.
	 * @return the new cursor position.
	 */
	public static int moveCursor(String string, int cursor, int delta) {
		// Global.LOGGER.info("\"{}\", cursor={}, delta={}", string, cursor, delta);
		if (0 < delta) {
			// Move forward
			cursor = moveCursorForward(string, cursor, delta);
		} else if (delta < 0) {
			// Move backward
			cursor = moveCursorBackward(string, cursor, -delta);
		}
		// Global.LOGGER.info(" --> cursor={}", cursor);
		return cursor;
	}
}
