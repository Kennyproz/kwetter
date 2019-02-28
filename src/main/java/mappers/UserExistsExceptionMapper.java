package mappers;

import Exceptions.UserExistsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class UserExistsExceptionMapper implements ExceptionMapper<UserExistsException> {

    @Override
    public Response toResponse(UserExistsException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}
