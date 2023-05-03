package entity;

import entity.Coupon;
import entity.Orderdetail;
import entity.UsersMaster;
import entity.Usersaddress;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-19T21:29:23")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Float> totalAmount;
    public static volatile SingularAttribute<Orders, Usersaddress> userAddressId;
    public static volatile SingularAttribute<Orders, Date> transDate;
    public static volatile CollectionAttribute<Orders, Orderdetail> orderdetailCollection;
    public static volatile SingularAttribute<Orders, Short> orderStatus;
    public static volatile SingularAttribute<Orders, Integer> id;
    public static volatile SingularAttribute<Orders, Coupon> couponId;
    public static volatile SingularAttribute<Orders, UsersMaster> userId;
    public static volatile SingularAttribute<Orders, Short> paymentType;

}