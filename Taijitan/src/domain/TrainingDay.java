/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


/*
 * @author tijsm
 */
@Entity
@Table(name = "trainingDay")
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

    public TrainingDay(Integer trainingDayId, double startHour, double stopHour,int dayOfWeek) {
        setTrainingDayId(trainingDayId);
        setStartHour(startHour);
        setStopHour(stopHour);
        setDuration(stopHour-startHour);
        setDayOfWeek(dayOfWeek);
    }

    public int getTrainingDayId() {
        return trainingDayId;
    }

    public void setTrainingDayId(int trainingDayId) {
        if(trainingDayId <0)
            throw new IllegalArgumentException("Ongeldige id");
        this.trainingDayId = trainingDayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Ongeldige naam");
        this.name = name;
    }

    public double getStartHour() {
        return startHour;
    }

    public void setStartHour(double startHour) {
        if(startHour < 0 || startHour > 24)
            throw new IllegalArgumentException("Ongeldig tijdstip");
        this.startHour = startHour;
    }

    public double getStopHour() {
        return stopHour;
    }

    public void setStopHour(double stopHour) {
        if(stopHour < 0 || stopHour > 24)
            throw new IllegalArgumentException("Ongeldig tijdstip");
        if(startHour >= stopHour)
            throw new IllegalArgumentException("stop uur kan niet voor start uur zijn");
        this.stopHour = stopHour;
    }

    public double getDuration() {
        return duration;
    }

    private void setDuration(double duration) {
        if(duration < 0)
            throw new IllegalArgumentException("Een duur kan niet negatief zijn");
        this.duration = duration;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        if(dayOfWeek < 0)
            throw  new IllegalArgumentException("Kan niet");
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
