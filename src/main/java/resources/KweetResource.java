package resources;

import models.Kweet;
import models.User;
import service.KweetService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/kweet")
@RequestScoped
public class KweetResource {

    @Inject
    KweetService kweetService;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Kweet kweet){
        kweetService.add(kweet);
        return Response.ok().build();
    }

    @PUT
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(Kweet kweet){
         kweetService.edit(kweet);
         return Response.ok().build();
    }

    @DELETE
    @Path("/remove")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response remove(Kweet kweet){
        kweetService.remove(kweet);
        return Response.status(202).entity("Deleted succesfully").build();
    }

    @GET
    @Path("/{username}")
    public Response kweets(@PathParam("username") String username){
               // kweetService.kweets(user);
        return Response.ok().build();
    }
}
