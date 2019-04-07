package domain;

import domain.Formula;
import domain.Session;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2019-04-07T16:49:53")
@StaticMetamodel(TrainingDay.class)
public class TrainingDay_ { 

    public static volatile SingularAttribute<TrainingDay, Double> duration;
    public static volatile CollectionAttribute<TrainingDay, Formula> formulaCollection;
    public static volatile SingularAttribute<TrainingDay, Formula> formulaId;
    public static volatile SingularAttribute<TrainingDay, Integer> dayOfWeek;
    public static volatile SingularAttribute<TrainingDay, Double> startHour;
    public static volatile SingularAttribute<TrainingDay, Double> stopHour;
    public static volatile SingularAttribute<TrainingDay, String> name;
    public static volatile CollectionAttribute<TrainingDay, Session> sessionCollection;
    public static volatile SingularAttribute<TrainingDay, Integer> trainingDayId;

}