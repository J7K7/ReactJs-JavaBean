package entity;

import entity.Retailerproduct;
import entity.Temporder;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-19T21:29:23")
@StaticMetamodel(Temporderdetail.class)
public class Temporderdetail_ { 

    public static volatile SingularAttribute<Temporderdetail, Temporder> tempOrderId;
    public static volatile SingularAttribute<Temporderdetail, Short> quantity;
    public static volatile SingularAttribute<Temporderdetail, Float> price;
    public static volatile SingularAttribute<Temporderdetail, Integer> id;
    public static volatile SingularAttribute<Temporderdetail, Retailerproduct> retailerProductId;

}