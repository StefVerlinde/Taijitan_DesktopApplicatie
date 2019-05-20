package repository;

import domain.User;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

public class UserDaoJpa extends GenericDaoJpa<User> implements UserDao {
    public UserDaoJpa() {
        super(User.class);
    }

    @Override
    public User getUserByName(String name,String first) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("User.findByFullName", User.class)
                    .setParameter("userName", name).setParameter("first",first)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void removeSessionsAndActivities(int id) {
        em.createNamedQuery("User.deleteActivities", User.class).setParameter("id", id).executeUpdate();
        em.createNamedQuery("User.deleteSessions", User.class).setParameter("id", id).executeUpdate();
    }
}
