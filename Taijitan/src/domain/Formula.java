/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author tijsm
 */
@Entity
@Table(name = "formula")
@NamedQueries({
        @NamedQuery(name = "Formula.findAll", query = "SELECT f FROM Formula f")})
public class Formula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FormulaId")
    private Integer formulaId;
    @Column(name = "Name")
    private String name;
    @JoinTable(name = "SessionFormula", joinColumns = {
            @JoinColumn(name = "FormulaId", referencedColumnName = "FormulaId")}, inverseJoinColumns = {
            @JoinColumn(name = "SessionId", referencedColumnName = "SessionId")})
    @ManyToMany
    private Collection<Session> sessionCollection;
    @JoinTable(name = "FormulaTrainingDay", joinColumns = {
            @JoinColumn(name = "FormulaId", referencedColumnName = "FormulaId")}, inverseJoinColumns = {
            @JoinColumn(name = "TrainingsDayId", referencedColumnName = "TrainingDayId")})
    @ManyToMany
    private Collection<TrainingDay> trainingDayCollection;
    @JoinColumn(name = "SessionId", referencedColumnName = "SessionId")
    @ManyToOne
    private Session sessionId;
    @OneToMany(mappedBy = "formulaId")
    private Collection<TrainingDay> trainingDayCollection1;
    @OneToMany(mappedBy = "formulaId")
    private Collection<User> userCollection;
    @OneToMany(mappedBy = "formulaId")
    private Collection<Session> sessionCollection1;

    public Formula() {
    }

    public Formula(Integer formulaId) {
        this.formulaId = formulaId;
    }

    public Integer getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(Integer formulaId) {
        this.formulaId = formulaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Session> getSessionCollection() {
        return sessionCollection;
    }

    public void setSessionCollection(Collection<Session> sessionCollection) {
        this.sessionCollection = sessionCollection;
    }

    public Collection<TrainingDay> getTrainingDayCollection() {
        return trainingDayCollection;
    }

    public void setTrainingDayCollection(Collection<TrainingDay> trainingDayCollection) {
        this.trainingDayCollection = trainingDayCollection;
    }

    public Session getSessionId() {
        return sessionId;
    }

    public void setSessionId(Session sessionId) {
        this.sessionId = sessionId;
    }

    public Collection<TrainingDay> getTrainingDayCollection1() {
        return trainingDayCollection1;
    }

    public void setTrainingDayCollection1(Collection<TrainingDay> trainingDayCollection1) {
        this.trainingDayCollection1 = trainingDayCollection1;
    }

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public Collection<Session> getSessionCollection1() {
        return sessionCollection1;
    }

    public void setSessionCollection1(Collection<Session> sessionCollection1) {
        this.sessionCollection1 = sessionCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formulaId != null ? formulaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Formula)) {
            return false;
        }
        Formula other = (Formula) object;
        if ((this.formulaId == null && other.formulaId != null) || (this.formulaId != null && !this.formulaId.equals(other.formulaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Formula[ formulaId=" + formulaId + " ]";
    }

}
