package resources;

import Exceptions.UserExistsException;
import Exceptions.UserNotFoundException;
import models.User;
import service.UserService;

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
    @Path("/get-by-username/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) throws UserNotFoundException
    {
        User user = userService.getUser(username);
        return Response.ok(user).build();
    }

    @GET
    @Path("/get-by-id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") long id){
        User user = userService.getUserById(id);
        return Response.ok(user).build();
    }

    @GET
    @Path("/all-users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allUsers(){
        List<User> users =  userService.users();
        return Response.status(200).entity(users).build();
    }

    @GET
    @Path("/search/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@PathParam("username") String username){
        List<User> users = userService.search(username);
        return Response.ok(users).build();
    }


    @POST
    @Path("/add-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) throws UserExistsException {
        User addedUser = userService.add(user);
        return Response.status(201).entity(addedUser).build();
    }

    @PUT
    @Path("/edit-user/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editUser(@PathParam("username") String username, User user) {
//        User u = userService.getUser(username);
//        u = user;
        userService.edit(user);
        return Response.ok().entity(user).build();
    }

    @DELETE
    @Path("/delete-user/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeUser(@PathParam("username") long userId) {
        userService.remove(userId);
        return Response.ok().build();
    }




}
