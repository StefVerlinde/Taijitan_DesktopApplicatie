/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author tijsm
 */
@Entity
@Table(name = "image")
@NamedQueries({
        @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i")})
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ImageId")
    private Integer imageId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "CourseMaterialMaterialId", referencedColumnName = "MaterialId")
    @ManyToOne
    private CourseMaterial courseMaterialMaterialId;

    public Image() {
    }

    public Image(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CourseMaterial getCourseMaterialMaterialId() {
        return courseMaterialMaterialId;
    }

    public void setCourseMaterialMaterialId(CourseMaterial courseMaterialMaterialId) {
        this.courseMaterialMaterialId = courseMaterialMaterialId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imageId != null ? imageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.imageId == null && other.imageId != null) || (this.imageId != null && !this.imageId.equals(other.imageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Image[ imageId=" + imageId + " ]";
    }

}
