package domain;

import domain.City;
import domain.Comment;
import domain.Formula;
import domain.Session;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-07T16:49:53")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Integer> country;
    public static volatile SingularAttribute<User, Integer> gender;
    public static volatile SingularAttribute<User, String> personalNationalNumber;
    public static volatile SingularAttribute<User, String> houseNumber;
    public static volatile CollectionAttribute<User, Session> sessionCollection;
    public static volatile SingularAttribute<User, String> landlineNumber;
    public static volatile SingularAttribute<User, Session> sessionId1;
    public static volatile SingularAttribute<User, String> birthPlace;
    public static volatile SingularAttribute<User, String> street;
    public static volatile SingularAttribute<User, Integer> rank;
    public static volatile SingularAttribute<User, City> cityPostalcode;
    public static volatile SingularAttribute<User, String> email;
    public static volatile CollectionAttribute<User, Comment> commentCollection;
    public static volatile SingularAttribute<User, Date> dateOfBirth;
    public static volatile SingularAttribute<User, Session> sessionId;
    public static volatile SingularAttribute<User, Integer> userId;
    public static volatile SingularAttribute<User, String> discriminator;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, Formula> formulaId;
    public static volatile SingularAttribute<User, String> phoneNumber;
    public static volatile SingularAttribute<User, Integer> nationality;
    public static volatile CollectionAttribute<User, Session> sessionCollection1;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Date> dateRegistred;
    public static volatile CollectionAttribute<User, Comment> commentCollection1;
    public static volatile SingularAttribute<User, String> mailParent;

}