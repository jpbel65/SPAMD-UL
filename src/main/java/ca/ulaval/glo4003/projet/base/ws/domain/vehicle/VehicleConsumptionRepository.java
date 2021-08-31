package ca.ulaval.glo4003.projet.base.ws.domain.vehicle;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;

public interface VehicleConsumptionRepository {
    Price getVehicleConsumptionPriceForAccessPeriod(String vehicleConsumption, String period);
}
