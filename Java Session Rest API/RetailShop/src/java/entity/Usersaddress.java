/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Abhay
 */
@Entity
@Table(name = "usersaddress")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usersaddress.findAll", query = "SELECT u FROM Usersaddress u"),
    @NamedQuery(name = "Usersaddress.findById", query = "SELECT u FROM Usersaddress u WHERE u.id = :id"),
    @NamedQuery(name = "Usersaddress.findByBuilding", query = "SELECT u FROM Usersaddress u WHERE u.building = :building"),
    @NamedQuery(name = "Usersaddress.findByStreet", query = "SELECT u FROM Usersaddress u WHERE u.street = :street"),
    @NamedQuery(name = "Usersaddress.findByPincode", query = "SELECT u FROM Usersaddress u WHERE u.pincode = :pincode"),
    @NamedQuery(name = "Usersaddress.findByCity", query = "SELECT u FROM Usersaddress u WHERE u.city = :city"),
    @NamedQuery(name = "Usersaddress.findByState", query = "SELECT u FROM Usersaddress u WHERE u.state = :state"),
    @NamedQuery(name = "Usersaddress.findByCountry", query = "SELECT u FROM Usersaddress u WHERE u.country = :country"),
    @NamedQuery(name = "Usersaddress.findByIsActive", query = "SELECT u FROM Usersaddress u WHERE u.isActive = :isActive")})
public class Usersaddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Building")
    private String building;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Street")
    private String street;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "Pincode")
    private String pincode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "City")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "State")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsActive")
    private boolean isActive;
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private UsersMaster userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userAddressId")
    private Collection<Orders> ordersCollection;

    public Usersaddress() {
    }

    public Usersaddress(Short id) {
        this.id = id;
    }

    public Usersaddress(Short id, String building, String street, String pincode, String city, String state, String country, boolean isActive) {
        this.id = id;
        this.building = building;
        this.street = street;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
        this.country = country;
        this.isActive = isActive;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public UsersMaster getUserId() {
        return userId;
    }

    public void setUserId(UsersMaster userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usersaddress)) {
            return false;
        }
        Usersaddress other = (Usersaddress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Usersaddress[ id=" + id + " ]";
    }
    
}
