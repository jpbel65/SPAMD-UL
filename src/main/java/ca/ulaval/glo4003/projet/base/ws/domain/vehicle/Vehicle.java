package ca.ulaval.glo4003.projet.base.ws.domain.vehicle;

public class Vehicle {

    private final String id;
    private final String brand;
    private final String model;
    private final Integer year;
    private final String licensePlate;
    private final VehicleConsumption vehicleConsumption;

    public Vehicle(String id, String brand, String model, Integer year, String licensePlate, VehicleConsumption vehicleConsumption) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.vehicleConsumption = vehicleConsumption;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleConsumption getVehicleConsumption() {
        return vehicleConsumption;
    }

    public boolean verifyVehicleConsumption(VehicleConsumption vehicleConsumption) {
        return vehicleConsumption.equals(this.vehicleConsumption);
    }
}
