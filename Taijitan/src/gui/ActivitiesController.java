package gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import domain.Domaincontroller;
import domain.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ActivitiesController extends AnchorPane {
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXDatePicker dtmStart;
    @FXML
    private JFXDatePicker dtmEnd;
    @FXML
    private JFXTextField txtMaxParticipants;
    @FXML
    private JFXComboBox<String> cboType;
    @FXML
    private JFXListView<User> lstMembers;
    @FXML
    private JFXListView<User> lstConfirmed;

    @FXML
    void mouseClicked(MouseEvent event) {
        User u = lstMembers.getSelectionModel().getSelectedItem();

        dc.addConfirmed(u);
        lstMembers.setItems(dc.getLijstMembers());
        lstConfirmed.setItems(dc.getLijstConfirmed());
//        ObservableList<User> lijstConfirmed = lstConfirmed.getItems();
//        lijstConfirmed.add(u);
//        lstConfirmed.setItems(lijstConfirmed );
//
//        ObservableList<User> lijstMembers = lstMembers.getItems();
//        lijstMembers.remove(u);
//        lstMembers.setItems(lijstMembers);
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

        lstMembers.setItems(dc.getLijstMembers());

    }




}
