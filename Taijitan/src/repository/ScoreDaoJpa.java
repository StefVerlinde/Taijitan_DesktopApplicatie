package repository;

import domain.Score;

public class ScoreDaoJpa extends GenericDaoJpa<Score> implements ScoreDao {
    public ScoreDaoJpa(){super(Score.class);}
}