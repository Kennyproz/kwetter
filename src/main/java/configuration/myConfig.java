package configuration;

import mappers.RoleExistsExceptionMapper;
import mappers.UserExistsExceptionMapper;
import mappers.UserNotFoundExceptionMapper;
import models.KweetConvertor;
import models.KweetCreator;
import models.UserCreator;
import org.glassfish.jersey.server.ResourceConfig;
import resources.KweetResource;
import resources.RoleResource;
import resources.UserResource;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class myConfig extends ResourceConfig {

    public myConfig(){
        register(CORSResponseFilter.class);
        register(KweetCreator.class);
        register(UserCreator.class);
       // register(KweetConvertor.class);
        register(UserResource.class);
        register(RoleResource.class);
        register(KweetResource.class);
        register(UserNotFoundExceptionMapper.class);
        register(UserExistsExceptionMapper.class);
        register(RoleExistsExceptionMapper.class);
    }
}
