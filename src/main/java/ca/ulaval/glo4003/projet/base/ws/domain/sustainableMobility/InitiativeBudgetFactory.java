package ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;

public class InitiativeBudgetFactory {
    public InitiativeBudget create(Price totalRevenues, float percentForInitiative, Price totalInitiativeCost) {
        return new InitiativeBudget(
            totalRevenues,
            percentForInitiative,
            totalInitiativeCost
        );
    }
}
