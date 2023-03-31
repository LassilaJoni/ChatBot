package com.chatbot;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;

import java.util.Locale;
import java.util.ResourceBundle;


public class SettingsViewController {
    LocalizationManager locale;

    HelloController hello;

    @FXML
    private MenuButton languageButton;

    @FXML
    private void setEnglishLocale() {
        locale.setLocale(Locale.ENGLISH);
        updateUI();
    }

    @FXML
    private void setFinnishLocale() {
        locale.setLocale(new Locale("fi"));
        updateUI();
    }

    private void updateUI() {
        ResourceBundle bundle = locale.getBundle();
        languageButton.setText(bundle.getString("button.currentLanguage"));
        hello.updateTabTitles();
    }

    public void setHelloController(HelloController hello) {
        this.hello = hello;
    }
}
