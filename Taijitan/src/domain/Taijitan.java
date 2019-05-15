package domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Taijitan {
    private UserDao userDao;
    private SessionDao sessionDao;
    private CityDao cityDao;
    private GenericDao formulaDao;
    private ActivityDao activityDao;
    private CourseMaterialDao courseMaterialDao;
    private List<User> sortedUsers;
    private ImageDao imageDao;



    //constructor
    public Taijitan() {
        setCityDao(new CityDaoJpa());
        setSessionDao(new SessionDaoJpa());
        setUserDao(new UserDaoJpa());
        setActivityDao(new ActivityDaoJpa());
        setFormulaDao(new GenericDaoJpa(Formula.class));
        setCourseMaterialDao(new CourseMaterialDaoJpa());
        setImageDao(new ImageDaoJpa());
        sortedUsers = getAllUsers();
        Collections.sort(sortedUsers, Comparator.comparing(User::getScore).reversed());
    }


    //getters - setters
    public UserDao getUserDao() {
        return userDao;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public SessionDao getSessionDao() {
        return sessionDao;
    }
    public void setSessionDao(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }
    public CityDao getCityDao() {
        return cityDao;
    }
    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }
    public void setImageDao(ImageDao imageDao) {this.imageDao = imageDao;}

    public CourseMaterialDao getCourseMaterialDao() {
        return courseMaterialDao;
    }

    public void setCourseMaterialDao(CourseMaterialDao courseMaterialDao) {
        this.courseMaterialDao = courseMaterialDao;
    }

    public ActivityDao getActivityDao(){
        return this.activityDao;
    }
    public void setActivityDao(ActivityDao act){
        this.activityDao = act;
    }
    public GenericDao getFormulaDao() {
        return formulaDao;
    }
    public void setFormulaDao(GenericDao formulaDao) {
        this.formulaDao = formulaDao;
    }
    public List<User> getAllUsers() {
        List<User> users = userDao.findAll();
        users.sort(Comparator.comparing(u -> u.getName()));
        return users;
    }
    public List<User> getAllMembers(){
        return getAllUsers().stream().collect(Collectors.toList());
    }
    public List<Activity> getAllActivities(){
        return activityDao.findAll();
    }
    public ObservableList<User> getAllMembersFX()
    {
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(getAllMembers()));
    }
    public ObservableList<Activity> getAllActivitiesFX(){
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList((getAllActivities())));
    }
    public List<CourseMaterial> getAllCourseMaterials(){
       return courseMaterialDao.findAll();
    }
    public List<Session> getAllSessions(){
        List<Session> sessions = sessionDao.findAll();
        return sessions;
    }
    public List<User> getUsersFromSession(Session session){
        List<User> users = new ArrayList<>(session.getUserCollection());
        return users;
    }
    public List<User> getSortedUsers() {
        return sortedUsers;
    }

    public void updateUser(User user)
    {
        userDao.startTransaction();
        userDao.update(user);
        userDao.commitTransaction();
        System.out.println("update user success");
    }
    public void updateActivity(Activity act){
        activityDao.startTransaction();
        activityDao.update(act);
        activityDao.commitTransaction();
        System.out.println("update activity success");
    }

    public void deleteUser(User user) {
        userDao.startTransaction();
        userDao.delete(user);
        userDao.commitTransaction();
    }
    public void deleteActivity(Activity act){
        activityDao.startTransaction();
        activityDao.delete(act);
        activityDao.commitTransaction();
    }
    public User getUserByFullName(String firstname,String lastname)
    {
        return userDao.getUserByName(lastname,firstname);
    }

    public void addUser(User user) {
        userDao.startTransaction();
        userDao.insert(user);
        userDao.commitTransaction();
    }

    public void addActivity(Activity activity){
        activityDao.startTransaction();
        activityDao.insert(activity);
        activityDao.commitTransaction();
    }

    public List<City> getAllCities()
    {
        return cityDao.findAll();
    }

    public void addCity(City city){
        cityDao.startTransaction();
        cityDao.insert(city);
        cityDao.commitTransaction();
    }

    public City getCityByPostal(String postal)
    {
        City city = cityDao.getByPostal(postal);
        System.out.println(city.toString());
        return city;
    }

    public List<Formula> getAllFormulas()
    {
        return this.formulaDao.findAll();
    }

    public void deleteCourseMaterial(CourseMaterial currentCourseMaterial) {
        courseMaterialDao.startTransaction();
        courseMaterialDao.delete(currentCourseMaterial);
        courseMaterialDao.commitTransaction();
    }

    public void addCourseMaterial(CourseMaterial newC) {
//        imageDao.startTransaction();
//        for(Image i : newC.getImageCollection())
//        {
//            imageDao.insert(i);
//        }
//        imageDao.commitTransaction();
        courseMaterialDao.startTransaction();
        courseMaterialDao.insert(newC);
        courseMaterialDao.commitTransaction();
    }
}
