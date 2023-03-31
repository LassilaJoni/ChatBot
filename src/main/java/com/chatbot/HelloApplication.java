package com.chatbot;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloApplication extends Application {

    LocalizationManager local;
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RootLayout.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("information", local.getLocale()));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("ChatBot v0.1");
            stage.getIcons().add(new Image("https://i.imgur.com/Z1VJWEl.png"));
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}