package gui;

import com.jfoenix.controls.*;
import domain.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class CourseMaterialController extends AnchorPane implements PropertyChangeListener {
    @FXML
    private JFXTextField txtTitle;
    @FXML
    private JFXTextArea txaDiscription;
    @FXML
    private JFXTextField txtYouTubeURL;
    @FXML
    private JFXComboBox<Rank> cboRank;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;

    private Domaincontroller dc;
    private FrameController fc;

    public CourseMaterialController(Domaincontroller dc, FrameController fc){
        this.dc = dc;
        this.fc = fc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseMaterial.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        buildGui();

    }

    private void buildGui() {
        cboRank.getItems().setAll(Rank.values());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getNewValue() != null)
        {
            CourseMaterial courseM = (CourseMaterial)evt.getNewValue();
            txtTitle.setText(courseM.getTitle());
            txtYouTubeURL.setText(courseM.getYoutubeURL());
            txaDiscription.setText(courseM.getFullDescription());
            cboRank.getSelectionModel().select(courseM.getRank());
        }
    }
}
