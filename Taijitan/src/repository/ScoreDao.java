package repository;

import domain.Score;
import domain.User;

import javax.persistence.EntityNotFoundException;

public interface ScoreDao extends GenericDao<Score>{
    Score getScoreByUserIdAndActivityName(User user, String name)
            throws EntityNotFoundException;
}
