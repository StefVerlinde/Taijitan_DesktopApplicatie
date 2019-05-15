package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import domain.CourseMaterial;
import domain.Domaincontroller;
import domain.Image;
import domain.Rank;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    @FXML
    private JFXButton btnImages;
    @FXML
    private JFXTextField txfImages;

    private Domaincontroller dc;
    private FrameController fc;
    private boolean isAdd;
    private Collection<Image> images;
    private Collection<String> imageNames;

    public CourseMaterialController(Domaincontroller dc, FrameController fc){
        this.dc = dc;
        this.fc = fc;
        isAdd = false;
        this.images = new ArrayList<>();
        this.imageNames = new ArrayList<>();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CourseMaterial.fxml"));
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

    private void toEditCourseMaterial(){
        btnEdit.setText("Pas lesmateriaal aan");
        btnDelete.setText("Verwijder lesmateriaal");
        fc.setVisibleAdd(true);
        btnDelete.setDisable(true);
    }

    private void buildGui() {
        cboRank.getItems().setAll(Rank.values());
        txfImages.setEditable(false);
        txfImages.setText("Kies afbeeldingen");
    }

    @FXML
    private void edit(){
        if(!isAdd){
            //todo add coursematerial
            //todo validatie
        }
        else
        {
            //add coursematerial
            //todo validatie
            //todo lijst update nog niet goed na updaten
            CourseMaterial newC = new CourseMaterial();
            newC.setTitle(txtTitle.getText());
            newC.setFullDescription(txaDiscription.getText());
            newC.setYoutubeURL(txtYouTubeURL.getText());
            newC.setRank(cboRank.getSelectionModel().getSelectedIndex());

            for(String name : this.imageNames)
            {
                Image newI = new Image();
                newI.setName(String.format("%s/%s", this.txtTitle.getText(), name));
                this.images.add(newI);
            }
            newC.setImageCollection(this.images);


            dc.addCourseMaterial(newC);
            toEditCourseMaterial();
            emptyFields();
            fc.updateListPanelCourseMaterial();
        }
    }
    @FXML
    private void delete(){
        if(!isAdd){
            if (dc.getCurrentCourseMaterial() != null) {
                if(AlertBoxController.ConfirmationAlert("Delete", "Wil je lesmateriaal " + dc.getCurrentCourseMaterial().getTitle() + " verwijderen?")){
                    dc.deleteCourseMaterial();
                    emptyFields();
                    disableFields();
                    this.btnEdit.setDisable(true);
                    this.btnDelete.setDisable(true);
                    fc.setDisableAdd(false);
                    fc.updateListPanelCourseMaterial();
                }
            }
        }
        else{
            toEditCourseMaterial();
            emptyFields();
            disableFields();
            fc.setDisableAdd(false);
        }
    }

    @FXML
    private void selectImages(){
        FileChooser fileChooser = new FileChooser();
        List<File> files = new ArrayList<File>();
        files = fileChooser.showOpenMultipleDialog(fc.getStage());

        if(files.isEmpty())
        {
            txfImages.setText("Kies afbeeldingen");
        } else {
            String uistring = "";
            for (File file : files) {
                uistring += file.getName() + ", ";
                this.imageNames.add(file.getName());
            }
            this.txfImages.setText(uistring);
        }
        System.out.println(this.imageNames.toString());
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
            toEditCourseMaterial();
            enableFields();
        }
    }
    public void enableFields(){
        txtTitle.setDisable(false);
        txtYouTubeURL.setDisable(false);
        txaDiscription.setDisable(false);
        cboRank.setDisable(false);
        btnDelete.setDisable(false);
        btnEdit.setDisable(false);
        txfImages.setDisable(false);
        btnImages.setDisable(false);
    }
    public void disableFields(){
        txtTitle.setDisable(true);
        txtYouTubeURL.setDisable(true);
        txaDiscription.setDisable(true);
        cboRank.setDisable(true);
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        txfImages.setDisable(true);
        btnImages.setDisable(true);
    }
    public void emptyFields(){
        txtTitle.setText("");
        txtYouTubeURL.setText("");
        txaDiscription.setText("");
        cboRank.getSelectionModel().clearSelection();
        txfImages.setText("");
    }
    public void setIsAdd(boolean b)
    {
        this.isAdd = b;
    }
    public void setBtnEditText(String s)
    {
        this.btnEdit.setText(s);
    }
    public void setBtnDeleteText(String s)
    {
        this.btnDelete.setText(s);
    }
    public void setDisableDelete(boolean b)
    {
        this.btnDelete.setDisable(b);
    }
    public boolean getIsAdd() {
        return isAdd;
    }
}
