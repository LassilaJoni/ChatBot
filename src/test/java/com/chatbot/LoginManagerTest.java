package com.chatbot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginManagerTest {

    private LoginManager loginManager;

    @BeforeEach
    void setUp() {
        loginManager = LoginManager.getInstance();
    }

    @Test
    void testLogin() {
        assertFalse(loginManager.isLoggedIn());
        loginManager.login("Miro", "Password123");
        assertTrue(loginManager.isLoggedIn());
    }

}
