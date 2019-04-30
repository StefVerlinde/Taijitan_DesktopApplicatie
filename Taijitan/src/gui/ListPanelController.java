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
    private FrameController fc;
    @FXML
    private JFXListView<User> lstItems;

    public ListPanelController(Domaincontroller dc, FrameController fc) {
        this.dc = dc;
        this.fc = fc;
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
                if(fc.isAddingMember()){
                    if(AlertBoxController.ConfirmationAlert("Alert", "Ben je zeker dat je dit scherm wilt verlaten? Alle ingegeven gegevens zullen verloren gaan.")){
                        this.dc.setCurrentUser(newValue);
                    }
                }
                else {
                    this.dc.setCurrentUser(newValue);
                }
            }
        });
    }
}
