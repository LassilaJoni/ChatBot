module com.chatbot {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.sql.rowset;


    opens com.chatbot to javafx.fxml;
    exports com.chatbot;
    exports com.chatbot.controllers;
    opens com.chatbot.controllers to javafx.fxml;
    exports com.chatbot.managers;
    opens com.chatbot.managers to javafx.fxml;
    exports com.chatbot.models;
    opens com.chatbot.models to javafx.fxml;
    exports com.chatbot.connectors;
    opens com.chatbot.connectors to javafx.fxml;
}