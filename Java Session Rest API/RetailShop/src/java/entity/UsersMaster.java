/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "users_master")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersMaster.findAll", query = "SELECT u FROM UsersMaster u"),
    @NamedQuery(name = "UsersMaster.findById", query = "SELECT u FROM UsersMaster u WHERE u.id = :id"),
    @NamedQuery(name = "UsersMaster.findByUsername", query = "SELECT u FROM UsersMaster u WHERE u.username = :username"),
    @NamedQuery(name = "UsersMaster.findByEmailId", query = "SELECT u FROM UsersMaster u WHERE u.emailId = :emailId"),
    @NamedQuery(name = "UsersMaster.findByPassword", query = "SELECT u FROM UsersMaster u WHERE u.password = :password"),
    @NamedQuery(name = "UsersMaster.findByFirstName", query = "SELECT u FROM UsersMaster u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "UsersMaster.findByLastName", query = "SELECT u FROM UsersMaster u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "UsersMaster.findByImage", query = "SELECT u FROM UsersMaster u WHERE u.image = :image"),
    @NamedQuery(name = "UsersMaster.findByMobileNo", query = "SELECT u FROM UsersMaster u WHERE u.mobileNo = :mobileNo"),
    @NamedQuery(name = "UsersMaster.findByIsActive", query = "SELECT u FROM UsersMaster u WHERE u.isActive = :isActive"),
    @NamedQuery(name = "UsersMaster.findAllUsers", query = "SELECT u FROM GroupMaster g INNER JOIN g.usersMaster u WHERE g.groupMasterPK.groupname = 'User'"),
    @NamedQuery(name = "UsersMaster.findAllRetailer", query = "SELECT u FROM GroupMaster g INNER JOIN g.usersMaster u WHERE g.groupMasterPK.groupname = 'Retailer'")
})
public class UsersMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private short id;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "EmailId")
    private String emailId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "Password")
    private String password;
    @Size(max = 16)
    @Column(name = "FirstName")
    private String firstName;
    @Size(max = 16)
    @Column(name = "LastName")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "Image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "MobileNo")
    private String mobileNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsActive")
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Wishlist> wishlistCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Temporder> temporderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersMaster")
    private Collection<GroupMaster> groupMasterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Usersaddress> usersaddressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Orders> ordersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "retailerId")
    private Collection<Retailerproduct> retailerproductCollection;

    public UsersMaster() {
    }

    public UsersMaster(String username) {
        this.username = username;
    }

    public UsersMaster(String username, short id, String emailId, String password, String image, String mobileNo, boolean isActive) {
        this.username = username;
        this.id = id;
        this.emailId = emailId;
        this.password = password;
        this.image = image;
        this.mobileNo = mobileNo;
        this.isActive = isActive;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Wishlist> getWishlistCollection() {
        return wishlistCollection;
    }

    public void setWishlistCollection(Collection<Wishlist> wishlistCollection) {
        this.wishlistCollection = wishlistCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Temporder> getTemporderCollection() {
        return temporderCollection;
    }

    public void setTemporderCollection(Collection<Temporder> temporderCollection) {
        this.temporderCollection = temporderCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<GroupMaster> getGroupMasterCollection() {
        return groupMasterCollection;
    }

    public void setGroupMasterCollection(Collection<GroupMaster> groupMasterCollection) {
        this.groupMasterCollection = groupMasterCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Usersaddress> getUsersaddressCollection() {
        return usersaddressCollection;
    }

    public void setUsersaddressCollection(Collection<Usersaddress> usersaddressCollection) {
        this.usersaddressCollection = usersaddressCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Retailerproduct> getRetailerproductCollection() {
        return retailerproductCollection;
    }

    public void setRetailerproductCollection(Collection<Retailerproduct> retailerproductCollection) {
        this.retailerproductCollection = retailerproductCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersMaster)) {
            return false;
        }
        UsersMaster other = (UsersMaster) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UsersMaster[ username=" + username + " ]";
    }
    
}
