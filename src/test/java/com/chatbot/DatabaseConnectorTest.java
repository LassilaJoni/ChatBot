package com.chatbot;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class DatabaseConnectorTest {
    
    //TODO: fix this test file :D

    boolean successfull = false;
    DatabaseConnector dbconn;

    @BeforeAll
    public void canConnect(){
        dbconn = com.chatbot.DatabaseConnector.getInstance();
        successfull = dbconn.canConnect();
    }

    @Test
    public void fetchAllDataTest() {
        assumeTrue(successfull);
        ArrayList<QA> list = dbconn.fetchAllData();
        assertTrue((list.size()>0));
    }

}
