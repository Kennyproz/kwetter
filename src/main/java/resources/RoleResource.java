package resources;

import Exceptions.RoleExistsException;
import models.Role;
import service.RoleService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static security.jwt.Constants.ADMIN;

@Path("/roles")
@PermitAll
@RequestScoped
public class RoleResource {

    @Inject
    RoleService roleService;

    @GET
    @Path("/all-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allRoles() {
        List<Role> roles = roleService.roles();
        return Response.status(200).entity(roles).build();
    }


    @POST
    @Path("/add-role")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRole(Role role) throws RoleExistsException {
        Role addedRole = roleService.add(role);
        return Response.status(201).entity(addedRole).build();
    }

    @DELETE
    @Path("/remove-role/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeRole(@PathParam("id") long roleId) {
        roleService.remove(roleId);
        return Response.noContent().build();
    }

    @GET
    @Path("/get-user-roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserRoles(long userId){
        List<Role> roles = roleService.userRoles(userId);
        return Response.ok().entity(roles).build();
    }

    @PUT
    @Path("/add")
    @RolesAllowed({ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserRole(@QueryParam("userId") long userId, @QueryParam("roleId") long roleId) {
        roleService.addRoleToUser(userId,roleId);
        return Response.ok().build();
    }

    @PUT
    @Path("/remove")
    @RolesAllowed({ADMIN})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeUserRole(@QueryParam("userId") long userId, @QueryParam("roleId") long roleId) {
        roleService.removeRoleFromUser(userId,roleId);
        return Response.ok().build();
    }

}
