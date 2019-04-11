
package gui;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.jfoenix.controls.*;
import domain.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class MembersController extends BorderPane
{
    private Domaincontroller dc;
    private User user;
    @FXML
    private JFXListView lstMembers;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private Label lblDateRegistered;
    @FXML
    private JFXComboBox cmbNationality;
    @FXML
    private JFXDatePicker dpBirthDate;
    @FXML
    private JFXTextField txtPersonalNationalNumber;
    @FXML
    private JFXTextField txtBirthPlace;
    @FXML
    private JFXComboBox cmbGender;
    @FXML
    private JFXTextField txtStreet;
    @FXML
    private JFXTextField txtPostalCode;
    @FXML
    private JFXComboBox cmbCountry;
    @FXML
    private JFXTextField txtHouseNumber;
    @FXML
    private JFXTextField txtCityName;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtLandLineNumber;
    @FXML
    private JFXTextField txtPhoneNumber;
    @FXML
    private JFXTextField txtMailParent;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnAdd;


    public MembersController(Domaincontroller dc)
    {
        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Members.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try
        {
            loader.load();
        } catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }


        buildGui();
    }

    private void buildGui(){
        lstMembers.setItems(FXCollections.observableArrayList(dc.getAllUsers()));


        lstMembers.getSelectionModel().selectedItemProperty().addListener((ObservableValue,oldValue,newValue) ->
        {
            if(newValue != null)
            {
                setUser((User) newValue);

                txtFirstName.setDisable(false);
                txtLastName.setDisable(false);
                txtBirthPlace.setDisable(false);
                txtPersonalNationalNumber.setDisable(false);
                txtStreet.setDisable(false);
                txtPostalCode.setDisable(false);
                txtHouseNumber.setDisable(false);
                txtCityName.setDisable(false);
                txtEmail.setDisable(false);
                txtLandLineNumber.setDisable(false);
                txtPhoneNumber.setDisable(false);
                txtMailParent.setDisable(false);
                cmbGender.setDisable(false);
                cmbNationality.setDisable(false);
                cmbCountry.setDisable(false);
                dpBirthDate.setDisable(false);
                btnEdit.setDisable(false);
                btnAdd.setDisable(false);
                btnDelete.setDisable(false);

                txtFirstName.setText(user.getFirstName());
                txtLastName.setText(user.getName());
                txtBirthPlace.setText(user.getBirthPlace());
                txtPersonalNationalNumber.setText(user.getPersonalNationalNumber());
                txtStreet.setText(user.getStreet());
                txtPostalCode.setText(user.getCityPostalcode().getPostalcode());
                txtHouseNumber.setText(user.getHouseNumber());
                txtCityName.setText(user.getCityPostalcode().getName());
                txtEmail.setText(user.getEmail());
                txtLandLineNumber.setText(user.getLandlineNumber());
                txtPhoneNumber.setText(user.getPhoneNumber());
                txtMailParent.setText(user.getMailParent());

                cmbGender.getItems().setAll(Gender.values());
                cmbGender.getSelectionModel().select(user.getGender());
                cmbNationality.getItems().setAll(Country.values());
                cmbNationality.getSelectionModel().select(user.getNationality());
                cmbCountry.getItems().setAll(Country.values());
                cmbCountry.getSelectionModel().select(user.getCountry());

                dpBirthDate.setValue(convertToLocalDate(user.getDateOfBirth()));
                lblDateRegistered.setText(formatDate(user.getDateRegistred()));
            }
        });
    }
    private void setUser(User user) {
        if(user != null)
            this.user = user;
    }

    @FXML
    private void delete(){
        if(user != null){
            AlertBoxController.ConfirmationAlert("Delete", "Wil je user " + user.getName() + " " + user.getFirstName() + " verwijderen?");
            dc.deleteUser(user);
        }
    }
    @FXML
    private void edit()
    {
        if(user != null) {
            user.setFirstName(txtFirstName.getText());
            user.setName(txtLastName.getText());
            user.setBirthPlace(txtBirthPlace.getText());
            user.setPersonalNationalNumber(txtPersonalNationalNumber.getText());
            user.setStreet(txtStreet.getText());
            if(!user.getCityPostalcode().getPostalcode().equals(txtPostalCode.getText()))
            {
                City newCity = new City(txtPostalCode.getText());
                newCity.setName(txtCityName.getText());
                user.setCityPostalcode(newCity);
            }
            user.setHouseNumber(txtHouseNumber.getText());
            user.setEmail(txtEmail.getText());
            user.setLandlineNumber(txtLandLineNumber.getText());
            user.setMailParent(txtMailParent.getText());
            user.setPhoneNumber(txtPhoneNumber.getText());
            user.setGender(cmbGender.getSelectionModel().getSelectedIndex());
            user.setCountry(cmbCountry.getSelectionModel().getSelectedIndex());
            user.setNationality(cmbNationality.getSelectionModel().getSelectedIndex());
            user.setDateOfBirth(convertToDate(dpBirthDate.getValue()));

            dc.updateUser(user);
        }
    }
    private String formatDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String output = formatter.format(date);
        return output;
    }
    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    private Date convertToDate(LocalDate localDate)
    {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
