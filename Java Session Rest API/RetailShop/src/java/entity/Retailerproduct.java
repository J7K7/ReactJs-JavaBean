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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Abhay
 */
@Entity
@Table(name = "retailerproduct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Retailerproduct.findAll", query = "SELECT r FROM Retailerproduct r"),
    @NamedQuery(name = "Retailerproduct.findById", query = "SELECT r FROM Retailerproduct r WHERE r.id = :id"),
    @NamedQuery(name = "Retailerproduct.findByPrice", query = "SELECT r FROM Retailerproduct r WHERE r.price = :price"),
    @NamedQuery(name = "Retailerproduct.findByQuantity", query = "SELECT r FROM Retailerproduct r WHERE r.quantity = :quantity"),
    @NamedQuery(name = "Retailerproduct.findByIsActive", query = "SELECT r FROM Retailerproduct r WHERE r.isActive = :isActive")})
public class Retailerproduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private float price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity")
    private short quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsActive")
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "retailerProductId")
    private Collection<Temporderdetail> temporderdetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "retailerProductId")
    private Collection<Wishlist> wishlistCollection;
    @JoinColumn(name = "ProductId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "RetailerId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private UsersMaster retailerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "retailerProductId")
    private Collection<Orderdetail> orderdetailCollection;

    public Retailerproduct() {
    }

    public Retailerproduct(Integer id) {
        this.id = id;
    }

    public Retailerproduct(Integer id, float price, short quantity, boolean isActive) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public Collection<Temporderdetail> getTemporderdetailCollection() {
        return temporderdetailCollection;
    }

    public void setTemporderdetailCollection(Collection<Temporderdetail> temporderdetailCollection) {
        this.temporderdetailCollection = temporderdetailCollection;
    }

    @XmlTransient
    public Collection<Wishlist> getWishlistCollection() {
        return wishlistCollection;
    }

    public void setWishlistCollection(Collection<Wishlist> wishlistCollection) {
        this.wishlistCollection = wishlistCollection;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public UsersMaster getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(UsersMaster retailerId) {
        this.retailerId = retailerId;
    }

    @XmlTransient
    public Collection<Orderdetail> getOrderdetailCollection() {
        return orderdetailCollection;
    }

    public void setOrderdetailCollection(Collection<Orderdetail> orderdetailCollection) {
        this.orderdetailCollection = orderdetailCollection;
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
        if (!(object instanceof Retailerproduct)) {
            return false;
        }
        Retailerproduct other = (Retailerproduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Retailerproduct[ id=" + id + " ]";
    }
    
}
