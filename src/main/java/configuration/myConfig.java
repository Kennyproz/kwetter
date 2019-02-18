package configuration;

import mappers.UserNotFoundExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import resources.UserResource;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class myConfig extends ResourceConfig {

    public myConfig(){
        register(UserResource.class);
        register(UserNotFoundExceptionMapper.class);
    }
}
