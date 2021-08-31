package ca.ulaval.glo4003.projet.base.ws.domain.permit.access;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.InvalidFieldException;

public class InvalidAccessTypeException extends InvalidFieldException {
    private static final String CODE = "INVALID_ACCESS_TYPE";
    private static final String FORMAT = "access permit of type %s is not allowed for %s parking";

    public InvalidAccessTypeException(AccessType permitType, AccessType expectedType) {
        super(CODE, String.format(FORMAT, permitType, expectedType));
    }
}
