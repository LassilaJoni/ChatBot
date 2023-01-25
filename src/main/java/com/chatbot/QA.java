package com.chatbot;

public class QA {
    private int id;
    private String question, answer;
    public QA(int id, String question, String answer){
        this.id = id;
        this.question = question;
        this.answer = answer;
    }
    public int getId(){
        return id;
    }
    public String getQuestion(){
        return  question;
    }
    public String getAnswer(){
        return answer;
    }
}
