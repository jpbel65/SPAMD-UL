package ca.ulaval.glo4003.projet.base.ws.domain.vehicle;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.InvalidFieldException;

public class InvalidVehicleConsumptionException extends InvalidFieldException {

    private final static String ERROR_CODE = "INVALID_VEHICLE_CONSUMPTION";
    private final static String ERROR_MESSAGE = "%s is not a supported vehicle consumption";

    InvalidVehicleConsumptionException(String vehicleConsumption) {
        super(ERROR_CODE, String.format(ERROR_MESSAGE, vehicleConsumption));
    }
}

