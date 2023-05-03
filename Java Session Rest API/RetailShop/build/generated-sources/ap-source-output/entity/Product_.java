package entity;

import entity.Category;
import entity.Productimage;
import entity.Retailerproduct;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-19T21:29:24")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile CollectionAttribute<Product, Productimage> productimageCollection;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Short> id;
    public static volatile SingularAttribute<Product, Boolean> isActive;
    public static volatile CollectionAttribute<Product, Retailerproduct> retailerproductCollection;
    public static volatile SingularAttribute<Product, Category> categoryId;

}