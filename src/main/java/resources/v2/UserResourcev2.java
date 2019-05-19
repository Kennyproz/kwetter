package resources.v2;

import Exceptions.UserExistsException;
import Exceptions.UserNotFoundException;
import models.Role;
import models.User;
import models.UserCreator;
import models.UserLogin;
import resources.UserResource;
import security.jwt.TokenProvider;
import service.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static security.jwt.Constants.ADMIN;

@Path("/v2/users")
@RequestScoped
@SuppressWarnings("Duplicates")
public class UserResourcev2 {
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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") long id, @Context UriInfo uriInfo) {
        User user = userService.getUserById(id);
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        Link followers = Link.fromUriBuilder(
                uriInfo.getBaseUriBuilder()
                        .path(UserResourcev2.class)
                        .path(Long.toString(user.getId()))
                        .path("/followers"))
                        .rel("followers")
                .build();
//        Link self = getUriForSelf(uriInfo,user);
        return Response.ok(user).links(self,followers).build();
    }


    @GET
    @Path("/{id}/following")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowing(@PathParam("id") long id) {
        List<User> users = userService.getFollowingById(id);
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}/followers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowers(@PathParam("id") long id){
        List<User> users = userService.getFollowersById(id);
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}/isfollowing/{userfollowid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isFollowing(@PathParam("id") long id, @PathParam("userfollowid") long userfollowid) {
        boolean isFollowing = userService.isFollowing(id,userfollowid);

        return Response.ok(isFollowing).build();
    }

    @GET
    @Path("/{id}/roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoles(@PathParam("id") long id)
    {
        List<Role> roles = userService.getUserRoles(id);
        return Response.ok().entity(roles).build();
    }

    @GET
//    @RolesAllowed({ADMIN})
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allUsers(@Context UriInfo uriInfo) {
        List<User> users = userService.users();
        users.forEach(u -> initLinks(u,uriInfo));
//        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
//        Link followers = Link.fromUriBuilder(
//                uriInfo.getBaseUriBuilder()
//                        .path(UserResourcev2.class)
//                        .path(Long.toString(users.getId()))
//                        .path("/followers"))
//                .rel("followers")
//                .build();
        return Response.status(200).entity(users).build();
    }

    @GET
    @Path("/search/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@PathParam("username") String username) {
        List<User> users = userService.search(username);
        return Response.ok(users).build();
    }


    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserCreator user) throws UserExistsException {
        User addedUser = userService.add(user);
        return Response.status(201).entity(addedUser).build();
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editUser(User user) {
        userService.edit(user);
        return Response.ok().entity(user).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeUser(@PathParam("id") long id) {
        userService.remove(id);
        return Response.status(204).build();
    }

    @PUT
    @Path("/{id}/follow/{userToFollowId}")
    public Response follow(@PathParam("id") long id, @PathParam("userToFollowId") long userToFollowId) {
        User u = userService.getUserById(id);
        userService.follow(id, userToFollowId);
        return Response.status(200).entity(u).build();
    }

    @PUT
    @Path("{id}/unfollow/{userToUnfollowId}")
    public Response unfollow(@PathParam("id") long id, @PathParam("userToUnfollowId") long userToUnfollowId) {
        User u = userService.getUserById(id);
        userService.unfollow(id, userToUnfollowId);
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
        String token = tokenProvider.createToken(userLogin.getUsername(),userLogin.getRoleNames(),true);
        userLogin.setToken(token);
        User u = userService.getFullUserById(userLogin.getId());
        u.setToken(token);
        userService.edit(u);
        return Response.status(200).entity(userLogin).build();
    }



    private void initLinks(User user, UriInfo uriInfo){
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        uriBuilder = uriBuilder.path(Long.toString(user.getId()));
        UriBuilder kweetUriBuilder = uriInfo.getBaseUriBuilder().path(KweetResourcev2.class);
        UriBuilder roleUriBuilder = uriInfo.getBaseUriBuilder().path(RoleResourcev2.class);
        Link.Builder linkBuilder = Link.fromUriBuilder(uriBuilder);

        Link selfLink = linkBuilder.build();
        Link followersLink = Link.fromUriBuilder(uriInfo.getRequestUriBuilder().path(Long.toString(user.getId())).path("/followers")).build();
        Link followingLink = Link.fromUriBuilder(uriInfo.getRequestUriBuilder().path(Long.toString(user.getId())).path("/following")).build();
        Link kweetsLink = Link.fromUriBuilder(kweetUriBuilder.path(Long.toString(user.getId()))).build();
        Link rolesLink = Link.fromUriBuilder(roleUriBuilder.path(Long.toString(user.getId()))).build();

        List<models.Link> links = new ArrayList<>();

        links.add(new models.Link(selfLink.toString(),"self"));
        links.add(new models.Link(followersLink.toString(),"followers"));
        links.add(new models.Link(followingLink.toString(),"following"));
        links.add(new models.Link(kweetsLink.toString(),"kweets"));
        links.add(new models.Link(rolesLink.toString(),"roles"));

        user.setLinks(links);
    }

    private String getUriForSelf(UriInfo uriInfo, User user){
        String uri = uriInfo.getBaseUriBuilder()
                .path(UserResource.class)
                .path(Long.toString(user.getId()))
                .build().toString();
        return uri;
    }


    public JsonObject toJosn(URI self){
        return Json.createObjectBuilder()
                .add("links", Json.createObjectBuilder()
                .add("rel", "self")
                .add("href", self.toString())
                ).build();
    }
}
