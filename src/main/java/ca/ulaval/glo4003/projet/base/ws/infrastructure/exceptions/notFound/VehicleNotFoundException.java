package ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class VehicleNotFoundException  extends ResourceNotFoundException {
    private final static String ERROR_CODE = "VEHICLE_ID_NOT_FOUND";
    private final static String ERROR_MESSAGE = "Vehicle with ID number %s not found";

    public VehicleNotFoundException(String vehicleId) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, vehicleId));
    }
}
