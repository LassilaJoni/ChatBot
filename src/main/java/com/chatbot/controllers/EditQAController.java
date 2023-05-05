package com.chatbot.controllers;

import com.chatbot.connectors.DatabaseConnector;
import com.chatbot.managers.LocalizationManager;
import com.chatbot.models.QA;
import com.chatbot.managers.QAManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller class for editing, saving, and deleting question-answer pairs in the application.
 */
public class EditQAController implements Initializable{
    @FXML
    ListView qaList;
    @FXML
    TextField question;
    @FXML
    TextField answer;
    @FXML
    Button save;
    @FXML
    Button delete;
    ArrayList<QA> qas;
    private int selectedId;

    LocalizationManager locale;

    ResourceBundle bundle = ResourceBundle.getBundle("information", locale.getLocale());

    /**
     * Initializes the controller class, loads data, and sets up event listeners.
     *
     * @param url            The location used to resolve relative paths for the root object
     * @param resourceBundle The resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    /**
     * Loads questions and answers into the ListView.
     */
    private void loadQuestions() {
        qaList.getItems().clear();
        qas = QAManager.getInstance().getQas();
        for (QA q : qas) {
            qaList.getItems().add(q.getId() + ".    " + bundle.getString("admin.question") + ":    " + q.getQuestion() + "    " + bundle.getString("admin.answer") + ":    " + q.getAnswer());
        }
    }

    /**
     * Loads data and sets up event listeners for ListView selections.
     */
    private void loadData() {
        loadQuestions();
        qaList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                try {
                    String selectedString = (String) qaList.getSelectionModel().getSelectedItem();
                    if (selectedString != null) {
                        selectedId = Integer.parseInt(selectedString.substring(0, selectedString.indexOf(".")));
                        QA data = qas.stream().filter(d -> selectedId == d.getId()).findAny().orElse(null);
                        question.setText(data.getQuestion());
                        answer.setText(data.getAnswer());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Saves the updated question and answer to the database.
     */
    public void save() {
        String questionSave = question.getText();
        String answerSave = answer.getText();
        DatabaseConnector.getInstance().updateQA(selectedId, questionSave, answerSave);
        question.clear();
        answer.clear();
        loadData();
    }

    /**
     * Deletes the selected question and answer from the database.
     */
    public void delete() {
        DatabaseConnector.getInstance().removeQA(selectedId);
        question.clear();
        answer.clear();
        loadData();
    }

    /**
     * Adds a new question and answer to the database.
     */
    public void add() {
        String questionAdd = question.getText();
        String answerAdda = answer.getText();
        DatabaseConnector.getInstance().addQA(questionAdd, answerAdda);
        question.clear();
        answer.clear();
        loadData();
    }
}
