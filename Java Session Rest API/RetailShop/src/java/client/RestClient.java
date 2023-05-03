/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:RestResource [Rest]<br>
 * USAGE:
 * <pre>
 *        RestClient client = new RestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author HP
 */
public class RestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/RetailShop/webresources";
    
    public RestClient(){
        
    }

    public RestClient(String token) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(new RestFilter(token));
        webTarget = client.target(BASE_URI).path("Rest");
    }

    public void addUser(String username, String email, String password, String fname, String lname, String image, String mobile, String group) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("User/Insert/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{username, email, password, fname, lname, image, mobile, group})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
