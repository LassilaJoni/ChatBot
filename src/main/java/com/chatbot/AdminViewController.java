package com.chatbot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdminViewController {

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    private Label info;

    @FXML
    BorderPane adminRoot;
    @FXML
    private void login() {
        String user = Username.getText();
        String pass = Password.getText();
        Username.setText("");
        Password.setText("");
        LoginManager.getInstance().login(user, pass);
        if (LoginManager.getInstance().isLoggedIn()){
            info.setText("Logged in succesfully");
            loadAdminPanel();
        } else {
            info.setText("Incorrect username or password");
        }
    }

    private void loadAdminPanel() {

        Node node = info.getScene().lookup("#rootLayout");
        BorderPane bp = (BorderPane) node;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditQAView.fxml"));
            Parent adminView = loader.load();
            bp.setCenter(adminView);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}