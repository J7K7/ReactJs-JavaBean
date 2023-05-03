package entity;

import entity.Temporderdetail;
import entity.UsersMaster;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-19T21:29:23")
@StaticMetamodel(Temporder.class)
public class Temporder_ { 

    public static volatile SingularAttribute<Temporder, Date> transDate;
    public static volatile SingularAttribute<Temporder, Integer> id;
    public static volatile CollectionAttribute<Temporder, Temporderdetail> temporderdetailCollection;
    public static volatile SingularAttribute<Temporder, UsersMaster> userId;
    public static volatile SingularAttribute<Temporder, Boolean> isOrder;

}