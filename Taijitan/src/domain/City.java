/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author tijsm
 */
@Entity
@Table(name = "city")
@NamedQueries({
        @NamedQuery(name = "City.findAll", query = "SELECT c FROM City c")})
public class City implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Postalcode")
    private String postalcode;
    @Column(name = "Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityPostalcode")
    private Collection<User> userCollection;

    public City() {
    }

    public City(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postalcode != null ? postalcode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.postalcode == null && other.postalcode != null) || (this.postalcode != null && !this.postalcode.equals(other.postalcode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.City[ postalcode=" + postalcode + " ]";
    }

}
