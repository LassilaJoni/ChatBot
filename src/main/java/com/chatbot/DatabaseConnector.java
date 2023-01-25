package com.chatbot;
import java.sql.*;
public class DatabaseConnector {
    private final String host, username, password;
    private Connection connection;
    private Statement statement;
    public DatabaseConnector(){
        Secrets secrets = new Secrets();
        host = secrets.host;
        username = secrets.username;
        password = secrets.password;
    }
    //Connects to database
    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(host, username, password);
            statement = connection.createStatement();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //Adds Question and Answer to database
    public void addQA(String question, String answer){
        if(question.length() > 255 || answer.length() > 255 || question.length() == 0 || answer.length() == 0) return;
        try{
            statement.executeUpdate("INSERT INTO `QA`(`Question`, `Answer`) VALUES ('" + question + "','" + answer + "')");
            System.out.println("Question added successfully");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //Removes Question and Answer from database
    public void removeQA(int id){
        try{
            statement.executeUpdate("DELETE FROM QA WHERE ID = " + id);
            System.out.println(id + " removed successfully");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //Disconnects from database
    public void disconnect(){
        try{
            connection.close();
            System.out.println("Disconnected successfully");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //Returns entire QA table from database as String
    @Override
    public String toString() {
        String returnString = "";
        try{
            ResultSet resultSet = statement.executeQuery("select  * from QA");
            while (resultSet.next()){
                returnString += "ID: " + resultSet.getInt(1) + ", Question: " + resultSet.getString(2) + ", Answer: " + resultSet.getString(3) + "\n";
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return returnString;
    }
}
