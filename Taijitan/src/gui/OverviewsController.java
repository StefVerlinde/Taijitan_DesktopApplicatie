package gui;

import com.jfoenix.controls.JFXButton;
import domain.Domaincontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    public OverviewsController(Domaincontroller dc){
        this.dc = dc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("overviews.fxml"));
        loader.setRoot(this);
        loader.setController(this);



        try{
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
        PresentsController pc = new PresentsController(this.dc);


        this.getChildren().add(pc);

    }

    @FXML
    void showRegistrations(ActionEvent event) {

    }

    private void buildGui(){

    }
}
