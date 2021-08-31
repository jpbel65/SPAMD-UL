package ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class ParkingPermitNotFoundException extends ResourceNotFoundException {
    private final static String ERROR_CODE = "PARKING_PERMIT_NOT_FOUND";
    private final static String ERROR_MESSAGE = "Parking permit ID number %s not found";

    public ParkingPermitNotFoundException(String paymentId) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, paymentId));
    }
}
