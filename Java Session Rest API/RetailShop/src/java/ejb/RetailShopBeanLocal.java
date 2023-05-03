/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Category;
import entity.Coupon;
import entity.Orderdetail;
import entity.Orders;
import entity.Product;
import entity.Productimage;
import entity.Retailerproduct;
import entity.Temporder;
import entity.Temporderdetail;
import entity.UsersMaster;
import entity.Usersaddress;
import entity.Wishlist;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author HP
 */
@Local
public interface RetailShopBeanLocal {
    
    public void addUserByGroup(String Username,String Email,String Password,String FirstName,String LastName,String Image,String Mobile,String group);
    public UsersMaster getUserByName(String username);
    
    
    // <editor-fold defaultstate="collapsed" desc="Category">

    
    public void Category_Insert(String Name, boolean IsActive, short parentId);
    public void Category_Update(short Id, String Name, boolean IsActive, short parentId);
    public void Category_Update_IsActive(short Id, boolean IsActive);
    public void Category_Delete(short Id);
    public Category Category_Select_ById(short Id);
    public Collection<Category> Category_SelectAll();
    public Collection<Category> Category_SelectAll_Active();
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Coupon">
    
    
    public void Coupon_Insert(String Name, float Discount, boolean IsFlat,
            Date ExpiryDate, boolean IsActive);
    public void Coupon_Update(int Id, String Name, float Discount,
            boolean IsFlat, Date ExpiryDate, boolean IsActive);
    public void Coupon_Update_IsActive(int Id, boolean IsActive);
    public void Coupon_Delete(int Id);
    public Coupon Coupon_Select_ById(int Id);
    public Collection<Coupon> Coupon_SelectAll();
    public Collection<Coupon> Coupon_SelectAll_Active();
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Group">
    
    
    public void Group_Insert(String Username, String Groupname);
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Order Detail">
    
    
    public void OrderDetail_Insert(short Quantity, float Price, short OrderId, short retailerProductId);
    public void OrderDetail_Update(int Id, short Quantity, float Price, short OrderId, short retailerProductId);
    public void OrderDetail_Update_Price(int Id, float Price);
    public void OrderDetail_Update_Quantity(int Id, short Quantity);
    public void OrderDetail_Delete(int Id);
    public void OrderDetail_DeleteAll_ByOrderId(int OrderId);
    public Orderdetail OrderDetail_Select_ById(int Id);
    public Collection<Orderdetail> OrderDetail_SelectAll();
    public Collection<Orderdetail> OrderDetail_SelectAll_ByOrderId(short OrderId);
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Orders">
    
    
    public int Orders_Insert(float TotalAmount, short OrderStatus, short PaymentType,
            Date TransDate, int CouponId, short UserAddressId, short UserId);
    public void Orders_Update(int Id, float TotalAmount, short OrderStatus, short PaymentType,
            Date TransDate, int CouponId, short UserAddressId, short UserId);
    public void Orders_Update_OrderStatus(int Id, short OrderStatus);
    public void Orders_Update_PaymentType(int Id, short PaymentType);
    public void Orders_Delete(int Id);
    public void Orders_DeleteAll_ByUserId(short UserId);
    public Orders Orders_Select_ById(int Id);
    public Collection<Orders> Orders_SelectAll();
    public Collection<Orders> Orders_SelectAll_ByUserId(short UserId);
    public Collection<Orders> Orders_SelectAll_ByPaymentType(short PaymentType);
    public Collection<Orders> Orders_SelectAll_ByOrderStatus(short OrderStatus);
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Product">
    
    
    public void Product_Insert(String Name, String Description, boolean IsActive, short CategoryId);
    public void Product_Update(short Id, String Name, String Description,
            boolean IsActive, short CategoryId);
    public void Product_Update_IsActive(short Id, boolean IsActive);
    public void Product_Delete(short Id);
    public Product Product_Select_ById(short Id);
    public Collection<Product> Product_SelectAll();
    public Collection<Product> Product_SelectAll_Active();
    public Collection<Product> Product_SelectAll_ByCategoryId(short CategoryId);
    public Collection<Product> Product_SelectAll_ByCategoryId_Active(short CategoryId);
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Product Image">
    
    
    public void ProductImage_Insert(String Name, short ProductId);
    public void ProductImage_Delete(int Id);
    public Collection<Productimage> ProductImage_SelectAll();
    public Collection<Productimage> ProductImage_SelectAll_ByProductId(short ProductId);
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Retailer Product">
    
    
    public void RetailerProduct_Insert(float Price, short Quantity, boolean IsActive,
            short RetailerId, short ProductId);
    public void RetailerProduct_Update(int Id, float Price, short Quantity,
            boolean IsActive, short RetailerId, short ProductId);
    public void RetailerProduct_Update_IsActive(int Id, boolean IsActive);
    public void RetailerProduct_UpdateAll_IsActive_ByRetailerId(short RetailerId, boolean IsActive);
    public void RetailerProduct_UpdateAll_IsActive_ByProductId(short ProductId, boolean IsActive);
    public void RetailerProduct_Delete(int Id);
    public void RetailerProduct_DeleteAll_ByRetailerId(short RetailerId);
    public void RetailerProduct_DeleteAll_ByProductId(short ProductId);
    public Retailerproduct RetailerProduct_Select_ById(int Id);
    public Collection<Retailerproduct> RetailerProduct_SelectAll();
    public Collection<Retailerproduct> RetailerProduct_SelectAll_ByProductId(short ProductId);
    public Collection<Retailerproduct> RetailerProduct_SelectAll_ByRetailerId(short RetailerId);
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Temp Order">
    
    
    public void TempOrder_Insert(Date TransDate, boolean IsOrder, short UserId);
    public void TempOrder_Update(int Id, Date TransDate, boolean IsOrder, short UserId);
    public void TempOrder_Update_IsOrder(int Id, boolean IsOrder);
    public void TempOrder_Delete(int Id);
    public void TempOrder_DeleteAll_ByUserId(short UserId);
    public Temporder TempOrder_Select_ById(short Id);
    public Collection<Temporder> TempOrder_SelectAll();
    public Collection<Temporder> TempOrder_SelectAll_ByUserId(short UserId);
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Temp Order Detail">
    
    
    public void TempOrderDetail_Insert(short Quantity, float Price, int RetailerProductId, int TempOrderId);
    public void TempOrderDetail_Update_Quantity(int Id, short Quantity);
    public void TempOrderDetail_Delete(int Id);
    public void TempOrderDetail_DeleteAll_ByTempOrderId(int TempOrderId);
    public Temporderdetail TempOrderDetail_Select_ById(int Id);
    public Collection<Temporderdetail> TempOrderDetail_SelectAll();
    public Collection<Temporderdetail> TempOrderDetail_SelectAll_ByUserId(int TempOrderId);
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Users Address">
    
    
    public void UsersAddress_Insert(String Building, String Street, String Pincode, String City,
            String State, String Country, boolean IsActive, short UserId);
    public void UsersAddress_Update(short Id, String Building, String Street, String Pincode,
            String City, String State, String Country, boolean IsActive, short UserId);
    public void UsersAddress_Update_IsActive(short Id, boolean IsActive);
    public void UsersAddress_Delete(short Id);
    public Usersaddress UsersAddress_Select_ById(short Id);
    public Collection<Usersaddress> UsersAddress_SelectAll_ByUserId(short UserId);
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Users">

    
    public UsersMaster Users_Insert(String Username, String EmailId, String Password,
            String FirstName, String LastName, String Image, String MobileNo, boolean IsActive);
    public void Users_Update(short Id, String EmailId, String FirstName, 
            String LastName, String Image, String MobileNo);
    public void Users_Update_IsActive(short Id, boolean IsActive);
    public void Users_Update_Password(short Id, String Password);
    public void Users_Delete(short Id);
    public UsersMaster Users_Select_ById(short Id);
    public Collection<UsersMaster> Users_SelectAll_Users();
    public Collection<UsersMaster> Users_SelectAll_Retailer();
    
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Wishlist">    
    
    
    public void Wishlist_Insert(short UserId, short RetailerProductId);
    public void Wishlist_Update(int Id, short UserId, short RetailerProductId);
    public void Wishlist_Delete(int Id);
    public void Wishlist_DeleteAll_ByUserId(short UserId);
    public Wishlist Wishlist_Select_ById(short Id);
    public Collection<Wishlist> Wishlist_SelectAll();
    public Collection<Wishlist> Wishlist_SelectAll_ByUserId(short UserId);
    
    
    // </editor-fold>
    
    
}
