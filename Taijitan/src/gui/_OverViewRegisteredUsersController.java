package gui;

import domain.Domaincontroller;
import domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class _OverViewRegisteredUsersController {

    private final Domaincontroller dc;
    @FXML
    private TableView<User> tblRegistredUsers;

    @FXML
    private TableColumn<User,String> colFamilyName;

    @FXML
    private TableColumn<User,String> colFirstName;

    @FXML
    private TableColumn<User,String> colDateOfBirth;

    @FXML
    private TableColumn<User,String> colTelephone;

    @FXML
    private TableColumn<User,String> colEmail;
    
    public _OverViewRegisteredUsersController(Domaincontroller dc){
        this.dc = dc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "AddressBookFrame.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

//        colFamilyName.setCellValueFactory(cellData ->
//                cellData.getValue().familyNameProperty());
//
//        colFirstName.setCellValueFactory(cellData ->
//                cellData.getValue().firstNameProperty());
//
//        colDateOfBirth.setCellValueFactory(cellData ->
//                cellData.getValue().dateOfBirthProperty());
//
//        colTelephone.setCellValueFactory(cellData ->
//                cellData.getValue().telephoneProperty());
//
//        colEmail.setCellValueFactory(cellData ->
//                cellData.getValue().emailProperty());

        tblRegistredUsers.setItems(dc.GetAllUsersFX());

    }

}


