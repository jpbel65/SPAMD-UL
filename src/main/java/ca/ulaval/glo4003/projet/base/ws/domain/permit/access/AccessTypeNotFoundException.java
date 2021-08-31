package ca.ulaval.glo4003.projet.base.ws.domain.permit.access;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class AccessTypeNotFoundException extends ResourceNotFoundException {
    private final static String ERROR_CODE = "ACCESS_TYPE_NOT_FOUND";
    private final static String ERROR_MESSAGE = "access type %s not found";

    public AccessTypeNotFoundException(String type) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, type));
    }
}
