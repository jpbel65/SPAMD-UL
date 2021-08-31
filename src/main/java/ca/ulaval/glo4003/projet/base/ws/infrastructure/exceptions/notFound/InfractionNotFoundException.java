package ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class InfractionNotFoundException extends ResourceNotFoundException {

    private final static String ERROR_CODE = "INFRACTION_CODE_NOT_FOUND";
    private final static String ERROR_MESSAGE = "Infraction with code number %s not found";

    public InfractionNotFoundException(String infractionCode) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, infractionCode));
    }
}
