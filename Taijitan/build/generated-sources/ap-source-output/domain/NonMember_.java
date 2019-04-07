package domain;

import domain.Session;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-07T16:49:53")
@StaticMetamodel(NonMember.class)
public class NonMember_ { 

    public static volatile SingularAttribute<NonMember, String> firstName;
    public static volatile SingularAttribute<NonMember, String> lastName;
    public static volatile SingularAttribute<NonMember, Integer> id;
    public static volatile SingularAttribute<NonMember, Session> sessionId;
    public static volatile SingularAttribute<NonMember, String> email;

}