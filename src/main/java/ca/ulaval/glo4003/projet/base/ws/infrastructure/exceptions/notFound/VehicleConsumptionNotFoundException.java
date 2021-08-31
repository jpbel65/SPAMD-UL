package ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class VehicleConsumptionNotFoundException extends ResourceNotFoundException {
    private final static String ERROR_CODE = "VEHICLE_CONSUMPTION_NOT_FOUND";
    private final static String ERROR_MESSAGE = "Vehicle consumption %s not found";

    public VehicleConsumptionNotFoundException(String consumptionVehicle) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, consumptionVehicle));
    }
}
