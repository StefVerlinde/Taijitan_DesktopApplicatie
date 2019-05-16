package domain;

import javax.persistence.*;
import java.io.Serializable;

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
    private String type;

    @Column(name= "Name")
    private  int name;

    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    private User user;

    public Score(){

    }

}
