
package gui;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import domain.Country;
import domain.Domaincontroller;
import domain.Gender;
import domain.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;


public class MembersController extends BorderPane
{
    private Domaincontroller dc;
    @FXML
    private JFXListView lstMembers;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXTextField txtDateRegistered;
    @FXML
    private JFXTextField txtNationality;
    @FXML
    private JFXTextField txtDateOfBirth;
    @FXML
    private JFXTextField txtPersonalNationalNumber;
    @FXML
    private JFXTextField txtBirthPlace;
    @FXML
    private JFXTextField txtGender;
    @FXML
    private JFXTextField txtStreet;
    @FXML
    private JFXTextField txtPostalCode;
    @FXML
    private JFXTextField txtCountry;
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
                User u = (User) newValue;
                txtFirstName.setText(u.getFirstName());
                txtLastName.setText(u.getName());
                txtDateOfBirth.setText(formatDate(u.getDateOfBirth()));
                txtDateRegistered.setText(formatDate(u.getDateRegistred()));
                txtNationality.setText(Country.getById(u.getNationality()).name());
                txtBirthPlace.setText(u.getBirthPlace());
                txtGender.setText(Gender.getById(u.getGender()).name());
                txtPersonalNationalNumber.setText(u.getPersonalNationalNumber());
                txtStreet.setText(u.getStreet());
                txtPostalCode.setText(u.getCityPostalcode().getPostalcode());
                txtCountry.setText(Country.getById(u.getCountry()).name());
                txtHouseNumber.setText(u.getHouseNumber());
                txtCityName.setText(u.getCityPostalcode().getName());
                txtEmail.setText(u.getEmail());
                txtLandLineNumber.setText(u.getLandlineNumber());
                txtPhoneNumber.setText(u.getPhoneNumber());
                txtMailParent.setText(u.getMailParent());

            }
        });
    }
    public static String formatDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String output = formatter.format(date);
        return output;
    }
}
