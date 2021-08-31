package ca.ulaval.glo4003.projet.base.ws.api.parkingUsage;

import ca.ulaval.glo4003.projet.base.ws.application.parkingUsage.ParkingUsageDto;
import ca.ulaval.glo4003.projet.base.ws.application.parkingUsage.ParkingUsageService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

@RunWith(MockitoJUnitRunner.class)
public class ParkingUsageResourceTest {
    private ParkingUsageResource parkingUsageResource;
    @Mock
    private ParkingUsageService parkingUsageService;
    @Mock
    private ParkingUsageDto parkingUsageDto;
    @Before
    public void setUp(){
        parkingUsageResource = new ParkingUsageResource(parkingUsageService);
    }

    @Test
    public void whenAddParkingUsage_thenParkingUsageServiceSaveParkingUsage() {
        parkingUsageResource.addParkingUsage(parkingUsageDto);

        BDDMockito.verify(parkingUsageService).saveParkingUsage(parkingUsageDto);
    }

    @Test
    public void whenAddParkingUsage_thenReturnHttpCreated() {
        Response response = parkingUsageResource.addParkingUsage(parkingUsageDto);

        Assert.assertEquals(201, response.getStatus());
    }
}