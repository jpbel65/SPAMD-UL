package ca.ulaval.glo4003.projet.base.ws.application.parkingUsage;

import ca.ulaval.glo4003.projet.base.ws.domain.parkingUsage.ParkingUsage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingUsageAssemblerTest {
    private ParkingUsageAssembler parkingUsageAssembler;
    private final String ANY_ZONE = "ZONE3";
    private ParkingUsageDto parkingUsageDto;

    @Before
    public void setUp(){
        parkingUsageAssembler = new ParkingUsageAssembler();
        parkingUsageDto = new ParkingUsageDto();
        parkingUsageDto.zone = ANY_ZONE;
    }

    @Test
    public void whenCreateParkingUsage_thenParkingUsageIsCreate() {
        Object object = parkingUsageAssembler.create(parkingUsageDto);

        Assert.assertEquals(object.getClass(), ParkingUsage.class);
    }
}