package ca.ulaval.glo4003.projet.base.ws.api.mappers;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions.InvalidDateInfraction;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidDateInfractionMapper implements ExceptionMapper<InvalidDateInfraction> {
    @Override
    public Response toResponse(InvalidDateInfraction exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.code = exception.getInfractionCode().toString();
        exceptionDTO.message = exception.getInfractionMessage();
        return Response.status(400)
            .entity(exceptionDTO)
            .type(MediaType.APPLICATION_JSON)
            .build();
    }
}
