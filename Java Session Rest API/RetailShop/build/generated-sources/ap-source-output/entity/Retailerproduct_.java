package entity;

import entity.Orderdetail;
import entity.Product;
import entity.Temporderdetail;
import entity.UsersMaster;
import entity.Wishlist;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-19T21:29:23")
@StaticMetamodel(Retailerproduct.class)
public class Retailerproduct_ { 

    public static volatile SingularAttribute<Retailerproduct, Short> quantity;
    public static volatile SingularAttribute<Retailerproduct, Product> productId;
    public static volatile SingularAttribute<Retailerproduct, Float> price;
    public static volatile CollectionAttribute<Retailerproduct, Orderdetail> orderdetailCollection;
    public static volatile CollectionAttribute<Retailerproduct, Wishlist> wishlistCollection;
    public static volatile SingularAttribute<Retailerproduct, Integer> id;
    public static volatile SingularAttribute<Retailerproduct, Boolean> isActive;
    public static volatile CollectionAttribute<Retailerproduct, Temporderdetail> temporderdetailCollection;
    public static volatile SingularAttribute<Retailerproduct, UsersMaster> retailerId;

}