package com.chatbot;

import java.sql.Blob;
import java.sql.ResultSet;

public class LoginManager {
    private static LoginManager loginManager_instance = null;
    private DatabaseConnector db;
    private boolean loggedIn;
    LoginManager(){
        db = DatabaseConnector.getInstance();
        loggedIn = false;
    }
    public static LoginManager getInstance(){
        if(loginManager_instance == null){
            loginManager_instance = new LoginManager();
        }
        return loginManager_instance;
    }
    //Login
    public void login(String username, String password) {
        if(db.login(username, password)) loggedIn = true;
    }
    public boolean isLoggedIn(){
        return loggedIn;
    }
}
