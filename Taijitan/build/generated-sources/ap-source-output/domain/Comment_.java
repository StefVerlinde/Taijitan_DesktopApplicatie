package domain;

import domain.CourseMaterial;
import domain.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-07T16:49:53")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, User> memberUserId;
    public static volatile SingularAttribute<Comment, Date> dateCreated;
    public static volatile SingularAttribute<Comment, CourseMaterial> courseMaterialId;
    public static volatile SingularAttribute<Comment, Boolean> isRead;
    public static volatile SingularAttribute<Comment, Integer> commentId;
    public static volatile SingularAttribute<Comment, User> userId;
    public static volatile SingularAttribute<Comment, String> content;

}