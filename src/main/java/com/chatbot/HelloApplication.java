package com.chatbot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("RootLayout.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        QAManager qaManager = new QAManager(databaseConnector);
        System.out.println(qaManager.ask("Toimiiko testi?"));
        //databaseConnector.newAdmin("Miro", "Password123");
        databaseConnector.login("Miro", "Password123");
        launch();


    }
}