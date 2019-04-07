package domain;

import domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-07T16:49:53")
@StaticMetamodel(City.class)
public class City_ { 

    public static volatile SingularAttribute<City, String> postalcode;
    public static volatile CollectionAttribute<City, User> userCollection;
    public static volatile SingularAttribute<City, String> name;

}