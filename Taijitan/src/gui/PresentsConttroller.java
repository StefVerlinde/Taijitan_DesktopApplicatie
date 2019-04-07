package gui;

import domain.Domaincontroller;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;


public class PresentsConttroller extends AnchorPane {

        @FXML
        private JFXListView lstSessions;
        // moet een lijst van sessions worden ipv een lijst van strings

        private Domaincontroller dc;


    public PresentsConttroller(Domaincontroller dc){
        this.dc = dc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Presents.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try{
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildGui();
    }

    private void buildGui(){
        ArrayList<String> dummyLijst = new ArrayList<>();
        dummyLijst.add("test1");
        dummyLijst.add("test2");
        dummyLijst.add("test3");
        //deze lijst moet vervangen worden door sessies uit de db

        System.out.println(dummyLijst.toString());

        lstSessions = new JFXListView();
        // deze initialisatie zou normaal niet moeten?

        lstSessions.setItems((FXCollections.observableArrayList((dummyLijst))));
    }
}
