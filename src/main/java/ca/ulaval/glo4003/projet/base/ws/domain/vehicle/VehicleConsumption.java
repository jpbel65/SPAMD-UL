package ca.ulaval.glo4003.projet.base.ws.domain.vehicle;

public enum VehicleConsumption {
    GREEDY("Gourmande"),
    ECONOMIC("Économique"),
    HYBRID_ECONOMIC("Hybride Économique"),
    SUPER_ECONOMIC("Super économique"),
    ZERO_EMISSION("0 pollution");

    private final String consumptionCode;

    VehicleConsumption(String consumptionCode) { this.consumptionCode = consumptionCode; }

    @Override
    public String toString() { return consumptionCode; }

    public static VehicleConsumption fromString(String consumptionCode) throws InvalidVehicleConsumptionException{
        for (VehicleConsumption vehicleConsumption : VehicleConsumption.values()){
            if(consumptionCode.toUpperCase().equals(vehicleConsumption.consumptionCode.toUpperCase())){
                return vehicleConsumption;
            }
        }
        throw new InvalidVehicleConsumptionException(consumptionCode);
    }
}
