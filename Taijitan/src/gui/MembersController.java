
package gui;

//region Imports
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.jfoenix.controls.*;
import domain.*;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
//endregion

public class MembersController extends BorderPane {

    //region Properties
    private boolean isAdd;
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
    @FXML
    private Label lblPersonal;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblContact;
    //endregion

    public MembersController(Domaincontroller dc) {
        isAdd = false;
        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Members.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }


        buildGui();
    }

    private void buildGui() {
        lstMembers.setItems(FXCollections.observableArrayList(dc.getAllUsers()));
        btnAdd.setDisable(false);

        lstMembers.getSelectionModel().selectedItemProperty().addListener((ObservableValue, oldValue, newValue) ->
        {
            if (newValue != null) {
                lblAddress.setText("");
                lblPersonal.setText("");
                lblContact.setText("");
                setUser((User) newValue);
                toEditUser();

                enableFields();

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

    private void emptyFields() {
        txtFirstName.clear();
        txtLastName.clear();
        txtBirthPlace.clear();
        txtPersonalNationalNumber.clear();
        txtStreet.clear();
        txtPostalCode.clear();
        txtHouseNumber.clear();
        txtCityName.clear();
        txtEmail.clear();
        txtLandLineNumber.clear();
        txtPhoneNumber.clear();
        txtMailParent.clear();

        cmbGender.getItems().setAll(Gender.values());
        cmbGender.getSelectionModel().clearSelection();
        cmbNationality.getItems().setAll(Country.values());
        cmbNationality.getSelectionModel().clearSelection();
        cmbCountry.getItems().setAll(Country.values());
        cmbCountry.getSelectionModel().clearSelection();

        dpBirthDate.getEditor().clear();
        lblDateRegistered.setText("");
        lblContact.setText("");
        lblPersonal.setText("");
        lblAddress.setText("");
    }

    private void enableFields() {
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
    }

    private void disableFields() {
        txtFirstName.setDisable(true);
        txtLastName.setDisable(true);
        txtBirthPlace.setDisable(true);
        txtPersonalNationalNumber.setDisable(true);
        txtStreet.setDisable(true);
        txtPostalCode.setDisable(true);
        txtHouseNumber.setDisable(true);
        txtCityName.setDisable(true);
        txtEmail.setDisable(true);
        txtLandLineNumber.setDisable(true);
        txtPhoneNumber.setDisable(true);
        txtMailParent.setDisable(true);
        cmbGender.setDisable(true);
        cmbNationality.setDisable(true);
        cmbCountry.setDisable(true);
        dpBirthDate.setDisable(true);
        btnEdit.setDisable(true);
        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void setUser(User user) {
        if (user != null)
            this.user = user;
    }

    private void toEditUser() {
        btnEdit.setText("Pas gegevens aan");
        btnDelete.setText("Verwijder lid");
        btnAdd.setVisible(true);
        isAdd = false;
        btnDelete.setDisable(true);
        btnEdit.disableProperty().unbind();
    }

    @FXML
    private void delete() {
        if (!isAdd) {
            if (user != null) {
                AlertBoxController.ConfirmationAlert("Delete", "Wil je user " + user.getName() + " " + user.getFirstName() + " verwijderen?");
                dc.deleteUser(user);
                lstMembers.setItems(FXCollections.observableArrayList(dc.getAllUsers()));
                emptyFields();
                disableFields();
                this.btnEdit.setDisable(true);
                this.btnDelete.setDisable(true);
                this.btnAdd.setDisable(false);
            }
        }
        else
        {
            toEditUser();
            disableFields();
            this.btnAdd.setDisable(false);
        }

    }

    @FXML
    private void edit() {
        boolean canSubmit = true;
        if (!isAdd)
        {
            if (user != null)
            {
                //region Edit data from existing user
                //Personal data
                try
                {
                    lblPersonal.setText("");
                    user.setFirstName(txtFirstName.getText());
                    user.setName(txtLastName.getText());
                    user.setBirthPlace(txtBirthPlace.getText());
                    user.setPersonalNationalNumber(txtPersonalNationalNumber.getText());
                    user.setDateOfBirth(convertToDate(dpBirthDate.getValue()));
                    user.setNationality(cmbNationality.getSelectionModel().getSelectedIndex());
                    user.setGender(cmbGender.getSelectionModel().getSelectedIndex());
                }
                catch(IllegalArgumentException e)
                {
                    canSubmit = false;
                    lblPersonal.setText(e.getMessage());
                }


                //Address
                try
                {
                    lblAddress.setText("");
                    user.setStreet(txtStreet.getText());
                    user.setHouseNumber(txtHouseNumber.getText());
                    user.setCountry(cmbCountry.getSelectionModel().getSelectedIndex());
                    if (!user.getCityPostalcode().getPostalcode().equals(txtPostalCode.getText())) {
                        if (dc.getAllCities().stream().filter(c -> c.getPostalcode().equals(txtPostalCode.getText())).count() == 0) {
                            City newCity = new City(txtPostalCode.getText(),txtCityName.getText());
                            user.setCityPostalcode(newCity);
                            dc.addCity(newCity);
                        } else {
                            user.setCityPostalcode(dc.getCityByPostal(txtPostalCode.getText()));
                        }
                    }

                }
                catch(IllegalArgumentException e)
                {
                    canSubmit = false;
                    lblAddress.setText(e.getMessage());
                }


                //contact
                try
                {
                    lblContact.setText("");
                    user.setEmail(txtEmail.getText());
                    user.setLandlineNumber(txtLandLineNumber.getText());
                    user.setMailParent(txtMailParent.getText());
                    user.setPhoneNumber(txtPhoneNumber.getText());
                }
                catch(IllegalArgumentException e)
                {
                    canSubmit = false;
                    lblContact.setText(e.getMessage());
                }
                //endregion

                //submit
                if(canSubmit) {
                    dc.updateUser(user);
                    lstMembers.setItems(FXCollections.observableArrayList(dc.getAllUsers()));
                }
            }
        }
        else {

            //region Add user
            //Personal data
            try
            {
                lblPersonal.setText("");
                user.setFirstName(txtFirstName.getText());
                user.setName(txtLastName.getText());
                user.setBirthPlace(txtBirthPlace.getText());
                user.setPersonalNationalNumber(txtPersonalNationalNumber.getText());
                user.setDateOfBirth(convertToDate(dpBirthDate.getValue()));
                user.setNationality(cmbNationality.getSelectionModel().getSelectedIndex());
                user.setGender(cmbGender.getSelectionModel().getSelectedIndex());
            }
            catch(IllegalArgumentException e)
            {
                canSubmit = false;
                lblPersonal.setText(e.getMessage());
            }

            //Address
            try
            {
                lblAddress.setText("");
                user.setStreet(txtStreet.getText());
                user.setHouseNumber(txtHouseNumber.getText());
                user.setCountry(cmbCountry.getSelectionModel().getSelectedIndex());
                if (dc.getAllCities().stream().filter(c -> c.getPostalcode().equals(txtPostalCode.getText())).count() == 0) {
                    City newCity = new City(txtPostalCode.getText(),txtCityName.getText());
                    user.setCityPostalcode(newCity);
                    dc.addCity(newCity);
                } else {
                    user.setCityPostalcode(dc.getCityByPostal(txtPostalCode.getText()));
                }
            }
            catch(IllegalArgumentException e)
            {
                canSubmit = false;
                lblAddress.setText(e.getMessage());
            }

            //contact
            try
            {
                lblContact.setText("");
                user.setEmail(txtEmail.getText());
                user.setLandlineNumber(txtLandLineNumber.getText());
                user.setMailParent(txtMailParent.getText());
                user.setPhoneNumber(txtPhoneNumber.getText());
            }
            catch(IllegalArgumentException e)
            {
                canSubmit = false;
                lblContact.setText(e.getMessage());
            }

            //extra required properties
            user.setDateRegistred(new Date());
            user.setDiscriminator("Member");
            user.setFormulaId(new Formula(2));
            user.setRank(7);
            //endregion

            //submit
            if(canSubmit) {
                dc.addUser(user);
                lstMembers.setItems(FXCollections.observableArrayList(dc.getAllUsers()));
                toEditUser();
                emptyFields();
            }
        }
    }
    @FXML
    private void addUser() {
        setUser(new User());
        lstMembers.getSelectionModel().clearSelection();
        isAdd = true;
        btnAdd.setVisible(false);
        btnEdit.setText("Voeg lid toe");
        btnDelete.setText("Annuleer");
        btnDelete.setDisable(false);
        enableFields();
        btnEdit.disableProperty().bind(
                Bindings.isEmpty(txtFirstName.textProperty())
                        .and(Bindings.isEmpty(txtLastName.textProperty()))
                        .and(Bindings.isEmpty(txtBirthPlace.textProperty()))
                        .and(Bindings.isEmpty(txtPersonalNationalNumber.textProperty()))
                        .and(Bindings.isEmpty(txtStreet.textProperty()))
                        .and(Bindings.isEmpty(txtPostalCode.textProperty()))
                        .and(Bindings.isEmpty(txtHouseNumber.textProperty()))
                        .and(Bindings.isEmpty(txtCityName.textProperty()))
                        .and(Bindings.isEmpty(txtEmail.textProperty()))
                        .and(Bindings.isEmpty(txtLandLineNumber.textProperty()))
                        .and(Bindings.isEmpty(cmbGender.idProperty()))
                        .and(Bindings.isEmpty(cmbCountry.idProperty()))
                        .and(Bindings.isEmpty(cmbNationality.idProperty()))
                        .and(Bindings.isEmpty(dpBirthDate.promptTextProperty()))
        );
        emptyFields();
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

    private Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
