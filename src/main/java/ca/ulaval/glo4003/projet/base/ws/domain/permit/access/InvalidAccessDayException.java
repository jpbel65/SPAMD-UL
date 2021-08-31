package ca.ulaval.glo4003.projet.base.ws.domain.permit.access;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.InvalidFieldException;

public class InvalidAccessDayException extends InvalidFieldException {

    private final static String ERROR_CODE = "INVALID_ACCESS_DAY";
    private final static String ERROR_MESSAGE = "Your access day is '%s'.";

    public InvalidAccessDayException(String dayOfTheWeek) {
        super(ERROR_CODE,String.format(ERROR_MESSAGE, dayOfTheWeek));
    }
}
