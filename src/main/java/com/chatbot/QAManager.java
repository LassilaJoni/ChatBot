package com.chatbot;

import java.util.ArrayList;

public class QAManager {
    private ArrayList<QA> qas;
    private DatabaseConnector db;
    public QAManager(DatabaseConnector db){
        this.db = db;
        this.db.connect();
        qas = getQas();
    }
    private synchronized ArrayList<QA> getQas(){
        while (db.getQas() == null){
            try{
                wait();
            }catch (Exception e){
                System.out.println(e);
            }
        }
        notifyAll();
        return db.getQas();
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
        question = question.toUpperCase().replaceAll("[^a-zA-Z0-9ÅÄÖ]", " ");
        return question;
    }
}
