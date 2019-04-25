package domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Taijitan {
    private UserDao userDao;
    private SessionDao sessionDao;
    private CityDao cityDao;
    private GenericDao formulaDao;

    //constructor
    public Taijitan() {
        setCityDao(new CityDaoJpa());
        setSessionDao(new SessionDaoJpa());
        setUserDao(new UserDaoJpa());
        setFormulaDao(new GenericDaoJpa(Formula.class));
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
    public ObservableList<User> getAllUsersFX()
    {
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(getAllUsers()));
    }
    public List<Session> getAllSessions(){
        List<Session> sessions = sessionDao.findAll();
        return sessions;
    }
    public List<User> getUsersFromSession(Session session){
        List<User> users = new ArrayList<>(session.getUserCollection());
        return users;
    }
    public void updateUser(User user)
    {
        userDao.startTransaction();
        userDao.update(user);
        userDao.commitTransaction();
        System.out.println("update success");
    }

    public void deleteUser(User user) {
        userDao.startTransaction();
        userDao.delete(user);
        userDao.commitTransaction();
    }

    public void addUser(User user) {
        userDao.startTransaction();
        userDao.insert(user);
        userDao.commitTransaction();
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
}
