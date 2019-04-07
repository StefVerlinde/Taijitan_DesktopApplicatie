package domain;

import domain.Comment;
import domain.Image;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-07T16:49:53")
@StaticMetamodel(CourseMaterial.class)
public class CourseMaterial_ { 

    public static volatile SingularAttribute<CourseMaterial, String> youtubeURL;
    public static volatile CollectionAttribute<CourseMaterial, Comment> commentCollection;
    public static volatile CollectionAttribute<CourseMaterial, Image> imageCollection;
    public static volatile SingularAttribute<CourseMaterial, Integer> rank;
    public static volatile SingularAttribute<CourseMaterial, Integer> materialId;
    public static volatile SingularAttribute<CourseMaterial, String> fullDescription;
    public static volatile SingularAttribute<CourseMaterial, String> title;

}