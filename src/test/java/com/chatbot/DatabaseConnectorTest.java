package com.chatbot;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.*;

import java.util.ArrayList;

import com.chatbot.connectors.DatabaseConnector;
import com.chatbot.models.QA;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class DatabaseConnectorTest {

    boolean successfull = false;
    DatabaseConnector dbconn;

    @BeforeAll
    public void canConnect() {
        dbconn = DatabaseConnector.getInstance();
        dbconn.fetchAllData();
        successfull = dbconn.canConnect();
    }

    @Test
    public void testLogin() {
        final String VALID_USERNAME = "miro";
        final String VALID_PASSWORD = "Password123";
        final String INVALID_USERNAME = "joni";
        final String INVALID_PASSWORD = "Password321";

        assertTrue(dbconn.login(VALID_USERNAME, VALID_PASSWORD));

        assertFalse(dbconn.login(INVALID_USERNAME, INVALID_PASSWORD));

    }

    @Test
    public void fetchAllDataTest() {
        assumeTrue(successfull);
        ArrayList<QA> list = dbconn.fetchAllData();
        assertTrue((list.size() > 0));
    }

    @Test
    public void addQATest() {
        String testQuestion = "Test Question";
        String testAnswer = "Test Answer";

        dbconn.addQA(testQuestion, testAnswer);
        ArrayList<QA> qas;
        qas = dbconn.fetchAllData();
        boolean foundQA = false;

        for (QA qa : qas) {
            System.out.println(qa.getQuestion() + "," + qa.getAnswer());
            if (qa.getQuestion().equals(testQuestion.toUpperCase()) && qa.getAnswer().equals(testAnswer)) {
                foundQA = true;
                dbconn.removeQA(qa.getId());
                break;
            }
        }
        assertTrue(foundQA);
    }

    @Test
    public void updateQATest() {
        int id = 0;

        boolean foundUpdatedQA = false;
        boolean foundQA = false;

        String testQuestion = "Test update question";
        String testAnswer = "Test update answer";
        String updatedQuestion = "Updated Question";
        String updatedAnswer = "Updated Answer";

        dbconn.addQA(testQuestion, testAnswer);
        ArrayList<QA> qas;
        qas = dbconn.fetchAllData();

        for (QA qa : qas) {
            if (qa.getQuestion().equals(testQuestion.toUpperCase()) && qa.getAnswer().equals(testAnswer)) {
                foundQA = true;
                id = qa.getId();
                break;
            }
        }
        assertTrue(foundQA);

        dbconn.updateQA(id, updatedQuestion, updatedAnswer);
        qas = dbconn.fetchAllData();
        for (QA qa : qas) {
            if (qa.getId() == id && qa.getQuestion().equals(updatedQuestion.toUpperCase()) && qa.getAnswer().equals(updatedAnswer)) {
                foundUpdatedQA = true;
                dbconn.removeQA(qa.getId());
                break;
            }
        }
        assertTrue(foundUpdatedQA);
    }


}
