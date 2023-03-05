package com.chatbot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QAManagerTest {

    private QAManager qaManager;

    @BeforeEach
    public void setUp() {
        qaManager = QAManager.getInstance();
    }

    @Test
    public void testAdd() {
        String questionAdd = "What is the meaning of life";
        String answerAdd = "IDK";
        qaManager.add(questionAdd, answerAdd);

        ArrayList<QA> qas;

        qas = qaManager.getQas();

        boolean foundAddedQA = false;

        for (QA qa : qas) {
            if (qa.getQuestion().equals(questionAdd.toUpperCase()) && qa.getAnswer().equals(answerAdd)) {
                foundAddedQA = true;
                break;
            }
        }

        assertTrue(foundAddedQA);
    }

    @Test
    void testAsk() {
        String question = "Toimiiko testi";
        String expectedAnswer = "toimiii";
        String actualAnswer = qaManager.ask(question);
        assertEquals(expectedAnswer, actualAnswer);

        question = "Bläää blääää bläääää";
        expectedAnswer = "En voi vastata tähän kysymykseen :(";
        actualAnswer = qaManager.ask(question);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    void testParseQuestion() {
        String question = "What is the meaning of life?";
        String parsedQuestion = "WHAT IS THE MEANING OF LIFE";
        String actualParsedQuestion = QAManager.parseQuestion(question);
        assertEquals(parsedQuestion, actualParsedQuestion);
    }

}
