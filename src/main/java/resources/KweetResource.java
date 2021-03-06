package resources;

import Exceptions.UserNotFoundException;
import models.Kweet;
import models.KweetCreator;
import service.KweetService;
import service.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static security.jwt.Constants.ADMIN;

@Path("/kweets")
@PermitAll
@RequestScoped
public class KweetResource {

    @Inject
    KweetService kweetService;

    @Inject
    UserService userService;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(KweetCreator kweetcreator) {
        Kweet kweet = kweetService.add(kweetcreator, userService.users());
        return Response.ok().entity(kweet).build();
    }

    @PUT
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(Kweet kweet) {
        kweetService.edit(kweet);
        return Response.ok().build();
    }

    @DELETE
    @RolesAllowed({ADMIN})
    @Path("/remove/{kweetId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("kweetId") long kweetId) {
        kweetService.remove(kweetId);
        return Response.status(202).build();
    }

    @GET
    @Path("/get-by-id/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response kweets(@PathParam("userId") long userId) {
        List<Kweet> kweets = kweetService.kweets(userId);
        return Response.status(200).entity(kweets).build();
    }

    @GET
    @Path("/get-by-username/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response kweets(@PathParam("username") String username) {
        List<Kweet> kweets = kweetService.kweets(username);
        return Response.ok().entity(kweets).build();
    }

    @GET
    @Path("/search/{search}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@PathParam("search") String search) {
        List<Kweet> kweets = kweetService.search(search);
        return Response.ok().entity(kweets).build();
    }

    @GET
    @PermitAll
    @Path("/timeline/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@PathParam("userId") long userId) {
        List<Kweet> kweets = kweetService.timeline(userId);
        return Response.ok().entity(kweets).build();
    }
}
