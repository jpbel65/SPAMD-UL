package ca.ulaval.glo4003.projet.base.ws.application.accessRequest;

import ca.ulaval.glo4003.projet.base.ws.domain.access.AccessRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccessRequestAssemblerTest {

    private AccessRequestDto accessRequestDto;
    private AccessRequestAssembler accessRequestAssembler;
    private final String ANY_ACCESS_CODE = "15847422";
    private static final String ANY_TYPE = "bike";

    @Before
    public void setUp() {
        accessRequestAssembler = new AccessRequestAssembler();
        accessRequestDto = new AccessRequestDto();
        accessRequestDto.accessCode = ANY_ACCESS_CODE;
        accessRequestDto.accessType = ANY_TYPE;
    }

    @Test
    public void whenAssemblingAccessRequest_thenShouldReturnAccessRequest() {
        Object accessRequest = accessRequestAssembler.create(accessRequestDto);

        Assert.assertEquals(AccessRequest.class, accessRequest.getClass());
    }
}
