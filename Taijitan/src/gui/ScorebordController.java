package gui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import domain.Domaincontroller;
import domain.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.function.Predicate;

public class ScorebordController extends AnchorPane {
    @FXML
    private JFXTreeTableView<User> tblScorebord;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton btnPrintPdf;

    @FXML
    private JFXButton btnPrintExcell;

    @FXML
    void printOverviewExcell(ActionEvent event) {
        printRegistredUsersExcell();
    }

    @FXML
    void printOverviewPdf(ActionEvent event) {
        printRegistredUsersPdf();
    }

    JFXTreeTableColumn<User, String> clmUserFirstName = new JFXTreeTableColumn<>("Voornaam");
    JFXTreeTableColumn<User, String> clmUserFamilyName = new JFXTreeTableColumn<>("Familienaam");
    JFXTreeTableColumn<User, String> clmScore = new JFXTreeTableColumn<>("Score");

    private Domaincontroller dc;
    private FrameController fc;

    public ScorebordController(Domaincontroller dc,FrameController fc) {
        this.dc = dc;
        this.fc = fc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scorebord.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
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

        tblScorebord = new JFXTreeTableView<>();
        tblScorebord.getColumns().setAll(clmUserFamilyName, clmUserFirstName,clmScore);
        tblScorebord.setRoot(root);
        tblScorebord.setShowRoot(false);

        searchFields();
    }

    private void searchFields() {
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tblScorebord.setPredicate(new Predicate<TreeItem<User>>() {
                    @Override
                    public boolean test(TreeItem<User> user) {
                        boolean flag = user.getValue().familyNameProperty().getValue().toLowerCase().contains(newValue.toLowerCase())
                                ||
                                user.getValue().firstNameProperty().getValue().toLowerCase().contains(newValue.toLowerCase())
                                ||
                                user.getValue().scoreProperty().getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
    }

    private void buildColumns() {
        clmUserFirstName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().firstNameProperty();
            }
        });

        clmUserFamilyName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().familyNameProperty();
            }
        });

        clmScore.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call (TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().scoreProperty();
            }
        });
    }

    private  void printRegistredUsersPdf(){

    }

    private void printRegistredUsersExcell() {

    }
}
