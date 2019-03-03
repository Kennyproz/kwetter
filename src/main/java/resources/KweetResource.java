package resources;

import Exceptions.UserNotFoundException;
import models.Kweet;
import models.KweetConvertor;
import models.KweetCreator;
import models.User;
import service.KweetService;
import service.UserService;

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

    @Inject
    UserService userService;

    KweetConvertor kweetConvertor = new KweetConvertor(userService.users());

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(KweetCreator kweetCreator){
        Kweet kweetToAdd = kweetConvertor.convertToKweet(kweetCreator);
        Kweet kweet = kweetService.add(kweetToAdd);
        return Response.ok().entity(kweet).build();
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
    public Response kweets(@PathParam("username") String username) throws UserNotFoundException {
        List<Kweet> kweets =  kweetService.kweets(userService.getUser(username));
        return Response.status(200).entity(kweets).build();
    }
}
