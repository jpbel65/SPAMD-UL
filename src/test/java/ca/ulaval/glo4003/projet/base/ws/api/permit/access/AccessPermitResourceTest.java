package ca.ulaval.glo4003.projet.base.ws.api.permit.access;

import ca.ulaval.glo4003.projet.base.ws.application.accessRequest.AccessRequestDto;
import ca.ulaval.glo4003.projet.base.ws.application.accessRequest.AccessRequestService;
import ca.ulaval.glo4003.projet.base.ws.application.permit.access.AccessPermitDto;
import ca.ulaval.glo4003.projet.base.ws.application.permit.access.AccessPermitService;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

@RunWith(MockitoJUnitRunner.class)
public class AccessPermitResourceTest {

    private AccessPermitResource accessPermitResource;

    @Mock
    private AccessPermitService accessPermitService;
    @Mock
    private AccessRequestService accessRequestService;
    @Mock
    private AccessPermitDto permitRequest;
    @Mock
    private AccessPermit accessPermit;
    @Mock
    private AccessPermitDto accessPermitDto;
    @Mock
    private AccessRequestDto accessRequestDto;

    private static final String ANY_ID = "42";

    @Before
    public void setUp() {
        BDDMockito.given(accessPermitService.create(permitRequest)).willReturn(accessPermit);
        BDDMockito.given(accessPermit.getId()).willReturn(ANY_ID);
        BDDMockito.given(accessPermitService.findAccessPermit(ANY_ID)).willReturn(accessPermitDto);
        accessPermitResource = new AccessPermitResource(accessPermitService, accessRequestService);
    }

    @Test
    public void whenCreatePermit_thenServiceCreatePermit() {
        accessPermitResource.create(permitRequest);

        BDDMockito.verify(accessPermitService).create(permitRequest);
    }

    @Test
    public void whenCreatePermit_thenReturnHttpCreated() {
        Response response = accessPermitResource.create(permitRequest);

        Assert.assertEquals(201, response.getStatus());
    }

    @Test
    public void whenCreatePermit_thenReturnPermitLocation() {
        Response response = accessPermitResource.create(permitRequest);

        Assert.assertTrue(response.getLocation().toString().contains(ANY_ID));
    }

    @Test
    public void whenAccessRequest_thenServiceCreateAccessRequest() {
        accessPermitResource.accessRequest(accessRequestDto);

        BDDMockito.verify(accessRequestService).createAccessRequest(accessRequestDto);
    }

    @Test
    public void whenAccessRequest_thenReturnHttpOk() {
        Response response = accessPermitResource.accessRequest(accessRequestDto);

        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void givenAnId_whenGetPermit_thenServiceShouldLookForParkingPermitWithTheCorrespondingId() {
        accessPermitResource.getAccessPermit(ANY_ID);

        BDDMockito.verify(accessPermitService).findAccessPermit(ANY_ID);
    }
}
