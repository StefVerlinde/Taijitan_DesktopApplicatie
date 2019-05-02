/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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

/**
 *
 * @author tijsm
 */
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

    @Basic(optional = false)
    @Column(name = "IsFull")
    private boolean isFull;
    @JoinTable(name = "ActivityMember", joinColumns = {
            @JoinColumn(name = "ActivityId", referencedColumnName = "ActivityId")}, inverseJoinColumns = {
            @JoinColumn(name = "MemberId", referencedColumnName = "UserId")})
    @ManyToMany
    private List<User> users;
//    @OneToMany(mappedBy = "activityId")
//    private Collection<User> userCollection1;

    public Activity() {
    }

    public Activity(int activityId) {
        this.activityId = activityId;
    }

    public Activity(String name, int type, Date startDate, Date endDate, List<User> users) {
        //this.activityId = activityId;
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.users = users;

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
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
