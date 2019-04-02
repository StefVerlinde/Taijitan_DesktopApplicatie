/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taijitan;

import domain.Domaincontroller;
import gui.FrameController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Jarne
 */
public class Taijitan extends Application
{
    private Domaincontroller dc = new Domaincontroller();
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new FrameController(dc));
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
