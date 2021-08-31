package ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class PriceNotFoundException extends ResourceNotFoundException {
    private final static String ERROR_CODE = "PRICE_NOT_FOUND";
    private final static String ERROR_MESSAGE = "'%s' did not return a price";

    public PriceNotFoundException(String reason) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, reason));
    }
}
