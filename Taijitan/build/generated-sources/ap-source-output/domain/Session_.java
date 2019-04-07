package domain;

import domain.Formula;
import domain.NonMember;
import domain.TrainingDay;
import domain.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-07T16:49:53")
@StaticMetamodel(Session.class)
public class Session_ { 

    public static volatile SingularAttribute<Session, Date> date;
    public static volatile CollectionAttribute<Session, Formula> formulaCollection;
    public static volatile SingularAttribute<Session, Formula> formulaId;
    public static volatile CollectionAttribute<Session, NonMember> nonMemberCollection;
    public static volatile SingularAttribute<Session, User> teacherUserId;
    public static volatile SingularAttribute<Session, Boolean> sessionStarted;
    public static volatile CollectionAttribute<Session, User> userCollection;
    public static volatile CollectionAttribute<Session, Formula> formulaCollection1;
    public static volatile CollectionAttribute<Session, User> userCollection1;
    public static volatile CollectionAttribute<Session, User> userCollection2;
    public static volatile SingularAttribute<Session, Integer> sessionId;
    public static volatile SingularAttribute<Session, TrainingDay> trainingDayId;

}