package domain;

import dto.ScoreDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name ="Score")
@NamedQueries({
        @NamedQuery(name = "Score.findByUserIdActivityName", query = "SELECT s FROM Score s WHERE s.name=:Name and s.user =:MemberId")
})
public class Score implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ScoreId")
    private  int scoreId;

    @Column(name= "Amount")
    private int amount;

    @Column(name= "Type")
    private String type;

    @Column(name= "Name")
    private String name;

    @JoinColumn(name = "MemberId", referencedColumnName = "UserId")
    @ManyToOne
    private User user;

    public Score(){
    }

    public Score(int scoreId){
        this.scoreId = scoreId;
    }

    public Score(String name, String type, int amount, User user) {
        setName(name);
        setType(type);
        setAmount(amount);
        setUser(user);
    }

    public Score(ScoreDTO scdto) {
        setName(scdto.getName());
        setType(scdto.getType());
        setAmount(scdto.getAmount());
        setUser(scdto.getUser());
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
    public String getType() {
        return type;
    }

    public void setType(String type) {
        if( type == null || type.trim().isEmpty())
            throw new IllegalArgumentException("Type is verplicht");
        this.type = type;
    }

    public User getUser(){return user;}

    public void setUser(User user){
        if(user == null)
            throw new IllegalArgumentException("Gebruiker is verplicht");
        this.user = user;
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
