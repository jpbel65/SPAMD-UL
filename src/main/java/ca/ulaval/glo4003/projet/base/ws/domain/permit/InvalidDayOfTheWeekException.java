package ca.ulaval.glo4003.projet.base.ws.domain.permit;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.InvalidFieldException;

public class InvalidDayOfTheWeekException extends InvalidFieldException {

    private final static String ERROR_CODE = "INVALID_DAY_OF_THE_WEEK";
    private final static String ERROR_MESSAGE = "%s is not a valid day of the week.";

    public InvalidDayOfTheWeekException(String dayOfTheWeek) {
        super(ERROR_CODE,String.format(ERROR_MESSAGE, dayOfTheWeek));
    }
}
