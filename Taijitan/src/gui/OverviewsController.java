package gui;

import com.jfoenix.controls.JFXButton;
import domain.Domaincontroller;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private TableView table = new TableView();

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
        _OverviewPresentsController pc = new _OverviewPresentsController(this.dc);


        this.getChildren().add(pc);

    }

    @FXML
    void showRegistrations(ActionEvent event) {

        //aanmaken kolommen
        TableColumn<User, String> familyName = new TableColumn<User, String>("name");
        TableColumn<User, String> firstName = new TableColumn<User, String>("firstName");
        TableColumn<User, String> dateOfBirth = new TableColumn<User, String>("dateOfBirth");
        //adres
        TableColumn<User, String> phoneNumber = new TableColumn<User, String>("phoneNumber");
        TableColumn<User, String> email = new TableColumn<User, String>("email");


        //opvul metadata
        familyName.setCellFactory(new PropertyValueFactory("name"));

        firstName.setCellFactory(new PropertyValueFactory("firstName"));
        dateOfBirth.setCellFactory(new PropertyValueFactory("dateOfBirth"));
        phoneNumber.setCellFactory(new PropertyValueFactory("phoneNumber"));
        email.setCellFactory(new PropertyValueFactory("email"));

        //opvullen
        ObservableList userList = FXCollections.observableArrayList(dc.getAllUsers());
        table.setItems(userList);

        //toevoegen kolommen
        table.getColumns().addAll(familyName, firstName, dateOfBirth, phoneNumber, email);


        System.out.println(userList.toString());

        //toevoegen aan scherm
        this.getChildren().add(table);





    }

    private void buildGui(){

    }
}
