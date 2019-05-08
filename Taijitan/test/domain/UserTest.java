package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class UserTest {
    private User user;

    @Before
    public void before(){
        user = new User();
    }

    @Test
    public void setUserId() {
        Integer id = 1;
        user.setUserId(id);
        Assert.assertEquals(id, user.getUserId());
    }

    @Test
    public void setGeldigeNaam() {
        user.setName("Verlinde");
        Assert.assertEquals("Verlinde", user.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigeNaam(){
        user.setName("");
    }

    @Test
    public void setGeldigeFirstName() {
        user.setFirstName("Stef");
        Assert.assertEquals("Stef", user.getFirstName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigeFirstName(){
        user.setFirstName("");
    }

    @Test
    public void setGeldigeDateOfBirth() {
        Date datum = new Date();
        user.setDateOfBirth(datum);
        Assert.assertEquals(datum, user.getDateOfBirth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigeDateOfBirth() {
        Date datum = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(datum);
        cal.add(Calendar.DATE, 1);
        user.setDateOfBirth(cal.getTime());
    }

    @Test
    public void setGeldigeStreet() {
        user.setStreet("Bijlokeweg");
        Assert.assertEquals("Bijlokeweg", user.getStreet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigeStreet() {
        user.setStreet("");
    }

    @Test
    public void setCountry() {
        user.setCountry(1);
        Assert.assertEquals(1, user.getCountry());
    }

    @Test
    public void setGeldigHouseNumber() {
        user.setHouseNumber("73");
        Assert.assertEquals("73", user.getHouseNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigHouseNumber() {
        user.setHouseNumber("");
    }

    @Test
    public void setGeldigPhoneNumber() {
        user.setPhoneNumber("+32479535999");
        Assert.assertEquals("+32479535999", user.getPhoneNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigPhoneNumber_Leeg() {
        user.setPhoneNumber("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigPhoneNumber_Tekort() {
        user.setPhoneNumber("04795");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigPhoneNumber_TeLang() {
        user.setPhoneNumber("047953599904795359999");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigPhoneNumber_OngeldigeStart() {
        user.setPhoneNumber("0079535999");
    }

    @Test
    public void setGeldigEmail() {
        user.setEmail("stefverlinde@hotmail.com");
        Assert.assertEquals("stefverlinde@hotmail.com", user.getEmail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigEmail_Leeg(){
        user.setEmail("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigEmail_ZonderApenstaart(){
        user.setEmail("stefverlinde+hotmail.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigEmail_ZonderPunt(){
        user.setEmail("stefverlinde@hotmail/com");
    }

    @Test
    public void setDateRegistred() {
        Date date = new Date(2019, 05, 07);
        user.setDateRegistred(date);
        Assert.assertEquals(date, user.getDateRegistred());
    }

    @Test
    public void setGender() {
        user.setGender(1);
        Assert.assertEquals(1, user.getGender());
    }


    @Test
    public void setNationality() {
        user.setNationality(1);
        Assert.assertEquals(1, user.getNationality());
    }


    @Test
    public void setGeldigPersonalNationalNumber() {
        user.setPersonalNationalNumber("98.02.08-306.37");
        Assert.assertEquals("98.02.08-306.37", user.getPersonalNationalNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigPersonalNationalNumber_Leeg(){
        user.setPersonalNationalNumber("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigPersonalNationalNumber_TeKort(){
        user.setPersonalNationalNumber("98.02.08-30");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigPersonalNationalNumber_TeLang(){
        user.setPersonalNationalNumber("98.02.08-306.37.38");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigPersonalNationalNumber_VerkeerdScheidingsteken(){
        user.setPersonalNationalNumber("98/02/08-306/37/38");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigPersonalNationalNumber_VerkeerdeMiddenScheiding(){
        user.setPersonalNationalNumber("98.02.08/306.37.38");
    }

    @Test
    public void setGeldigBirthPlace() {
        user.setBirthPlace("Gent");
        Assert.assertEquals("Gent", user.getBirthPlace());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigBirthPlace() {
        user.setBirthPlace("");
    }

    @Test
    public void setGeldigLandlineNumber() {
        user.setLandlineNumber("+3292381250");
        Assert.assertEquals("+3292381250", user.getLandlineNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigLandlineNumber_Tekort() {
        user.setPhoneNumber("09795");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigLandlineNumber_TeLang() {
        user.setPhoneNumber("097953599904795359999");
    }

    @Test
    public void setGeldigMailParent() {
        user.setMailParent("biekevandecaveye@hotmail.com");
        Assert.assertEquals("biekevandecaveye@hotmail.com", user.getMailParent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigMailParent_ZonderApenstaart(){
        user.setMailParent("stefverlinde+hotmail.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigMailParent_ZonderPunt(){
        user.setMailParent("stefverlinde@hotmail/com");
    }

    @Test
    public void setDiscriminator() {
        user.setDiscriminator("Member");
        Assert.assertEquals("Member", user.getDiscriminator());
    }

    @Test
    public void setRank() {
        Integer rank = 3;
        user.setRank(rank);
        Assert.assertEquals(rank, user.getRank());
    }


    @Test
    public void setScore() {
        user.setScore(5);
        Assert.assertEquals(5, user.getScore());
    }


    @Test
    public void setSessionCollection() {
        Collection<Session> sessionCollection = new ArrayList();
        sessionCollection.add(new Session());
        sessionCollection.add(new Session());
        user.setSessionCollection(sessionCollection);
        Assert.assertEquals(sessionCollection.size(), user.getSessionCollection().size());
    }

    @Test
    public void setCommentCollection() {
        Collection<Comment> commentCollection = new ArrayList<>();
        commentCollection.add(new Comment());
        commentCollection.add(new Comment());
        user.setCommentCollection(commentCollection);
        Assert.assertEquals(commentCollection.size(), user.getCommentCollection().size());
    }

    @Test
    public void setCommentCollection1() {
        Collection<Comment> commentCollection = new ArrayList<>();
        commentCollection.add(new Comment());
        commentCollection.add(new Comment());
        user.setCommentCollection1(commentCollection);
        Assert.assertEquals(commentCollection.size(), user.getCommentCollection1().size());
    }

    @Test
    public void setGeldigCityPostalcode() {
        City newCity = new City("9000", "Gent");
        user.setCityPostalcode(newCity);
        Assert.assertEquals(newCity, user.getCityPostalcode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigCityPostalcode_cityIsNull(){
        City newCity = new City();
        user.setCityPostalcode(newCity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setOngeldigCityPostalcode_PostalCodeTooShort(){
        City newCity = new City("900", "Gent");
        user.setCityPostalcode(newCity);
    }

    @Test
    public void setFormulaId() {
        Formula newFormula = new Formula();
        user.setFormulaId(newFormula);
        Assert.assertEquals(newFormula, user.getFormulaId());
    }


    @Test
    public void setSessionId() {
        Session newSession = new Session();
        user.setSessionId(newSession);
        Assert.assertEquals(newSession, user.getSessionId());
    }


    @Test
    public void setSessionId1() {
        Session newSession = new Session();
        user.setSessionId1(newSession);
        Assert.assertEquals(newSession, user.getSessionId1());
    }


    @Test
    public void setSessionCollection1() {
        Collection<Session> sessionCollection = new ArrayList();
        sessionCollection.add(new Session());
        sessionCollection.add(new Session());
        user.setSessionCollection1(sessionCollection);
        Assert.assertEquals(sessionCollection.size(), user.getSessionCollection1().size());
    }
}