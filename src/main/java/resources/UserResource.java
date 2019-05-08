package resources;

import Exceptions.UserExistsException;
import Exceptions.UserNotFoundException;
import models.Role;
import models.User;
import models.UserCreator;
import models.UserLogin;
import security.jwt.TokenProvider;
import service.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

import static security.jwt.Constants.ADMIN;
import static security.jwt.Constants.USER;

@Path("/user")
//@RolesAllowed({USER,ADMIN})
@RequestScoped
public class UserResource {

    @Inject
    UserService userService;

    @Inject
    TokenProvider tokenProvider;

    @GET
    @Path("/get-by-username/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) throws UserNotFoundException {
        User user = userService.getUser(username);
        return Response.ok(user).build();
    }

    @GET
    @Path("/get-by-id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") long id) {
        User user = userService.getUserById(id);
        return Response.ok(user).build();
    }


    @GET
    @Path("/following/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowing(@PathParam("id") long id) {
        List<User> users = userService.getFollowingById(id);
        return Response.ok(users).build();
    }

    @GET
    @Path("/followers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowers(@PathParam("id") long id){
        List<User> users = userService.getFollowersById(id);
        return Response.ok(users).build();
    }

    @GET
    @Path("/following/{userid}/{userfollowid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isFollowing(@PathParam("userid") long id, @PathParam("userfollowid") long userfollowid) {
        boolean isFollowing = userService.isFollowing(id,userfollowid);

        return Response.ok(isFollowing).build();
    }

    @GET
    @Path("/roles/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoles(@PathParam("userId") long id)
    {
        List<Role> roles = userService.getUserRoles(id);
        return Response.ok().entity(roles).build();
    }

    @GET
    @PermitAll
    @Path("/all-users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allUsers() {
        List<User> users = userService.users();
        return Response.status(200).entity(users).build();
    }

    @GET
    @RolesAllowed({USER,ADMIN})
    @Path("/search/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@PathParam("username") String username) {
        List<User> users = userService.search(username);
        return Response.ok(users).build();
    }


    @POST
    @Path("/add-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserCreator user) throws UserExistsException {
        User addedUser = userService.add(user);
        return Response.ok().entity(addedUser).build();
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
    @Path("/delete-user/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeUser(@PathParam("userId") long userId) {
        userService.remove(userId);
        return Response.ok().build();
    }

    @PUT
    @Path("/follow")
    public Response follow(@QueryParam("userId") long userId, @QueryParam("userToFollowId") long userToFollowId) {
        User u = userService.getUserById(userId);
        userService.follow(userId, userToFollowId);
        return Response.status(200).entity(u).build();
    }

    @PUT
    @Path("/unfollow")
    public Response unfollow(@QueryParam("userId") long userId, @QueryParam("userToUnfollowId") long userToUnfollowId) {
        User u = userService.getUserById(userId);
        userService.unfollow(userId, userToUnfollowId);
        return Response.status(200).entity(u).build();
    }

    @POST
    @PermitAll
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserLogin user) throws UserNotFoundException {
        User userLogin = userService.login(user.getUsername(), user.getPassword());
        if(userLogin == null){
            return Response.status(404).build();
        }
        Set<String> roles = userService.getRoleNames(userLogin.getId());
        String token = tokenProvider.createToken(userLogin.getUsername(),userLogin.getRoleNames(),true);
        userLogin.setToken(token);
        User u = userService.getUser(userLogin.getUsername());
        u.setToken(token);
        userService.edit(u);
        return Response.status(200).entity(userLogin).build();
    }






}
