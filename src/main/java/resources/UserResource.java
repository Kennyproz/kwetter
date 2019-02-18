package resources;


import Exceptions.UserNotFoundException;
import models.User;
import service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
@RequestScoped
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) throws UserNotFoundException
    {
        User user = userService.getUser(username);
        return Response.ok(user).build();
    }

    @GET
    @Path("/all-users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allUsers(){
        List<User> users =  userService.users();
        return Response.ok(users).build();
    }




}
