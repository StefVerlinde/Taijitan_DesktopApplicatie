package gui;

import com.jfoenix.controls.JFXListView;
import domain.Domaincontroller;
import domain.Session;
import domain.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;


public class _OverviewPresentsController extends AnchorPane {

    @FXML
    private JFXListView lstSessions;

    @FXML
    private Label lblTeacher;

    @FXML
    private Label lblDate;

    @FXML
    private JFXListView lstPressents;


    private Domaincontroller dc;
    private FrameController fc;


    private void buildGui() {
        lstSessions.setItems(FXCollections.observableArrayList(dc.getAllSessions()));

        lstSessions.getSelectionModel().selectedItemProperty().addListener((ObservableValue, oldValue, newValue) ->
        {
            if (newValue != null) {
                Session sellectedSession = (Session) newValue;


                System.out.println("tekst voor in lbl: " + sellectedSession.toString());
                System.out.println("tekst voor in lbl2: " + sellectedSession.getTeacherUserId().getFirstName());


                lblDate.setText(String.format("Datum: %s", sellectedSession.toString()));
                lblTeacher.setText(String.format("Trainer: %s %s", sellectedSession.getTeacherUserId().getFirstName(), sellectedSession.getTeacherUserId().getName()));
                lblTeacher = new Label(sellectedSession.getTeacherUserId().getFirstName());

                lstPressents.setItems(FXCollections.observableArrayList(dc.getUsersFromSession(sellectedSession)));
            }
        });


    }

    public _OverviewPresentsController(Domaincontroller dc, FrameController fc) {
        this.dc = dc;
        this.fc = fc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("_OverviewPresents.fxml"));
        loader.setRoot(this);
        loader.setController(this);


        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildGui();


    }
    @FXML
    private void selectMemberFromList(MouseEvent event)
    {
        if(event.getClickCount() == 2){
            User selectedUser = (User) lstPressents.getSelectionModel().getSelectedItem();
            List<User> users = dc.getAllUsers();
            if(users.contains(selectedUser)){
                System.out.println(selectedUser);
                fc.changeToMembersWithSelectedUser(selectedUser, "presentOverview");
            }
            else {
                AlertBoxController.BasicAlert("Error", selectedUser.getFirstName() + " " + selectedUser.getName() + " is geen bestaand lid meer.");
            }
        }
    }
}
