module com.chatbot {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.chatbot to javafx.fxml;
    exports com.chatbot;
}