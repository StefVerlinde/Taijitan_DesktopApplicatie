package domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.util.List;

public class Domaincontroller
{
    private final Taijitan taijitan;
    private User currentUser;
    private final PropertyChangeSupport subjectUsers;

    private ObservableList<User> lijstMembers;
    private ObservableList<User> lijstConfirmed;

    public Domaincontroller(){
        this.taijitan = new Taijitan();
        this.subjectUsers = new PropertyChangeSupport(this);

        lijstMembers = FXCollections.observableArrayList(getAllUsers());
        lijstConfirmed = FXCollections.observableArrayList();
    }
    public Taijitan getTaijitan() {
        return taijitan;
    }
    public List<User> getAllUsers() {
        return taijitan.getAllUsers();
    }
    public ObservableList<User> getAllMembersFX()
    {
        return taijitan.getAllMembersFX();
    }
    public List<Session> getAllSessions(){
        return taijitan.getAllSessions();
    }
    public List<User> getUsersFromSession(Session session){
        return taijitan.getUsersFromSession(session);
    }
    public void updateUser()
    {
        taijitan.updateUser(currentUser);
    }
    public void deleteUser() {
        taijitan.deleteUser(currentUser);
        setCurrentUser(null);
    }
    public void addUser(User user) {
        taijitan.addUser(user);
    }
    public void addActivity(Activity act){
        taijitan.addActivity(act);
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
    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User newUser) {
        subjectUsers.firePropertyChange("currentUser",this.currentUser,newUser);
        this.currentUser = newUser;
    }
    public void addPropertyChangeListenerCurrentUser(PropertyChangeListener pcl) {
        subjectUsers.addPropertyChangeListener(pcl);
        pcl.propertyChange(new PropertyChangeEvent(pcl, "currentUser", null, this.currentUser));
    }

    public void addConfirmed(User u){
        this.lijstConfirmed.add(u);
        this.lijstMembers.remove(u);
    }
    public void removeConfirmed(User u){
        System.out.println(lijstConfirmed.toString());
        this.lijstMembers.add(u);
        this.lijstConfirmed.remove(u);
    }

    public ObservableList<User> getLijstMembers(){
        return this.lijstMembers;
    }
    public ObservableList<User> getLijstConfirmed(){return this.lijstConfirmed;}
}
