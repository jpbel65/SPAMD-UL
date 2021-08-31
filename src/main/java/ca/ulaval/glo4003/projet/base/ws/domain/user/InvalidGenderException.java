package ca.ulaval.glo4003.projet.base.ws.domain.user;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.InvalidFieldException;

public class InvalidGenderException extends InvalidFieldException {

    private final static String ERROR_CODE = "INVALID_GENDER";
    private final static String ERROR_MESSAGE = "%s is not a supported gender";

    public InvalidGenderException(String gender) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE,gender));
    }
}
