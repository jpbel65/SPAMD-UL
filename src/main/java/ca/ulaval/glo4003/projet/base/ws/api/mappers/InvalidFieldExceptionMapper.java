package ca.ulaval.glo4003.projet.base.ws.api.mappers;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.InvalidFieldException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidFieldExceptionMapper implements ExceptionMapper<InvalidFieldException> {

    @Override
    public Response toResponse(InvalidFieldException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.code = exception.getErrorCode();
        exceptionDTO.message = exception.getMessage();
        return Response.status(400)
                .entity(exceptionDTO)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
