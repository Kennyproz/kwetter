package resources.v2;


import models.Kweet;
import models.KweetCreator;
import service.KweetService;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("v2/kweets")
@RequestScoped
@SuppressWarnings("Duplicates")
public class KweetResourcev2 {
    @Inject
    KweetService kweetService;

    @Inject
    UserService userService;

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response add(KweetCreator kweetcreator) {
        Kweet kweet = kweetService.add(kweetcreator, userService.users());
        return Response.ok().entity(kweet).build();
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(Kweet kweet) {
        kweetService.edit(kweet);
        return Response.ok().build();
    }

    @DELETE
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response remove(Kweet kweet) {
        kweetService.remove(kweet);
        return Response.status(202).entity("Deleted succesfully").build();
    }

    @GET
    @Path("/{userId}")
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
    @Path("{userId}/timeline")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@PathParam("userId") long userId) {
        List<Kweet> kweets = kweetService.timeline(userId);
        return Response.ok().entity(kweets).build();
    }
}
