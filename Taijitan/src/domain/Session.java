/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;


/**
 *
 * @author tijsm
 */
@Entity
@Table(name = "session")
//@NamedQueries({
  //  @NamedQuery(name = "Session.findAll", query = "SELECT s FROM Session s")})
public class Session implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SessionId")
    private Integer sessionId;
    @Basic(optional = false)
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "SessionStarted")
    private boolean sessionStarted;
    @JoinTable(name = "SessionMember", joinColumns = {
        @JoinColumn(name = "SessionId", referencedColumnName = "SessionId")}, inverseJoinColumns = {
        @JoinColumn(name = "MemberId", referencedColumnName = "UserId")})
    @ManyToMany
    private Collection<User> userCollection;
    @ManyToMany(mappedBy = "sessionCollection")
    private Collection<Formula> formulaCollection;
    @OneToMany(mappedBy = "sessionId")
    private Collection<Formula> formulaCollection1;
    @OneToMany(mappedBy = "sessionId")
    private Collection<User> userCollection1;
    @OneToMany(mappedBy = "sessionId1")
    private Collection<User> userCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessionId")
    private Collection<NonMember> nonMemberCollection;
    @JoinColumn(name = "FormulaId", referencedColumnName = "FormulaId")
    @ManyToOne
    private Formula formulaId;
    @JoinColumn(name = "TrainingDayId", referencedColumnName = "TrainingDayId")
    @ManyToOne
    private TrainingDay trainingDayId;
    @JoinColumn(name = "TeacherUserId", referencedColumnName = "UserId")
    @ManyToOne
    private User teacherUserId;

    public Session() {
    }

    public Session(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Session(Integer sessionId, Date date, boolean sessionStarted) {
        this.sessionId = sessionId;
        this.date = date;
        this.sessionStarted = sessionStarted;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getSessionStarted() {
        return sessionStarted;
    }

    public void setSessionStarted(boolean sessionStarted) {
        this.sessionStarted = sessionStarted;
    }

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public Collection<Formula> getFormulaCollection() {
        return formulaCollection;
    }

    public void setFormulaCollection(Collection<Formula> formulaCollection) {
        this.formulaCollection = formulaCollection;
    }

    public Collection<Formula> getFormulaCollection1() {
        return formulaCollection1;
    }

    public void setFormulaCollection1(Collection<Formula> formulaCollection1) {
        this.formulaCollection1 = formulaCollection1;
    }

    public Collection<User> getUserCollection1() {
        return userCollection1;
    }

    public void setUserCollection1(Collection<User> userCollection1) {
        this.userCollection1 = userCollection1;
    }

    public Collection<User> getUserCollection2() {
        return userCollection2;
    }

    public void setUserCollection2(Collection<User> userCollection2) {
        this.userCollection2 = userCollection2;
    }

    public Collection<NonMember> getNonMemberCollection() {
        return nonMemberCollection;
    }

    public void setNonMemberCollection(Collection<NonMember> nonMemberCollection) {
        this.nonMemberCollection = nonMemberCollection;
    }

    public Formula getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(Formula formulaId) {
        this.formulaId = formulaId;
    }

    public TrainingDay getTrainingDayId() {
        return trainingDayId;
    }

    public void setTrainingDayId(TrainingDay trainingDayId) {
        this.trainingDayId = trainingDayId;
    }

    public User getTeacherUserId() {
        return teacherUserId;
    }

    public void setTeacherUserId(User teacherUserId) {
        this.teacherUserId = teacherUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sessionId != null ? sessionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Session)) {
            return false;
        }
        Session other = (Session) object;
        if ((this.sessionId == null && other.sessionId != null) || (this.sessionId != null && !this.sessionId.equals(other.sessionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println(newstring); // 2011-01-18
        return String.format("%s", dateFormatter(getDate()));
    }


    private String dateFormatter(Date date){
        int minutes = date.getMinutes();
        int hours = date.getHours();
        int days = date.getDay();
        int months = date.getMonth();
        int years = date.getYear();

        String formatted = String.format("%d/%d/%d  --  %d:%d", years, months, days, hours, minutes);

        return formatted;
    }
    
}
