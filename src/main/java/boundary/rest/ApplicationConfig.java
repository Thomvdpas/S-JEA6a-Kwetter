package boundary.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author Thom van de Pas on 9-3-2018
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {

//    public ApplicationConfig() {
//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setVersion("2.0.0");
//        beanConfig.setSchemes(new String[]{"http"});
//        beanConfig.setHost("localhost:8080");
//        beanConfig.setBasePath("/Kwetter/api");
//        beanConfig.setResourcePackage("boundary/rest");
//        beanConfig.setPrettyPrint(true);
//        beanConfig.setScan(true);
//    }

//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> resources = new HashSet();
//
//        //resources.add(FirstResource.class);
//        //resources.add(SecondResource.class);
//        //...
//        resources.add(AccountResponseResource.class);
//        resources.add(HashtagResponseResource.class);
//        resources.add(HeartResponseResource.class);
//        resources.add(KweetResponseResource.class);
//        resources.add(ProfileResponseResource.class);
//
//        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
//        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
//
//        return resources;
//    }
}
