package entity;

import entity.Orders;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-19T21:29:23")
@StaticMetamodel(Coupon.class)
public class Coupon_ { 

    public static volatile SingularAttribute<Coupon, Date> expiryDate;
    public static volatile SingularAttribute<Coupon, Boolean> isFlat;
    public static volatile SingularAttribute<Coupon, String> name;
    public static volatile SingularAttribute<Coupon, Float> discount;
    public static volatile SingularAttribute<Coupon, Integer> id;
    public static volatile CollectionAttribute<Coupon, Orders> ordersCollection;
    public static volatile SingularAttribute<Coupon, Boolean> isActive;

}