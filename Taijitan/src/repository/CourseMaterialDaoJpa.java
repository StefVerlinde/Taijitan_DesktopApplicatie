package repository;

import domain.City;
import domain.CourseMaterial;
import domain.User;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

public class CourseMaterialDaoJpa extends GenericDaoJpa<CourseMaterial> implements CourseMaterialDao {
    public CourseMaterialDaoJpa(){super(CourseMaterial.class);}

    @Override
    public CourseMaterial findLast() throws EntityNotFoundException {
        try {
            return em.createNamedQuery("CourseMaterial.findLast", CourseMaterial.class)
                    .setMaxResults(1).getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void removeCommentsAndImages(CourseMaterial cm) {
        em.createNamedQuery("CourseMaterial.removeComments", CourseMaterial.class).setParameter("id", cm).executeUpdate();
        em.createNamedQuery("CourseMaterial.removeImages", CourseMaterial.class).setParameter("id", cm).executeUpdate();
    }
}
