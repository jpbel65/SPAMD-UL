package ca.ulaval.glo4003.projet.base.ws.api.mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.time.format.DateTimeParseException;

public class TimeParserExceptionMapper implements ExceptionMapper<DateTimeParseException> {

    @Override
    public Response toResponse(DateTimeParseException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.code = "INVALID_HOUR";
        exceptionDTO.message = "Proper format HH:MM";
        return Response.status(400)
                .entity(exceptionDTO)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
