package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Collection;
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
        int valOfType = ActivityType.excursie.ordinal();
        activity.setType(ActivityType.excursie.ordinal());
        Assert.assertEquals(1, valOfType);
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