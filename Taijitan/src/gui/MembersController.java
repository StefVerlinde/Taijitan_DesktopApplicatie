/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import domain.Domaincontroller;
import domain.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
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
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtPhoneNumber;


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
                txtEmail.setText(u.getEmail());
                txtFirstName.setText(u.getFirstName());
                txtLastName.setText(u.getName());
                txtPhoneNumber.setText(u.getPhoneNumber());
            }
        });
    }
}
