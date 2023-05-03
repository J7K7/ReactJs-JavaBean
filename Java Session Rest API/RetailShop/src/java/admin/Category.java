/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package admin;

import client.RestClient;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import rest.RetailShopClient;

/**
 *
 * @author Abhay
 */
@Named(value = "category")
@Dependent
public class Category {

    private short Id;
    private String Name;
    private short ParentCategoryId;
    private Collection<Category> categorys;
    RetailShopClient client = new RetailShopClient();

    public short getId() {
        return Id;
    }

    public void setId(short Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public short getParentCategoryId() {
        return ParentCategoryId;
    }

    public void setParentCategoryId(short ParentCategoryId) {
        this.ParentCategoryId = ParentCategoryId;
    }

    public Collection<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(Collection<Category> categorys) {
        this.categorys = categorys;
    }
    
    
    
    public Category() {
    }
    
}
