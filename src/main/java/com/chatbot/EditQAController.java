package com.chatbot;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    private void loadQuestions() {
        qaList.getItems().clear();
        qas = QAManager.getInstance().getQas();
        for (QA q : qas) {
            qaList.getItems().add(q.getId() + ".    " + bundle.getString("admin.question") + ":    " + q.getQuestion() + "    " + bundle.getString("admin.answer") + ":    " + q.getAnswer());
        }
    }
    private void loadData(){
        loadQuestions();
        qaList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                try {
                    String selectedString = (String) qaList.getSelectionModel().getSelectedItem();
                    if(selectedString != null) {
                        selectedId = Integer.parseInt(selectedString.substring(0, selectedString.indexOf(".")));
                        QA data = qas.stream().filter(d -> selectedId == d.getId()).findAny().orElse(null);
                        question.setText(data.getQuestion());
                        answer.setText(data.getAnswer());
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void save(){
        String questionSave = question.getText();
        String answerSave = answer.getText();
        DatabaseConnector.getInstance().updateQA(selectedId, questionSave, answerSave);
        question.clear();
        answer.clear();
        loadData();
    }
    public void delete(){
        DatabaseConnector.getInstance().removeQA(selectedId);
        question.clear();
        answer.clear();
        loadData();
    }
    public void add(){
        String questionAdd = question.getText();
        String answerAdda = answer.getText();
        DatabaseConnector.getInstance().addQA(questionAdd, answerAdda);
        question.clear();
        answer.clear();
        loadData();
    }
}
