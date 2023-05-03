package client;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author HP
 */
@Provider
public class RestFilter implements ClientRequestFilter {

    public static String mytoken;
    
    public RestFilter(String token) {      
        mytoken = token;
     }
    
    @Override
    public void filter(ClientRequestContext requestContext) {
        requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer "+mytoken);
    }
    
}
