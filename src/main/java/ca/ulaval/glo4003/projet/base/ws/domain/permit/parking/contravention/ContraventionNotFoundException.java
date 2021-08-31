package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class ContraventionNotFoundException extends ResourceNotFoundException {
    private final static String ERROR_CODE = "CONTRAVENTION_ID_NOT_FOUND";
    private final static String ERROR_MESSAGE = "Contravention with ID number %s not found";

    public ContraventionNotFoundException(String contraventionId) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, contraventionId));
    }
}
