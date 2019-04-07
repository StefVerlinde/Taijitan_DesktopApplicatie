package domain;

import domain.Session;
import domain.TrainingDay;
import domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-07T16:49:53")
@StaticMetamodel(Formula.class)
public class Formula_ { 

    public static volatile SingularAttribute<Formula, Integer> formulaId;
    public static volatile CollectionAttribute<Formula, User> userCollection;
    public static volatile CollectionAttribute<Formula, Session> sessionCollection1;
    public static volatile SingularAttribute<Formula, String> name;
    public static volatile CollectionAttribute<Formula, TrainingDay> trainingDayCollection;
    public static volatile CollectionAttribute<Formula, Session> sessionCollection;
    public static volatile SingularAttribute<Formula, Session> sessionId;
    public static volatile CollectionAttribute<Formula, TrainingDay> trainingDayCollection1;

}