/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Abhay
 */
@Entity
@Table(name = "temporder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Temporder.findAll", query = "SELECT t FROM Temporder t"),
    @NamedQuery(name = "Temporder.findById", query = "SELECT t FROM Temporder t WHERE t.id = :id"),
    @NamedQuery(name = "Temporder.findByTransDate", query = "SELECT t FROM Temporder t WHERE t.transDate = :transDate"),
    @NamedQuery(name = "Temporder.findByIsOrder", query = "SELECT t FROM Temporder t WHERE t.isOrder = :isOrder")})
public class Temporder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TransDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsOrder")
    private boolean isOrder;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tempOrderId")
    private Collection<Temporderdetail> temporderdetailCollection;
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private UsersMaster userId;

    public Temporder() {
    }

    public Temporder(Integer id) {
        this.id = id;
    }

    public Temporder(Integer id, Date transDate, boolean isOrder) {
        this.id = id;
        this.transDate = transDate;
        this.isOrder = isOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public boolean getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(boolean isOrder) {
        this.isOrder = isOrder;
    }

    @XmlTransient
    public Collection<Temporderdetail> getTemporderdetailCollection() {
        return temporderdetailCollection;
    }

    public void setTemporderdetailCollection(Collection<Temporderdetail> temporderdetailCollection) {
        this.temporderdetailCollection = temporderdetailCollection;
    }

    public UsersMaster getUserId() {
        return userId;
    }

    public void setUserId(UsersMaster userId) {
        this.userId = userId;
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
        if (!(object instanceof Temporder)) {
            return false;
        }
        Temporder other = (Temporder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Temporder[ id=" + id + " ]";
    }
    
}
