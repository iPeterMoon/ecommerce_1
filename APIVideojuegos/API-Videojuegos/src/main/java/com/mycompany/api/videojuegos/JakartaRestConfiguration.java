package com.mycompany.api.videojuegos;

import api.LoginResource;
import api.CartResource;
import api.HistoryResource;
import api.OrderDetailResource;
import api.OrderResource;
import api.ProductosResource;
import api.ResenasResource;
import api.UserResource;
import api.VerifyResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Configures Jakarta RESTful Web Services for the application.
 *
 * @author Juneau
 */
//@ApplicationPath("api")
public class JakartaRestConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(ProductosResource.class);
        classes.add(CartResource.class);
        classes.add(LoginResource.class);
        classes.add(VerifyResource.class);
        classes.add(HistoryResource.class);
        classes.add(OrderDetailResource.class);
        classes.add(UserResource.class);
        classes.add(api.ProfileResource.class);
        classes.add(OrderResource.class);
        classes.add(ResenasResource.class);
        return classes;
    }

}
