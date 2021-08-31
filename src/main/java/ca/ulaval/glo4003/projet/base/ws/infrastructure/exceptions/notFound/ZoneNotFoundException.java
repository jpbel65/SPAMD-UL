package ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class ZoneNotFoundException extends ResourceNotFoundException {
    private final static String ERROR_CODE = "PARKING_ZONE_NOT_FOUND";
    private final static String ERROR_MESSAGE = "Zone name '%s' not found";

    public ZoneNotFoundException(String zone) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, zone));
    }
}
