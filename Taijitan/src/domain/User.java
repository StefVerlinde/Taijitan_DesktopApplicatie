/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javafx.beans.property.SimpleStringProperty;
import repository.UserDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * @author tijsm
 */
@Entity
@Table(name = "users")
@NamedQueries({
//        @NamedQuery(name = "User.findByName", query = "SELECT u FROM user u WHERE u.name = :userName")
})
public class User implements Serializable {

    //region DBProperties
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UserId")
    private Integer userId;

    @Column(name = "Name")
    private String name;

    @Column(name = "FirstName")
    private String firstName;

    @Basic(optional = false)
    @Column(name = "DateOfBirth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Column(name = "Street")
    private String street;

    @Basic(optional = false)
    @Column(name = "Country")
    private int country;

    @Column(name = "HouseNumber")
    private String houseNumber;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Email")
    private String email;

    @Basic(optional = false)
    @Column(name = "DateRegistred")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistred;

    @Basic(optional = false)
    @Column(name = "Gender")
    private int gender;

    @Basic(optional = false)
    @Column(name = "Nationality")
    private int nationality;

    @Column(name = "PersonalNationalNumber")
    private String personalNationalNumber;

    @Column(name = "BirthPlace")
    private String birthPlace;

    @Column(name = "LandlineNumber")
    private String landlineNumber;

    @Column(name = "MailParent")
    private String mailParent;

    @Basic(optional = false)
    @Column(name = "Discriminator")
    private String discriminator;

    @Column(name = "Rank")
    private Integer rank;

    @ManyToMany(mappedBy = "userCollection")
    private Collection<Session> sessionCollection;

    @OneToMany(mappedBy = "memberUserId")
    private Collection<Comment> commentCollection;

    @OneToMany(mappedBy = "userId")
    private Collection<Comment> commentCollection1;

    @JoinColumn(name = "CityPostalcode", referencedColumnName = "Postalcode")
    @ManyToOne(optional = false)
    private City cityPostalcode;

    @JoinColumn(name = "FormulaId", referencedColumnName = "FormulaId")
    @ManyToOne
    private Formula formulaId;

    @JoinColumn(name = "SessionId", referencedColumnName = "SessionId")
    @ManyToOne
    private Session sessionId;

    @JoinColumn(name = "SessionId1", referencedColumnName = "SessionId")
    @ManyToOne
    private Session sessionId1;

    @OneToMany(mappedBy = "teacherUserId")
    private Collection<Session> sessionCollection1;
    //endregion


//    private final SimpleStringProperty familyNameProperty = new SimpleStringProperty();
//    private final SimpleStringProperty firstNameProperty = new SimpleStringProperty();
//    private final SimpleStringProperty dateOfBirthProperty = new SimpleStringProperty();
//    private final SimpleStringProperty telephoneProperty = new SimpleStringProperty();
//    private final SimpleStringProperty emailProperty = new SimpleStringProperty();

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(Integer userId, Date dateOfBirth, int country, Date dateRegistred, int gender, int nationality, String discriminator) {
        this.userId = userId;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.dateRegistred = dateRegistred;
        this.gender = gender;
        this.nationality = nationality;
        this.discriminator = discriminator;
    }

    public Integer getUserId() {
        return userId;
    }


    //region Getters and Setters
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
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
    //endregion

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return firstName + " " + name;
    }

//    public SimpleStringProperty  familyNameProperty(){
//        return this.familyNameProperty;
//    }
//    public SimpleStringProperty  firstNameProperty(){
//        return this.firstNameProperty;
//    }
//    public SimpleStringProperty  dateOfBirthProperty(){
//        return this.dateOfBirthProperty;
//    }
//    public SimpleStringProperty  telephoneProperty(){
//        return this.telephoneProperty;
//    }
//    public SimpleStringProperty  emailProperty(){
//        return this.emailProperty;
//    }




}
