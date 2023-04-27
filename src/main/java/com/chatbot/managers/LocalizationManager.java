package com.chatbot.managers;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * A utility class that manages localization resources for the chatbot application.
 */
public class LocalizationManager {

    /**
     * The base name of the resource bundle containing localized information.
     */
    private static final String BUNDLE_BASE_NAME = "information";

    /**
     * The current locale for the application. Defaults to the system's default locale.
     */
    private static Locale locale = Locale.getDefault();

    /**
     * Returns the ResourceBundle containing localized information
     * based on the current locale.
     *
     * @return the appropriate ResourceBundle for the current locale
     */
    public static ResourceBundle getBundle() {
        return ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale);
    }

    /**
     * Sets the current locale for the application.
     *
     * @param newLocale the new Locale to be set as the current locale
     */
    public static void setLocale(Locale newLocale) {
        locale = newLocale;
    }

    /**
     * Returns the current locale for the application.
     *
     * @return the current Locale used by the application
     */
    public static Locale getLocale() {
        return locale;
    }
}


