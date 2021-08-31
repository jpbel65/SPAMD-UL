package ca.ulaval.glo4003.projet.base.ws.api.sutainableMobility;

import ca.ulaval.glo4003.projet.base.ws.api.sustainableMobility.InitiativeResource;
import ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility.InitiativeDto;
import ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility.SustainableMobilityService;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.Initiative;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;


@RunWith(MockitoJUnitRunner.class)
public class InitiativeResourceTest {

    private InitiativeResource resource;

    @Mock
    private SustainableMobilityService sustainableMobilityService;
    @Mock
    private InitiativeDto initiativeDto;
    @Mock
    private Initiative initiative;

    private static final String ANY_CODE = "some-code";
    private static final float ANY_FUNDS = 42;

    @Before
    public void setUp() {
        resource = new InitiativeResource(sustainableMobilityService);
        initiativeDto.cost = ANY_FUNDS;
        BDDMockito.given(initiative.getCode()).willReturn(ANY_CODE);
        BDDMockito.given(sustainableMobilityService.createInitiative(initiativeDto)).willReturn(initiative);
    }

    @Test
    public void whenCreateInitiative_thenSustainableMobilityServiceCreatesInitiative() {
        resource.createInitiativeFund(initiativeDto);

        BDDMockito.verify(sustainableMobilityService).createInitiative(initiativeDto);
    }

    @Test
    public void whenCreateInitiative_thenReturnHttpCreated() {
        Response response = resource.createInitiativeFund(initiativeDto);

        Assert.assertEquals(201, response.getStatus());
    }

    @Test
    public void whenAddToInitiativeFund_thenSustainableMobilityServiceAddsToInitiative(){
        Response response = resource.addToInitiativeFund(ANY_CODE, initiativeDto);

        BDDMockito.verify(sustainableMobilityService).addFundsToInitiative(ANY_CODE, ANY_FUNDS);
    }

    @Test
    public void whenGetInitiativeList_thenSustainableMobilityServiceGetAllInitiatives() {
        resource.getInitiativeList();

        BDDMockito.verify(sustainableMobilityService).getInitiatives();

    }
}
