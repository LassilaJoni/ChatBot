package com.chatbot;

import java.util.ArrayList;

public class QAManager {
    private static QAManager qaManager_instance= null;
    private ArrayList<QA> qas;
    private DatabaseConnector db;
    public QAManager(){
        db = DatabaseConnector.getInstance();
        qas = db.fetchAllData();
    }
    public static QAManager getInstance(){
        if(qaManager_instance == null){
            qaManager_instance = new QAManager();
        }
        return qaManager_instance;
    }
    public void add(String question, String answer){
        db.addQA(question, answer);
    }
    public String ask(String question){
        String finalQuestion = parseQuestion(question);
        QA qaInList = qas.stream().filter(o -> finalQuestion.equals(o.getQuestion())).findFirst().orElse(null);
        if(qaInList != null){
            return  qaInList.getAnswer();
        }
        return "En voi vastata tähän kysymykseen :(";
    }
    //Parses Questions added to the database and asked by the user to a simplified format in order to increase matches
    public static String parseQuestion(String question){
        question = question.toUpperCase().replaceAll("[^a-zA-Z0-9ÅÄÖ -]", "");;
        return question;
    }
}
