package com.lab221.lab221asm6.utils;

public class StringUtil {

    /**
     * Check blank string.
     * @param text the string to check
     * @return true if blank
     */
    public static boolean isBlank(String text) {
        return text == null || text.isBlank();
    }
}
