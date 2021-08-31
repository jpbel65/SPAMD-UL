package ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.application.report.price.PriceReportService;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.Initiative;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.InitiativeBudget;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.InitiativeBudgetFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.InitiativeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SustainableMobilityServiceTest {

    private static final String ANY_CODE = "INITIATIVE_1";
    private static final float PERCENT_FOR_INITIATIVE = 40.0f;
    private static final Price TOTAL_REVENUES = new Price(42000);
    private static final Price TOTAL_INITIATIVE_COST = new Price(500);
    private static final float ANY_FUNDS = 1500;

    @Mock public InitiativeBudget anyBudget;
    private final InitiativeBudgetDto anyBudgetDto = new InitiativeBudgetDto();

    private final List<Initiative> anyInitiatives = new ArrayList<>();
    private final List<InitiativeDto> anyInitiativesDto = new ArrayList<>();
    private final InitiativeDto anyInitiativeDto = new InitiativeDto();
    @Mock public Initiative anyInitiative;

    @Mock PriceReportService priceReportService;
    @Mock InitiativeRepository initiativeRepository;
    @Mock InitiativeBudgetFactory budgetFactory;
    @Mock InitiativeBudgetAssembler budgetAssembler;
    @Mock InitiativeAssembler initiativeAssembler;

    private SustainableMobilityService sustainableMobilityService;

    @Before
    public void setup() {
        given(priceReportService.getTotalRevenues()).willReturn(TOTAL_REVENUES);
        given(initiativeRepository.getTotalCost()).willReturn(TOTAL_INITIATIVE_COST);
        given(budgetFactory.create(TOTAL_REVENUES, PERCENT_FOR_INITIATIVE, TOTAL_INITIATIVE_COST)).willReturn(anyBudget);
        given(budgetAssembler.create(anyBudget)).willReturn(anyBudgetDto);

        given(initiativeAssembler.create(anyInitiatives)).willReturn(anyInitiativesDto);
        given(initiativeAssembler.create(anyInitiativeDto)).willReturn(anyInitiative);

        given(initiativeRepository.findByCode(ANY_CODE)).willReturn(anyInitiative);

        sustainableMobilityService = new SustainableMobilityService(
            PERCENT_FOR_INITIATIVE,
            initiativeRepository,
            initiativeAssembler,
            budgetFactory,
            budgetAssembler,
            priceReportService
        );
    }

    @Test
    public void whenGetBudget_thenGetTotalRevenues() {
        sustainableMobilityService.getBudget();

        verify(priceReportService).getTotalRevenues();
    }

    @Test
    public void whenGetBudget_thenGetTotalPriceOfInitiatives() {
        sustainableMobilityService.getBudget();

        verify(initiativeRepository).getTotalCost();
    }

    @Test
    public void whenGetBudget_thenCreateBudget() {
        sustainableMobilityService.getBudget();

        verify(budgetFactory).create(TOTAL_REVENUES, PERCENT_FOR_INITIATIVE, TOTAL_INITIATIVE_COST);
    }

    @Test
    public void whenGetBudget_thenAssembleBudgetDto() {
        sustainableMobilityService.getBudget();

        verify(budgetAssembler).create(anyBudget);
    }

    @Test
    public void whenGetBudget_thenBudgetDtoIsReturned() {
        InitiativeBudgetDto budget = sustainableMobilityService.getBudget();

        assertEquals(anyBudgetDto, budget);
    }

    @Test
    public void whenGetInitiatives_thenGetAllInitiativesFromRepository() {
        sustainableMobilityService.getInitiatives();

        verify(initiativeRepository).getAllInitiatives();
    }

    @Test
    public void whenGetInitiatives_thenAssembleInitiatives() {
        sustainableMobilityService.getInitiatives();

        verify(initiativeAssembler).create(anyInitiatives);
    }

    @Test
    public void whenGetInitiatives_thenReturnAssembledInitiatives() {
        List<InitiativeDto> initiatives = sustainableMobilityService.getInitiatives();

        assertEquals(anyInitiativesDto, initiatives);
    }

    @Test
    public void whenCreateInitiative_thenVerifyIfSufficientFunds() {
        sustainableMobilityService.createInitiative(anyInitiativeDto);

        verify(anyBudget).verifyIfSufficientFunds(anyInitiativeDto.cost);
    }

    @Test
    public void whenCreateInitiative_thenCreateInitiativeFromDto() {
        sustainableMobilityService.createInitiative(anyInitiativeDto);

        verify(initiativeAssembler).create(anyInitiativeDto);
    }

    @Test
    public void whenCreateInitiative_thenSaveInitiativeInRepository() {
        sustainableMobilityService.createInitiative(anyInitiativeDto);

        verify(initiativeRepository).save(anyInitiative);
    }

    @Test
    public void whenCreateInitiative_thenReturnCreatedInitiative() {
        Initiative initiative = sustainableMobilityService.createInitiative(anyInitiativeDto);

        assertEquals(anyInitiative, initiative);
    }

    @Test
    public void whenAddFundsToInitiative_thenFindInitiativeByCode() {
        sustainableMobilityService.addFundsToInitiative(ANY_CODE, ANY_FUNDS);

        verify(initiativeRepository).findByCode(ANY_CODE);
    }

    @Test
    public void whenAddFundsToInitiative_thenCreateCurrentBudget() {
        sustainableMobilityService.addFundsToInitiative(ANY_CODE, ANY_FUNDS);

        verify(budgetFactory).create(TOTAL_REVENUES, PERCENT_FOR_INITIATIVE, TOTAL_INITIATIVE_COST);
    }

    @Test
    public void whenAddFundsToInitiative_thenVerifyIfSufficientFunds() {
        sustainableMobilityService.addFundsToInitiative(ANY_CODE, ANY_FUNDS);

        verify(anyBudget).verifyIfSufficientFunds(ANY_FUNDS);
    }

    @Test
    public void whenAddFundsToInitiative_thenAddFundsToInitiative() {
        sustainableMobilityService.addFundsToInitiative(ANY_CODE, ANY_FUNDS);

        verify(anyInitiative).addFund(ANY_FUNDS);
    }

    @Test
    public void whenAddFundsToInitiative_thenSaveInitiative() {
        sustainableMobilityService.addFundsToInitiative(ANY_CODE, ANY_FUNDS);

        verify(initiativeRepository).save(anyInitiative);
    }
}
