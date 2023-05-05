package com.chatbot.connectors;

import com.chatbot.managers.QAManager;
import com.chatbot.models.Password;
import com.chatbot.models.QA;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.ArrayList;


/**
 * A singleton class for connecting to the database and performing CRUD operations.
 */
public class DatabaseConnector {
    private static DatabaseConnector db_instance = null;
    private final String host, username, password;
    private Connection connection;
    private Statement statement;
    private ArrayList<QA> qas;
    private boolean canConnect = false;

    /**
     * constructor for initializing database connection properties.
     */
    public DatabaseConnector() {
        host = System.getenv("host");
        username = System.getenv("username");
        password = System.getenv("password");
    }

    /**
     * Returns the singleton instance of the DatabaseConnector.
     *
     * @return DatabaseConnector instance
     */
    public static DatabaseConnector getInstance() {
        if (db_instance == null) {
            db_instance = new DatabaseConnector();
        }
        return db_instance;
    }

    /**
     * Connects to the database.
     */
    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(host, username, password);
            statement = connection.createStatement();
            canConnect = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        //System.out.println("Connected successfully");
    }

    /**
     * Disconnects from the database.
     */
    private void disconnect() {
        try {
            connection.close();
            //System.out.println("DcanConnect successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Fetches all data from the database and puts them in an ArrayList.
     *
     * @return ArrayList of QA objects fetched from the database
     */
    public ArrayList<QA> fetchAllData() {
        connect();
        qas = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from QA");
            while (resultSet.next()) {
                qas.add(new QA(resultSet.getInt(1), QAManager.parseQuestion(resultSet.getString(2)), resultSet.getString(3)));
            }
            return qas;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            disconnect();
        }
        return null;
    }

    /**
     * Adds a question and answer to the database.
     *
     * @param question The question to be added
     * @param answer   The answer to be added
     */
    public void addQA(String question, String answer) {
        connect();
        if (question.length() > 255 || answer.length() > 255 || question.length() == 0 || answer.length() == 0) return;
        try {
            statement.executeUpdate("INSERT INTO `QA`(`Question`, `Answer`) VALUES ('" + question + "','" + answer + "')");
            System.out.println("Question added successfully");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            disconnect();
        }
    }

    /**
     * Updates a question and answer in the database by ID.
     *
     * @param id       The ID of the question and answer to update
     * @param question The updated question
     * @param answer   The updated answer
     */
    public void updateQA(int id, String question, String answer) {
        connect();
        try {
            statement.executeUpdate("UPDATE QA SET Question = '" + question + "', Answer = '" + answer + "' WHERE ID = " + id);
            System.out.println(id + " updated successfully");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            disconnect();
        }
    }

    /**
     * Removes a question and answer from the database by ID.
     *
     * @param id The ID of the question and answer to remove
     */
    public void removeQA(int id) {
        connect();
        try {
            statement.executeUpdate("DELETE FROM QA WHERE ID = " + id);
            System.out.println(id + " removed successfully");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            disconnect();
        }
    }

    /**
     * Verifies login credentials of an admin.
     *
     * @param username The admin's username
     * @param password The admin's password
     * @return true if the credentials are correct, false otherwise
     */
    public boolean login(String username, String password) {
        connect();
        try {
            ResultSet rs = statement.executeQuery("SELECT Hash, Salt FROM Admins WHERE Username = '" + username + "'");
            if (!rs.isBeforeFirst()) {
                System.out.println("Incorrect Username or Password");
                return false;
            }
            Blob hashBlob = null;
            Blob saltBlob = null;

            if(rs.next()) {
                hashBlob = rs.getBlob(1);
                saltBlob = rs.getBlob(2);
            }
            if(Password.isCorrectPassword(password, saltBlob.getBytes(1, (int) saltBlob.length()), hashBlob.getBytes(1, (int) hashBlob.length()))) {
                System.out.println("Login successful");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            disconnect();
        }
        System.out.println("Incorrect Username or Password");
        return false;
    }

    /**
     * Creates a new admin in the database.
     *
     * @param username The admin's username
     * @param password The admin's password
     */
    public void newAdmin(String username, String password) {
        connect();
        byte[] salt = Password.getSalt();
        byte[] hash = Password.hash(password, salt);
        try {
            Blob saltBlob = new SerialBlob(salt);
            Blob hashBlob = new SerialBlob(hash);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Admins (Username, Hash, Salt) VALUES (?,?,?)");
            statement.setString(1, username);
            statement.setBlob(2, hashBlob);
            statement.setBlob(3, saltBlob);
            statement.executeUpdate();
            System.out.println("Created new Admin: " + username);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            disconnect();
        }
    }

    /**
     * Checks if the connector can connect to the database.
     *
     * @return true if the connector can connect, false otherwise
     */
    public boolean canConnect() {
        return canConnect;
    }
}
