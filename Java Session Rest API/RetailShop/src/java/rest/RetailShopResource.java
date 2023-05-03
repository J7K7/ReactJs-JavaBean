package rest;

import common.EmailProvider;
import ejb.RetailShopBeanLocal;
import entity.Category;
import entity.Coupon;
import entity.Product;
import entity.Productimage;
import entity.RestResponse;
import entity.UsersMaster;
import entity.Usersaddress;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 * REST Web Service
 *
 * @author Abhay
 */
@Path("RetailShop")
public class RetailShopResource {

    @Context
    private UriInfo context;

    @EJB
    RetailShopBeanLocal retailShopBeanLocal;
    
    @Inject
    private SecurityContext securityContext;
    
    private AuthenticationStatus status;
    private Set<String> roles;
    private String message;

    private Pbkdf2PasswordHashImpl passwordHashImpl;

    public RetailShopResource() {
        passwordHashImpl = new Pbkdf2PasswordHashImpl();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Category">

    
    @POST
    @Path("/Category/Post/{Name}/{IsActive}/{parentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Category_Insert(@PathParam("Name") String Name,
            @PathParam("IsActive") boolean IsActive, @PathParam("parentId") short parentId) {
        try {
            
            retailShopBeanLocal.Category_Insert(Name, IsActive, parentId);
            
            RestResponse restResponse = new RestResponse("Record Inserted.");
            
            return restResponse;
            
        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = getCategoryInsertUpdateException(ex);
            
            return restResponse;
        }
    }

    
    @POST
    @Path("/Category/Put/{Id}/{Name}/{IsActive}/{parentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Category_Update(@PathParam("Id") short Id,
            @PathParam("Name") String Name, @PathParam("IsActive") boolean IsActive,
            @PathParam("parentId") short parentId) {
        try {
            
            retailShopBeanLocal.Category_Update(Id, Name, IsActive, parentId);
            
            RestResponse restResponse = new RestResponse("Record Updated.");
            
            return restResponse;
            
        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = getCategoryInsertUpdateException(ex);
            
            return restResponse;
        }
    }
    
    
    @POST
    @Path("/Category/PutStatus/{Id}/{IsActive}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Category_Update_IsActive(@PathParam("Id") short Id,
            @PathParam("IsActive") boolean IsActive) {
        try {
            
            retailShopBeanLocal.Category_Update_IsActive(Id, IsActive);
            
            RestResponse restResponse = new RestResponse("Record Updated.");
            
            return restResponse;
            
        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();
            
            return restResponse;
        }
    }

    
    @POST
    @Path("/Category/Delete/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Category_Delete(@PathParam("Id") short Id) {
        try {

            retailShopBeanLocal.Category_Delete(Id);

            RestResponse restResponse = new RestResponse("Record Deleted.");

            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();

            return restResponse;
        }
    }

    
    @GET
    @Path("/Category/GetById/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category Category_Select_ById(@PathParam("Id") short Id) {
        return retailShopBeanLocal.Category_Select_ById(Id);
    }

    
    @GET
    @Path("/Category/Get")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Category> Category_SelectAll() {
        return retailShopBeanLocal.Category_SelectAll();
    }
    
    
    @GET
    @Path("/Category/GetActive")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Category> Category_SelectAll_Active() {
        return retailShopBeanLocal.Category_SelectAll_Active();
    }
    
    
    private String getCategoryInsertUpdateException(Exception ex) {
        if (ex.getCause().getCause().getMessage().contains("UK_Category")) {
            return "Category Name Already Exists.";
        } else {
            return ex.getCause().getCause().getMessage();
        }
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Coupon">
    
    
    @POST
    @Path("/Coupon/Post/{Name}/{Discount}/{IsFlat}/{ExpiryDate}/{IsActive}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Coupon_Insert(@PathParam("Name") String Name,
            @PathParam("Discount") float Discount, @PathParam("IsFlat") boolean IsFlat,
            @PathParam("ExpiryDate") String ExpiryDate, @PathParam("IsActive") boolean IsActive) {
        try {
            
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date Expiry = simpleDateFormat.parse(ExpiryDate);
            retailShopBeanLocal.Coupon_Insert(Name, Discount, IsFlat, Expiry, IsActive);
           
            RestResponse restResponse = new RestResponse("Record Inserted.");
            
            return restResponse;
            
        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = getCouponInsertUpdateException(ex);
            
            return restResponse;
        }
    }
    
    
    @POST
    @Path("/Coupon/Put/{Id}/{Name}/{Discount}/{IsFlat}/{ExpiryDate}/{IsActive}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Coupon_Update(@PathParam("Id") int Id,
            @PathParam("Name") String Name, @PathParam("Discount") float Discount,
            @PathParam("IsFlat") boolean IsFlat, @PathParam("ExpiryDate") String ExpiryDate,
            @PathParam("IsActive") boolean IsActive) {
        try {
            
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date Expiry = simpleDateFormat.parse(ExpiryDate);
            retailShopBeanLocal.Coupon_Update(Id, Name, Discount, IsFlat, Expiry, IsActive);
           
            RestResponse restResponse = new RestResponse("Record Updated.");
            
            return restResponse;
            
        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = getCouponInsertUpdateException(ex);
            
            return restResponse;
        }        
    }
        
    
    @POST
    @Path("/Coupon/PutStatus/{Id}/{IsActive}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Coupon_Update_IsActive(@PathParam("Id") int Id,
            @PathParam("IsActive") boolean IsActive) {
        try {
            
            retailShopBeanLocal.Coupon_Update_IsActive(Id, IsActive);
            
            RestResponse restResponse = new RestResponse("Record Updated.");
            
            return restResponse;
            
        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();
            
            return restResponse;
        }            
    }
    
    
    @POST
    @Path("/Coupon/Delete/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Coupon_Delete(@PathParam("Id") int Id) {
        try {

            retailShopBeanLocal.Coupon_Delete(Id);

            RestResponse restResponse = new RestResponse("Record Deleted.");

            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();

            return restResponse;
        }
    }
    
    
    @GET
    @Path("/Coupon/GetById/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Coupon Coupon_Select_ById(@PathParam("Id") int Id) {
        return retailShopBeanLocal.Coupon_Select_ById(Id);
    }
    
    
    @GET
    @Path("/Coupon/Get")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Coupon> Coupon_SelectAll() {
        return retailShopBeanLocal.Coupon_SelectAll();
    }
    
    
    @GET
    @Path("/Coupon/GetActive")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Coupon> Coupon_SelectAll_Active() {
        return retailShopBeanLocal.Coupon_SelectAll_Active();
    }
    
    
    private String getCouponInsertUpdateException(Exception ex) {
        if (ex.getCause().getCause().getMessage().contains("UK_Coupon")) {
            return "Coupon Name Already Exists.";
        } else {
            return ex.getCause().getCause().getMessage();
        }
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Product">
    
    
    @POST
    @Path("/Product/Post/{Name}/{Description}/{IsActive}/{CategoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Product_Insert(@PathParam("Name") String Name,
            @PathParam("Description") String Description, @PathParam("IsActive") boolean IsActive,
            @PathParam("CategoryId") short CategoryId) {
        try {
            
            retailShopBeanLocal.Product_Insert(Name, Description, IsActive, CategoryId);
            
            RestResponse restResponse = new RestResponse("Record Inserted.");
            
            return restResponse;
            
        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = getProductInsertUpdateException(ex);
            
            return restResponse;
        }
    }
    
    
    @POST
    @Path("/Product/Put/{Id}/{Name}/{Description}/{IsActive}/{CategoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Product_Update(@PathParam("Id") short Id,
            @PathParam("Name") String Name, @PathParam("Description") String Description,
            @PathParam("IsActive") boolean IsActive, @PathParam("CategoryId") short CategoryId) {
        try {
            
            retailShopBeanLocal.Product_Update(Id, Name, Description, IsActive, CategoryId);
            
            RestResponse restResponse = new RestResponse("Record Updated.");
            
            return restResponse;
            
        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = getProductInsertUpdateException(ex);
            
            return restResponse;
        }
    }


    @POST
    @Path("/Product/PutStatus/{Id}/{IsActive}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Product_Update_IsActive(@PathParam("Id") short Id,
            @PathParam("IsActive") boolean IsActive) {
        try {
            
            retailShopBeanLocal.Product_Update_IsActive(Id, IsActive);
            
            RestResponse restResponse = new RestResponse("Record Updated.");
            
            return restResponse;
            
        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();
            
            return restResponse;
        }
    }
    
    
    @POST
    @Path("/Product/Delete/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Product_Delete(@PathParam("Id") short Id) {
        try {

            retailShopBeanLocal.Product_Delete(Id);

            RestResponse restResponse = new RestResponse("Record Deleted.");

            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();

            return restResponse;
        }
    }
    
    
    @GET
    @Path("/Product/GetById/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product Product_Select_ById(@PathParam("Id") short Id) {
        return retailShopBeanLocal.Product_Select_ById(Id);
    }
    
    
    @GET
    @Path("/Product/Get")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Product> Product_SelectAll() {
        return retailShopBeanLocal.Product_SelectAll();
    }
    
    @GET
    @Path("/Product/GetActive")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Product> Product_SelectAll_Active() {
        return retailShopBeanLocal.Product_SelectAll_Active();
    }
    
    
    @GET
    @Path("/Product/GetByCategory/{CategoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Product> Product_SelectAll_ByCategoryId(@PathParam("CategoryId") short CategoryId) {
        return retailShopBeanLocal.Product_SelectAll_ByCategoryId(CategoryId);
    }
    
    
    @GET
    @Path("/Product/GetActiveByCategory/{CategoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Product> Product_SelectAll_ByCategoryId_Active(@PathParam("CategoryId") short CategoryId) {
        return retailShopBeanLocal.Product_SelectAll_ByCategoryId_Active(CategoryId);
    }
    
    
    private String getProductInsertUpdateException(Exception ex) {
        if (ex.getCause().getCause().getMessage().contains("UK_Product")) {
            return "Product Name with Category Already Exists.";
        } else {
            return ex.getCause().getCause().getMessage();
        }
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Product Image">
    
    
    @POST
    @Path("/ProductImage/Post/{Name}/{ProductId}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse ProductImage_Insert(@PathParam("Name") String Name,
            @PathParam("ProductId") short ProductId) {
        try {
            
            retailShopBeanLocal.ProductImage_Insert(Name, ProductId);
            
            RestResponse restResponse = new RestResponse("Record Inserted.");
            
            return restResponse;
            
        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            if (ex.getCause().getCause().getMessage().contains("UK_ProductImage")) {
                restResponse.Message = "Product Image Name Already Exists.";
            } else {
                restResponse.Message = ex.getCause().getCause().getMessage();
            }
            
            return restResponse;
        }
    }
    
    
    @POST
    @Path("/ProductImage/Delete/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse ProductImage_Delete(@PathParam("Id") int Id) {
        try {

            retailShopBeanLocal.ProductImage_Delete(Id);

            RestResponse restResponse = new RestResponse("Record Deleted.");

            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();

            return restResponse;
        }
    }
    
    
    @GET
    @Path("/ProductImage/Get")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Productimage> ProductImage_SelectAll() {
        return retailShopBeanLocal.ProductImage_SelectAll();
    }
    
    
    @GET
    @Path("/ProductImage/GetByProduct/{ProductId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Productimage> ProductImage_SelectAll_ByProductId(@PathParam("ProductId") short ProductId) {
        return retailShopBeanLocal.ProductImage_SelectAll_ByProductId(ProductId);
    }
    
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Users Address">
    
    
    @POST
    @Path("/UsersAddress/Post/{Building}/{Street}/{Pincode}/{City}/{State}/{Country}/{IsActive}/{UserId}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse UsersAddress_Insert(@PathParam("Building") String Building,
            @PathParam("Street") String Street, @PathParam("Pincode") String Pincode,
            @PathParam("City") String City, @PathParam("State") String State,
            @PathParam("Country") String Country, @PathParam("IsActive") boolean IsActive,
            @PathParam("UserId") short UserId) {
        try {

            retailShopBeanLocal.UsersAddress_Insert(Building, Street, Pincode, City, State, Country, IsActive, UserId);

            RestResponse restResponse = new RestResponse("Record Inserted");

            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();

            return restResponse;
        }
    }
    
    
    @POST
    @Path("/UsersAddress/Put/{Id}/{Building}/{Street}/{Pincode}/{City}/{State}/{Country}/{IsActive}/{UserId}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse UsersAddress_Update(@PathParam("Id") short Id, @PathParam("Building") String Building,
            @PathParam("Street") String Street, @PathParam("Pincode") String Pincode,
            @PathParam("City") String City, @PathParam("State") String State,
            @PathParam("Country") String Country, @PathParam("IsActive") boolean IsActive,
            @PathParam("UserId") short UserId){
        try {

            retailShopBeanLocal.UsersAddress_Update(Id, Building, Street, Pincode, City, State, Country, IsActive, UserId);

            RestResponse restResponse = new RestResponse("Record Updated.");

            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();

            return restResponse;
        }
    }
    
    
    @POST
    @Path("/UsersAddress/PutStatus/{Id}/{IsActive}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse UsersAddress_Update_IsActive(@PathParam("Id") short Id, @PathParam("IsActive") boolean IsActive) {
        try {

            retailShopBeanLocal.UsersAddress_Update_IsActive(Id, IsActive);

            RestResponse restResponse = new RestResponse("Record Updated.");

            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();

            return restResponse;
        }
    }
    
    
    @POST
    @Path("/UsersAddress/Delete/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse UsersAddress_Delete(@PathParam("Id") short Id) {
        try {

            retailShopBeanLocal.UsersAddress_Delete(Id);

            RestResponse restResponse = new RestResponse("Record Deleted.");

            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();

            return restResponse;
        }
    }
    
    
    @GET
    @Path("/UsersAddress/GetById/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usersaddress UsersAddress_Select_ById(@PathParam("Id") short Id){
        return retailShopBeanLocal.UsersAddress_Select_ById(Id);
    }
    
    
    @GET
    @Path("/UsersAddress/GetByUser/{UserId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Usersaddress> UsersAddress_SelectAll_ByUserId(@PathParam("UserId") short UserId){
        return retailShopBeanLocal.UsersAddress_SelectAll_ByUserId(UserId);
    }
    
    
    // </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="Users">
    
    
    @POST
    @Path("/Retailer/Post/{Username}/{EmailId}/{Password}/{FirstName}/{LastName}/{Image}/{MobileNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Retailer_Insert(@PathParam("Username") String Username,
            @PathParam("EmailId") String EmailId, @PathParam("Password") String Password,
            @PathParam("FirstName") String FirstName, @PathParam("LastName") String LastName,
            @PathParam("Image") String Image, @PathParam("MobileNo") String MobileNo) {
        try {

            Password = passwordHashImpl.generate(Password.toCharArray());
            boolean IsActive = false;

            UsersMaster Retailer = retailShopBeanLocal.Users_Insert(Username, EmailId,
                    Password, FirstName, LastName, Image, MobileNo, IsActive);

            UsersMaster usersMaster = retailShopBeanLocal.Users_Select_ById(Retailer.getId());

            retailShopBeanLocal.Group_Insert(usersMaster.getUsername(), "Retailer");
            
            EmailProvider.RetailerInsertMail(EmailId, FirstName, LastName);

            RestResponse restResponse = new RestResponse("Record Inserted");

            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = getUserInsertUpdateException(ex);

            return restResponse;
        }
    }

    
    @POST
    @Path("/User/Post/{Username}/{EmailId}/{Password}/{FirstName}/{LastName}/{Image}/{MobileNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse User_Insert(@PathParam("Username") String Username,
            @PathParam("EmailId") String EmailId, @PathParam("Password") String Password,
            @PathParam("FirstName") String FirstName, @PathParam("LastName") String LastName,
            @PathParam("Image") String Image, @PathParam("MobileNo") String MobileNo) {
        try {

            Password = passwordHashImpl.generate(Password.toCharArray());
            boolean IsActive = false;

            UsersMaster users = retailShopBeanLocal.Users_Insert(Username, EmailId,
                    Password, FirstName, LastName, Image, MobileNo, IsActive);

            UsersMaster usersMaster = retailShopBeanLocal.Users_Select_ById(users.getId());

            retailShopBeanLocal.Group_Insert(usersMaster.getUsername(), "User");
            
            EmailProvider.UserInsertMail(EmailId, FirstName, LastName);

            RestResponse restResponse = new RestResponse("Record Inserted.");

            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = getUserInsertUpdateException(ex);

            return restResponse;
        }
    }

    
    @POST
    @Path("/Users/Put/{Id}/{EmailId}/{FirstName}/{LastName}/{Image}/{MobileNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Users_Update(@PathParam("Id") short Id, @PathParam("EmailId") String EmailId,
            @PathParam("FirstName") String FirstName, @PathParam("LastName") String LastName, 
            @PathParam("Image") String Image, @PathParam("MobileNo") String MobileNo) {
        try {

            retailShopBeanLocal.Users_Update(Id, EmailId, FirstName, LastName, Image, MobileNo);

            RestResponse restResponse = new RestResponse("Record Updated.");

            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = getUserInsertUpdateException(ex);

            return restResponse;
        }
    }

    
    @POST
    @Path("/Users/PutStatus/{Id}/{IsActive}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Users_Update_IsActive(@PathParam("Id") short Id, @PathParam("IsActive") boolean IsActive) {
        try {

            retailShopBeanLocal.Users_Update_IsActive(Id, IsActive);

            RestResponse restResponse = new RestResponse("Record Updated.");

            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();

            return restResponse;
        }
    }

    
    @POST
    @Path("/Users/PutPassword/{Id}/{Password}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Users_Update_Password(@PathParam("Id") short Id, @PathParam("Password") String Password) {
        try {
            
            RestResponse restResponse = new RestResponse();

            Password = passwordHashImpl.generate(Password.toCharArray());

            retailShopBeanLocal.Users_Update_Password(Id, Password);

            restResponse.Message = "Password Updated Successfully.";
            
            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();

            return restResponse;
        }
    }

    
    @POST
    @Path("/Users/PutVerifyPassword/{Id}/{OldPassword}/{NewPassword}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Users_Update_VerifyPassword(@PathParam("Id") short Id,
            @PathParam("OldPassword") String OldPassword, @PathParam("NewPassword") String NewPassword) {
        try {
            
            RestResponse restResponse = new RestResponse();
            
            UsersMaster users = retailShopBeanLocal.Users_Select_ById(Id);
            
            if(passwordHashImpl.verify(OldPassword.toCharArray(), users.getPassword())){

                NewPassword = passwordHashImpl.generate(NewPassword.toCharArray());

                retailShopBeanLocal.Users_Update_Password(Id, NewPassword);

                restResponse.Message = "Password Updated Successfully.";
                
            }
            else {
                restResponse.Message = "Old Password Does not Match.";
            }            
            
            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();

            return restResponse;
        }
    }

    
    @POST
    @Path("/Users/Delete/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse Users_Delete(@PathParam("Id") short Id) {
        try {

            retailShopBeanLocal.Users_Delete(Id);

            RestResponse restResponse = new RestResponse("Record Deleted.");

            return restResponse;

        } catch (Exception ex) {
            RestResponse restResponse = new RestResponse();
            
            restResponse.Message = ex.getCause().getCause().getMessage();

            return restResponse;
        }
    }

    
    @GET
    @Path("/Users/GetById/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsersMaster Users_SelectById(@PathParam("Id") short Id) {
        return retailShopBeanLocal.Users_Select_ById(Id);
    }

    
    @GET
    @Path("/Users/GetUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<UsersMaster> Users_SelectAll_Users() {
        return retailShopBeanLocal.Users_SelectAll_Users();
    }

    
    @GET
    @Path("/Users/GetRetailer")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<UsersMaster> Users_SelectAll_Retailer() {
        return retailShopBeanLocal.Users_SelectAll_Retailer();
    }

    
    private String getUserInsertUpdateException(Exception ex) {
        if (ex.getCause().getCause().getMessage().contains("PRIMARY")) {
            return "Username Already Exists.";
        } else if (ex.getCause().getCause().getMessage().contains("UK_Users_Email")) {
            return "Email Id Already Exists.";
        } else if (ex.getCause().getCause().getMessage().contains("UK_Users_Mobile")) {
            return "Mobile No. Already Exists.";
        } else {
            return ex.getCause().getCause().getMessage();
        }
    }

    
    // </editor-fold>
    
    // Login
    
    @POST
    @Path("/User/login/{username}/{password}")
    @Produces(MediaType.TEXT_HTML)
    public String login(@PathParam("username") String username,@PathParam("password") String password) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            request.getSession().setAttribute("logged-group", "");

            Credential credential = new UsernamePasswordCredential(username, new Password(password));
            AuthenticationStatus status = securityContext.authenticate(request, response, withParams().credential(credential));
            if (status.equals(SEND_CONTINUE)) {
                // Authentication mechanism has send a redirect, should not
                // send anything to response from JSF now. The control will now go into HttpAuthenticationMechanism
                context.responseComplete();
            }
            System.out.println("In Resoursce");
            if (roles.contains("Admin")) {
                request.getSession().setAttribute("logged-group", "Admin");
                System.out.println("In admin");
                return "admin/dashboard";
            }
            else if (roles.contains("Retailer")) {
                request.getSession().setAttribute("logged-group", "Retailer");
                System.out.println("In Retailer");
                return "retailer/dashboard";
            } 
            else if (roles.contains("Customer")) {
                request.getSession().setAttribute("logged-group", "Customer");
                System.out.println("In Customer");
                return "customer/dashBoard";
            }
        } catch (Exception e) {
            message = "Out- Either user or login is wrong !!!";
        }       
        return "/Login";
    }
    
    private static void addError(FacesContext context, String message) {
        context = FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(SEVERITY_ERROR, message, null));
    }

    @GET
    @Path("/User/logout")
    @Produces(MediaType.TEXT_HTML)
    public String logout() throws ServletException {
        System.out.println("In Log out");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().setAttribute("logged-group", "");
        request.logout();
        request.getSession().invalidate();

        return "Login";
    }
}
