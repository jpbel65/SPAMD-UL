package ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.InvalidFieldException;

public class InvalidEmailAddressException extends InvalidFieldException {
    private final static String ERROR_CODE = "INVALID_EMAIL_ADDRESS";
    private final static String ERROR_MESSAGE = "The delivery email '%s' is invalid";

    public InvalidEmailAddressException(String address) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, address));
    }
}
