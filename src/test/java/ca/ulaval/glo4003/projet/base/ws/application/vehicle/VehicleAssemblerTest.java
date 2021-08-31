package ca.ulaval.glo4003.projet.base.ws.application.vehicle;

import ca.ulaval.glo4003.projet.base.ws.domain.Id.IdGenerator;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.Vehicle;
import ca.ulaval.glo4003.projet.base.ws.domain.vehicle.VehicleConsumption;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class VehicleAssemblerTest {

    private final static String ID = "ID";
    private final static String BRAND = "Ford";
    private final static String MODEL = "Focus";
    private final static int YEAR = 2010;
    private final static String LICENSE_PLATE = "A1B 2C3";
    private final static VehicleConsumption VEHICLE_CONSUMPTION = VehicleConsumption.GREEDY;

    private VehicleAssembler vehicleAssembler;
    private VehicleDto vehicleDto;
    private Vehicle vehicle;

    @Mock
    private IdGenerator idGenerator;

    @Before
    public void setUp() {
        vehicleAssembler = new VehicleAssembler(idGenerator);
        vehicleDto = new VehicleDto();
        vehicleDto.brand = BRAND;
        vehicleDto.model = MODEL;
        vehicleDto.year = YEAR;
        vehicleDto.licensePlate = LICENSE_PLATE;
        vehicleDto.vehicleConsumption = VEHICLE_CONSUMPTION.toString();
        vehicle = new Vehicle(ID, BRAND, MODEL, YEAR, LICENSE_PLATE, VEHICLE_CONSUMPTION);
    }

    @Test
    public void whenAssemblingVehicle_thenShouldReturnVehicle() {
        Object actualVehicle = vehicleAssembler.create(vehicleDto);

        assertEquals(actualVehicle.getClass(), Vehicle.class);
    }

    @Test
    public void whenAssemblingVehicleDto_thenShouldReturnVehicleDto() {
        VehicleDto actualVehicleDto = vehicleAssembler.create(vehicle);

        assertEquals(actualVehicleDto.getClass(), VehicleDto.class);
    }

    @Test
    public void whenAssemblingVehicle_thenCallIdGenerator(){
        vehicleAssembler.create(vehicleDto);

        BDDMockito.verify(idGenerator).generateId();
    }

}
