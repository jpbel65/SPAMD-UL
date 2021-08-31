package ca.ulaval.glo4003.projet.base.ws.api.sutainableMobility;

import ca.ulaval.glo4003.projet.base.ws.api.sustainableMobility.InitiativeBudgetResource;
import ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility.InitiativeBudgetDto;
import ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility.SustainableMobilityService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;


@RunWith(MockitoJUnitRunner.class)
public class InitiativeBudgetResourceTest {

    private InitiativeBudgetResource resource;
    private static final float ANY_PERCENT = 42;
    private InitiativeBudgetDto initiativeBudgetDto;

    @Mock
    private SustainableMobilityService sustainableMobilityService;
    @Before
    public void setUp() {
        resource = new InitiativeBudgetResource(sustainableMobilityService);
        initiativeBudgetDto = new InitiativeBudgetDto();
        initiativeBudgetDto.percentForInitiatives = ANY_PERCENT;

    }

    @Test
    public void whenChangePercentForInitiatives_thenSetPercentForInitiatives() {
        resource.changePercentForInitiatives(initiativeBudgetDto);
        BDDMockito.verify(sustainableMobilityService).setPercentForInitiatives(ANY_PERCENT);
    }

    @Test
    public void whenChangePercentForInitiatives_thenReturnHttpCreated() {
        Response response = resource.changePercentForInitiatives(initiativeBudgetDto);

        Assert.assertEquals(201, response.getStatus());
    }

    @Test
    public void whenGetInitiativeBudget_thenSustainableMobilityServiceGetBudget() {
        resource.getInitiativeBudget();

        BDDMockito.verify(sustainableMobilityService).getBudget();
    }
}
