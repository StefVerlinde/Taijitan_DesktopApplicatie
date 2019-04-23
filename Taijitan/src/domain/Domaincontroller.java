package domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Domaincontroller {

    private UserDao userDao;
    private SessionDao sessionDao;
    private CityDao cityDao;
    private GenericDao formulaDao;

    public Domaincontroller(){

        setUserDao(new UserDaoJpa());
        setSessionDao(new SessionDaoJpa());
        setCityDao(new CityDaoJpa());
        setFormulaDao(new GenericDaoJpa(Formula.class));
    }

    public void setUserDao(UserDaoJpa udj){

        this.userDao = udj;
    }

    public void setSessionDao(SessionDaoJpa sd) {

        this.sessionDao = sd;
    }

    public void setCityDao(CityDaoJpa cdj) {
        this.cityDao = cdj;
    }

    public void setFormulaDao(GenericDaoJpa<Formula> fdj) {
        this.formulaDao = fdj;
    }

    public List<User> getAllUsers() {
        List<User> users = userDao.findAll();
        users.sort(Comparator.comparing(u -> u.getName()));
        return users;
    }
    public ObservableList<User> GetAllUsersFX()
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
        GenericDaoJpa.startTransaction();
        userDao.update(user);
        GenericDaoJpa.commitTransaction();
        System.out.println("update success");
    }

    public void deleteUser(User user) {
        GenericDaoJpa.startTransaction();
        userDao.delete(user);
        GenericDaoJpa.commitTransaction();
    }

    public void addUser(User user) {
        GenericDaoJpa.startTransaction();
        userDao.insert(user);
        GenericDaoJpa.commitTransaction();
    }

    public List<City> getAllCities()
    {
        return cityDao.findAll();
    }

    public void addCity(City city){
        GenericDaoJpa.startTransaction();
        cityDao.insert(city);
        GenericDaoJpa.commitTransaction();
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
