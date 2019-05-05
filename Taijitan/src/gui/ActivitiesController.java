package gui;

import com.jfoenix.controls.*;
import domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivitiesController extends AnchorPane implements PropertyChangeListener {
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
    @FXML
    private Label lblError;

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
    dc.setLijstConfirmed(new ArrayList<>(){});
    dc.setLijstMembers(dc.getAllMembers());
    lstMembers.setItems(dc.getLijstMembers());
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
    public void enableFields()
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
        boolean canSubmit = true;
        //TODO edit user

        int tp;
        if(cboType.getSelectionModel().getSelectedItem() == ActivityType.excursie.toString()){
            tp = 0;
        }
        else{
            tp = 1;
        }

        List<User> users = new ArrayList<>(dc.getLijstConfirmed());
        try{
            Activity act = new Activity("test",tp,Dates.convertToDate(dtmStart.getValue()),
                    Dates.convertToDate(dtmEnd.getValue()), users);
            dc.addActivity(act);
        }catch(IllegalArgumentException e)
        {
            canSubmit = false;
            lblError.setText(e.getMessage());
        }



        if(canSubmit) {
            toEditUser();
            emptyFields();
            fc.updateListPanelActivities();
        }
    }

    @FXML
    private void delete(){
        if (!isAdd) {
            if (dc.getCurrentActivity() != null) {
                if(AlertBoxController.ConfirmationAlert("Delete", "Wil je activiteit " + dc.getCurrentActivity().getName() + " verwijderen?")){
                    dc.deleteActivity();
                    emptyFields();
                    disableFields();
                    this.btnEdit.setDisable(true);
                    this.btnDelete.setDisable(true);
                    fc.setDisableAdd(false);
                    fc.updateListPanelActivities();
                }
            }
        } else {
            toEditUser();
            emptyFields();
            disableFields();
            fc.setDisableAdd(false);
        }
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
    public boolean getIsAdd() {
        return isAdd;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getNewValue() != null)
        {
            Activity act = (Activity)evt.getNewValue();
            txtName.setText(act.getName());
            dtmStart.setValue(Dates.convertToLocalDate(act.getStartDate()));
            dtmEnd.setValue(Dates.convertToLocalDate(act.getEndDate()));
            cboType.getSelectionModel().select(act.getType());
            dc.setLijstConfirmed(act.getUsers());
            dc.setLijstMembers(dc.getAllMembers().stream().filter(u -> !containsUser(act.getUsers(),u)).collect(Collectors.toList()));
            lstConfirmed.setItems(dc.getLijstConfirmed());
            lstMembers.setItems(dc.getLijstMembers());
            toEditUser();
            enableFields();
        }
    }
    private boolean containsUser(List<User> users,User user){
        return users.contains(user);
    }
}
