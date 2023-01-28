package com.chatbot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ChatViewController {

    @FXML
    private BorderPane rootLayout;

    @FXML
    private Button SendButton;

    @FXML
    private TextField MessageField;

    @FXML
    private VBox MessageVBox;

    @FXML
    private void sendMessage() {
        
    }

}