package com.chatbot;

import java.util.ArrayList;

public class QAManager {
    private ArrayList<QA> Qas;
    private DatabaseConnector db;
    public QAManager(){
        db = new DatabaseConnector();
        db.connect();
        Qas = db.getQas();
    }
    public String ask(String question){
        question = parseQuestion(question);
        //TODO: Implement this
        return "";
    }
    //Parses Questions added to the database and asked by the user to a simplified format in order to increase matches
    public static String parseQuestion(String question){
        question = question.toUpperCase().replaceAll("[^a-zA-Z0-9ÅÄÖ]", " ");
        return question;
    }
}
