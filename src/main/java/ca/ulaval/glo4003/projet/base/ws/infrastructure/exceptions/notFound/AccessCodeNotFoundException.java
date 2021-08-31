package ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class AccessCodeNotFoundException extends ResourceNotFoundException {

    private final static String ERROR_CODE = "ACCESS_CODE_NOT_FOUND";
    private final static String ERROR_MESSAGE = "Access permit with access code %s not found";

    public AccessCodeNotFoundException(String accessCode) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, accessCode));
    }
}
