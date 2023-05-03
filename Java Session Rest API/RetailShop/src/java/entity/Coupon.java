/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Abhay
 */
@Entity
@Table(name = "coupon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coupon.findAll", query = "SELECT c FROM Coupon c"),
    @NamedQuery(name = "Coupon.findById", query = "SELECT c FROM Coupon c WHERE c.id = :id"),
    @NamedQuery(name = "Coupon.findByName", query = "SELECT c FROM Coupon c WHERE c.name = :name"),
    @NamedQuery(name = "Coupon.findByDiscount", query = "SELECT c FROM Coupon c WHERE c.discount = :discount"),
    @NamedQuery(name = "Coupon.findByIsFlat", query = "SELECT c FROM Coupon c WHERE c.isFlat = :isFlat"),
    @NamedQuery(name = "Coupon.findByExpiryDate", query = "SELECT c FROM Coupon c WHERE c.expiryDate = :expiryDate"),
    @NamedQuery(name = "Coupon.findByIsActive", query = "SELECT c FROM Coupon c WHERE c.isActive = :isActive"),
    @NamedQuery(name = "Coupon.findByActive", query = "SELECT c FROM Coupon c WHERE c.isActive = true AND c.expiryDate > CURRENT_DATE")
})
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Discount")
    private float discount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsFlat")
    private boolean isFlat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ExpiryDate")
//    @Temporal(TemporalType.TIMESTAMP)
    @JsonbDateFormat(value = "yyyy-MM-dd hh:mm:ss")
    private Date expiryDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsActive")
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "couponId")
    private Collection<Orders> ordersCollection;

    public Coupon() {
    }

    public Coupon(Integer id) {
        this.id = id;
    }

    public Coupon(Integer id, String name, float discount, boolean isFlat, Date expiryDate, boolean isActive) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.isFlat = isFlat;
        this.expiryDate = expiryDate;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public boolean getIsFlat() {
        return isFlat;
    }

    public void setIsFlat(boolean isFlat) {
        this.isFlat = isFlat;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    @JsonbTransient
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
        if (!(object instanceof Coupon)) {
            return false;
        }
        Coupon other = (Coupon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Coupon[ id=" + id + " ]";
    }
    
}
