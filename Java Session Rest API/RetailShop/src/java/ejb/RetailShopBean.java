package ejb;

import entity.Category;
import entity.Coupon;
import entity.GroupMaster;
import entity.GroupMasterPK;
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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import web.Servlet2;

/**
 *
 * @author HP
 */
@Stateless
public class RetailShopBean implements RetailShopBeanLocal {
    
    @PersistenceContext(unitName = "RetailShopPersistenceUnit")
    private EntityManager entityManager;
    private Pbkdf2PasswordHashImpl pb;
    
    // <editor-fold defaultstate="collapsed" desc="User">
    
    @Override
    public void addUserByGroup(String Username, String Email, String Password, String FirstName, String LastName, String Image, String Mobile,String group) {
        pb = new Pbkdf2PasswordHashImpl();
        String encryptedpassword = pb.generate(Password.toCharArray());
        
        UsersMaster um = new UsersMaster();
        um.setUsername(Username);
        um.setEmailId(Email);
        um.setPassword(encryptedpassword);
        um.setFirstName(FirstName);
        um.setLastName(LastName);
        um.setImage(Image);
        um.setMobileNo(Mobile);
        switch (group) {
            case "Admin":
                um.setIsActive(true);
                break;
            case "Customer":
                um.setIsActive(true);
                break;
            default:
                um.setIsActive(false);
                break;
        }
        
        
        entityManager.persist(um);
        
        try {
            sendMail(Email);
        } catch (MessagingException ex) {
            Logger.getLogger(RetailShopBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GroupMaster gm = new GroupMaster();
        gm.setGroupMasterPK(new GroupMasterPK(group,Username));
        gm.setUsersMaster(um);
        entityManager.persist(gm);
        System.out.println("!! Admin Inserted !!");
        
    }

    public void sendMail(String recipient) throws MessagingException {
        System.out.println("Enter Into SendMail !!");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.port", "587");

        String myAccount = "7vyas7jainish@gmail.com";
        String password = "jai2290800";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount, password);
            }
        });
        System.out.println("Enter Into PrepareMessage !!");
        Message message = prepareMessage(session, myAccount, recipient);
        Transport.send(message);
        System.out.println("Email Send Successfully !!");

    }

    private Message prepareMessage(Session session, String myAccount, String recipient) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("!! Registration Success !!");
            message.setText("Welcome To Retail Shop Management System !! \n You Have Success Fully Register... ");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(Servlet2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public UsersMaster getUserByName(String username) {
        UsersMaster user = null;
        try{
            user = entityManager.find(UsersMaster.class, username);
        return user;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
       return user;
    }
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Category">

    
    @Override
    public void Category_Insert(String Name, boolean IsActive, short parentId) {
        Category category = new Category();
        
        if(parentId > 0){
            Category parentCategory = entityManager.find(Category.class, parentId);
            category.setParentId(parentCategory);
        }
        
        category.setName(Name);
        category.setIsActive(IsActive);
        
        entityManager.persist(category);
    }

    
    @Override
    public void Category_Update(short Id, String Name, boolean IsActive, short parentId) {
        Category category = entityManager.find(Category.class, Id);

        if(parentId > 0){
            Category parentCategory = entityManager.find(Category.class, parentId);
            category.setParentId(parentCategory);
        }
        else {
            category.setParentId(null);
        }
        
        category.setName(Name);
        category.setIsActive(IsActive);
        
        entityManager.merge(category);
    }
    
    
    @Override
    public void Category_Update_IsActive(short Id, boolean IsActive) {
        Category category = entityManager.find(Category.class, Id);
        
        category.setIsActive(IsActive);
        
        entityManager.merge(category);
    }

    
    @Override
    public void Category_Delete(short Id) {
        Category category = entityManager.find(Category.class, Id);
        
        entityManager.remove(category);
    }

    
    @Override
    public Category Category_Select_ById(short Id) {
        return entityManager.find(Category.class, Id);
    }

    
    @Override
    public Collection<Category> Category_SelectAll() {
        return entityManager.createNamedQuery("Category.findAll").getResultList();
    }
    
    
    @Override
    public Collection<Category> Category_SelectAll_Active() {
        return entityManager.createNamedQuery("Category.findByActive").getResultList();
//        @NamedQuery(name = "Category.findByActive", query = "SELECT c FROM Category c WHERE c.isActive = true")
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Coupon">
    
    
    @Override
    public void Coupon_Insert(String Name, float Discount, boolean IsFlat,
            Date ExpiryDate, boolean IsActive) {
        Coupon coupon = new Coupon();
        
        coupon.setName(Name);
        coupon.setDiscount(Discount);
        coupon.setIsFlat(IsFlat);
        coupon.setExpiryDate(ExpiryDate);
        coupon.setIsActive(IsActive);
        
        entityManager.persist(coupon);
    }

    
    @Override
    public void Coupon_Update(int Id, String Name, float Discount,
            boolean IsFlat, Date ExpiryDate, boolean IsActive) {
        Coupon coupon = entityManager.find(Coupon.class, Id);
        
        coupon.setName(Name);
        coupon.setDiscount(Discount);
        coupon.setIsFlat(IsFlat);
        coupon.setExpiryDate(ExpiryDate);
        coupon.setIsActive(IsActive);
        
        entityManager.merge(coupon);
    }
    
    
    @Override
    public void Coupon_Update_IsActive(int Id, boolean IsActive) {
        Coupon coupon = entityManager.find(Coupon.class, Id);
        
        coupon.setIsActive(IsActive);
        
        entityManager.merge(coupon);
    }

    
    @Override
    public void Coupon_Delete(int Id) {
        Coupon coupon = entityManager.find(Coupon.class, Id);
        
        entityManager.remove(coupon);
    }

    
    @Override
    public Coupon Coupon_Select_ById(int Id) {
        return entityManager.find(Coupon.class, Id);
    }

    
    @Override
    public Collection<Coupon> Coupon_SelectAll() {
        return entityManager.createNamedQuery("Coupon.findAll").getResultList();
    }

    
    @Override
    public Collection<Coupon> Coupon_SelectAll_Active() {
        return entityManager.createNamedQuery("Coupon.findByActive").getResultList();
//        @NamedQuery(name = "Coupon.findByActive", query = "SELECT c FROM Coupon c WHERE c.isActive = true AND c.expiryDate > NOW()")
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Group">
    
    
    @Override
    public void Group_Insert(String Username, String Groupname) {
        GroupMaster group = new GroupMaster();
        
        UsersMaster users = entityManager.find(UsersMaster.class, Username);
        
        group.setGroupMasterPK(new GroupMasterPK(Groupname, users.getUsername()));
        group.setUsersMaster(users);
        
        entityManager.persist(group);
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="OrderDetail">
    
    
    @Override
    public void OrderDetail_Insert(short Quantity, float Price, short OrderId, short retailerProductId) {
        Orderdetail orderDetail = new Orderdetail();
        
        Orders orders = entityManager.find(Orders.class, OrderId);
        Retailerproduct retailerProduct = entityManager.find(Retailerproduct.class, retailerProductId);
        
        orderDetail.setQuantity(Quantity);
        orderDetail.setPrice(Price);
        orderDetail.setOrderId(orders);
        //orderDetail.setRetailerProductId(retailerProduct);
        
        entityManager.persist(orderDetail);
        
        Collection<Orderdetail> orderDetails = orders.getOrderdetailCollection();
        orderDetails.add(orderDetail);
        orders.setOrderdetailCollection(orderDetails);
        
        entityManager.merge(orders);
    }

    
    @Override
    public void OrderDetail_Update(int Id, short Quantity, float Price, short OrderId, short retailerProductId) {
        Orderdetail orderDetail = entityManager.find(Orderdetail.class, Id);
        
        Orders orders = entityManager.find(Orders.class, OrderId);
        Retailerproduct retailerProduct = entityManager.find(Retailerproduct.class, retailerProductId);
        
        orderDetail.setQuantity(Quantity);
        orderDetail.setPrice(Price);
        orderDetail.setOrderId(orders);
        //orderDetail.setRetailerproductId(retailerProduct);
        
        entityManager.merge(orderDetail);
        
        Collection<Orderdetail> orderDetails = orders.getOrderdetailCollection();
        orderDetails.add(orderDetail);
        orders.setOrderdetailCollection(orderDetails);
        
        entityManager.merge(orders);
    }

    
    @Override
    public void OrderDetail_Update_Price(int Id, float Price) {
        Orderdetail orderDetail = entityManager.find(Orderdetail.class, Id);
        
        Orders orders = orderDetail.getOrderId();
        
        orderDetail.setPrice(Price);
        
        entityManager.merge(orderDetail);
        
        Collection<Orderdetail> orderDetails = orders.getOrderdetailCollection();
        orderDetails.add(orderDetail);
        orders.setOrderdetailCollection(orderDetails);
        
        entityManager.merge(orders);
    }

    
    @Override
    public void OrderDetail_Update_Quantity(int Id, short Quantity) {
        Orderdetail orderDetail = entityManager.find(Orderdetail.class, Id);
        
        Orders orders = orderDetail.getOrderId();
        
        orderDetail.setQuantity(Quantity);
        
        entityManager.merge(orderDetail);
        
        Collection<Orderdetail> orderDetails = orders.getOrderdetailCollection();
        orderDetails.add(orderDetail);
        orders.setOrderdetailCollection(orderDetails);
        
        entityManager.merge(orders);
    }

    
    @Override
    public void OrderDetail_Delete(int Id) {
        Orderdetail orderDetail = entityManager.find(Orderdetail.class, Id);
        
        Orders orders = orderDetail.getOrderId();
        Collection<Orderdetail> orderDetails = orders.getOrderdetailCollection();
        orderDetails.remove(orderDetail);
        orders.setOrderdetailCollection(orderDetails);
        
        entityManager.remove(orderDetail);        
        entityManager.merge(orders);
    }
    
    
    @Override
    public void OrderDetail_DeleteAll_ByOrderId(int OrderId){
        Orders orders = entityManager.find(Orders.class, OrderId);
        
        Collection<Orderdetail> orderDetails = orders.getOrderdetailCollection();
        
        for (Orderdetail orderDetail : orderDetails) {
            orderDetails.remove(orderDetail);
            entityManager.remove(orderDetail);
        }
        
        orders.setOrderdetailCollection(orderDetails);
        
        entityManager.merge(orders);
    }

    
    @Override
    public Orderdetail OrderDetail_Select_ById(int Id) {
        return entityManager.find(Orderdetail.class, Id);
    }

    
    @Override
    public Collection<Orderdetail> OrderDetail_SelectAll() {
        return entityManager.createNamedQuery("Orderdetail.findAll").getResultList();
    }
    
    
    @Override
    public Collection<Orderdetail> OrderDetail_SelectAll_ByOrderId(short OrderId){
        Orders orders = entityManager.find(Orders.class, OrderId);
        
        entityManager.refresh(orders);
        
        return orders.getOrderdetailCollection();
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Orders">
    
    
    @Override
    public int Orders_Insert(float TotalAmount, short OrderStatus, short PaymentType,
            Date TransDate, int CouponId, short UserAddressId, short UserId) 
    {
        
        Orders order = new Orders();
        
        Coupon coupon = entityManager.find(Coupon.class, CouponId);
        UsersMaster usersMaster = entityManager.find(UsersMaster.class, UserId);
        Usersaddress usersAddress = entityManager.find(Usersaddress.class, UserAddressId);
        
        order.setTotalAmount(TotalAmount);
        order.setOrderStatus(OrderStatus);
        order.setPaymentType(PaymentType);
        order.setTransDate(TransDate);
        
        order.setCouponId(coupon);
        order.setUserId(usersMaster);
        order.setUserAddressId(usersAddress);
        
        entityManager.persist(order);
        
        Collection<Orders> orders = usersMaster.getOrdersCollection();
        orders.add(order);        
        usersMaster.setOrdersCollection(orders);
        
        entityManager.merge(usersMaster);
        
        return order.getId();
    }

    
    @Override
    public void Orders_Update(int Id, float TotalAmount, short OrderStatus, short PaymentType,
            Date TransDate, int CouponId, short UserAddressId, short UserId) {
        
        Orders order = entityManager.find(Orders.class, Id);
        
        Coupon coupon = entityManager.find(Coupon.class, CouponId);
        UsersMaster usersMaster = entityManager.find(UsersMaster.class, UserId);
        Usersaddress usersaddress = entityManager.find(Usersaddress.class, UserAddressId);
        
        order.setTotalAmount(TotalAmount);
        order.setOrderStatus(OrderStatus);
        order.setPaymentType(PaymentType);
        order.setTransDate(TransDate);
        
        order.setCouponId(coupon);
        order.setUserId(usersMaster);
        order.setUserAddressId(usersaddress);
        
        entityManager.merge(order);
        
        Collection<Orders> orders = usersMaster.getOrdersCollection();        
        orders.add(order);        
        usersMaster.setOrdersCollection(orders);
        
        entityManager.merge(usersMaster);
    }
    
    
    @Override
    public void Orders_Update_OrderStatus(int Id, short OrderStatus) {
        Orders order = entityManager.find(Orders.class, Id);
        
        UsersMaster usersMaster = order.getUserId();
        
        order.setOrderStatus(OrderStatus);
        
        entityManager.merge(order);
        
        Collection<Orders> orders = usersMaster.getOrdersCollection();
        orders.add(order);
        usersMaster.setOrdersCollection(orders);
        
        entityManager.merge(usersMaster);
    }
    
    
    @Override
    public void Orders_Update_PaymentType(int Id, short PaymentType) {
        Orders order = entityManager.find(Orders.class, Id);
        
        UsersMaster usersMaster = order.getUserId();
        
        order.setPaymentType(PaymentType);        
        
        entityManager.merge(order);
        
        Collection<Orders> orders = usersMaster.getOrdersCollection();        
        orders.add(order);        
        usersMaster.setOrdersCollection(orders);
        
        entityManager.merge(usersMaster);
    }

    
    @Override
    public void Orders_Delete(int Id) {
        Orders order = entityManager.find(Orders.class, Id);
        
        UsersMaster usersMaster = order.getUserId();        
        Collection<Orders> orders = usersMaster.getOrdersCollection();
        orders.remove(order);
        usersMaster.setOrdersCollection(orders);
        
        entityManager.remove(order);
        entityManager.merge(usersMaster);
    }
    
    
    @Override
    public void Orders_DeleteAll_ByUserId(short UserId) {
        UsersMaster usersMaster = entityManager.find(UsersMaster.class, UserId);
        
        Collection<Orders> orders = usersMaster.getOrdersCollection();
        
        for (Orders order : orders) {
            orders.remove(order);
            entityManager.remove(order);
        }
        
        usersMaster.setOrdersCollection(orders);
        
        entityManager.merge(usersMaster);
    }

    
    @Override
    public Orders Orders_Select_ById(int Id) {
        return entityManager.find(Orders.class, Id);
    }

    
    @Override
    public Collection<Orders> Orders_SelectAll() {
        return entityManager.createNamedQuery("Orders.findAll").getResultList();
    }
    
    
    @Override
    public Collection<Orders> Orders_SelectAll_ByUserId(short UserId) {
        UsersMaster usersMaster = entityManager.find(UsersMaster.class, UserId);
        
        entityManager.refresh(usersMaster);
        
        return usersMaster.getOrdersCollection();
    }
    
    
    @Override
    public Collection<Orders> Orders_SelectAll_ByPaymentType(short PaymentType) {
        return entityManager.createNamedQuery("Orders.findByPaymentType").setParameter("paymentType", PaymentType).getResultList();
    }

    
    @Override
    public Collection<Orders> Orders_SelectAll_ByOrderStatus(short OrderStatus) {
        return entityManager.createNamedQuery("Orders.findByOrderStatus").setParameter("orderStatus", OrderStatus).getResultList();
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Product">
    
    
    @Override
    public void Product_Insert(String Name, String Description, boolean IsActive, short CategoryId) {
        Product product = new Product();

        Category category = entityManager.find(Category.class, CategoryId);

        product.setName(Name);
        product.setDescription(Description);
        product.setIsActive(IsActive);
        product.setCategoryId(category);

        entityManager.persist(product);
        
        Collection<Product> products = category.getProductCollection();
        products.add(product);
        category.setProductCollection(products);
        
        entityManager.merge(category);
    }

    
    @Override
    public void Product_Update(short Id, String Name, String Description,
            boolean IsActive, short CategoryId) {
        
        Product product = entityManager.find(Product.class, Id);

        Category category = entityManager.find(Category.class, CategoryId);

        product.setName(Name);
        product.setDescription(Description);
        product.setIsActive(IsActive);
        product.setCategoryId(category);

        entityManager.merge(product);
        
        Collection<Product> products = category.getProductCollection();
        products.add(product);
        category.setProductCollection(products);
        
        entityManager.merge(category);        
    }
    
    
    @Override
    public void Product_Update_IsActive(short Id, boolean IsActive) {
        Product product = entityManager.find(Product.class, Id);
        
        Category category = product.getCategoryId();
        
        product.setIsActive(IsActive);
        
        entityManager.merge(product);
        
        Collection<Product> products = category.getProductCollection();
        products.add(product);
        category.setProductCollection(products);
        
        entityManager.merge(category);
    }

    
    @Override
    public void Product_Delete(short Id) {
        Product product = entityManager.find(Product.class, Id);
        
        Category category = product.getCategoryId();
        Collection<Product> products = category.getProductCollection();
        products.remove(product);
        category.setProductCollection(products);

        entityManager.remove(product);
        entityManager.merge(category);        
    }

    
    @Override
    public Product Product_Select_ById(short Id) {
        return entityManager.find(Product.class, Id);
    }

    
    @Override
    public Collection<Product> Product_SelectAll() {
        return entityManager.createNamedQuery("Product.findAll").getResultList();
    }
    
    
    @Override
    public Collection<Product> Product_SelectAll_ByCategoryId(short CategoryId) {
        Category category = entityManager.find(Category.class, CategoryId);
        
        entityManager.refresh(category);
        
        return category.getProductCollection();
    }

    
    @Override
    public Collection<Product> Product_SelectAll_Active() {
        return entityManager.createNamedQuery("Product.findByActive").getResultList();
//        @NamedQuery(name = "Product.findByActive", query = "SELECT p FROM Product p INNER JOIN p.categoryId c WHERE p.isActive = true AND c.isActive = true")
    }

    
    @Override
    public Collection<Product> Product_SelectAll_ByCategoryId_Active(short CategoryId) {
        Category category = entityManager.find(Category.class, CategoryId);
        return entityManager.createNamedQuery("Product.findByCategoryActive").setParameter("categoryId", category).getResultList();
//          @NamedQuery(name = "Product.findByCategoryActive", query = "SELECT p FROM Product p INNER JOIN p.categoryId c WHERE p.isActive = true AND c.isActive = true AND p.categoryId = :categoryId")
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="ProductImage">
    
    
    @Override
    public void ProductImage_Insert(String Name, short ProductId) {
        Productimage productImage = new Productimage();
        
        Product product = entityManager.find(Product.class, ProductId);
        
        productImage.setName(Name);
        productImage.setProductId(product);
        
        entityManager.persist(productImage);
        
        Collection<Productimage> productImages = product.getProductimageCollection();
        productImages.add(productImage);
        product.setProductimageCollection(productImages);
        
        entityManager.merge(product);
    }

    
    @Override
    public void ProductImage_Delete(int Id) {
        Productimage productImage = entityManager.find(Productimage.class, Id);
        
        Product product = productImage.getProductId();
        Collection<Productimage> productImages = product.getProductimageCollection();
        productImages.remove(productImage);
        product.setProductimageCollection(productImages);
        
        entityManager.remove(productImage);
        entityManager.merge(product);
    }

    
    @Override
    public Collection<Productimage> ProductImage_SelectAll() {
        return entityManager.createNamedQuery("Productimage.findAll").getResultList();
    }
    
    
    @Override
    public Collection<Productimage> ProductImage_SelectAll_ByProductId(short ProductId) {
        Product product = entityManager.find(Product.class, ProductId);
        
        entityManager.refresh(product);
        
        return product.getProductimageCollection();
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="RetailerProduct">
    
    
    @Override
    public void RetailerProduct_Insert(float Price, short Quantity, boolean IsActive,
            short RetailerId, short ProductId) {
        
        Retailerproduct retailerProduct = new Retailerproduct();

        UsersMaster users = entityManager.find(UsersMaster.class, RetailerId);
        Product product = entityManager.find(Product.class, ProductId);

        retailerProduct.setPrice(Price);
        retailerProduct.setQuantity(Quantity);
        retailerProduct.setIsActive(IsActive);
        retailerProduct.setRetailerId(users);
        retailerProduct.setProductId(product);

        entityManager.persist(retailerProduct);

        Collection<Retailerproduct> retailerProducts = users.getRetailerproductCollection();
        retailerProducts.add(retailerProduct);
        users.setRetailerproductCollection(retailerProducts);

        entityManager.merge(users);
    }

    
    @Override
    public void RetailerProduct_Update(int Id, float Price, short Quantity,
            boolean IsActive, short RetailerId, short ProductId) {
        
        Retailerproduct retailerProduct = entityManager.find(Retailerproduct.class, Id);

        UsersMaster users = entityManager.find(UsersMaster.class, RetailerId);
        Product product = entityManager.find(Product.class, ProductId);

        retailerProduct.setPrice(Price);
        retailerProduct.setQuantity(Quantity);
        retailerProduct.setIsActive(IsActive);
        retailerProduct.setRetailerId(users);
        retailerProduct.setProductId(product);
        
        entityManager.merge(retailerProduct);

        Collection<Retailerproduct> retailerProducts = users.getRetailerproductCollection();
        retailerProducts.add(retailerProduct);
        users.setRetailerproductCollection(retailerProducts);

        entityManager.merge(users);
    }
    
    
    @Override
    public void RetailerProduct_Update_IsActive(int Id, boolean IsActive){
        Retailerproduct retailerProduct =  entityManager.find(Retailerproduct.class, Id);
        
        retailerProduct.setIsActive(IsActive);
        
        entityManager.merge(retailerProduct);
        
        UsersMaster users = retailerProduct.getRetailerId();
        Collection<Retailerproduct> retailerProducts = users.getRetailerproductCollection();
        retailerProducts.add(retailerProduct);
        users.setRetailerproductCollection(retailerProducts);
        
        entityManager.merge(users);
    }
    
    
    @Override
    public void RetailerProduct_UpdateAll_IsActive_ByRetailerId(short RetailerId, boolean IsActive){
        UsersMaster users = entityManager.find(UsersMaster.class, RetailerId);
        
        Collection<Retailerproduct> retailerProducts = users.getRetailerproductCollection();
        
        for (Retailerproduct retailerProduct : retailerProducts) {
            retailerProduct.setIsActive(IsActive);
            entityManager.merge(retailerProduct);
            retailerProducts.add(retailerProduct);
        }
        
        users.setRetailerproductCollection(retailerProducts);
        
        entityManager.merge(users);
    }
    
    
    @Override
    public void RetailerProduct_UpdateAll_IsActive_ByProductId(short ProductId, boolean IsActive){
        Product product = entityManager.find(Product.class, ProductId);
        
        Collection<Retailerproduct> retailerProducts = product.getRetailerproductCollection();
        
        for (Retailerproduct retailerProduct : retailerProducts) {
            retailerProduct.setIsActive(IsActive);
            entityManager.merge(retailerProduct);
            retailerProducts.add(retailerProduct);
        }
        
        product.setRetailerproductCollection(retailerProducts);
        
        entityManager.merge(product);
    }

    
    @Override
    public void RetailerProduct_Delete(int Id) {
        Retailerproduct retailerProduct = entityManager.find(Retailerproduct.class, Id);
        
        UsersMaster usersMaster = retailerProduct.getRetailerId();
        
        Collection<Retailerproduct> retailerProducts = usersMaster.getRetailerproductCollection();
        retailerProducts.remove(retailerProduct);
        usersMaster.setRetailerproductCollection(retailerProducts);
        
        entityManager.remove(retailerProduct);
        entityManager.merge(usersMaster);
    }
    
    
    @Override
    public void RetailerProduct_DeleteAll_ByRetailerId(short RetailerId){
        UsersMaster usersMaster = entityManager.find(UsersMaster.class, RetailerId);
        
        Collection<Retailerproduct> retailerProducts = usersMaster.getRetailerproductCollection();
        
        for (Retailerproduct retailerProduct : retailerProducts) {
            retailerProducts.remove(retailerProduct);
            entityManager.remove(retailerProducts);
        }
        
        usersMaster.setRetailerproductCollection(retailerProducts);
        
        entityManager.merge(usersMaster);
    }
    
    
    @Override
    public void RetailerProduct_DeleteAll_ByProductId(short ProductId){
        Product product = entityManager.find(Product.class, ProductId);
        
        Collection<Retailerproduct> retailerProducts = product.getRetailerproductCollection();
        
        for (Retailerproduct retailerProduct : retailerProducts) {
            retailerProducts.remove(retailerProduct);
            entityManager.remove(retailerProduct);
        }
        
        product.setRetailerproductCollection(retailerProducts);
        
        entityManager.merge(product);
    }

    
    @Override
    public Retailerproduct RetailerProduct_Select_ById(int Id) {
        return entityManager.find(Retailerproduct.class, Id);
    }

    
    @Override
    public Collection<Retailerproduct> RetailerProduct_SelectAll() {
        return entityManager.createNamedQuery("Retailerproduct.findAll").getResultList();
    }
    
    
    @Override
    public Collection<Retailerproduct> RetailerProduct_SelectAll_ByProductId(short ProductId){
        Product product = entityManager.find(Product.class, ProductId);
        
        entityManager.refresh(product);
        
        return product.getRetailerproductCollection();
    }
    
    
    @Override
    public Collection<Retailerproduct> RetailerProduct_SelectAll_ByRetailerId(short RetailerId){
        UsersMaster usersMaster = entityManager.find(UsersMaster.class, RetailerId);
        
        entityManager.refresh(usersMaster);
        
        return usersMaster.getRetailerproductCollection();
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Temp Order">
    
    
    @Override
    public void TempOrder_Insert(Date TransDate, boolean IsOrder, short UserId) {
        Temporder temporder = new Temporder();
        
        UsersMaster usersMaster = entityManager.find(UsersMaster.class, UserId);

        temporder.setUserId(usersMaster);
        temporder.setTransDate(TransDate);
        temporder.setIsOrder(IsOrder);
        
        entityManager.persist(temporder);
        
        Collection<Temporder> temporders = usersMaster.getTemporderCollection();
        temporders.add(temporder);
        usersMaster.setTemporderCollection(temporders);
        
        entityManager.merge(usersMaster);
    }

    
    @Override
    public void TempOrder_Update(int Id, Date TransDate, boolean IsOrder, short UserId) {
        Temporder temporder = entityManager.find(Temporder.class, Id);

        UsersMaster usersMaster = entityManager.find(UsersMaster.class, UserId);

        temporder.setUserId(usersMaster);
        temporder.setTransDate(TransDate);
        temporder.setIsOrder(IsOrder);
        
        entityManager.merge(temporder);
        
        Collection<Temporder> temporders = usersMaster.getTemporderCollection();
        temporders.add(temporder);
        usersMaster.setTemporderCollection(temporders);
        
        entityManager.merge(usersMaster);
    }

    
    @Override
    public void TempOrder_Update_IsOrder(int Id, boolean IsOrder) {
        Temporder temporder = entityManager.find(Temporder.class, Id);

        UsersMaster usersMaster = temporder.getUserId();
        
        temporder.setIsOrder(IsOrder);
        
        entityManager.merge(temporder);
        
        Collection<Temporder> temporders = usersMaster.getTemporderCollection();
        temporders.add(temporder);
        usersMaster.setTemporderCollection(temporders);
        
        entityManager.merge(usersMaster);
    }

    
    @Override
    public void TempOrder_Delete(int Id) {
        Temporder temporder = entityManager.find(Temporder.class, Id);

        UsersMaster usersMaster = temporder.getUserId();
        
        Collection<Temporder> temporders = usersMaster.getTemporderCollection();
        temporders.remove(temporder);
        usersMaster.setTemporderCollection(temporders);
        
        entityManager.remove(temporder);
        entityManager.merge(usersMaster);
    }
    
    
    @Override
    public void TempOrder_DeleteAll_ByUserId(short UserId){
        UsersMaster usersMaster = entityManager.find(UsersMaster.class, UserId);
        
        Collection<Temporder> temporders = usersMaster.getTemporderCollection();
        
        for (Temporder temporder : temporders) {
            temporders.remove(temporder);
            entityManager.remove(temporder);
        }
        
        usersMaster.setTemporderCollection(temporders);
        
        entityManager.merge(usersMaster);
    }

    
    @Override
    public Temporder TempOrder_Select_ById(short Id) {
        return entityManager.find(Temporder.class, Id);
    }

    
    @Override
    public Collection<Temporder> TempOrder_SelectAll() {
        return entityManager.createNamedQuery("Temporder.findAll").getResultList();
    }
    
    
    @Override
    public Collection<Temporder> TempOrder_SelectAll_ByUserId(short UserId){
        UsersMaster usersMaster = entityManager.find(UsersMaster.class, UserId);
        
        entityManager.refresh(usersMaster);
        
        return usersMaster.getTemporderCollection();
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Temp Order Detail">
    
    
    @Override
    public void TempOrderDetail_Insert(short Quantity, float Price, int RetailerProductId, int TempOrderId) {
        Temporderdetail temporderdetail = new Temporderdetail();
        
        Temporder temporder = entityManager.find(Temporder.class, TempOrderId);
        Retailerproduct retailerproduct = entityManager.find(Retailerproduct.class, RetailerProductId);
        
        temporderdetail.setPrice(Price);
        temporderdetail.setQuantity(Quantity);
        temporderdetail.setTempOrderId(temporder);
        temporderdetail.setRetailerProductId(retailerproduct);        
        
        entityManager.persist(temporderdetail);
        
        Collection<Temporderdetail> temporderdetails = temporder.getTemporderdetailCollection();
        temporderdetails.add(temporderdetail);
        temporder.setTemporderdetailCollection(temporderdetails);
        
        entityManager.merge(temporder);
    }

    
    @Override
    public void TempOrderDetail_Update_Quantity(int Id, short Quantity) {
        Temporderdetail temporderdetail = entityManager.find(Temporderdetail.class, Id);
        
        temporderdetail.setQuantity(Quantity);
        
        entityManager.merge(temporderdetail);
        
        Temporder temporder = temporderdetail.getTempOrderId();        
        Collection<Temporderdetail> temporderdetails = temporder.getTemporderdetailCollection();
        temporderdetails.add(temporderdetail);
        temporder.setTemporderdetailCollection(temporderdetails);
        
        entityManager.merge(temporder);
    }

    
    @Override
    public void TempOrderDetail_Delete(int Id) {
        Temporderdetail temporderdetail = entityManager.find(Temporderdetail.class, Id);
        
        Temporder temporder = temporderdetail.getTempOrderId();        
        Collection<Temporderdetail> temporderdetails = temporder.getTemporderdetailCollection();
        temporderdetails.remove(temporderdetail);
        temporder.setTemporderdetailCollection(temporderdetails);
        
        entityManager.remove(temporderdetail);
        entityManager.merge(temporder);
    }
    
    
    @Override
    public void TempOrderDetail_DeleteAll_ByTempOrderId(int TempOrderId){
        Temporder temporder = entityManager.find(Temporder.class, TempOrderId);
        
        Collection<Temporderdetail> temporderdetails = temporder.getTemporderdetailCollection();
        
        for (Temporderdetail temporderdetail : temporderdetails) {
            temporderdetails.remove(temporderdetail);
            entityManager.remove(temporderdetail);
        }
        
        temporder.setTemporderdetailCollection(temporderdetails);
        
        entityManager.merge(temporder);
    }

    
    @Override
    public Temporderdetail TempOrderDetail_Select_ById(int Id) {
        return entityManager.find(Temporderdetail.class, Id);
    }

    
    @Override
    public Collection<Temporderdetail> TempOrderDetail_SelectAll() {
        return entityManager.createNamedQuery("Temporderdetail.findAll").getResultList();
    }
    
    
    @Override
    public Collection<Temporderdetail> TempOrderDetail_SelectAll_ByUserId(int TempOrderId){
        Temporder temporder = entityManager.find(Temporder.class, TempOrderId);
        
        entityManager.refresh(temporder);
        
        return temporder.getTemporderdetailCollection();
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="UsersAddress">
    
    
    @Override
    public void UsersAddress_Insert(String Building, String Street, String Pincode, String City,
            String State, String Country, boolean IsActive, short UserId) {
        
        Usersaddress usersAddress = new Usersaddress();

        UsersMaster usersMaster = Users_Select_ById(UserId);

        usersAddress.setBuilding(Building);
        usersAddress.setStreet(Street);
        usersAddress.setPincode(Pincode);
        usersAddress.setCity(City);
        usersAddress.setState(State);
        usersAddress.setCountry(Country);
        usersAddress.setIsActive(IsActive);
        usersAddress.setUserId(usersMaster);

        entityManager.persist(usersAddress);

        Collection<Usersaddress> usersAddressCollection = usersMaster.getUsersaddressCollection();
        usersAddressCollection.add(usersAddress);
        usersMaster.setUsersaddressCollection(usersAddressCollection);

        entityManager.merge(usersMaster);
    }
    

    @Override
    public void UsersAddress_Update(short Id, String Building, String Street, String Pincode,
            String City, String State, String Country, boolean IsActive, short UserId) {
        
        Usersaddress usersAddress = entityManager.find(Usersaddress.class, Id);

        UsersMaster usersMaster = Users_Select_ById(UserId);

        usersAddress.setBuilding(Building);
        usersAddress.setStreet(Street);
        usersAddress.setPincode(Pincode);
        usersAddress.setCity(City);
        usersAddress.setState(State);
        usersAddress.setCountry(Country);
        usersAddress.setIsActive(IsActive);
        usersAddress.setUserId(usersMaster);

        entityManager.merge(usersAddress);

        Collection<Usersaddress> AddressCollection = usersMaster.getUsersaddressCollection();
        AddressCollection.add(usersAddress);
        usersMaster.setUsersaddressCollection(AddressCollection);

        entityManager.merge(usersMaster);
    }

    
    @Override
    public void UsersAddress_Update_IsActive(short Id, boolean IsActive) {
        Usersaddress usersAddress = entityManager.find(Usersaddress.class, Id);

        UsersMaster usersMaster = usersAddress.getUserId();

        usersAddress.setIsActive(IsActive);

        entityManager.merge(usersAddress);

        Collection<Usersaddress> usersAddressCollection = usersMaster.getUsersaddressCollection();
        usersAddressCollection.add(usersAddress);
        usersMaster.setUsersaddressCollection(usersAddressCollection);

        entityManager.merge(usersMaster);
    }

    
    @Override
    public void UsersAddress_Delete(short Id) {
        Usersaddress usersAddress = entityManager.find(Usersaddress.class, Id);

        UsersMaster usersMaster = usersAddress.getUserId();
        Collection<Usersaddress> usersAddressCollection = usersMaster.getUsersaddressCollection();
        usersAddressCollection.remove(usersAddress);
        usersMaster.setUsersaddressCollection(usersAddressCollection);

        entityManager.remove(usersAddress);
        entityManager.merge(usersMaster);
    }

    
    @Override
    public Usersaddress UsersAddress_Select_ById(short Id) {
        return entityManager.find(Usersaddress.class, Id);
    }
    
    
    @Override
    public Collection<Usersaddress> UsersAddress_SelectAll_ByUserId(short UserId) {
        UsersMaster usersMaster = Users_Select_ById(UserId);
        
        entityManager.refresh(usersMaster);
        
        return usersMaster.getUsersaddressCollection();
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Users">

    
    @Override
    public UsersMaster Users_Insert(String Username, String EmailId, String Password,
            String FirstName, String LastName, String Image, 
            String MobileNo, boolean IsActive) {
        
        UsersMaster users = new UsersMaster();
        
        users.setUsername(Username);
        users.setEmailId(EmailId);
        users.setPassword(Password);
        users.setFirstName(FirstName);
        users.setLastName(LastName);
        users.setImage(Image);
        users.setMobileNo(MobileNo);
        users.setIsActive(IsActive);
        
        entityManager.persist(users);
        
        return users;
    }

    
    @Override
    public void Users_Update(short Id, String EmailId, String FirstName, 
            String LastName, String Image, String MobileNo) {
        
        UsersMaster users = Users_Select_ById(Id);
        
        users.setEmailId(EmailId);
        users.setFirstName(FirstName);
        users.setLastName(LastName);
        users.setImage(Image);
        users.setMobileNo(MobileNo);
        
        entityManager.merge(users);
    }
    
    
    @Override
    public void Users_Update_IsActive(short Id, boolean IsActive) {
        UsersMaster users = Users_Select_ById(Id);
        
        users.setIsActive(IsActive);
        
        entityManager.merge(users);
    }
    
    
    @Override
    public void Users_Update_Password(short Id, String Password) {
        UsersMaster users = Users_Select_ById(Id);
        
        users.setPassword(Password);
        
        entityManager.merge(users);
    }

    
    @Override
    public void Users_Delete(short Id) {
        UsersMaster users = Users_Select_ById(Id);
        
        entityManager.remove(users);
    }

    
    @Override
    public UsersMaster Users_Select_ById(short Id) {
        return (UsersMaster) entityManager.createNamedQuery("UsersMaster.findById").setParameter("id", Id).getResultList().iterator().next();
    }
    
    
    @Override
    public Collection<UsersMaster> Users_SelectAll_Users() {
        return entityManager.createNamedQuery("UsersMaster.findAllUsers").getResultList();
//        @NamedQuery(name = "UsersMaster.findAllUsers", query = "SELECT u FROM GroupMaster g INNER JOIN g.usersMaster u WHERE g.groupMasterPK.groupname = 'User'")
    }    
    
    
    @Override
    public Collection<UsersMaster> Users_SelectAll_Retailer() {
        return entityManager.createNamedQuery("UsersMaster.findAllRetailer").getResultList();
//        @NamedQuery(name = "UsersMaster.findAllRetailer", query = "SELECT u FROM GroupMaster g INNER JOIN g.usersMaster u WHERE g.groupMasterPK.groupname = 'Retailer'")
    }
    
    
    // </editor-fold>    
    
    
    // <editor-fold defaultstate="collapsed" desc="Wishlist">    
    
    
    @Override
    public void Wishlist_Insert(short UserId, short RetailerProductId) {
        Wishlist wishlist = new Wishlist();

        UsersMaster usersMaster = entityManager.find(UsersMaster.class, UserId);
        Retailerproduct retailerProduct = entityManager.find(Retailerproduct.class, RetailerProductId);

        //wishlist.setRetailerProductId(retailerProduct);
        wishlist.setUserId(usersMaster);

        entityManager.persist(wishlist);

        Collection<Wishlist> wishlists = usersMaster.getWishlistCollection();
        wishlists.add(wishlist);
        usersMaster.setWishlistCollection(wishlists);

        entityManager.merge(usersMaster);
    }

    
    @Override
    public void Wishlist_Update(int Id, short UserId, short RetailerProductId) {
        Wishlist wishlist = entityManager.find(Wishlist.class, Id);

        UsersMaster usersMaster = entityManager.find(UsersMaster.class, UserId);
        Retailerproduct retailerProduct = entityManager.find(Retailerproduct.class, RetailerProductId);

        //wishlist.setRetailerProductId(retailerProduct);
        wishlist.setUserId(usersMaster);

        entityManager.merge(wishlist);

        Collection<Wishlist> wishlists = usersMaster.getWishlistCollection();
        wishlists.add(wishlist);
        usersMaster.setWishlistCollection(wishlists);

        entityManager.merge(wishlist);
    }

    
    @Override
    public void Wishlist_Delete(int Id) {
        Wishlist wishlist = entityManager.find(Wishlist.class, Id);
        
        UsersMaster usersMaster = wishlist.getUserId();
        
        Collection<Wishlist> wishlists = usersMaster.getWishlistCollection();
        wishlists.remove(wishlist);
        usersMaster.setWishlistCollection(wishlists);
        
        entityManager.remove(wishlist);
        entityManager.merge(usersMaster);
    }
    
    
    @Override
    public void Wishlist_DeleteAll_ByUserId(short UserId){
        UsersMaster usersMaster = entityManager.find(UsersMaster.class, UserId);
        
        Collection<Wishlist> wishlists = usersMaster.getWishlistCollection();
        
        for (Wishlist wishlist : wishlists) {
            wishlists.remove(wishlist);
            entityManager.remove(wishlist);
        }
        
        usersMaster.setWishlistCollection(wishlists);
        
        entityManager.merge(usersMaster);
    }

    
    @Override
    public Wishlist Wishlist_Select_ById(short Id) {
        return entityManager.find(Wishlist.class, Id);
    }

    
    @Override
    public Collection<Wishlist> Wishlist_SelectAll() {
        return entityManager.createNamedQuery("Wishlist.findAll").getResultList();
    }
    
    
    @Override
    public Collection<Wishlist> Wishlist_SelectAll_ByUserId(short UserId){
        UsersMaster usersMaster = entityManager.find(UsersMaster.class, UserId);
        
        entityManager.refresh(usersMaster);
        
        return usersMaster.getWishlistCollection();
    }
    
    
    // </editor-fold>
    
    
}
