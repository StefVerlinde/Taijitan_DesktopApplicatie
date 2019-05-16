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

import static org.bouncycastle.crypto.tls.ContentType.alert;


public class OverviewsController extends VBox {


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


    private Domaincontroller dc;
    private FrameController fc;
    private _OverviewPresentsController pc;
    private _OverViewRegisteredUsersController oru;
    private  _OverviewActivitiesController oa;
    private _OverviewScorebord osb;


    public OverviewsController(Domaincontroller dc, FrameController fc, _OverviewPresentsController pc, _OverViewRegisteredUsersController oru, _OverviewActivitiesController oa, _OverviewScorebord osb) {
        this.dc = dc;
        this.fc = fc;
        this.pc = pc;
        this.oru = oru;
        this.oa = oa;
        this.osb = osb;

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
        this.getChildren().remove(oa);
        this.getChildren().remove((osb));
    }

    @FXML
    void showActivities(ActionEvent event) {
        clearNodes();
        this.getChildren().add(oa);
    }

    @FXML
    void showChampionship(ActionEvent event) {
        clearNodes();
        this.getChildren().add(osb);
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
