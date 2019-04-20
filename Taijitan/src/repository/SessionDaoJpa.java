package repository;

import domain.Session;

public class SessionDaoJpa extends GenericDaoJpa<Session> implements SessionDao {
    public SessionDaoJpa() {
        super(Session.class);
    }


}
