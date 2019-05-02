package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import domain.Domaincontroller;
import domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class ListPanelController extends VBox  {
    private Domaincontroller dc;
    private FrameController fc;
    @FXML
    private JFXListView<User> lstItems;
    @FXML
    private JFXButton btnAdd;


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
        lstItems.setItems(dc.getAllMembersFX());

        lstItems.getSelectionModel().selectedItemProperty().addListener((ObservableValue,oldValue,newValue) ->
        {
            if(newValue != null)
            {
                if(fc.isAddingMember()){
                    if(AlertBoxController.ConfirmationAlert("Alert", "Ben je zeker dat je dit scherm wilt verlaten? Alle ingegeven gegevens zullen verloren gaan.")){
                        this.dc.setCurrentUser(newValue);
                    }
                    else {
                        lstItems.refresh();
                    }
                }
                else {
                    this.dc.setCurrentUser(newValue);
                }
            }
        });
    }
    public void fillWithActivities(){
        btnAdd.setText("Voeg activiteit toe");
        lstItems.setItems(null);
        /*lstItems.setItems(dc.getAllActivitiesFX());

        lstItems.getSelectionModel().selectedItemProperty().addListener((ObservableValue,oldValue,newValue) ->
        {
            if(newValue != null)
            {
                //TODO
            }
        });*/
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
            fc.emptyfieldsActivities();
        }
    }

}
