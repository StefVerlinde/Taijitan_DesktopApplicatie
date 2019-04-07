package repository;

import domain.User;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

public class UserDaoJpa extends GenericDaoJpa<User> implements UserDao {
    public UserDaoJpa() {
        super(User.class);
    }

    @Override
    public User getUserByName(String name) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("User.findByName", User.class)
                    .setParameter("userName", name)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        }
    }
}
