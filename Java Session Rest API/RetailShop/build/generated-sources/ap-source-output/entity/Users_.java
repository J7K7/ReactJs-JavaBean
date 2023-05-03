package entity;

import entity.Groups;
import entity.Orders;
import entity.RetailerProduct;
import entity.TempOrder;
import entity.UsersAddress;
import entity.Wishlist;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-05-20T14:59:34")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> lastName;
    public static volatile SingularAttribute<Users, String> image;
    public static volatile CollectionAttribute<Users, UsersAddress> usersAddressCollection;
    public static volatile CollectionAttribute<Users, Wishlist> wishlistCollection;
    public static volatile SingularAttribute<Users, String> emailId;
    public static volatile SingularAttribute<Users, String> mobileNo;
    public static volatile CollectionAttribute<Users, Orders> ordersCollection;
    public static volatile SingularAttribute<Users, Boolean> isActive;
    public static volatile SingularAttribute<Users, String> firstName;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile CollectionAttribute<Users, RetailerProduct> retailerProductCollection;
    public static volatile SingularAttribute<Users, Short> id;
    public static volatile CollectionAttribute<Users, Groups> groupsCollection;
    public static volatile CollectionAttribute<Users, TempOrder> tempOrderCollection;
    public static volatile SingularAttribute<Users, String> username;

}