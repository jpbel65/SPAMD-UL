package ca.ulaval.glo4003.projet.base.ws.domain.delivery;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.InvalidFieldException;

public class InvalidDeliveryModeException extends InvalidFieldException {

    private final static String ERROR_CODE = "INVALID_DELIVERY_MODE";
    private final static String ERROR_MESSAGE = "%s is invalid";

    public InvalidDeliveryModeException(String deliveryMode) {
        super(ERROR_CODE,String.format(ERROR_MESSAGE,deliveryMode));
    }
}
