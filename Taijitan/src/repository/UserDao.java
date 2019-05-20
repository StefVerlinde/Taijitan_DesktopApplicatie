package repository;

import domain.User;

import javax.persistence.EntityNotFoundException;

public interface UserDao extends GenericDao<User> {

    User getUserByName(String Name,String first)
            throws EntityNotFoundException;
    void removeSessionsAndActivities(int id);
}
