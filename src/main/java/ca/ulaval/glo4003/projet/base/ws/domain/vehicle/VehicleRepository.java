package ca.ulaval.glo4003.projet.base.ws.domain.vehicle;

import java.util.List;

public interface VehicleRepository {

    List<Vehicle> findAll();

    Vehicle findById(String id);

    void save(Vehicle vehicle);
}
