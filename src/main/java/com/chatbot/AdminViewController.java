package com.chatbot;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class AdminViewController {

    private DatabaseConnector dbconn = new DatabaseConnector();

    @FXML
    private TextField Username;

    @FXML
    private TextField Password;

    @FXML
    private Label info;

    @FXML
    private void login() {
        String user = Username.getText();
        String pass = Password.getText();
        Username.setText("");
        Password.setText("");
        boolean isSuccesfull = dbconn.login(user, pass);

        if (isSuccesfull){
            info.setText("Logged in succesfully");
        } else {
            info.setText("Incorrect username or password");
        }

        
    }

}