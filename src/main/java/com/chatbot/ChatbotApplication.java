package com.chatbot;

import com.chatbot.managers.LocalizationManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * The main entry point for the Chatbot application. This class initializes the primary stage
 * and loads the root layout with the corresponding localization settings.
 */
public class ChatbotApplication extends Application {

    LocalizationManager local;

    /**
     * Starts the chatbot application by initializing the primary stage and loading the root layout
     * with the appropriate localization settings.
     *
     * @param stage The primary stage for the application
     * @throws IOException If an error occurs during loading the FXML layout
     */
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ChatbotApplication.class.getResource("RootLayout.fxml"));
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

    /**
     * The main method that launches the chatbot application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}