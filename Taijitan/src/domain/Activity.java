/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dto.ActivityDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.management.monitor.StringMonitor;
import javax.persistence.*;

@Entity
@Table(name = "Activity")
@NamedQueries({
        @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"),
        @NamedQuery(name = "Activity.findByActivityId", query = "SELECT a FROM Activity a WHERE a.activityId = :activityId"),
        @NamedQuery(name = "Activity.findByName", query = "SELECT a FROM Activity a WHERE a.name = :name"),
        @NamedQuery(name = "Activity.findByType", query = "SELECT a FROM Activity a WHERE a.type = :type"),
        @NamedQuery(name = "Activity.findByStartDate", query = "SELECT a FROM Activity a WHERE a.startDate = :startDate"),
        @NamedQuery(name = "Activity.findByEndDate", query = "SELECT a FROM Activity a WHERE a.endDate = :endDate"),
        @NamedQuery(name = "Activity.findByIsFull", query = "SELECT a FROM Activity a WHERE a.isFull = :isFull")})
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ActivityId")
    private int activityId;
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Type")
    private int type;
    @Basic(optional = false)
    @Column(name = "StartDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "EndDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "score")
    private int score;
    @Column(name = "MaxParticpants")
    private int maxParticpants;


    @Basic(optional = false)
    @Column(name = "IsFull")
    private boolean isFull;
    @JoinTable(name = "ActivityMember", joinColumns = {
            @JoinColumn(name = "ActivityId", referencedColumnName = "ActivityId")}, inverseJoinColumns = {
            @JoinColumn(name = "MemberId", referencedColumnName = "UserId")})
    @ManyToMany
    private List<User> users;

    public Activity() {
    }

    public Activity(int activityId) {
        this.activityId = activityId;
    }

    public Activity(String name, int type, Date startDate, Date endDate, List<User> users, int score, int maxp) {
        //this.activityId = activityId;
        setName(name);
        setType(type);
        setStartDate(startDate);
        setEndDate(endDate);
        setUsers(users);
        setScore(score);
        setMaxParticpants(maxp);
    }

    public Activity(ActivityDTO a)
    {
        setName(a.getName());
        setType(a.getType());
        setStartDate(a.getStartDate());
        setEndDate(a.getEndDate());
        setUsers(a.getUsers());
        setScore(a.getScore());
        setMaxParticpants(a.getMaxParticpants());
    }

    public void setScore(int score) {
        if(score <1)
            throw new IllegalArgumentException("Gelieve een score in te geven die groter is dan 0");
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }

    public void setMaxParticpants(int maxp) {
        if(maxp < 1)
            throw new IllegalArgumentException("Je kunt geen activiteit toevoegen met minder dan 1 plek");
        this.maxParticpants = maxp;
    }

    public int getMaxParticpants(){
        return this.maxParticpants;
    }


    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.trim().isEmpty())
            throw new IllegalArgumentException("Naam is verplicht");
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        if(startDate == null)
            throw new IllegalArgumentException("startdatum is verplicht!");
        /*else if(startDate.before(new Date()))
            throw new IllegalArgumentException("Een activiteit moet in de toekomst beginnen!");*/
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        if(endDate == null)
            throw new IllegalArgumentException("einddatum is verplicht!");
        /*else if(endDate.before(new Date()))
            throw new IllegalArgumentException("Een activiteit moet in de toekomst eindigen!");*/
        else if(endDate.before(startDate))
            throw new IllegalArgumentException("Een activiteit kan niet voor de startdatum eindigen!");
        this.endDate = endDate;
    }

    public boolean getIsFull() {
        return isFull;
    }

    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setUserCollection1(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return activityId == activity.activityId &&
                type == activity.type &&
                isFull == activity.isFull &&
                Objects.equals(name, activity.name) &&
                Objects.equals(startDate, activity.startDate) &&
                Objects.equals(endDate, activity.endDate) &&
                Objects.equals(users, activity.users) ;
//                Objects.equals(userCollection1, activity.userCollection1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, name, type, startDate, endDate, isFull, users);
    }

    @Override
    public String toString() {
        return String.format("%s",this.name);
    }

}
