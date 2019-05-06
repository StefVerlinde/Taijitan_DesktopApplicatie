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
    private Activity currentActivity;
    private final PropertyChangeSupport subjectUsers;
    private final PropertyChangeSupport subjectActivities;
    private ObservableList<User> lijstMembers;
    private ObservableList<User> lijstConfirmed;


    public Domaincontroller(){
        this.taijitan = new Taijitan();
        this.subjectUsers = new PropertyChangeSupport(this);
        this.subjectActivities = new PropertyChangeSupport(this);
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
    public ObservableList<Activity> getAllActivitiesFX(){return taijitan.getAllActivitiesFX();}
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
    public void updateActivity(){taijitan.updateActivity(currentActivity);}
    public void deleteUser() {
        taijitan.deleteUser(currentUser);
        setCurrentUser(null);
    }
    public void deleteActivity(){
        taijitan.deleteActivity(this.currentActivity);
        setCurrentActivity(null);
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
    public List<User> getAllMembers(){
        return taijitan.getAllMembers();
    }
    public void addPropertyChangeListenerCurrentUser(PropertyChangeListener pcl) {
        subjectUsers.addPropertyChangeListener(pcl);
        pcl.propertyChange(new PropertyChangeEvent(pcl, "currentUser", null, this.currentUser));
    }
    public void setCurrentActivity(Activity act){
        subjectActivities.firePropertyChange("currentActivity",this.currentActivity,act);
        this.currentActivity = act;
    }
    public void addPropertyChangeListenerCurrentActivity(PropertyChangeListener pcl) {
        subjectActivities.addPropertyChangeListener(pcl);
        pcl.propertyChange(new PropertyChangeEvent(pcl,"currentActivity",null,this.currentActivity));
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
    public void setLijstMembers(List<User> lijstMembers) {
        this.lijstMembers = FXCollections.observableArrayList(lijstMembers); }
    public void setLijstConfirmed(List<User> lijstConfirmed) {
        this.lijstConfirmed = FXCollections.observableArrayList(lijstConfirmed);
    }
    public Activity getCurrentActivity() {
        return currentActivity;
    }
    public ObservableList<User> getLijstMembers(){
        return FXCollections.unmodifiableObservableList(this.lijstMembers);}
    public ObservableList<User> getLijstConfirmed(){ return FXCollections.unmodifiableObservableList(this.lijstConfirmed);}

}
