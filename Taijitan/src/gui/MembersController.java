
package gui;

//region Imports

import java.io.IOException;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<User> users;
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
    @FXML
    private JFXComboBox cmbFormula;

    //endregion

    public MembersController(Domaincontroller dc) {
        isAdd = false;
        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Members.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        users = dc.getAllUsers().stream().filter(u -> u.getDiscriminator().equals("Member")).collect(Collectors.toList());

        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }


        buildGui();
    }

    private void buildGui() {
        lstMembers.setItems(FXCollections.observableArrayList(users));
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
                cmbFormula.getItems().setAll(dc.getAllFormulas().stream().map(f -> f.getName()).toArray());
                cmbFormula.getSelectionModel().select(user.getFormulaId().getName());

                dpBirthDate.setValue(convertToLocalDate(user.getDateOfBirth()));
                lblDateRegistered.setText(formatDate(user.getDateRegistred()));
            }
        });
    }

    private void updateUsers() {
        users = dc.getAllUsers().stream().filter(u -> u.getDiscriminator().equals("Member")).collect(Collectors.toList());
        lstMembers.setItems(FXCollections.observableArrayList(users));
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
        cmbFormula.getItems().setAll(dc.getAllFormulas().stream().map(f -> f.getName()).toArray());
        cmbFormula.getSelectionModel().clearSelection();

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
        cmbFormula.setDisable(false);
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
        cmbFormula.setDisable(true);
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
    }

    @FXML
    private void delete() {
        if (!isAdd) {
            if (user != null) {
                if(AlertBoxController.ConfirmationAlert("Delete", "Wil je user " + user.getName() + " " + user.getFirstName() + " verwijderen?")){
                    dc.deleteUser(user);
                    updateUsers();
                    emptyFields();
                    disableFields();
                    this.btnEdit.setDisable(true);
                    this.btnDelete.setDisable(true);
                    this.btnAdd.setDisable(false);
                }
            }
        } else {
            toEditUser();
            emptyFields();
            disableFields();
            this.btnAdd.setDisable(false);
        }

    }

    @FXML
    private void edit() {
        boolean canSubmit = true;
        if (!isAdd) {
            if (user != null) {
                //region Edit data from existing user
                //Personal data
                try {
                    lblPersonal.setText("");
                    user.setFirstName(txtFirstName.getText());
                    user.setName(txtLastName.getText());
                    user.setBirthPlace(txtBirthPlace.getText());
                    user.setPersonalNationalNumber(txtPersonalNationalNumber.getText());
                    user.setDateOfBirth(convertToDate(dpBirthDate.getValue()));
                    if (cmbNationality.getSelectionModel().isEmpty()) {
                        throw new IllegalArgumentException("Nationaliteit mag niet leeg zijn");
                    } else {
                        user.setNationality(cmbNationality.getSelectionModel().getSelectedIndex());
                    }
                    if (cmbGender.getSelectionModel().isEmpty()) {
                        throw new IllegalArgumentException("Geslacht mag niet leeg zijn");
                    } else {
                        user.setGender(cmbGender.getSelectionModel().getSelectedIndex());
                    }
                    if (cmbFormula.getSelectionModel().isEmpty()) {
                        throw new IllegalArgumentException("Formule mag niet leeg zijn");
                    } else {
                        user.setFormulaId(dc.getAllFormulas().stream().filter(f -> f.getName().equals(cmbFormula.getSelectionModel().getSelectedItem())).findFirst().get());
                    }
                } catch (IllegalArgumentException e) {
                    canSubmit = false;
                    lblPersonal.setText(e.getMessage());
                }


                //Address
                try {
                    lblAddress.setText("");
                    user.setStreet(txtStreet.getText());
                    user.setHouseNumber(txtHouseNumber.getText());
                    if (cmbCountry.getSelectionModel().isEmpty()) {
                        throw new IllegalArgumentException("Land mag niet leeg zijn");
                    } else {
                        user.setCountry(cmbCountry.getSelectionModel().getSelectedIndex());
                    }
                    if (!user.getCityPostalcode().getPostalcode().equals(txtPostalCode.getText())) {
                        if (dc.getAllCities().stream().filter(c -> c.getPostalcode().equals(txtPostalCode.getText())).count() == 0) {
                            City newCity = new City(txtPostalCode.getText(), txtCityName.getText());
                            user.setCityPostalcode(newCity);
                            dc.addCity(newCity);
                        } else {
                            user.setCityPostalcode(dc.getCityByPostal(txtPostalCode.getText()));
                        }
                    }

                } catch (IllegalArgumentException e) {
                    canSubmit = false;
                    lblAddress.setText(e.getMessage());
                }


                //contact
                try {
                    lblContact.setText("");
                    user.setEmail(txtEmail.getText());
                    user.setLandlineNumber(txtLandLineNumber.getText());
                    user.setMailParent(txtMailParent.getText());
                    user.setPhoneNumber(txtPhoneNumber.getText());
                } catch (IllegalArgumentException e) {
                    canSubmit = false;
                    lblContact.setText(e.getMessage());
                }
                //endregion

                //submit
                if (canSubmit) {
                    dc.updateUser(user);
                    updateUsers();
                }
            }
        } else {

            //region Add user
            //Personal data
            try {
                lblPersonal.setText("");
                user.setFirstName(txtFirstName.getText());
                user.setName(txtLastName.getText());
                user.setBirthPlace(txtBirthPlace.getText());
                user.setPersonalNationalNumber(txtPersonalNationalNumber.getText());
                user.setDateOfBirth(convertToDate(dpBirthDate.getValue()));
                if (cmbNationality.getSelectionModel().isEmpty()) {
                    throw new IllegalArgumentException("Nationaliteit mag niet leeg zijn");
                } else {
                    user.setNationality(cmbNationality.getSelectionModel().getSelectedIndex());
                }
                if (cmbGender.getSelectionModel().isEmpty()) {
                    throw new IllegalArgumentException("Geslacht mag niet leeg zijn");
                } else {
                    user.setGender(cmbGender.getSelectionModel().getSelectedIndex());
                }
                if (cmbFormula.getSelectionModel().isEmpty()) {
                    throw new IllegalArgumentException("Formule mag niet leeg zijn");
                } else {
                    user.setFormulaId(dc.getAllFormulas().stream().filter(f -> f.getName().equals(cmbFormula.getSelectionModel().getSelectedItem())).findFirst().get());
                }
            } catch (IllegalArgumentException e) {
                canSubmit = false;
                lblPersonal.setText(e.getMessage());
            }

            //Address
            try {
                lblAddress.setText("");
                user.setStreet(txtStreet.getText());
                user.setHouseNumber(txtHouseNumber.getText());
                if (cmbCountry.getSelectionModel().isEmpty()) {
                    throw new IllegalArgumentException("Land mag niet leeg zijn");
                } else {
                    user.setCountry(cmbCountry.getSelectionModel().getSelectedIndex());
                }
                if (dc.getAllCities().stream().filter(c -> c.getPostalcode().equals(txtPostalCode.getText())).count() == 0) {
                    City newCity = new City(txtPostalCode.getText(), txtCityName.getText());
                    user.setCityPostalcode(newCity);
                    dc.addCity(newCity);
                } else {
                    user.setCityPostalcode(dc.getCityByPostal(txtPostalCode.getText()));
                }
            } catch (IllegalArgumentException e) {
                canSubmit = false;
                lblAddress.setText(e.getMessage());
            }

            //contact
            try {
                lblContact.setText("");
                user.setEmail(txtEmail.getText());
                user.setLandlineNumber(txtLandLineNumber.getText());
                user.setMailParent(txtMailParent.getText());
                user.setPhoneNumber(txtPhoneNumber.getText());
            } catch (IllegalArgumentException e) {
                canSubmit = false;
                lblContact.setText(e.getMessage());
            }

            //extra required properties
            user.setDateRegistred(new Date());
            user.setDiscriminator("Member");
            user.setRank(1);
            //endregion

            //submit
            if (canSubmit) {
                dc.addUser(user);
                updateUsers();
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
