package entity;

import entity.Orders;
import entity.UsersMaster;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-19T21:29:24")
@StaticMetamodel(Usersaddress.class)
public class Usersaddress_ { 

    public static volatile SingularAttribute<Usersaddress, String> pincode;
    public static volatile SingularAttribute<Usersaddress, String> country;
    public static volatile SingularAttribute<Usersaddress, String> city;
    public static volatile SingularAttribute<Usersaddress, String> street;
    public static volatile SingularAttribute<Usersaddress, Short> id;
    public static volatile SingularAttribute<Usersaddress, String> state;
    public static volatile CollectionAttribute<Usersaddress, Orders> ordersCollection;
    public static volatile SingularAttribute<Usersaddress, Boolean> isActive;
    public static volatile SingularAttribute<Usersaddress, UsersMaster> userId;
    public static volatile SingularAttribute<Usersaddress, String> building;

}