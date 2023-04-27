package com.chatbot.controllers;

import com.chatbot.managers.LocalizationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * A controller class for the Settings View in the chatbot application. This class handles
 * updating the user interface based on the user's language preferences.
 */
public class SettingsViewController {
    LocalizationManager locale;

    MainViewController hello;

    @FXML
    private MenuButton languageButton;
    @FXML
    private Label languageLabel;

    /**
     * Sets the application's locale to English and updates the user interface.
     */
    @FXML
    private void setEnglishLocale() {
        locale.setLocale(Locale.ENGLISH);
        updateUI();
    }

    /**
     * Sets the application's locale to Finnish and updates the user interface.
     */
    @FXML
    private void setFinnishLocale() {
        locale.setLocale(new Locale("fi"));
        updateUI();
    }

    /**
     * Updates the user interface elements with the appropriate language strings
     * based on the current locale.
     */
    private void updateUI() {
        ResourceBundle bundle = locale.getBundle();
        languageButton.setText(bundle.getString("button.currentLanguage"));
        languageLabel.setText(bundle.getString("settings.label.language"));
        hello.updateTabTitles();
    }

    /**
     * Sets the HelloController for this SettingsViewController.
     *
     * @param hello The HelloController instance
     */
    public void setHelloController(MainViewController hello) {
        this.hello = hello;
    }
}
