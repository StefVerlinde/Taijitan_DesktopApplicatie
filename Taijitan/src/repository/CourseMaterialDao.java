package repository;

import domain.CourseMaterial;
import domain.User;

import javax.persistence.EntityNotFoundException;

public interface CourseMaterialDao extends  GenericDao<CourseMaterial>{
    CourseMaterial findLast()
            throws EntityNotFoundException;
}
