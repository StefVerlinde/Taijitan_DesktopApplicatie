/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    @Basic(optional = false)
    @Column(name = "ActivityId")
    private Integer activityId;
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
    private Collection<User> userCollection;
    @OneToMany(mappedBy = "activityId")
    private Collection<User> userCollection1;

    public Activity() {
    }

    public Activity(Integer activityId) {
        this.activityId = activityId;
    }

    public Activity(Integer activityId, int type, Date startDate, Date endDate, boolean isFull) {
        this.activityId = activityId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.endDate = endDate;
        this.isFull = isFull;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
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

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public Collection<User> getUserCollection1() {
        return userCollection1;
    }

    public void setUserCollection1(Collection<User> userCollection1) {
        this.userCollection1 = userCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (activityId != null ? activityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.activityId == null && other.activityId != null) || (this.activityId != null && !this.activityId.equals(other.activityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Activity[ activityId=" + activityId + " ]";
    }

}
