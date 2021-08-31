package ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.contravention;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.InvalidFieldException;

public class ContraventionAlreadyPaidException extends InvalidFieldException {
    private final static String ERROR_CODE = "CONTRAVENTION_ALREADY_PAID";
    private final static String ERROR_MESSAGE = "The contravention %s is already paid";

    public ContraventionAlreadyPaidException(String contraventionId) {
        super(ERROR_CODE,String.format(ERROR_MESSAGE,contraventionId));
    }
}
