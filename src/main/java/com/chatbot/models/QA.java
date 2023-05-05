package com.chatbot.models;

/**
 * A class to model a question and its corresponding answer.
 */
public class QA {
    private int id;
    private String question, answer;

    /**
     * Constructs a QA object with the specified id, question, and answer.
     *
     * @param id       The unique identifier for the QA pair
     * @param question The question string
     * @param answer   The answer string
     */
    public QA(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    /**
     * Gets the unique identifier for the QA pair.
     *
     * @return The id of the QA pair
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the question string.
     *
     * @return The question
     */
    public String getQuestion() {
        return question;
    }


    /**
     * Gets the answer string.
     *
     * @return The answer
     */
    public String getAnswer() {
        return answer;
    }
}
