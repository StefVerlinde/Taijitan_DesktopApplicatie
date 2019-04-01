/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;


/*
 * @author tijsm
 */
@Entity
@Table(name = "TrainingDay")
@NamedQueries({
    @NamedQuery(name = "TrainingDay.findAll", query = "SELECT t FROM TrainingDay t")})
public class TrainingDay implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TrainingDayId")
    private Integer trainingDayId;
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "StartHour")
    private double startHour;
    @Basic(optional = false)
    @Column(name = "StopHour")
    private double stopHour;
    @Basic(optional = false)
    @Column(name = "Duration")
    private double duration;
    @Basic(optional = false)
    @Column(name = "DayOfWeek")
    private int dayOfWeek;
    @ManyToMany(mappedBy = "trainingDayCollection")
    private Collection<Formula> formulaCollection;
    @JoinColumn(name = "FormulaId", referencedColumnName = "FormulaId")
    @ManyToOne
    private Formula formulaId;
    @OneToMany(mappedBy = "trainingDayId")
    private Collection<Session> sessionCollection;

    public TrainingDay() {
    }

    public TrainingDay(Integer trainingDayId) {
        this.trainingDayId = trainingDayId;
    }

    public TrainingDay(Integer trainingDayId, double startHour, double stopHour, double duration, int dayOfWeek) {
        this.trainingDayId = trainingDayId;
        this.startHour = startHour;
        this.stopHour = stopHour;
        this.duration = duration;
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getTrainingDayId() {
        return trainingDayId;
    }

    public void setTrainingDayId(Integer trainingDayId) {
        this.trainingDayId = trainingDayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartHour() {
        return startHour;
    }

    public void setStartHour(double startHour) {
        this.startHour = startHour;
    }

    public double getStopHour() {
        return stopHour;
    }

    public void setStopHour(double stopHour) {
        this.stopHour = stopHour;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Collection<Formula> getFormulaCollection() {
        return formulaCollection;
    }

    public void setFormulaCollection(Collection<Formula> formulaCollection) {
        this.formulaCollection = formulaCollection;
    }

    public Formula getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(Formula formulaId) {
        this.formulaId = formulaId;
    }

    public Collection<Session> getSessionCollection() {
        return sessionCollection;
    }

    public void setSessionCollection(Collection<Session> sessionCollection) {
        this.sessionCollection = sessionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trainingDayId != null ? trainingDayId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrainingDay)) {
            return false;
        }
        TrainingDay other = (TrainingDay) object;
        if ((this.trainingDayId == null && other.trainingDayId != null) || (this.trainingDayId != null && !this.trainingDayId.equals(other.trainingDayId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.TrainingDay[ trainingDayId=" + trainingDayId + " ]";
    }
    
}
