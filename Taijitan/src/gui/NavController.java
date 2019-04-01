/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jarne
 */
public class NavController extends VBox
{

    @FXML
    private JFXButton btnMembers;
    @FXML
    private JFXButton btnActivities;
    @FXML
    private JFXButton btnCourseMaterial;
    @FXML
    private JFXButton btnOverviews;
    @FXML
    private JFXButton btnLeaderBoard;
    
    private FrameController controller;
    
    public NavController(FrameController frame)
    {
        controller = frame;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Nav.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try
        {
            loader.load();
        } catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }  

    @FXML
    private void loadMembers(ActionEvent event)
    {
        controller.changeContent("members");
    }
    @FXML
    private void loadActivities(ActionEvent event)
    {
    }

    @FXML
    private void loadCourseMaterial(ActionEvent event)
    {
    }

    @FXML
    private void loadOverviews(ActionEvent event)
    {
    }

    @FXML
    private void loadLeaderBoard(ActionEvent event)
    {
    } 

    @FXML
    private void loadWelcome(MouseEvent event)
    {
        controller.changeContent("welcome");
    }
}
