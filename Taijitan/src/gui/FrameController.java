/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;

/**
 *
 * @author Jarne
 */
public class FrameController extends HBox
{
    private NavController nav;
    private WelcomeController welcome;
    private MembersController members;
    private Object current;
    
    public FrameController() {
        nav = new NavController(this);
        welcome = new WelcomeController();
        members = new MembersController();
        current = welcome;
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
