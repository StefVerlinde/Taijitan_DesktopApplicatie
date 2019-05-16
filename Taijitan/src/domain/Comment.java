/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author tijsm
 */
@Entity
@Table(name = "comment")
@NamedQueries({
        @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CommentId")
    private Integer commentId;
    @Column(name = "Content")
    private String content;
    @Basic(optional = false)
    @Column(name = "DateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @Column(name = "IsRead")
    private boolean isRead;
    @JoinColumn(name = "CourseMaterialId", referencedColumnName = "MaterialId")
    @ManyToOne
    private CourseMaterial courseMaterialId;
    @JoinColumn(name = "MemberUserId", referencedColumnName = "UserId")
    @ManyToOne
    private User memberUserId;


    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    private User userId;

    public Comment() {
    }

    public Comment(Integer commentId) {
        this.commentId = commentId;
    }

    public Comment(Integer commentId, Date dateCreated, boolean isRead) {
        this.commentId = commentId;
        this.dateCreated = dateCreated;
        this.isRead = isRead;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(content == null ||content.trim().isEmpty())
            throw new IllegalArgumentException("Ongeldige conent");
        this.content = content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        if(dateCreated == null)
            throw new IllegalArgumentException("Ongeldige datum");
        this.dateCreated = dateCreated;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public CourseMaterial getCourseMaterialId() {
        return courseMaterialId;
    }

    public void setCourseMaterialId(CourseMaterial courseMaterialId) {
        this.courseMaterialId = courseMaterialId;
    }

    public User getMemberUserId() {
        return memberUserId;
    }

    public void setMemberUserId(User memberUserId) {
        this.memberUserId = memberUserId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentId != null ? commentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commentId == null && other.commentId != null) || (this.commentId != null && !this.commentId.equals(other.commentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Comment[ commentId=" + commentId + " ]";
    }

}
