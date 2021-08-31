package ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;

public class InitiativeBudget {
    private final Price totalRevenues;
    private final float percentForInitiative;
    private final Price totalInitiativeCost;

    public InitiativeBudget(Price totalRevenues, float percentForInitiative, Price totalInitiativeCost) {
        this.totalRevenues = totalRevenues;
        this.percentForInitiative = percentForInitiative;
        this.totalInitiativeCost = totalInitiativeCost;
    }

    public Price getTotalRevenues() {
        return totalRevenues;
    }

    public float getPercentForInitiative() {
        return percentForInitiative;
    }

    public Price getTotalInitiativeCost() {
        return totalInitiativeCost;
    }

    public Price getAvailableFunds() {
        return totalRevenues.multiply(percentForInitiative/100f).subtract(totalInitiativeCost);
    }

    public void verifyIfSufficientFunds(float cost) {
        if (cost < 0)
            throw new NegativeCostException();
        if (cost > getAvailableFunds().getValue())
            throw new InsufficientFundsException(cost, getAvailableFunds().getValue());
    }
}
