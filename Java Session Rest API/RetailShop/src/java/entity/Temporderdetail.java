/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abhay
 */
@Entity
@Table(name = "temporderdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Temporderdetail.findAll", query = "SELECT t FROM Temporderdetail t"),
    @NamedQuery(name = "Temporderdetail.findById", query = "SELECT t FROM Temporderdetail t WHERE t.id = :id"),
    @NamedQuery(name = "Temporderdetail.findByQuantity", query = "SELECT t FROM Temporderdetail t WHERE t.quantity = :quantity"),
    @NamedQuery(name = "Temporderdetail.findByPrice", query = "SELECT t FROM Temporderdetail t WHERE t.price = :price")})
public class Temporderdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity")
    private short quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private float price;
    @JoinColumn(name = "RetailerProductId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Retailerproduct retailerProductId;
    @JoinColumn(name = "TempOrderId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Temporder tempOrderId;

    public Temporderdetail() {
    }

    public Temporderdetail(Integer id) {
        this.id = id;
    }

    public Temporderdetail(Integer id, short quantity, float price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Retailerproduct getRetailerProductId() {
        return retailerProductId;
    }

    public void setRetailerProductId(Retailerproduct retailerProductId) {
        this.retailerProductId = retailerProductId;
    }

    public Temporder getTempOrderId() {
        return tempOrderId;
    }

    public void setTempOrderId(Temporder tempOrderId) {
        this.tempOrderId = tempOrderId;
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
        if (!(object instanceof Temporderdetail)) {
            return false;
        }
        Temporderdetail other = (Temporderdetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Temporderdetail[ id=" + id + " ]";
    }
    
}
