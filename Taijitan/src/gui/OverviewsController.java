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


    @FXML
    private JFXButton btnActivities;
    @FXML
    private JFXButton btnshowRegistrations;
    @FXML
    private JFXButton btnPresents;
    @FXML
    private JFXButton btnChampionship;
    @FXML
    private TableView<User> table = new TableView();


    private Domaincontroller dc;
    private FrameController fc;
    private _OverviewPresentsController pc;
    private _OverViewRegisteredUsersController oru;
    private  _OverviewActivitiesController oa;
    private _OverviewScorebordController osb;


    public OverviewsController(Domaincontroller dc, FrameController fc, _OverviewPresentsController pc, _OverViewRegisteredUsersController oru, _OverviewActivitiesController oa, _OverviewScorebordController osb) {
        this.dc = dc;
        this.fc = fc;
        this.pc = pc;
        this.oru = oru;
        this.oa = oa;
        this.osb = osb;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Overviews.fxml"));
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
        oa = new _OverviewActivitiesController(dc, fc);
        this.getChildren().add(oa);
    }

    public void showActivities() {
        clearNodes();
        this.getChildren().add(oa);
    }

    @FXML
    void showChampionship(ActionEvent event) {
        clearNodes();
        osb = new _OverviewScorebordController(dc, fc);
        this.getChildren().add(osb);
    }

    public void showChampionship() {
        clearNodes();
        this.getChildren().add(osb);
        osb.refresh();
    }

    @FXML
    void showCourseMaterial(ActionEvent event) {
        clearNodes();
    }

    @FXML
    void showPresents(ActionEvent event) {
        clearNodes();
        pc = new _OverviewPresentsController(dc, fc);
        this.getChildren().add(pc);
    }


    public void showPresents() {
        clearNodes();
        this.getChildren().add(pc);
    }

    @FXML
    void showRegistrations(ActionEvent event) {
        clearNodes();
        oru = new _OverViewRegisteredUsersController(dc, fc);
        this.getChildren().add(oru);
    }

    public void showRegistrations() {
        clearNodes();
        this.getChildren().add(oru);
    }

    private void buildGui() {
        clearNodes();
        this.getChildren().addAll(btnActivities, btnshowRegistrations, btnPresents,  btnActivities);
    }

    public void refreshActivities() {
        this.oa.refresh();
    }

    public void refreshPresents() {
        this.pc.refresh();
    }

    public void refreshRegistered() {
        this.oru.refresh();
    }

    public void refreshChampionship() {
        this.osb.refresh();
    }
}
