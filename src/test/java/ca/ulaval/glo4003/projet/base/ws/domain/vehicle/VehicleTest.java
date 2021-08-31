package ca.ulaval.glo4003.projet.base.ws.domain.vehicle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VehicleTest {

    private static final String ANY_ID = "123";
    private static final String ANY_BRAND = "KYA";
    private static final String ANY_MODEL = "MODULO";
    private static final Integer ANY_YEAR = 159;
    private static final String ANY_LICENSE_PLATE = "1D4E5D1";
    private static final VehicleConsumption ANY_VEHICLE_CONSUMPTION = VehicleConsumption.GREEDY;
    private static final VehicleConsumption ANY_BAD_VEHICLE_CONSUMPTION = VehicleConsumption.ECONOMIC;
    private Vehicle vehicle;

    @Before
    public void setUp() {
        vehicle = new Vehicle(ANY_ID, ANY_BRAND, ANY_MODEL, ANY_YEAR, ANY_LICENSE_PLATE, ANY_VEHICLE_CONSUMPTION);
    }

    @Test
    public void givenBadVehicleConsumption_whenVerifyVehicleConsumption_thenReturnFalse() {
        boolean bool = vehicle.verifyVehicleConsumption(ANY_BAD_VEHICLE_CONSUMPTION);

        Assert.assertFalse(bool);
    }

    @Test
    public void givenGoodVehicleConsumption_whenVerifyVehicleConsumption_thenReturnFalse() {
        boolean bool = vehicle.verifyVehicleConsumption(ANY_VEHICLE_CONSUMPTION);

        Assert.assertTrue(bool);
    }
}
