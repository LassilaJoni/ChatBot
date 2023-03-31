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

    public void updateTabTitles() {
        ResourceBundle bundle = ResourceBundle.getBundle("information", locale.getLocale());

        chatTab.setText(bundle.getString("tab.chat"));
        settingsTab.setText(bundle.getString("tab.settings"));
        adminTab.setText(bundle.getString("tab.admin"));
    }
}