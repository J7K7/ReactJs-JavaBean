/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:RetailShopResource
 * [RetailShop]<br>
 * USAGE:
 * <pre>
 *        RetailShopClient client = new RetailShopClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author HP
 */
public class RetailShopClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/RetailShop/webresources";

    public RetailShopClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("RetailShop");
    }

    public <T> T UsersAddress_Update_IsActive(Class<T> responseType, String Id, String IsActive) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UsersAddress/PutStatus/{0}/{1}", new Object[]{Id, IsActive})).request().post(null, responseType);
    }

    public <T> T Product_SelectAll_ByCategoryId_Active(Class<T> responseType, String CategoryId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Product/GetActiveByCategory/{0}", new Object[]{CategoryId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T Category_SelectAll_Active(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("Category/GetActive");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T UsersAddress_Delete(Class<T> responseType, String Id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UsersAddress/Delete/{0}", new Object[]{Id})).request().post(null, responseType);
    }

    public <T> T Coupon_Delete(Class<T> responseType, String Id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Coupon/Delete/{0}", new Object[]{Id})).request().post(null, responseType);
    }

    public <T> T Product_SelectAll(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("Product/Get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T Coupon_Update_IsActive(Class<T> responseType, String Id, String IsActive) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Coupon/PutStatus/{0}/{1}", new Object[]{Id, IsActive})).request().post(null, responseType);
    }

    public <T> T Product_Update_IsActive(Class<T> responseType, String Id, String IsActive) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Product/PutStatus/{0}/{1}", new Object[]{Id, IsActive})).request().post(null, responseType);
    }

    public <T> T Users_Update(Class<T> responseType, String Id, String EmailId, String FirstName, String LastName, String Image, String MobileNo) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Users/Put/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{Id, EmailId, FirstName, LastName, Image, MobileNo})).request().post(null, responseType);
    }

    public <T> T UsersAddress_Insert(Class<T> responseType, String Building, String Street, String Pincode, String City, String State, String Country, String IsActive, String UserId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UsersAddress/Post/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{Building, Street, Pincode, City, State, Country, IsActive, UserId})).request().post(null, responseType);
    }

    public <T> T Product_SelectAll_ByCategoryId(Class<T> responseType, String CategoryId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Product/GetByCategory/{0}", new Object[]{CategoryId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String login(String username, String password) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("User/login/{0}/{1}", new Object[]{username, password})).request().post(null, String.class);
    }

    public <T> T UsersAddress_Update(Class<T> responseType, String Id, String Building, String Street, String Pincode, String City, String State, String Country, String IsActive, String UserId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UsersAddress/Put/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{Id, Building, Street, Pincode, City, State, Country, IsActive, UserId})).request().post(null, responseType);
    }

    public <T> T Users_Delete(Class<T> responseType, String Id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Users/Delete/{0}", new Object[]{Id})).request().post(null, responseType);
    }

    public <T> T ProductImage_SelectAll_ByProductId(Class<T> responseType, String ProductId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("ProductImage/GetByProduct/{0}", new Object[]{ProductId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T UsersAddress_SelectAll_ByUserId(Class<T> responseType, String UserId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("UsersAddress/GetByUser/{0}", new Object[]{UserId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String logout() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("User/logout");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public <T> T Product_Insert(Class<T> responseType, String Name, String Description, String IsActive, String CategoryId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Product/Post/{0}/{1}/{2}/{3}", new Object[]{Name, Description, IsActive, CategoryId})).request().post(null, responseType);
    }

    public <T> T User_Insert(Class<T> responseType, String Username, String EmailId, String Password, String FirstName, String LastName, String Image, String MobileNo) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("User/Post/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{Username, EmailId, Password, FirstName, LastName, Image, MobileNo})).request().post(null, responseType);
    }

    public <T> T Coupon_Insert(Class<T> responseType, String Name, String Discount, String IsFlat, String ExpiryDate, String IsActive) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Coupon/Post/{0}/{1}/{2}/{3}/{4}", new Object[]{Name, Discount, IsFlat, ExpiryDate, IsActive})).request().post(null, responseType);
    }

    public <T> T Coupon_SelectAll_Active(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("Coupon/GetActive");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T Coupon_SelectAll(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("Coupon/Get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T Users_Update_Password(Class<T> responseType, String Id, String Password) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Users/PutPassword/{0}/{1}", new Object[]{Id, Password})).request().post(null, responseType);
    }

    public <T> T Category_SelectAll(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("Category/Get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T Product_Select_ById(Class<T> responseType, String Id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Product/GetById/{0}", new Object[]{Id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T Users_Update_IsActive(Class<T> responseType, String Id, String IsActive) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Users/PutStatus/{0}/{1}", new Object[]{Id, IsActive})).request().post(null, responseType);
    }

    public <T> T Users_SelectById(Class<T> responseType, String Id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Users/GetById/{0}", new Object[]{Id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T Users_SelectAll_Users(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("Users/GetUsers");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T Retailer_Insert(Class<T> responseType, String Username, String EmailId, String Password, String FirstName, String LastName, String Image, String MobileNo) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Retailer/Post/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{Username, EmailId, Password, FirstName, LastName, Image, MobileNo})).request().post(null, responseType);
    }

    public <T> T Category_Delete(Class<T> responseType, String Id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Category/Delete/{0}", new Object[]{Id})).request().post(null, responseType);
    }

    public <T> T ProductImage_Delete(Class<T> responseType, String Id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("ProductImage/Delete/{0}", new Object[]{Id})).request().post(null, responseType);
    }

    public <T> T Category_Insert(Class<T> responseType, String Name, String IsActive, String parentId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Category/Post/{0}/{1}/{2}", new Object[]{Name, IsActive, parentId})).request().post(null, responseType);
    }

    public <T> T Category_Update(Class<T> responseType, String Id, String Name, String IsActive, String parentId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Category/Put/{0}/{1}/{2}/{3}", new Object[]{Id, Name, IsActive, parentId})).request().post(null, responseType);
    }

    public <T> T ProductImage_SelectAll(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("ProductImage/Get");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T ProductImage_Insert(Class<T> responseType, String Name, String ProductId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("ProductImage/Post/{0}/{1}", new Object[]{Name, ProductId})).request().post(null, responseType);
    }

    public <T> T Coupon_Select_ById(Class<T> responseType, String Id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Coupon/GetById/{0}", new Object[]{Id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T Product_Update(Class<T> responseType, String Id, String Name, String Description, String IsActive, String CategoryId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Product/Put/{0}/{1}/{2}/{3}/{4}", new Object[]{Id, Name, Description, IsActive, CategoryId})).request().post(null, responseType);
    }

    public <T> T Category_Select_ById(Class<T> responseType, String Id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Category/GetById/{0}", new Object[]{Id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T UsersAddress_Select_ById(Class<T> responseType, String Id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("UsersAddress/GetById/{0}", new Object[]{Id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T Users_SelectAll_Retailer(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("Users/GetRetailer");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T Product_Delete(Class<T> responseType, String Id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Product/Delete/{0}", new Object[]{Id})).request().post(null, responseType);
    }

    public <T> T Product_SelectAll_Active(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("Product/GetActive");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T Coupon_Update(Class<T> responseType, String Id, String Name, String Discount, String IsFlat, String ExpiryDate, String IsActive) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Coupon/Put/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{Id, Name, Discount, IsFlat, ExpiryDate, IsActive})).request().post(null, responseType);
    }

    public <T> T Category_Update_IsActive(Class<T> responseType, String Id, String IsActive) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Category/PutStatus/{0}/{1}", new Object[]{Id, IsActive})).request().post(null, responseType);
    }

    public <T> T Users_Update_VerifyPassword(Class<T> responseType, String Id, String OldPassword, String NewPassword) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("Users/PutVerifyPassword/{0}/{1}/{2}", new Object[]{Id, OldPassword, NewPassword})).request().post(null, responseType);
    }

    public void close() {
        client.close();
    }
    
}
