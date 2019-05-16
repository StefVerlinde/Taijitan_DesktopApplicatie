package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name ="Score")
public class Score implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ScoreId")
    private  int scoreId;

    @Column(name= "Amount")
    private int amount;

    @Column(name= "Type")
    private int type;

    @Column(name= "Name")
    private String name;

    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    private User user;

    public Score(){
    }

    public Score(int scoreId){
        this.scoreId = scoreId;
    }

    public Score(String name, int type, int amount) {
        //this.activityId = activityId;
        setName(name);
        setType(type);
        setAmount(amount);
    }

    public void setAmount(int score) {
        if(score <1)
            throw new IllegalArgumentException("Gelieve een score in te geven die groter is dan 0");
        this.amount = score;
    }

    public int getAmount(){
        return this.amount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return scoreId == score.scoreId &&
                type == score.type &&
                Objects.equals(name, score.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreId, name, type);
    }

    @Override
    public String toString() {
        return String.format("%s",this.name);
    }
}
