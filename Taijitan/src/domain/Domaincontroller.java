package domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Domaincontroller
{
    private Taijitan taijitan;

    public Domaincontroller(){
        setTaijitan(new Taijitan());
    }
    public Taijitan getTaijitan() {
        return taijitan;
    }
    public void setTaijitan(Taijitan taijitan) {
        this.taijitan = taijitan;
    }
    public List<User> getAllUsers() {
        return taijitan.getAllUsers();
    }
    public ObservableList<User> getAllUsersFX()
    {
        return taijitan.getAllUsersFX();
    }
    public List<Session> getAllSessions(){
        return taijitan.getAllSessions();
    }
    public List<User> getUsersFromSession(Session session){
        return taijitan.getUsersFromSession(session);
    }
    public void updateUser(User user)
    {
        taijitan.updateUser(user);
    }
    public void deleteUser(User user) {
        taijitan.deleteUser(user);
    }
    public void addUser(User user) {
        taijitan.addUser(user);
    }
    public List<City> getAllCities()
    {
       return taijitan.getAllCities();
    }
    public void addCity(City city){
        taijitan.addCity(city);
    }
    public City getCityByPostal(String postal)
    {
        return taijitan.getCityByPostal(postal);
    }
    public List<Formula> getAllFormulas()
    {
        return taijitan.getAllFormulas();
    }
}
