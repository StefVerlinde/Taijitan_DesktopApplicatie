package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sessionmember")
public class SessionMember {
    @Id
    @Column(name = "SessionId")
    private Integer activityId;
    @Id
    @Column(name = "MemberId")
    private Integer memberId;
}
