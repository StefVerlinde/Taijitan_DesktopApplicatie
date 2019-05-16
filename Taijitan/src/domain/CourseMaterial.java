/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dto.CourseMaterialDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author tijsm
 */
@Entity
@Table(name = "courseMaterial")
@NamedQueries({
        @NamedQuery(name = "CourseMaterial.findLast", query = "SELECT c FROM CourseMaterial c ORDER BY c.materialId DESC")
        }
)
public class CourseMaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaterialId")
    private Integer materialId;
    @Column(name = "YoutubeURL")
    private String youtubeURL;
    @Column(name = "FullDescription")
    private String fullDescription;
    @Basic(optional = false)
    @Column(name = "Rank")
    private int rank;
    @Column(name = "Title")
    private String title;
    @OneToMany(mappedBy = "courseMaterialId")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "courseMaterialMaterialId")
    private Collection<Image> imageCollection;

    public CourseMaterial() {
    }

    public CourseMaterial(Integer materialId) {
        this.materialId = materialId;
    }

    public CourseMaterial(CourseMaterialDTO cm) {
        setMaterialId(cm.getMaterialId());
        setFullDescription(cm.getFullDescription());
        setRank(cm.getRank());
        setTitle(cm.getTitle());
        setYoutubeURL(cm.getYoutubeURL());
        setCommentCollection(cm.getCommentCollection());
    }

    public CourseMaterial(Integer materialId, int rank) {
        this.materialId = materialId;
        this.rank = rank;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        if(fullDescription.trim().isEmpty())
            throw new IllegalArgumentException("Gelieve een beschrijving in te vullen");
        this.fullDescription = fullDescription;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title.trim().isEmpty())
            throw new IllegalArgumentException("Gelieve een titel in te vullen");
        this.title = title;
    }

    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public Collection<Image> getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection) {
        if(imageCollection == null || imageCollection.isEmpty())
            throw new IllegalArgumentException("Gelieve foto's te selecteren");
        this.imageCollection = imageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialId != null ? materialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CourseMaterial)) {
            return false;
        }
        CourseMaterial other = (CourseMaterial) object;
        if ((this.materialId == null && other.materialId != null) || (this.materialId != null && !this.materialId.equals(other.materialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s - %s",Rank.getById(getRank()),getTitle());
    }

}
