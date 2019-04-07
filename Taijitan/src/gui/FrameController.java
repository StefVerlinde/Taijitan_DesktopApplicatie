/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Domaincontroller;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Screen;

/**
 *
 * @author Jarne
 */
public class FrameController extends HBox
{
    private Domaincontroller dc;
    private NavController nav;
    private WelcomeController welcome;
    private MembersController members;
    private Object current;
    
    public FrameController(Domaincontroller dc) {
        this.dc = dc;
        nav = new NavController(this);
        welcome = new WelcomeController();
        members = new MembersController(dc);
        current = welcome;
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());
        welcome.setMinWidth(bounds.getWidth()-nav.getPrefWidth());
        members.setMinWidth(bounds.getWidth()-nav.getPrefWidth());
        getChildren().addAll(nav,welcome);
    }
    public void changeContent(String string)
    {
        getChildren().remove(current);
        
        switch(string.toLowerCase())
        {
            case "members":
                current = members;
                getChildren().add(members);
                break;
            case "welcome":
                current = welcome;
                getChildren().add(welcome);
                break;
        }
        
        
    }
}
