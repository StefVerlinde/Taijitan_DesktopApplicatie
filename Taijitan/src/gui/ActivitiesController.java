package gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import domain.Activity;
import domain.ActivityType;
import domain.Domaincontroller;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ActivitiesController extends AnchorPane {
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXDatePicker dtmStart;
    @FXML
    private JFXDatePicker dtmEnd;
    @FXML
    private JFXComboBox<String> cboType;
    @FXML
    private JFXListView<User> lstMembers;
    @FXML
    private JFXListView<User> lstConfirmed;

    @FXML
    void toConfirmed(MouseEvent event) {
        User u = lstMembers.getSelectionModel().getSelectedItem();

        dc.addConfirmed(u);
        lstMembers.setItems(dc.getLijstMembers());
        lstConfirmed.setItems(dc.getLijstConfirmed());

        lstMembers.getSelectionModel().clearSelection();
    }

    @FXML
    void toMember(MouseEvent event) {
        User u = lstConfirmed.getSelectionModel().getSelectedItem();

        dc.removeConfirmed(u);
        lstMembers.setItems(dc.getLijstMembers());
        lstConfirmed.setItems(dc.getLijstConfirmed());

        lstConfirmed.getSelectionModel().clearSelection();
    }

    @FXML
    void addActivity(ActionEvent event) {
        int tp;
        if(cboType.getSelectionModel().getSelectedItem() == ActivityType.excursion.toString()){
            tp = 0;
        }
        else{
            tp = 1;
        }

        List<User> users = new ArrayList<>(dc.getLijstConfirmed());
//        Activity act = new Activity(tp, convertToDate(dtmStart.getValue()), convertToDate(dtmEnd.getValue()), users);
        Activity act = new Activity(txtName.getText(), tp, convertToDateViaSqlDate(dtmStart.getValue()), convertToDateViaSqlDate(dtmEnd.getValue()), users);
        System.out.println(act.getEndDate());
        System.out.println(act.getStartDate());
        System.out.println(act.getName());
        System.out.println(act.getUsers().toString());


        dc.addActivity(act);
    }


    private Domaincontroller dc;

    public ActivitiesController(Domaincontroller dc){
        this.dc = dc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Activities.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildGui();
    }

    private void buildGui(){
        lstMembers.setItems(dc.getLijstMembers());
        txtName.setEditable(true);


        ObservableList<String> types = FXCollections.observableArrayList(ActivityType.stage.toString(), ActivityType.excursion.toString());
        cboType.setItems(types);
    }

    private Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }





}
