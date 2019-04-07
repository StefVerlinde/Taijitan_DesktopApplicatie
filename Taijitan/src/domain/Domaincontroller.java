package domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.UserDao;
import repository.UserDaoJpa;

import java.util.List;

public class Domaincontroller {

    private UserDao userDao;

    public Domaincontroller(){
        setUserDao(new UserDaoJpa());
    }

    public void setUserDao(UserDaoJpa udj){
        userDao = udj;
    }

    public List<User> getAllUsers(){
        List<User> users = userDao.findAll();
        for (User u:users)
        {
            System.out.println(u.toString());
        }
        return users;
    }
    public ObservableList<User> GetAllUsersFX()
    {
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(getAllUsers()));
    }
}
