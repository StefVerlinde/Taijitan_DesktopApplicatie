package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import domain.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class ListPanelController <T> extends VBox  {
    private Domaincontroller dc;
    private FrameController fc;
    @FXML
    private JFXListView<T> lstItems;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXComboBox cboRank;


    public ListPanelController(Domaincontroller dc, FrameController fc) {
        this.dc = dc;
        this.fc = fc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }


    public void fillWithMembers(){
        btnAdd.setText("Voeg lid toe");
        lstItems.setItems((ObservableList<T>)dc.getAllMembersFX());

        lstItems.getSelectionModel().selectedItemProperty().addListener((ObservableValue,oldValue,newValue) ->
        {
            if(newValue != null)
            {
                User usr = (User)newValue;
                if(fc.isAddingMember()){
                    if(AlertBoxController.ConfirmationAlert("Alert", "Ben je zeker dat je dit scherm wilt verlaten? Alle ingegeven gegevens zullen verloren gaan.")){
                        this.dc.setCurrentUser(usr);
                    }
                    else {
                        lstItems.refresh();
                    }
                }
                else {
                    this.dc.setCurrentUser(usr);
                }
            }
        });
    }

    public void fillWithMembersScore(){
        btnAdd.setVisible(false);
        lstItems.setItems((ObservableList<T>)dc.getAllMembersFX());

        lstItems.getSelectionModel().selectedItemProperty().addListener((ObservableValue,oldValue,newValue) ->
        {
            if(newValue != null)
            {
                User usr = (User)newValue;
                this.dc.setCurrentUserScore(usr);
            }
        });
    }

    public void fillWithActivities(){
        btnAdd.setText("Voeg activiteit toe");
        lstItems.setItems((ObservableList<T>) dc.getAllActivitiesFX());

        lstItems.getSelectionModel().selectedItemProperty().addListener((ObservableValue,oldValue,newValue) ->
        {
            if(newValue != null)
            {
                Activity act = (Activity)newValue;
                if(fc.isAddingActivity()){
                    if(AlertBoxController.ConfirmationAlert("Alert", "Ben je zeker dat je dit scherm wilt verlaten? Alle ingegeven gegevens zullen verloren gaan.")){
                        this.dc.setCurrentActivity(act);
                    }
                    else {
                        lstItems.refresh();
                    }
                }
                else {
                    this.dc.setCurrentActivity(act);
                }
            }
        });
    }
    public void fillWithCourseMaterial(){
        lstItems.getSelectionModel().clearSelection();
        cboRank.setVisible(true);
        btnAdd.setText("Voeg lesmateriaal toe");
        lstItems.setItems((ObservableList<T>)dc.getLijstCourseMaterial());

        cboRank.getItems().setAll(Rank.values());

        cboRank.valueProperty().addListener(new ChangeListener<Rank>() {
            @Override public void changed(ObservableValue ov, Rank old, Rank newV) {
                dc.filterCourseMaterial(newV);
            }
        });

        lstItems.getSelectionModel().selectedItemProperty().addListener((ObservableValue,oldValue,newValue) ->
        {
            if(newValue != null)
            {
                CourseMaterial courseM = (CourseMaterial)newValue;
                dc.setCurrentCourseMaterial(courseM);
            }
        });

    }

    public void setDisableAdd(boolean b){
        this.btnAdd.setDisable(b);
    }
    public void setVisibleAdd(boolean b){
        this.btnAdd.setVisible(b);
    }
    @FXML
    private void add() {

        if(btnAdd.getText().equals("Voeg lid toe"))
        {
            fc.setIsAddMembers(true);
            this.setVisibleAdd(false);
            fc.setBtnEditTextMembers("Voeg lid loe");
            fc.setBtnDeleteTextMembers("Annuleer");
            fc.setDisableDeleteMembers(false);
            fc.enableFieldsMember();
            fc.emptyFieldsMember();
        }
        else if(btnAdd.getText().equals("Voeg activiteit toe")){
            fc.setIsAddActivities(true);
            this.setVisibleAdd(false);
            fc.setBtnEditTextActivities("Voeg activiteit toe");
            fc.setBtnDeleteTextActivities("Annuleer");
            fc.setDisableDeleteActivities(false);
            fc.enableFieldsActivities();
            fc.emptyFieldsActivities();
        }
        else if(btnAdd.getText().equals("Voeg lesmateriaal toe")){
            fc.setIsAddCourseMaterial(true);
            this.setVisibleAdd(false);
            fc.setBtnEditTextCourseMaterial("Voeg lesmateriaal toe");
            fc.setBtnDeleteTextCourseMaterial("Annuleer");
            fc.setDisableDeleteCourseMaterial(false);
            fc.enableFieldsCourseMaterial();
            fc.emptyFieldsCourseMaterial();
        }
    }

}
