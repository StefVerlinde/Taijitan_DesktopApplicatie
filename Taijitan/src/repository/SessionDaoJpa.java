package repository;

import domain.Session;

import java.util.List;

public class SessionDaoJpa extends GenericDaoJpa<Session> implements SessionDao {
    public SessionDaoJpa(){
        super(Session.class);
    }


}
