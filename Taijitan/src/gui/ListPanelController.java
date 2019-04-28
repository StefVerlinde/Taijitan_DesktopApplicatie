package gui;

import com.jfoenix.controls.JFXListView;
import domain.Domaincontroller;
import domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class ListPanelController extends VBox{
    private Domaincontroller dc;
    @FXML
    private JFXListView<User> lstItems;

    public ListPanelController(Domaincontroller dc) {
        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void fillWithMembers(){
        lstItems.setItems(dc.getAllMembersFX());

        lstItems.getSelectionModel().selectedItemProperty().addListener((ObservableValue,oldValue,newValue) ->
        {
            if(newValue != null)
            {
                this.dc.setCurrentUser(newValue);
            }
        });
    }
}
