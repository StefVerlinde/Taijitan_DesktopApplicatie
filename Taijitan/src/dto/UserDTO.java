package dto;

import domain.City;
import domain.Comment;
import domain.Formula;
import domain.Session;

import java.util.Collection;
import java.util.Date;

public class UserDTO {
    private Integer userId;
    private String name;
    private String firstName;
    private Date dateOfBirth;
    private String street;
    private int country;
    private String houseNumber;
    private String phoneNumber;
    private String email;
    private Date dateRegistred;
    private int gender;
    private int nationality;
    private String personalNationalNumber;
    private String birthPlace;
    private String landlineNumber;
    private String mailParent;
    private String discriminator;
    private int rank;
    private int score;
    private Collection<Session> sessionCollection;
    private Collection<Comment> commentCollection;
    private Collection<Comment> commentCollection1;
    private City cityPostalcode;
    private Formula formulaId;
    private Session sessionId;
    private Session sessionId1;
    private Collection<Session> sessionCollection1;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateRegistred() {
        return dateRegistred;
    }

    public void setDateRegistred(Date dateRegistred) {
        this.dateRegistred = dateRegistred;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getNationality() {
        return nationality;
    }

    public void setNationality(int nationality) {
        this.nationality = nationality;
    }

    public String getPersonalNationalNumber() {
        return personalNationalNumber;
    }

    public void setPersonalNationalNumber(String personalNationalNumber) {
        this.personalNationalNumber = personalNationalNumber;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public String getMailParent() {
        return mailParent;
    }

    public void setMailParent(String mailParent) {
        this.mailParent = mailParent;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Collection<Session> getSessionCollection() {
        return sessionCollection;
    }

    public void setSessionCollection(Collection<Session> sessionCollection) {
        this.sessionCollection = sessionCollection;
    }

    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public Collection<Comment> getCommentCollection1() {
        return commentCollection1;
    }

    public void setCommentCollection1(Collection<Comment> commentCollection1) {
        this.commentCollection1 = commentCollection1;
    }

    public City getCityPostalcode() {
        return cityPostalcode;
    }

    public void setCityPostalcode(City cityPostalcode) {
        this.cityPostalcode = cityPostalcode;
    }

    public Formula getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(Formula formulaId) {
        this.formulaId = formulaId;
    }

    public Session getSessionId() {
        return sessionId;
    }

    public void setSessionId(Session sessionId) {
        this.sessionId = sessionId;
    }

    public Session getSessionId1() {
        return sessionId1;
    }

    public void setSessionId1(Session sessionId1) {
        this.sessionId1 = sessionId1;
    }

    public Collection<Session> getSessionCollection1() {
        return sessionCollection1;
    }

    public void setSessionCollection1(Collection<Session> sessionCollection1) {
        this.sessionCollection1 = sessionCollection1;
    }
}
