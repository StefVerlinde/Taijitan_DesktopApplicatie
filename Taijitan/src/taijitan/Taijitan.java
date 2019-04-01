/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taijitan;

import gui.LoginController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Jarne
 */
public class Taijitan extends Application
{
    
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new LoginController());
        stage.setScene(scene);

        // The stage will not get smaller than its preferred (initial) size.
        stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        stage.show();
    }

    public static void main(String... args) {
        Application.launch(Taijitan.class, args);
    }
    
}
