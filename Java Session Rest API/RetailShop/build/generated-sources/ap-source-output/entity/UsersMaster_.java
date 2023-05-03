package entity;

import entity.GroupMaster;
import entity.Orders;
import entity.Retailerproduct;
import entity.Temporder;
import entity.Usersaddress;
import entity.Wishlist;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-19T21:29:23")
@StaticMetamodel(UsersMaster.class)
public class UsersMaster_ { 

    public static volatile CollectionAttribute<UsersMaster, Usersaddress> usersaddressCollection;
    public static volatile SingularAttribute<UsersMaster, String> lastName;
    public static volatile SingularAttribute<UsersMaster, String> image;
    public static volatile CollectionAttribute<UsersMaster, Wishlist> wishlistCollection;
    public static volatile CollectionAttribute<UsersMaster, Temporder> temporderCollection;
    public static volatile SingularAttribute<UsersMaster, String> emailId;
    public static volatile SingularAttribute<UsersMaster, String> mobileNo;
    public static volatile CollectionAttribute<UsersMaster, Orders> ordersCollection;
    public static volatile SingularAttribute<UsersMaster, Boolean> isActive;
    public static volatile CollectionAttribute<UsersMaster, GroupMaster> groupMasterCollection;
    public static volatile SingularAttribute<UsersMaster, String> firstName;
    public static volatile SingularAttribute<UsersMaster, String> password;
    public static volatile SingularAttribute<UsersMaster, Short> id;
    public static volatile CollectionAttribute<UsersMaster, Retailerproduct> retailerproductCollection;
    public static volatile SingularAttribute<UsersMaster, String> username;

}