package gui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import domain.Domaincontroller;
import domain.Session;
import domain.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.function.Predicate;

public class _OverViewRegisteredUsersController extends AnchorPane {

    @FXML
    private JFXTreeTableView<User> tblRegistredUsers;

    @FXML
    private JFXTextField txtSearch;

    JFXTreeTableColumn<User, String>  clmUserFirstName = new JFXTreeTableColumn<>("Voornaam");
    JFXTreeTableColumn<User, String>  clmUserFamilyName = new JFXTreeTableColumn<>("Familienaam");
    JFXTreeTableColumn<User, String>  clmUserDateOfBirth = new JFXTreeTableColumn<>("Geboortedatum");
    JFXTreeTableColumn<User, String>  clmUserTelephone = new JFXTreeTableColumn<>("Telefoonnummer");
    JFXTreeTableColumn<User, String>  clmUserEmail = new JFXTreeTableColumn<>("Email adres");

    private Domaincontroller dc;

    public _OverViewRegisteredUsersController(Domaincontroller dc){
        this.dc = dc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("_OverviewRegisteredUsers.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try{
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildGui();
    }

    private void buildGui(){
        buildColumns();

        ObservableList<User> users = FXCollections.observableArrayList(dc.getAllUsers());

        //opvullen tabel
        final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);
        System.out.println(users.size());

//        tblRegistredUsers = new JFXTreeTableView<>();
        tblRegistredUsers.getColumns().setAll(clmUserFamilyName, clmUserFirstName, clmUserDateOfBirth, clmUserEmail, clmUserTelephone);
        tblRegistredUsers.setRoot(root);
        tblRegistredUsers.setShowRoot(false);

        searchFields();
    }

    private void searchFields(){
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tblRegistredUsers.setPredicate(new Predicate<TreeItem<User>>() {
                    @Override
                    public boolean test(TreeItem<User> user) {
                       boolean flag = user.getValue().familyNameProperty().getValue().toLowerCase().contains(newValue.toLowerCase())
                               ||
                               user.getValue().firstNameProperty().getValue().toLowerCase().contains(newValue.toLowerCase())
                               ||
                               user.getValue().telephoneProperty().getValue().toLowerCase().contains(newValue.toLowerCase());
                       return flag;
                    }
                });
            }
        });
    }


    private void buildColumns(){
        clmUserFirstName.setPrefWidth(200);
        clmUserFirstName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().firstNameProperty();
            }
        });

        clmUserFamilyName.setPrefWidth(200);
        clmUserFamilyName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().familyNameProperty();
            }
        });

        clmUserDateOfBirth.setPrefWidth(200);
        clmUserDateOfBirth.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().dateOfBirthProperty();
            }
        });

        clmUserTelephone.setPrefWidth(240);
        clmUserTelephone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().telephoneProperty();
            }
        });

        clmUserEmail.setPrefWidth(300);
        clmUserEmail.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().emailProperty();
            }
        });
    }

}


