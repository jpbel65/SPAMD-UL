package ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class AccessPermitNotFoundException extends ResourceNotFoundException {
    private final static String ERROR_CODE = "PARKING_PERMIT_ID_NOT_FOUND";
    private final static String ERROR_MESSAGE = "Access permit ID number %s not found";

    public AccessPermitNotFoundException(String permitId) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, permitId));
    }
}
