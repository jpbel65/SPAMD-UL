package ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class UserNotFoundException  extends ResourceNotFoundException {
    private final static String ERROR_CODE = "USER_ID_NOT_FOUND";
    private final static String ERROR_MESSAGE = "User with ID number %s not found";

    public UserNotFoundException(String userId) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, userId));
    }
}
