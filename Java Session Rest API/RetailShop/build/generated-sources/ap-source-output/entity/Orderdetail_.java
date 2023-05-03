package entity;

import entity.Orders;
import entity.Retailerproduct;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-19T21:29:23")
@StaticMetamodel(Orderdetail.class)
public class Orderdetail_ { 

    public static volatile SingularAttribute<Orderdetail, Short> quantity;
    public static volatile SingularAttribute<Orderdetail, Orders> orderId;
    public static volatile SingularAttribute<Orderdetail, Float> price;
    public static volatile SingularAttribute<Orderdetail, Integer> id;
    public static volatile SingularAttribute<Orderdetail, Retailerproduct> retailerProductId;

}