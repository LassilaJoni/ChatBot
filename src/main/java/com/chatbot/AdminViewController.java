package com.chatbot;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class AdminViewController {

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    private Label info;

    @FXML
    private void login() {
        String user = Username.getText();
        String pass = Password.getText();
        Username.setText("");
        Password.setText("");

        DatabaseConnector dbconn = new DatabaseConnector();
        new QAManager(dbconn);

        boolean isSuccesfull = dbconn.login(user, pass);

        if (isSuccesfull){
            info.setText("Logged in succesfully");
        } else {
            info.setText("Incorrect username or password");
        }

        
    }

}