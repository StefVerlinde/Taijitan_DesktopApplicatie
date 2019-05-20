package domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="ActivityMember")
@Table(name="ActivityMember")

public class ActivityMember implements Serializable {
    @Id
    @Column(name = "ActivityId")
    private Integer activityId;

    @Id
    @Column(name = "MemberId")
    private Integer memberId;

}
