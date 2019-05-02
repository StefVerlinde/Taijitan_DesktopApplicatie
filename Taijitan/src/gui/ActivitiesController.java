package gui;

import com.jfoenix.controls.*;
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
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    private boolean isAdd;
    private Domaincontroller dc;
    private FrameController fc;

    public ActivitiesController(Domaincontroller dc,FrameController fc){
        this.dc = dc;
        this.fc = fc;
        isAdd = false;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Activities.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildGui();
        fc.setDisableAdd(false);
        emptyFields();
        disableFields();
    }

    private void buildGui(){
        lstMembers.setItems(dc.getLijstMembers());
        txtName.setEditable(true);
        ObservableList<String> types = FXCollections.observableArrayList(ActivityType.stage.toString(), ActivityType.excursie.toString());
        cboType.setItems(types);
    }

    private void toEditUser() {
        btnEdit.setText("Pas activiteit aan");
        btnDelete.setText("Verwijder Activiteit");
        fc.setVisibleAdd(true);
        isAdd = false;
        btnDelete.setDisable(true);
    }

    public void emptyFields(){
    txtName.setText("");
    dtmEnd.getEditor().clear();
    dtmStart.getEditor().clear();
    cboType.getSelectionModel().clearSelection();
    lstConfirmed.setItems(null);

    }
    public void disableFields(){
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
        txtName.setDisable(true);
        dtmStart.setDisable(true);
        dtmEnd.setDisable(true);
        lstConfirmed.setDisable(true);
        lstMembers.setDisable(true);
        cboType.setDisable(true);
    }
    public void enableField()
    {
        btnDelete.setDisable(false);
        btnEdit.setDisable(false);
        txtName.setDisable(false);
        dtmStart.setDisable(false);
        dtmEnd.setDisable(false);
        lstConfirmed.setDisable(false);
        lstMembers.setDisable(false);
        cboType.setDisable(false);
    }

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
    private void edit() {
        int tp;
        if(cboType.getSelectionModel().getSelectedItem() == ActivityType.excursie.toString()){
            tp = 0;
        }
        else{
            tp = 1;
        }

        List<User> users = new ArrayList<>(dc.getLijstConfirmed());
        Activity act = new Activity(txtName.getText(), tp, convertToDateViaSqlDate(dtmStart.getValue()), convertToDateViaSqlDate(dtmEnd.getValue()), users);
        System.out.println(act.getEndDate());
        System.out.println(act.getStartDate());
        System.out.println(act.getName());
        System.out.println(act.getUsers().toString());


        dc.addActivity(act);
    }

    @FXML
    private void delete(){
        if (!isAdd) {
            //TODO
        } else {
            toEditUser();
            emptyFields();
            disableFields();
            fc.setDisableAdd(false);
        }
    }

    private Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public void setIsAdd(boolean b)
    {
        this.isAdd = b;
    }
    public void setBtnEditText(String s)
    {
        this.btnEdit.setText(s);
    }
    public void setBtnDeleteText(String s)
    {
        this.btnDelete.setText(s);
    }
    public void setDisableDelete(boolean b)
    {
        this.btnDelete.setDisable(b);
    }



}
