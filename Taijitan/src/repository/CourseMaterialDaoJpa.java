package repository;

import domain.City;
import domain.CourseMaterial;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

public class CourseMaterialDaoJpa extends GenericDaoJpa<CourseMaterial> implements CourseMaterialDao {
    public CourseMaterialDaoJpa(){super(CourseMaterial.class);}


}
