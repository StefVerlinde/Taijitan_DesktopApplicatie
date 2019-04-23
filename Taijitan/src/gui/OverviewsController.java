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
    private FrameController fc;
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

    private _OverviewPresentsController pc;
    private _OverViewRegisteredUsersController oru;


    public OverviewsController(Domaincontroller dc, FrameController fc, _OverviewPresentsController pc, _OverViewRegisteredUsersController oru) {
        this.dc = dc;
        this.fc = fc;
        this.pc = pc;
        this.oru = oru;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("overviews.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearNodes(){
        this.getChildren().remove(pc);
        this.getChildren().remove(oru);

    }

    @FXML
    void showActivities(ActionEvent event) {
        clearNodes();
    }

    @FXML
    void showChampionship(ActionEvent event) {
        clearNodes();


    }

    @FXML
    void showCourseMaterial(ActionEvent event) {
        clearNodes();


    }

    @FXML
    void showPresents(ActionEvent event) {
        clearNodes();

        this.getChildren().add(pc);

    }

    @FXML
    void showRegistrations(ActionEvent event) {
        clearNodes();

        this.getChildren().add(oru);
    }

    private void buildGui() {
        this.getChildren().addAll(btnActivities, btnshowRegistrations, btnPresents, btnCourseMaterial, btnActivities);

    }
}
