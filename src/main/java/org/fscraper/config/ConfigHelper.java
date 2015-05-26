package org.fscraper.config;

/**
 * Get the config values based on a type of a value
 *
 *
 * @author Jamil Rzayev
 * @author Jamil Rzayev March, 2015
 */
public class ConfigHelper {

    /**
     * Get the config value as a {@link String}
     *
     * @param name The config setting name.
     * @param values The map of config values
     *
     * @return The value, or null if not found
     */
    public static String getString(String value) {
        if (value == null) {
            return null;
        }

        if ( String.class.isInstance( value ) ) {
            return (String) value;
        }
        return value.toString();
    }

    /**
     * Get the config value as a {@link String}
     *
     * @param value The config setting name.
     * @param values The map of config values
     * @param defaultValue The default value to use if not found
     *
     * @return The value.
     */
    public static String getString(String input, String defaultValue) {
        final String value = getString(input);
        return value == null ? defaultValue : value;
    }

    /**
     * Get the config value as a boolean (default of false)
     *
     * @param name The config setting name.
     * @param values The map of config values
     *
     * @return The value.
     */
    public static boolean getBoolean(String name) {
        return getBoolean(name, false);
    }

    /**
     * Get the config value as a boolean.
     *
     * @param name The config setting name.
     * @param values The map of config values
     * @param defaultValue The default value to use if not found
     *
     * @return The value.
     */
    public static boolean getBoolean(String strValue, boolean defaultValue) {

        boolean value;
        value = ( strValue == null )?
                defaultValue:
                Boolean.parseBoolean(strValue);

        return value;
    }

    /**
     * Get the config value as an int
     *
     * @param strValue 		- value from config values
     * @param defaultValue	- The default value to use if not found
     *
     * @return The value.
     *
     * @author Jamil Rzayev
     * @since 02-02-2015
     */
    public static int getInt(String strValue, int defaultValue) {

        int value;
        value = ((strValue == null) ) ? defaultValue:Integer.parseInt(strValue);
        return value;
    }
}