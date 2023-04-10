package com.chatbot;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationManager {

    private static final String BUNDLE_BASE_NAME = "information";
    private static Locale locale = Locale.getDefault();

    public static ResourceBundle getBundle() {
        return ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale);
    }

    public static void setLocale(Locale newLocale) {
        locale = newLocale;
    }

    public static Locale getLocale() {
        return locale;
    }
}


