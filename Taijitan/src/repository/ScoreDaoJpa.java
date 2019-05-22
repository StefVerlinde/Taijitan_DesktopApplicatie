package repository;

import domain.Score;
import domain.User;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

public class ScoreDaoJpa extends GenericDaoJpa<Score> implements ScoreDao {
    public ScoreDaoJpa(){super(Score.class);}

    public Score getScoreByUserIdAndActivityName(User user, String name) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Score.findByUserIdActivityName", Score.class)
                    .setParameter("MemberId", user).setParameter("Name",name)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        }
    }
}