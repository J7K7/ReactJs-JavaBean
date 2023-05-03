package beans;

import entity.UsersMaster;
import java.io.Serializable;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ejb.RetailShopBeanLocal;

@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    @EJB
    RetailShopBeanLocal abl;
    @Inject
    private SecurityContext securityContext;

    private String username;
    private String password;
    private String message;
    private AuthenticationStatus status;
    private Set<String> roles;

    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    public AuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }

    /**
     * AuthenticationStatus status= securityContext.authenticate(request,
     * response, // withParams().credential(creden Creates a new instance of
     * LoginBean
     */
    public LoginBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String login() {
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

            System.out.println("In bean");
            if (roles.contains("Admin")) {
                System.out.println("In admin");
                request.getSession().getServletContext().setAttribute("username", username);
                String user = (String) request.getSession().getServletContext().getAttribute("username");
                UsersMaster userObj = abl.getUserByName(user);
                String image = userObj.getImage();
                System.out.println("Image is = " + image);
                request.getSession().getServletContext().setAttribute("image", image);

                request.getSession().setAttribute("logged-group", "Admin");
                return "/admin/dashboard.jsf";

            }
            else if (roles.contains("Retailer")) {

                request.getSession().getServletContext().setAttribute("retailer", username);
                String user = (String) request.getSession().getServletContext().getAttribute("retailer");
                UsersMaster userObj = abl.getUserByName(user);
                String image = userObj.getImage();
                System.out.println("Image is = " + image);
                request.getSession().getServletContext().setAttribute("image", image);
                request.getSession().setAttribute("logged-group", "Retailer");
                return "/retailer/dashboard.jsf";
            } else if (roles.contains("Customer")) {

                System.out.println("In Customer");
                request.getSession().getServletContext().setAttribute("customer", username);

                request.getSession().setAttribute("logged-group", "Customer");
                return "dashBoard.jsf";
            }
        } catch (Exception e) {
            message = "Out- Either user or login is wrong !!!";
        }       
        return "Login.jsf";
    }

    private static void addError(FacesContext context, String message) {
        context = FacesContext.getCurrentInstance();
        context
                .addMessage(
                        null,
                        new FacesMessage(SEVERITY_ERROR, message, null));
    }

    public String logout() throws ServletException {
        System.out.println("In Log out");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().setAttribute("logged-group", "");
        request.logout();
        request.getSession().invalidate();

        return "Login.jsf";

    }

}
