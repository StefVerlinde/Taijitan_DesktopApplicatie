package gui;

import com.jfoenix.controls.JFXButton;
import domain.Domaincontroller;
import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class OverviewsController extends VBox {

    private Domaincontroller dc;
    @FXML
    private JFXButton btnActivities;

    @FXML
    private JFXButton btnshowRegistrations;

    @FXML
    private JFXButton btnPresents;

    @FXML
    private JFXButton btnCourseMaterial;

    @FXML
    private JFXButton btnChampionship;

    @FXML
    private TableView<User> table = new TableView();

    public OverviewsController(Domaincontroller dc) {
        this.dc = dc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("overviews.fxml"));
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
    void showActivities(ActionEvent event) {

    }

    @FXML
    void showChampionship(ActionEvent event) {

    }

    @FXML
    void showCourseMaterial(ActionEvent event) {

    }

    @FXML
    void showPresents(ActionEvent event) {
        _OverviewPresentsController pc = new _OverviewPresentsController(this.dc);

        this.getChildren().add(pc);

    }

    @FXML
    void showRegistrations(ActionEvent event) {
        _OverViewRegisteredUsersController oru = new _OverViewRegisteredUsersController(this.dc);

        System.out.println("ik ben hier");
        this.getChildren().add(oru);


    }

    private void buildGui() {

    }
}
