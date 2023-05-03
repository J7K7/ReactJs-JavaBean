package entity;

import entity.Retailerproduct;
import entity.UsersMaster;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-19T21:29:23")
@StaticMetamodel(Wishlist.class)
public class Wishlist_ { 

    public static volatile SingularAttribute<Wishlist, Integer> id;
    public static volatile SingularAttribute<Wishlist, UsersMaster> userId;
    public static volatile SingularAttribute<Wishlist, Retailerproduct> retailerProductId;

}