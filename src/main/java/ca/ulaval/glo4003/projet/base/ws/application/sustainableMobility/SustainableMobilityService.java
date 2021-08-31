package ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.application.report.price.PriceReportService;
import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.Initiative;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.InitiativeBudget;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.InitiativeBudgetFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.InitiativeRepository;

import java.util.List;

public class SustainableMobilityService {
    // Pourrait être extrait dans un SustainableMobilityConfiguration repository pour être configuré au Runtime
    private float percentForInitiatives;

    private final InitiativeRepository initiativeRepository;
    private final InitiativeAssembler initiativeAssembler;

    private final InitiativeBudgetFactory budgetFactory;
    private final InitiativeBudgetAssembler budgetAssembler;

    // Pourrait être renommé à RevenueService
    private final PriceReportService priceReportService;

    public SustainableMobilityService(float percentForInitiatives, InitiativeRepository initiativeRepository, InitiativeAssembler initiativeAssembler, InitiativeBudgetFactory budgetFactory, InitiativeBudgetAssembler budgetAssembler, PriceReportService priceReportService) {
        this.percentForInitiatives = percentForInitiatives;
        this.initiativeRepository = initiativeRepository;
        this.initiativeAssembler = initiativeAssembler;
        this.budgetFactory = budgetFactory;
        this.budgetAssembler = budgetAssembler;
        this.priceReportService = priceReportService;
    }

    public InitiativeBudgetDto getBudget() {
        InitiativeBudget budget = createCurrentBudget();
        return budgetAssembler.create(budget);
    }

    public List<InitiativeDto> getInitiatives() {
        List<Initiative> initiatives = initiativeRepository.getAllInitiatives();
        return initiativeAssembler.create(initiatives);
    }

    public Initiative createInitiative(InitiativeDto initiativeDto) {
        InitiativeBudget budget = createCurrentBudget();
        budget.verifyIfSufficientFunds(initiativeDto.cost);
        Initiative initiative = initiativeAssembler.create(initiativeDto);
        initiativeRepository.save(initiative);
        return initiative;
    }

    public void addFundsToInitiative(String initiativeCode, float fundsToAdd) {
        Initiative initiative = initiativeRepository.findByCode(initiativeCode);
        InitiativeBudget budget = createCurrentBudget();
        budget.verifyIfSufficientFunds(fundsToAdd);
        initiative.addFund(fundsToAdd);
        initiativeRepository.save(initiative);
    }

    private InitiativeBudget createCurrentBudget() {
        Price totalRevenues = priceReportService.getTotalRevenues();
        Price totalCost = initiativeRepository.getTotalCost();
        return budgetFactory.create(totalRevenues, percentForInitiatives, totalCost);
    }

    public void setPercentForInitiatives(float percent) {
        percentForInitiatives = percent;
    }
}
