package domain;

import dto.ActivityDTO;
import dto.CourseMaterialDTO;
import dto.UserDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class Domaincontroller
{
    private final Taijitan taijitan;
    private User currentUser;
    private Activity currentActivity;
    private CourseMaterial currentCourseMaterial;
    private final PropertyChangeSupport subjectUsers;
    private final PropertyChangeSupport subjectUsersScore;
    private final PropertyChangeSupport subjectActivities;
    private final PropertyChangeSupport subjectCourseMaterial;
    private ObservableList<User> lijstMembers;
    private ObservableList<User> lijstConfirmed;
    private FilteredList<User> filteredMembersLijst;
    private FilteredList<User> getFilteredConfirmedLijst;
    private ObservableList<CourseMaterial> lijstCourseMaterial;
    private FilteredList<CourseMaterial> filteredCourseMaterialLijst;




    public Domaincontroller(){
        this.taijitan = new Taijitan();
        this.subjectUsers = new PropertyChangeSupport(this);
        this.subjectUsersScore = new PropertyChangeSupport(this);
        this.subjectActivities = new PropertyChangeSupport(this);
        this.subjectCourseMaterial = new PropertyChangeSupport(this);
        lijstMembers = FXCollections.observableArrayList(getAllUsers());
        filteredMembersLijst = new FilteredList<>(lijstMembers,p -> true);
        lijstConfirmed = FXCollections.observableArrayList();
        getFilteredConfirmedLijst = new FilteredList<>(lijstConfirmed,p -> true);
        lijstCourseMaterial = FXCollections.observableArrayList(taijitan.getAllCourseMaterials());
        filteredCourseMaterialLijst = new FilteredList<>(lijstCourseMaterial,p -> true);
    }
    public Taijitan getTaijitan() {
        return taijitan;
    }
    public List<User> getAllUsers() {
        return taijitan.getAllUsers();
    }
    public ObservableList<User> getAllMembersFX(){return taijitan.getAllMembersFX();}
    public ObservableList<Activity> getAllActivitiesFX(){return taijitan.getAllActivitiesFX();}
    public List<Session> getAllSessions(){
        return taijitan.getAllSessions();
    }
    public List<Activity> getAllActivities(){return taijitan.getAllActivities();}
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
    public List<User> getSortedUsers(){
        return taijitan.getSortedUsers();
    }
    public void deleteActivity(){
        taijitan.deleteActivity(this.currentActivity);
        setCurrentActivity(null);
    }
    public void deleteCourseMaterial(){
        taijitan.deleteCourseMaterial(this.currentCourseMaterial);
        setCurrentCourseMaterial(null);
    }
    public void filter(String str){
        filteredMembersLijst.setPredicate(user ->{
            return user.getFirstName().toLowerCase().contains(str.toLowerCase())
                    || user.getName().toLowerCase().contains(str.toLowerCase());
        });
    }
    public void filterConfirmed(String str){
        getFilteredConfirmedLijst.setPredicate(user ->{
            return user.getFirstName().toLowerCase().contains(str.toLowerCase())
                    || user.getName().toLowerCase().contains(str.toLowerCase());
        });
    }
    public void filterCourseMaterial(Rank rank){
        filteredCourseMaterialLijst.setPredicate(course -> {
            return course.getRank() == rank.getId();
        });
    }

    public List<User> getUsersFromActivity(Activity act){
        return act.getUsers();
    }
    public void addUser(UserDTO user) {
        taijitan.addUser(user);
    }
    public void addActivity(ActivityDTO act){
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
    public void setCurrentUserScore(User newUser) {
        subjectUsersScore.firePropertyChange("currentUser",this.currentUser,newUser);
        this.currentUser = newUser;
    }
    public List<User> getAllMembers(){
        return taijitan.getAllMembers();
    }
    public void addPropertyChangeListenerCurrentUser(PropertyChangeListener pcl) {
        subjectUsers.addPropertyChangeListener(pcl);
        pcl.propertyChange(new PropertyChangeEvent(pcl, "currentUser", null, this.currentUser));
    }
    public void addPropertyChangeListenerCurrentUserScore(PropertyChangeListener pcl) {
        subjectUsersScore.addPropertyChangeListener(pcl);
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
    public void addPropertyChangeListenerCurrentCourseMaterial(PropertyChangeListener pcl){
        subjectCourseMaterial.addPropertyChangeListener(pcl);
        pcl.propertyChange(new PropertyChangeEvent(pcl,"currentCourseMaterial",null,this.currentCourseMaterial));
    }

    public void addConfirmed(User u){
        this.lijstConfirmed.add(u);
        this.lijstMembers.remove(u);

    }
    public void removeConfirmed(User u){
        this.lijstMembers.add(u);
        this.lijstConfirmed.remove(u);


    }
    public void setLijstMembers(List<User> lijstMembers) {
        this.lijstMembers = FXCollections.observableArrayList(lijstMembers);
        filteredMembersLijst = new FilteredList<>(this.lijstMembers,p -> true);
    }
    public void setLijstConfirmed(List<User> lijstConfirmed) {
        this.lijstConfirmed = FXCollections.observableArrayList(lijstConfirmed);
        getFilteredConfirmedLijst = new FilteredList<>(this.lijstConfirmed,p -> true);
    }
    public Activity getCurrentActivity() {
        return currentActivity;
    }
    public ObservableList<User> getLijstMembers(){
        return FXCollections.unmodifiableObservableList(this.filteredMembersLijst);}
    public ObservableList<User> getLijstConfirmed(){
        return FXCollections.unmodifiableObservableList(this.getFilteredConfirmedLijst);
    }
    public ObservableList<CourseMaterial> getLijstCourseMaterial(){
        return FXCollections.unmodifiableObservableList(this.filteredCourseMaterialLijst);
    }

    public CourseMaterial getCurrentCourseMaterial() {
        return currentCourseMaterial;
    }

    public void setCurrentCourseMaterial(CourseMaterial newC) {
        subjectCourseMaterial.firePropertyChange("currentCourseMaterial",this.currentCourseMaterial,newC);
        this.currentCourseMaterial = newC;
    }

    public void addCourseMaterial(CourseMaterialDTO newC) {
        taijitan.addCourseMaterial(newC);
    }
}
