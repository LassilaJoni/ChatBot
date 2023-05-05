package com.chatbot.managers;

import com.chatbot.connectors.DatabaseConnector;
import com.chatbot.models.QA;

import java.util.ArrayList;

/**
 * Singleton class for managing the chatbot's questions and answers.
 */
public class QAManager {
    private static QAManager qaManager_instance = null;
    private ArrayList<QA> qas;
    private DatabaseConnector db;

    /**
     * Constructor for the singleton QAManager.
     */
    public QAManager() {
        db = DatabaseConnector.getInstance();
        qas = db.fetchAllData();
    }

    /**
     * Gets the instance of the QAManager.
     *
     * @return The singleton instance of QAManager
     */
    public static QAManager getInstance() {
        if (qaManager_instance == null) {
            qaManager_instance = new QAManager();
        }
        return qaManager_instance;
    }

    /**
     * Adds a question and answer to the database.
     *
     * @param question The question to add
     * @param answer   The answer to add
     */
    public void add(String question, String answer) {
        db.addQA(question, answer);
    }

    /**
     * Attempts to find an answer to the given question.
     *
     * @param question The question to ask
     * @return The answer to the question, or a default message if not found
     */
    public String ask(String question) {
        String finalQuestion = parseQuestion(question);
        QA qaInList = qas.stream().filter(o -> finalQuestion.equals(o.getQuestion())).findFirst().orElse(null);
        if (qaInList != null) {
            return qaInList.getAnswer();
        }
        return "En voi vastata tähän kysymykseen :(";
    }

    /**
     * Fetches all question and answer pairs from the database.
     *
     * @return An ArrayList of QA objects
     */
    public ArrayList getQas() {
        qas = db.fetchAllData();
        return qas;
    }

    /**
     * Parses a question into a simplified format to increase matches.
     *
     * @param question The question to parse
     * @return The parsed question
     */
    public static String parseQuestion(String question) {
        question = question.toUpperCase().replaceAll("[^a-zA-Z0-9ÅÄÖ -]", "");
        ;
        return question;
    }
}
