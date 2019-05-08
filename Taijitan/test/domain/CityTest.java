package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class CityTest {

    private City city;

    @Before
    public void before(){
        this.city = new City();
    }

    @Test
    public void setValidPostalCode(){
        String code = "9810";
        this.city.setPostalcode(code);
        Assert.assertEquals(code, city.getPostalcode());
    }

    @Test (expected = IllegalArgumentException.class)
    public void setInvalidPostalCode_toSmall(){
        String code = "999";

    }
    @Test (expected = IllegalArgumentException.class)
    public void setInvalidPostalCode_toBig(){
        String code = "10000";
        this.city.setPostalcode(code);
    }

    @Test
    public void setValidName(){
        String name = "Nazareth";
        this.city.setName(name);
        Assert.assertEquals(name, city.getName());
    }

    @Test (expected= IllegalArgumentException.class)
    public void setNullName_givesError(){
        this.city.setName(null);
    }

    @Test (expected= IllegalArgumentException.class)
    public void setEmptyName_givesError(){
        this.city.setName("");
    }


}