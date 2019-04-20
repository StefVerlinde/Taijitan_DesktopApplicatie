package repository;

import domain.City;
import domain.User;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

public class CityDaoJpa extends GenericDaoJpa<City> implements CityDao {
    public CityDaoJpa(){super(City.class);}

    @Override
    public City getByPostal(String postal) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("City.findByPostal", City.class)
                    .setParameter("postal", postal)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        }
    }
}
