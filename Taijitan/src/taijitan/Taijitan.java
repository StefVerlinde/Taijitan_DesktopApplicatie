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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jarne
 */
public class Taijitan extends Application
{
    private EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    private EntityManager em = emf.createEntityManager();
    private Domaincontroller dc = new Domaincontroller();
    @Override
    public void start(Stage stage) {
        dc.getAllUsers();
        Scene scene = new Scene(new FrameController(dc));
        stage.setScene(scene);
        stage.getIcons().add(new Image("/assets/img/logo.png"));
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/assets/img/logo.png")));

        // The stage will not get smaller than its preferred (initial) size.
        stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String... args) {
        Application.launch(Taijitan.class, args);
    }
    
}
