package com.chatbot.managers;

import com.chatbot.connectors.DatabaseConnector;

/**
 * Singleton class for managing login state and user authentication.
 */
public class LoginManager {
    private static LoginManager loginManager_instance = null;
    private DatabaseConnector db;
    private boolean loggedIn;

    /**
     * constructor for the singleton LoginManager.
     */
    LoginManager() {
        db = DatabaseConnector.getInstance();
        loggedIn = false;
    }

    /**
     * Gets the instance of the LoginManager.
     *
     * @return The singleton instance of LoginManager
     */
    public static LoginManager getInstance() {
        if (loginManager_instance == null) {
            loginManager_instance = new LoginManager();
        }
        return loginManager_instance;
    }

    /**
     * Attempts to log in using the provided username and password.
     *
     * @param username The username for login
     * @param password The password for login
     */
    public void login(String username, String password) {
        if (db.login(username, password)) loggedIn = true;
    }

    /**
     * Checks if the user is logged in.
     *
     * @return true if the user is logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
}
