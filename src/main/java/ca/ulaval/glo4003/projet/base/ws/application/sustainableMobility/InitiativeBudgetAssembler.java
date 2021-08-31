package ca.ulaval.glo4003.projet.base.ws.application.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility.InitiativeBudget;

public class InitiativeBudgetAssembler {

    public InitiativeBudgetDto create(InitiativeBudget budget) {
        InitiativeBudgetDto budgetDto = new InitiativeBudgetDto();
        budgetDto.totalRevenues = budget.getTotalRevenues().getValue();
        budgetDto.percentForInitiatives = budget.getPercentForInitiative();
        budgetDto.spentForInitiatives = budget.getTotalInitiativeCost().getValue();
        budgetDto.availableFunds = budget.getAvailableFunds().getValue();
        return budgetDto;
    }
}
