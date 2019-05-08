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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
    private final ObservableList<String> types = FXCollections.observableArrayList(ActivityType.stage.toString(), ActivityType.excursie.toString());


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
    lblError.setText("");
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
        if(!isAdd){
            //edit activity
            try{
                Activity act = dc.getCurrentActivity();
                act.setName(txtName.getText());
                act.setUsers(dc.getLijstConfirmed());
                act.setStartDate(Dates.convertToDate(dtmStart.getValue()));
                act.setEndDate(Dates.convertToDate(dtmEnd.getValue()));

                /*if(!Dates.sameDay(act.getStartDate(),Dates.convertToDate(dtmStart.getValue())))
                {
                    act.setStartDate(Dates.convertToDate(dtmStart.getValue()));
                }

                if(!Dates.sameDay(act.getEndDate(),Dates.convertToDate(dtmEnd.getValue())))
                {
                    act.setEndDate(Dates.convertToDate(dtmEnd.getValue()));
                }*/

                if (cboType.getSelectionModel().isEmpty()) {
                    throw new IllegalArgumentException("Type mag niet leeg zijn");
                } else {
                    act.setType(cboType.getSelectionModel().getSelectedIndex());
                }
            }
            catch(IllegalArgumentException e)
            {
                canSubmit = false;
                lblError.setText(e.getMessage());
            }

            if(canSubmit) {
                dc.updateActivity();
                disableFields();
                emptyFields();
                fc.updateListPanelActivities();
            }
        }
        else{
            //add activity
            List<User> users = new ArrayList<>(dc.getLijstConfirmed());
            Activity act = new Activity();
            try{
                act.setName(txtName.getText());
                act.setUsers(users);
                act.setStartDate(Dates.convertToDate(dtmStart.getValue()));
                act.setEndDate(Dates.convertToDate(dtmEnd.getValue()));

                if (cboType.getSelectionModel().isEmpty()) {
                    throw new IllegalArgumentException("Type mag niet leeg zijn");
                } else {
                    act.setType(cboType.getSelectionModel().getSelectedIndex());
                }


            }catch(IllegalArgumentException e)
            {
                canSubmit = false;
                lblError.setText(e.getMessage());
            }

            if(canSubmit) {
                dc.addActivity(act);
                toEditUser();
                emptyFields();
                fc.updateListPanelActivities();
            }
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

    public void fillFieldWithSelectedActivity(Activity selectedActivity) {
        enableAll();
        txtName.setText(selectedActivity.getName());
        dtmStart.setValue(convertToLocalDate(selectedActivity.getStartDate()));
        dtmEnd.setValue(convertToLocalDate(selectedActivity.getEndDate()));
        cboType.setItems(types);
        if(selectedActivity.getType() == 0){
            cboType.getSelectionModel().selectFirst();
        }
        else{
            cboType.getSelectionModel().select(1);
        }

        lstConfirmed.setItems(FXCollections.observableArrayList(selectedActivity.getUsers()));

        List<User> notConfirmedUsers = dc.getAllUsers();
        for (User u : selectedActivity.getUsers()){
            notConfirmedUsers.remove(u);
        }

        lstMembers.setItems(FXCollections.observableArrayList(notConfirmedUsers));
    }

    private LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private void enableAll(){
        txtName.setDisable(false);
        dtmEnd.setDisable(false);
        dtmStart.setDisable(false);
        lstMembers.setDisable(false);
        lstConfirmed.setDisable(false);
        cboType.setDisable(false);
        btnEdit.setDisable(false);
        btnDelete.setDisable(false);
    }


}
