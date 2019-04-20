package gui;

import domain.Domaincontroller;
import domain.Session;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;


import java.io.IOException;


public class _OverviewPresentsController extends AnchorPane {

    @FXML
    private JFXListView lstSessions;

    @FXML
    private Label lblTeacher;

    @FXML
    private Label lblDate;

    @FXML
    private JFXListView<String> lstPressents;


    private Domaincontroller dc;


    public _OverviewPresentsController(Domaincontroller dc){
        this.dc = dc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("_OverviewPresents.fxml"));
        loader.setRoot(this);
        loader.setController(this);



        try{
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildGui();
    }

    private void buildGui(){
      lstSessions.setItems(FXCollections.observableArrayList(dc.getAllSessions()));

        lstSessions.getSelectionModel().selectedItemProperty().addListener((ObservableValue,oldValue,newValue) ->
        {
            if(newValue != null)
            {
                Session sellectedSession = (Session) newValue;


                System.out.println("tekst voor in lbl: " + sellectedSession.toString());
                System.out.println("tekst voor in lbl2: " + sellectedSession.getTeacherUserId().getFirstName());


                lblDate.setText(String.format("Datum: %s", sellectedSession.toString()));
                lblTeacher.setText(String.format("Trainer: %s %s", sellectedSession.getTeacherUserId().getFirstName(), sellectedSession.getTeacherUserId().getName()));
                lblTeacher = new Label(sellectedSession.getTeacherUserId().getFirstName());

                lstPressents.setItems(FXCollections.observableArrayList(dc.getNamesMembersFromSession(sellectedSession)));


            }
        });


    }
}
