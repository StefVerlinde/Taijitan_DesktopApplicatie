package domain;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import java.util.Date;


public class ActivityTest {

    private Activity activity;

    @Before
    public void before(){
        activity = new Activity();
    }

    @Test
    public void testValidActivityId(){
        int id = 1;
        activity.setActivityId(id);
        Assert.assertEquals(id, activity.getActivityId());
    }

    @Test
    public void testValidName(){
        String name = "testName";
        activity.setName(name);
        Assert.assertEquals(name, activity.getName());
    }

    @Test
    public void testValidType(){
        activity.setType(1);
        Assert.assertEquals(1, activity.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEndDateBeforeStartdate_givesError(){
        activity.setStartDate(new Date(2018,9,9));
        activity.setEndDate(new Date(2017/9/9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void startDateIsNull_givesEroor(){
        activity.setStartDate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void endDateIsNull_givesEroor(){
        activity.setEndDate(null);
    }


}