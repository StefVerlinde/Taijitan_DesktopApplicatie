package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AlertBoxController {

    public static void ConfirmationAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            System.out.println("Okay");
        }
        else{
            System.out.println("Cancel");
        }
    }
}
