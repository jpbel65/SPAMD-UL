package ca.ulaval.glo4003.projet.base.ws.application.accessRequest;

import ca.ulaval.glo4003.projet.base.ws.domain.access.AccessRequest;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermit;
import ca.ulaval.glo4003.projet.base.ws.domain.permit.access.AccessPermitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AccessRequestServiceTest {
    public static final String ANY_CODE = "12345";

    private AccessRequestService accessRequestService;
    @Mock private AccessRequest accessRequest;

    @Mock private AccessRequestAssembler accessRequestAssembler;
    @Mock private AccessPermitRepository accessPermitRepository;

    @Mock private AccessRequestDto accessRequestDto;
    @Mock private AccessPermit accessPermit;

    @Before
    public void setup() {
        given(accessRequestAssembler.create(accessRequestDto)).willReturn(accessRequest);
        given(accessRequest.getAccessCode()).willReturn(ANY_CODE);
        given(accessPermitRepository.findByAccessCode(ANY_CODE)).willReturn(accessPermit);

        accessRequestService = new AccessRequestService(accessRequestAssembler, accessPermitRepository);
    }

    @Test
    public void whenCreateAccessRequest_thenAssembleAccessRequest() {
        accessRequestService.createAccessRequest(accessRequestDto);

        BDDMockito.verify(accessRequestAssembler).create(accessRequestDto);
    }

    @Test
    public void whenCreateAccessRequest_thenFindAccessPermitByAccessCode() {
        accessRequestService.createAccessRequest(accessRequestDto);

        BDDMockito.verify(accessPermitRepository).findByAccessCode(ANY_CODE);
    }

    @Test
    public void whenCreateAccessRequest_thenVerifyAccessPermit() {
        accessRequestService.createAccessRequest(accessRequestDto);

        BDDMockito.verify(accessRequest).verify(accessPermit);
    }
}
