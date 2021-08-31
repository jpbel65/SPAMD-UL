package ca.ulaval.glo4003.projet.base.ws.application.vehicle;

public class VehicleDto {

    public String id;
    public String brand;
    public String model;
    public Integer year;
    public String licensePlate;
    public String vehicleConsumption;

    @Override
    public String toString() {
        return "VehicleDto{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", licensePlate='" + licensePlate + '\'' +
                ", vehicleConsumption='" + vehicleConsumption + '\'' +
                '}';
    }
}
