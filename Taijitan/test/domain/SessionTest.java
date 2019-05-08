package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

public class SessionTest {
    private Session session;

    @Before
    public void before(){
        session = new Session();
    }
    @Test
    public void setSessionId() {
        Integer id = 1;
        session.setSessionId(id);
        Assert.assertEquals(id, session.getSessionId());
    }

    @Test
    public void setDate() {
        Date newDate = new Date();
        session.setDate(newDate);
        Assert.assertEquals(newDate, session.getDate());
    }

    @Test
    public void setSessionStarted() {
        session.setSessionStarted(true);
        Assert.assertTrue(session.getSessionStarted());
    }

    @Test
    public void setUserCollection() {
        Collection<User> userCollection = new ArrayList<>();
        userCollection.add(new User());
        userCollection.add(new User());
        session.setUserCollection(userCollection);
        Assert.assertEquals(userCollection.size(), session.getUserCollection().size());
    }

    @Test
    public void setFormulaCollection() {
        Collection<Formula> formuleCollection = new ArrayList<>();
        formuleCollection.add(new Formula());
        formuleCollection.add(new Formula());
        session.setFormulaCollection(formuleCollection);
        Assert.assertEquals(formuleCollection.size(), session.getFormulaCollection().size());
    }

    @Test
    public void setUserCollection1() {
        Collection<User> userCollection = new ArrayList<>();
        userCollection.add(new User());
        userCollection.add(new User());
        session.setUserCollection1(userCollection);
        Assert.assertEquals(userCollection.size(), session.getUserCollection1().size());
    }

    @Test
    public void setUserCollection2() {
        Collection<User> userCollection = new ArrayList<>();
        userCollection.add(new User());
        userCollection.add(new User());
        session.setUserCollection2(userCollection);
        Assert.assertEquals(userCollection.size(), session.getUserCollection2().size());
    }

    @Test
    public void setNonMemberCollection() {
        Collection<NonMember> nonMemberCollection = new ArrayList<>();
        nonMemberCollection.add(new NonMember());
        nonMemberCollection.add(new NonMember());
        session.setNonMemberCollection(nonMemberCollection);
        Assert.assertEquals(nonMemberCollection.size(), session.getNonMemberCollection().size());
    }

    @Test
    public void setFormulaId() {
        Formula formula = new Formula();
        session.setFormulaId(formula);
        Assert.assertEquals(formula, session.getFormulaId());
    }

    @Test
    public void setTrainingDayId() {
        TrainingDay trainingDay = new TrainingDay();
        session.setTrainingDayId(trainingDay);
        Assert.assertEquals(trainingDay, session.getTrainingDayId());
    }

    @Test
    public void setTeacherUserId() {
        User user = new User();
        session.setTeacherUserId(user);
        Assert.assertEquals(user, session.getTeacherUserId());
    }
}