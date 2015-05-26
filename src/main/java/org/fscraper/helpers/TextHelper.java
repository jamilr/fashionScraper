package org.fscraper.helpers;

/**
 * Created by jr on 3/26/2015.
 */

public class TextHelper {

    public static String removeAllNonUnicode(String text) {

        return text.replaceAll("[^\\u0000-\\uFFFF]", "");
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
