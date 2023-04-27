package com.chatbot.controllers;

import com.chatbot.connectors.DatabaseConnector;
import com.chatbot.managers.LocalizationManager;
import com.chatbot.models.QA;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * A controller class for managing the chat view in the chatbot application. This class
 * handles sending and displaying messages between the user and the chatbot, as well as
 * loading and storing message history.
 */
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
    private ScrollPane chatScroll;

    private LocalizationManager locale;

    DatabaseConnector db = DatabaseConnector.getInstance();

    static ArrayList<AnchorPane> messageHistory = new ArrayList<>();

    ArrayList<QA> qas = new ArrayList<>();

    /**
     * Initializes the chat view by loading message history and fetching data from the database.
     */
    @FXML
    public void initialize() {
        qas = db.fetchAllData();
        //load message history on page enter
        for (AnchorPane ap : messageHistory) {
            MessageVBox.getChildren().add(ap);
        }
    }

    /**
     * Computes the Levenshtein distance between two strings. The distance is the minimum number
     * of single-character edits (insertions, deletions, or substitutions) required to change one
     * string into the other.
     *
     * @param s1 The first string to be compared
     * @param s2 The second string to be compared
     * @return The Levenshtein distance between the two strings
     */
    public static int levenshteinDistance(String s1, String s2) {
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

    /**
     * Handles the action of sending a message by the user. The user's message is displayed,
     * and the chatbot generates a response based on the message.
     */
    @FXML
    private void sendMessage() {

        String message = MessageField.getText();

        if (message.isEmpty()) return;

        messageFactory(messageType.USER, message);
        MessageField.clear();

        //Determine the strictness of the algorithm, smaller number means more strict
        int inputStrictness = 4;

        ResourceBundle bundle = locale.getBundle();
        String chatBotResponse = bundle.getString("chat.meNoUnderstand");

        for (QA qa : qas) {
            int newDistance = levenshteinDistance(message, qa.getQuestion());
            if (newDistance < inputStrictness) {
                inputStrictness = newDistance;
                chatBotResponse = qa.getAnswer();
            }
        }

        messageFactory(messageType.BOT, chatBotResponse);
    }

    /**
     * A private enum representing the types of messages, either from the user or the bot.
     */
    private enum messageType {
        USER,
        BOT
    }

    /**
     * A factory method to create and display messages in the chat view. This method handles
     * the formatting and styling of messages based on the message type.
     *
     * @param type    The type of the message, either from the user or the bot
     * @param message The content of the message
     */
    private void messageFactory(messageType type, String message) {
        Label msg = new Label(message);
        msg.setWrapText(true);
        msg.setTextAlignment(TextAlignment.JUSTIFY);
        AnchorPane ap = new AnchorPane();

        ap.prefHeight(Region.USE_COMPUTED_SIZE);
        ap.prefWidth(Region.USE_COMPUTED_SIZE);

        switch (type){
            case USER -> {
                msg.setId("userMessage");
                ap.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            }
            case BOT -> {
                msg.setId("botMessage");
            }
        }

        msg.setMaxWidth(MessageVBox.getWidth() / 2);
        ap.getChildren().add(msg);
        MessageVBox.getChildren().add(ap);

        messageHistory.add(ap);

        //Sticks to bottom of the scrollbar
        chatScroll.vvalueProperty().bind(MessageVBox.heightProperty());

    }

}