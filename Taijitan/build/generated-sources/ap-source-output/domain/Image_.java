package domain;

import domain.CourseMaterial;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-07T16:49:53")
@StaticMetamodel(Image.class)
public class Image_ { 

    public static volatile SingularAttribute<Image, Integer> imageId;
    public static volatile SingularAttribute<Image, CourseMaterial> courseMaterialMaterialId;
    public static volatile SingularAttribute<Image, String> name;
    public static volatile SingularAttribute<Image, String> description;

}