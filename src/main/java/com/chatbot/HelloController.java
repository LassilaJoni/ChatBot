/**
 * A controller class for managing the main view of the chatbot application. This class handles
 * switching between different views (Chat, Settings, and Admin) and updating the tab titles
 * based on the current locale.
 */
package com.chatbot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

public class HelloController {

    LocalizationManager locale;
    @FXML
    private BorderPane rootLayout;

    @FXML
    private Tab chatTab;
    @FXML
    private Tab settingsTab;
    @FXML
    private Tab adminTab;

    /**
     * Loads and displays the ChatView in the center of the root layout.
     */
    @FXML
    private void showChatView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatView.fxml"));
            loader.setResources(ResourceBundle.getBundle("information", locale.getLocale()));
            Parent chatView = loader.load();
            rootLayout.setCenter(chatView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads and displays the SettingsView in the center of the root layout.
     * Also sets the HelloController for the SettingsViewController.
     */
    @FXML
    private void showSettingsView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsView.fxml"));
            loader.setResources(ResourceBundle.getBundle("information", locale.getLocale()));
            Parent chatView = loader.load();
            rootLayout.setCenter(chatView);
            SettingsViewController settingsController = loader.getController();
            settingsController.setHelloController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads and displays the AdminView or EditQAView in the center of the root layout,
     * depending on the login status of the user.
     */
    @FXML
    private void showAdminView() {
        if (LoginManager.getInstance().isLoggedIn()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditQAView.fxml"));
                loader.setResources(ResourceBundle.getBundle("information", locale.getLocale()));
                Parent adminView = loader.load();
                rootLayout.setCenter(adminView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
                loader.setResources(ResourceBundle.getBundle("information", locale.getLocale()));
                Parent adminView = loader.load();
                rootLayout.setCenter(adminView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Updates the titles of the tabs based on the current locale.
     */
    public void updateTabTitles() {
        ResourceBundle bundle = ResourceBundle.getBundle("information", locale.getLocale());

        chatTab.setText(bundle.getString("tab.chat"));
        settingsTab.setText(bundle.getString("tab.settings"));
        adminTab.setText(bundle.getString("tab.admin"));
    }
}