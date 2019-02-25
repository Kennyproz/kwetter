package resources;

import models.Role;
import service.RoleService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/role")
@RequestScoped
public class RoleResource {

    @Inject
    RoleService roleService;

    @GET
    @Path("/all-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allRoles(){
        List<Role> roles =  roleService.roles();
        return Response.status(200).entity(roles).build();
        //return Response.ok(roles).build();
    }


    @POST
    @Path("/add-role")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRole(Role role){
        roleService.add(role);
        return Response.ok().build();
    }

    @DELETE
    @Path("/remove-role/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeRole(@PathParam("id") int roleId ){
       // roleService.remove(roleId);
        return Response.ok().build();
    }

}
