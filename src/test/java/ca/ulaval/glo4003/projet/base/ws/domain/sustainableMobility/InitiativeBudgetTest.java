package ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import org.junit.Test;

import static org.junit.Assert.*;

public class InitiativeBudgetTest {
    private final Price anyRevenues = new Price(42000);
    private final float anyPercentage = 42.0f;
    private final Price anyInitiativeCost = new Price(500);

    private final InitiativeBudget budget = new InitiativeBudget(anyRevenues, anyPercentage, anyInitiativeCost);

    @Test
    public void whenGetAvailableFunds_thenReturnAvailableFunds() {
        Price availableFunds = budget.getAvailableFunds();

        Price expectedFunds = anyRevenues.multiply(anyPercentage/100).subtract(anyInitiativeCost);
        assertEquals(expectedFunds, availableFunds);
    }

    @Test(expected = InsufficientFundsException.class)
    public void givenAHighCost_whenVerifyIfSufficientFunds_thenThrowInsufficientFundsException() {
        float highCost = anyRevenues.getValue();

        budget.verifyIfSufficientFunds(highCost);
    }

    @Test(expected = NegativeCostException.class)
    public void whenVerifyFundsOnNegativeCost_thenThrowNegativeCostException() {
        budget.verifyIfSufficientFunds(-500);
    }
}
