package gui;

import com.jfoenix.controls.*;
import domain.*;
import dto.ScoreDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;


public class ScoresController extends AnchorPane implements PropertyChangeListener {
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtScore;
    @FXML
    private JFXComboBox cboType;
    @FXML
    private Label lblError;
    @FXML
    private JFXButton btnConfirm;
    @FXML
    private Label lblNaamLid;

    private Domaincontroller dc;
    private FrameController fc;
    private User selectedUser;

    private final ObservableList<String> types = FXCollections.observableArrayList(ScoreType.Aanwezigheid.toString(), ScoreType.Examen.toString(),
            ScoreType.Sportproef.toString(), ScoreType.Andere.toString());


    public ScoresController(Domaincontroller dc, FrameController fc) {
        this.dc = dc;
        this.fc = fc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scores.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildGui();
        fc.setDisableAdd(false);
        emptyFields();
        disableFields();
    }

    private void buildGui(){
        txtName.setEditable(true);
        cboType.setItems(types);
    }

    public void emptyFields(){
        txtName.setText("");
        cboType.getSelectionModel().clearSelection();
        txtScore.setText("");
        lblNaamLid.setText("");
        lblError.setText("");
        selectedUser = null;
    }
    public void disableFields(){
        txtName.setDisable(true);
        cboType.setDisable(true);
        txtScore.setDisable(true);
        btnConfirm.setDisable(true);
    }
    public void enableFields()
    {
        txtName.setDisable(false);
        cboType.setDisable(false);
        txtScore.setDisable(false);
        btnConfirm.setDisable(false);
    }

    @FXML
    void confirm(){
        boolean canSubmit = true;
        ScoreDTO sco = new ScoreDTO();
        try {
            sco.setName(txtName.getText());
            sco.setUser(selectedUser);
            sco.setAmount(Integer.valueOf(txtScore.getText()));

            if (cboType.getSelectionModel().isEmpty()) {
                throw new IllegalArgumentException("Type mag niet leeg zijn");
            } else {
                sco.setType(cboType.getSelectionModel().getSelectedItem().toString());
            }
        }
        catch (NumberFormatException e){
            canSubmit = false;
            lblError.setText("Gelieve een geldige score in te geven");
        }
        catch(IllegalArgumentException e)
        {
            canSubmit = false;
            lblError.setText(e.getMessage());
        }


        try {
            if(canSubmit)
            {
                selectedUser.addScoreTotScores(new Score(sco));
                dc.updateUser();
                //  dc.addScore(sco);
                emptyFields();
                disableFields();
                fc.updateListPanelMembers();
            }
        }
        catch (IllegalArgumentException e){
            canSubmit =false;
            lblError.setText(e.getMessage());
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getNewValue() != null)
        {
            lblNaamLid.setText(evt.getNewValue().toString());
            selectedUser = (User)evt.getNewValue();
            enableFields();
        }
    }
}
