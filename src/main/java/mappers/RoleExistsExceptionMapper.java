package mappers;

import Exceptions.RoleExistsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class RoleExistsExceptionMapper  implements ExceptionMapper<RoleExistsException> {

    @Override
    public Response toResponse(RoleExistsException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}