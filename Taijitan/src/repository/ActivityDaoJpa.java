package repository;

import domain.Activity;

public class ActivityDaoJpa extends GenericDaoJpa<Activity> implements ActivityDao {

    public ActivityDaoJpa(){
        super(Activity.class);
    }
}
