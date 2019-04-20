package repository;

import domain.City;

import javax.persistence.EntityNotFoundException;

public interface CityDao extends GenericDao<City> {
    City getByPostal(String postal)
            throws EntityNotFoundException;;
}
