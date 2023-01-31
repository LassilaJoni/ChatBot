package com.chatbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class ChatViewController {

    @FXML
    private BorderPane rootLayout;

    @FXML
    private Button SendButton;

    @FXML
    private TextField MessageField;

    @FXML
    private VBox MessageVBox;

    int distance = Integer.MAX_VALUE;

    private Map<String, String> chatBotResponses = new HashMap<>() {{
        put("Hi", "Hello!");
        put("How are you?", "I'm good, thanks for asking. How about you?");
        put("What's your name?", "I'm a chatbot. What's yours?");
       put("Hello", "Hello!");
    }};

    //Check for spelling using Levenshtein Distance Computing Algorithm
    private static int levenshteinDistance(String s1, String s2) {
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

        if(message.isEmpty()) return;
        MessageVBox.getChildren().add(text);
        MessageField.clear();

        String DefaultResponse = "Sorry I didn't understand your question" + " Here are some questions I know how to answer: \n" + chatBotResponses.keySet();

        for (Map.Entry<String, String> entry : chatBotResponses.entrySet()) {
            int currentDistance = levenshteinDistance(entry.getKey(), message);
            if (currentDistance < distance) {
                distance = currentDistance;
                DefaultResponse = entry.getValue();
            }
        }

        Text textResponse = new Text("ChatBot: "+ DefaultResponse);
        MessageVBox.getChildren().add(textResponse);
    }



}