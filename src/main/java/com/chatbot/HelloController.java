package com.chatbot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class HelloController {

    @FXML
    private BorderPane rootLayout;

    @FXML
    private void showChatView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatView.fxml"));
            Parent chatView = loader.load();
            rootLayout.setCenter(chatView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showAdminView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
            Parent adminView = loader.load();
            rootLayout.setCenter(adminView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}