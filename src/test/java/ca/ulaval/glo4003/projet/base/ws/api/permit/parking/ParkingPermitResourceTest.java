package ca.ulaval.glo4003.projet.base.ws.api.permit.parking;


import ca.ulaval.glo4003.projet.base.ws.application.permit.parking.ContraventionDto;
import ca.ulaval.glo4003.projet.base.ws.application.permit.parking.ParkingPermitDto;
import ca.ulaval.glo4003.projet.base.ws.application.permit.parking.ParkingPermitService;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.parking.ParkingPermit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

@RunWith(MockitoJUnitRunner.class)
public class ParkingPermitResourceTest {

    @Mock
    private ParkingPermitDto parkingPermitDto;
    @Mock
    private ParkingPermit parkingPermit;
    @Mock
    private ParkingPermitService parkingPermitService;
    private ParkingPermitResource parkingPermitResource;
    private final String ANY_ID = "15";

    private final ContraventionDto contraventionDto = new ContraventionDto();

    @Before
    public void setUp() {
        parkingPermitResource = new ParkingPermitResource(parkingPermitService);
        BDDMockito.when(parkingPermitService.createParkingPermit(parkingPermitDto)).thenReturn(parkingPermit);
        BDDMockito.when(parkingPermitService.verifyPermit(ANY_ID, contraventionDto)).thenReturn(contraventionDto);
    }

    @Test
    public void whenCreateParkingPermit_thenServiceCreateParkingPermit() {
        parkingPermitResource.createParkingPermit(parkingPermitDto);

        BDDMockito.verify(parkingPermitService).createParkingPermit(parkingPermitDto);
    }

    @Test
    public void whenGetParkingPermit_thenServiceFindParkingPermit() {
        parkingPermitResource.getParkingPermit(ANY_ID);

        BDDMockito.verify(parkingPermitService).findParkingPermit(ANY_ID);
    }

    @Test
    public void whenCreateParkingPermit_thenReturnHttpCreated() {
        Response response = parkingPermitResource.createParkingPermit(parkingPermitDto);

        Assert.assertEquals(201, response.getStatus());
    }

    @Test
    public void whenVerifyParkingPermit_thenServiceVerifyPermit() {
        parkingPermitResource.verify(ANY_ID, contraventionDto);

        BDDMockito.verify(parkingPermitService).verifyPermit(ANY_ID, contraventionDto);
    }

    @Test
    public void whenVerifyParkingPermit_thenReturnHttpCreated() {
        Response response = parkingPermitResource.verify(ANY_ID, contraventionDto);

        Assert.assertEquals(201, response.getStatus());
    }

    @Test
    public void whenVerifyParkingPermit_thenResponseContainsContraventionLocation() {
        Response response = parkingPermitResource.verify(ANY_ID, contraventionDto);

        String expectedLocation =
            ParkingPermitResource.PARKING_PERMIT + "/" + ANY_ID + "/" +
            ParkingPermitResource.CONTRAVENTION + "/" + contraventionDto.id;
        Assert.assertEquals(expectedLocation, response.getHeaderString("Location"));
    }

    @Test
    public void whenGetContravention_thenServiceFindContraventionById() {
        parkingPermitResource.getContravention(ANY_ID, ANY_ID);

        BDDMockito.verify(parkingPermitService).findContraventionById(ANY_ID, ANY_ID);
    }

    @Test
    public void whenPayContravention_thenServicePayContravention() {
        parkingPermitResource.payContravention(ANY_ID, ANY_ID);

        BDDMockito.verify(parkingPermitService).payContravention(ANY_ID, ANY_ID);
    }

    @Test
    public void whenPayContravention_thenReturnHttpOk() {
        Response response = parkingPermitResource.payContravention(ANY_ID, ANY_ID);

        Assert.assertEquals(200, response.getStatus());
    }
}
