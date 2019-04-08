/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Domaincontroller;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

public class FrameController extends HBox
{
    private Domaincontroller dc;
    private NavController nav;
    private WelcomeController welcome;
    private MembersController members;
    private PresentsController presents;
    private Object current;
    Rectangle2D bounds;
    
    public FrameController(Domaincontroller dc) {
        this.dc = dc;
        setupStart();
    }

    private void setupStart(){
        nav = new NavController(this);

        Screen screen = Screen.getPrimary();
        bounds = screen.getVisualBounds();
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());

        getChildren().add(nav);
        setupWelcome();
    }

    private void setupWelcome(){
        welcome = new WelcomeController();
        current = welcome;
        welcome.setMinWidth(bounds.getWidth()-nav.getPrefWidth());

        getChildren().add(welcome);
    }

    private void setupMember(){
        members = new MembersController(dc);
        current = members;
        members.setMinWidth(bounds.getWidth()-nav.getPrefWidth());

        getChildren().add(members);
    }

    private void setupPresents(){
        presents = new PresentsController(dc);
        current = presents;
        presents.setMinWidth(bounds.getWidth()-nav.getPrefWidth());

        getChildren().add(presents);
    }

    public void changeContent(String string)
    {
        getChildren().remove(current);
        
        switch(string.toLowerCase())
        {
            case "members":
                setupMember();
                break;
            case "welcome":
               setupWelcome();
                break;
            case "presents":
                setupPresents();
                break;
        }
        
        
    }
}
