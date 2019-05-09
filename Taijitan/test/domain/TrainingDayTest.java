package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrainingDayTest {
    private TrainingDay trainingDay;

    @Before
    public void before(){
        trainingDay = new TrainingDay();
    }

    @Test
    public void constructor_OK(){
        new TrainingDay(1,10.5,18.5,4);
    }

    @Test
    public void setTrainingDayId_OK(){
        trainingDay.setTrainingDayId(4);
        assertEquals(4,trainingDay.getTrainingDayId());
    }
    @Test(expected = IllegalArgumentException.class)
    public void setTrainingDayId_Invalid(){
        trainingDay.setTrainingDayId(-5);
    }
    @Test
    public void setName_OK(){
        trainingDay.setName("test");
    }
    @Test(expected = IllegalArgumentException.class)
    public void setName_Null(){
        trainingDay.setName(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void setName_empty(){
        trainingDay.setName("");
    }
    @Test
    public void setStartHour_OK(){
        trainingDay.setStartHour(15);
    }
    @Test(expected = IllegalArgumentException.class)
    public void setStartHour_Negative(){
        trainingDay.setStartHour(-5);
    }
    @Test(expected = IllegalArgumentException.class)
    public void setStartHour_Invalid(){
        trainingDay.setStartHour(26);
    }
    @Test
    public void setStopHour_OK(){
        trainingDay.setStopHour(16);
    }
    @Test(expected = IllegalArgumentException.class)
    public void setStopHour_Negative(){
        trainingDay.setStopHour(-5);
    }
    @Test(expected = IllegalArgumentException.class)
    public void setStopHour_Invalid(){
        trainingDay.setStopHour(26);
    }
    @Test(expected = IllegalArgumentException.class)
    public void setStopHour_BeforeStartHour(){
        trainingDay.setStartHour(10);
        trainingDay.setStopHour(9);
    }
    @Test
    public void setDayOfWeek_OK(){
        trainingDay.setDayOfWeek(4);
    }
    @Test(expected = IllegalArgumentException.class)
    public void setDayOfWeek_Invalid()
    {
        trainingDay.setDayOfWeek(-5);
    }


}
