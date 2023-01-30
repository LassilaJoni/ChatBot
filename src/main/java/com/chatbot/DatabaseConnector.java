package com.chatbot;
import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnector {
    private static DatabaseConnector db_instance = null;
    private final String host, username, password;
    private Connection connection;
    private Statement statement;
    private ArrayList<QA> qas;
    public DatabaseConnector(){
        Secrets secrets = new Secrets();
        host = secrets.host;
        username = secrets.username;
        password = secrets.password;
    }
    public static DatabaseConnector getInstance(){
        if(db_instance == null){
            db_instance = new DatabaseConnector();
        }
        return db_instance;
    }
    //Connects to database
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(host, username, password);
            statement = connection.createStatement();
        }catch (Exception e){
            System.out.println(e);
        }
        //System.out.println("Connected successfully");
    }
    //Disconnects from database
    private void disconnect(){
        try{
            connection.close();
            //System.out.println("Disconnected successfully");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //Fetches all data from database and puts them in an ArrayList
    public ArrayList<QA> fetchAllData(){
        connect();
        qas = new ArrayList<>();
        try{
            ResultSet resultSet = statement.executeQuery("select * from QA");
            while (resultSet.next()){
                qas.add(new QA(resultSet.getInt(1), QAManager.parseQuestion(resultSet.getString(2)), resultSet.getString(3)));
            }
            return qas;
        }catch (Exception e){
            System.out.println(e);
        }finally {
            disconnect();
        }
        return null;
    }
    //Adds Question and Answer to database
    public void addQA(String question, String answer){
        connect();
        if(question.length() > 255 || answer.length() > 255 || question.length() == 0 || answer.length() == 0) return;
        try{
            statement.executeUpdate("INSERT INTO `QA`(`Question`, `Answer`) VALUES ('" + question + "','" + answer + "')");
            System.out.println("Question added successfully");
        }catch (Exception e){
            System.out.println(e);
        }finally {
            disconnect();
        }
    }
    //Removes Question and Answer from database
    public void removeQA(int id){
        connect();
        try{
            statement.executeUpdate("DELETE FROM QA WHERE ID = " + id);
            System.out.println(id + " removed successfully");
        }catch (Exception e){
            System.out.println(e);
        }finally {
            disconnect();
        }
    }
    //Login
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
        } catch(Exception e) {
            System.out.println(e);
        }
        finally {
            disconnect();
        }
        System.out.println("Incorrect Username or Password");
        return false;
    }
    //Creates new Admin to the database
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
        }catch(Exception e) {
            System.out.println(e);
        }finally {
            disconnect();
        }
    }
}
