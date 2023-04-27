
package com.chatbot.controllers;

import com.chatbot.managers.LocalizationManager;
import com.chatbot.managers.LoginManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * A controller class for the Admin View in the chatbot application. This class handles
 * user login, displaying login status, and loading the admin panel if the login is successful.
 */

public class AdminViewController {

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    private Label info;

    @FXML
    BorderPane adminRoot;
    LocalizationManager locale;

    ResourceBundle bundle = ResourceBundle.getBundle("information", locale.getLocale());

    /**
     * Handles the login process by checking the user's credentials and updating
     * the info label accordingly. If the login is successful, it loads the admin panel.
     */
    @FXML
    private void login() {
        String user = Username.getText();
        String pass = Password.getText();
        Username.setText("");
        Password.setText("");
        LoginManager.getInstance().login(user, pass);
        if (LoginManager.getInstance().isLoggedIn()) {
            info.setText(bundle.getString("login.correctCredentials"));
            loadAdminPanel();
        } else {
            info.setText(bundle.getString("login.falseCredentials"));
        }
    }

    /**
     * Loads the admin panel (EditQAView) and replaces the current view in the center
     * of the root layout.
     */
    private void loadAdminPanel() {

        Node node = info.getScene().lookup("#rootLayout");
        BorderPane bp = (BorderPane) node;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/chatbot/EditQAView.fxml"));
            loader.setResources(ResourceBundle.getBundle("information", locale.getLocale()));
            Parent adminView = loader.load();
            bp.setCenter(adminView);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}