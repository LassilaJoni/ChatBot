package com.chatbot;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ChatViewControllerTest {

    private ChatViewController chatViewController;

    @BeforeAll
    public void setUp() {
        chatViewController = new ChatViewController();
    }

    @Test
    public void testLevenshteinDistance() {
        String s1 = "miten menee";
        String s2 = "muten manee";
        int distance = chatViewController.levenshteinDistance(s1, s2);
        assertEquals(2, distance);
    }


}
