package com.chatbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ChatViewController {

    @FXML
    private BorderPane rootLayout;

    @FXML
    private Button SendButton;

    @FXML
    private TextField MessageField;

    @FXML
    private VBox MessageVBox;
    DatabaseConnector db = DatabaseConnector.getInstance();

    //Check for spelling using Levenshtein Distance Computing Algorithm
    private static int levenshteinDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]) + 1, dp[i - 1][j - 1] + cost);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    @FXML
    private void sendMessage() {
        String message = MessageField.getText();

        Text text = new Text("User: " + message);
        text.setWrappingWidth(MessageVBox.getWidth() - 10);

        if (message.isEmpty()) return;

        MessageVBox.getChildren().add(text);
        MessageField.clear();

        ArrayList <QA> qas = db.fetchAllData();

        //Determine the strictness of the algorithm, smaller number means more strict
        int inputStrictness = 4;

        String chatBotResponse = "Sorry I didn't understand your question.";

        for (QA qa: qas) {
            int newDistance = levenshteinDistance(message, qa.getQuestion());
            if (newDistance < inputStrictness) {
                inputStrictness = newDistance;
                chatBotResponse = qa.getAnswer();
            }
        }

        Text textResponse = new Text("ChatBot: " + chatBotResponse);
        MessageVBox.getChildren().add(textResponse);
    }
}