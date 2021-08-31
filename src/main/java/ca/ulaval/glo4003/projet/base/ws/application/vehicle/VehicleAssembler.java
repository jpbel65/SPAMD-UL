package ca.ulaval.glo4003.projet.base.ws.application.vehicle;

import ca.ulaval.glo4003.projet.base.ws.domain.Id.IdGenerator;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.Vehicle;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumption;

import java.util.UUID;

public class VehicleAssembler {
    private final IdGenerator idGenerator;

    public VehicleAssembler(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Vehicle create(VehicleDto vehicleDto) {
        String id = idGenerator.generateId();
        VehicleConsumption vehicleConsumption = VehicleConsumption.fromString(vehicleDto.vehicleConsumption);
        return new Vehicle(id,
                vehicleDto.brand,
                vehicleDto.model,
                vehicleDto.year,
                vehicleDto.licensePlate,
                vehicleConsumption);
    }

    public VehicleDto create(Vehicle vehicle) {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.id = vehicle.getId();
        vehicleDto.brand = vehicle.getBrand();
        vehicleDto.model = vehicle.getModel();
        vehicleDto.year = vehicle.getYear();
        vehicleDto.licensePlate = vehicle.getLicensePlate();
        vehicleDto.vehicleConsumption = vehicle.getVehicleConsumption().toString();
        return vehicleDto;
    }
}
