package ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.domain.price.Price;
import org.junit.Test;

import static org.junit.Assert.*;

public class InitiativeTest {


    private static final String ANY_CODE = "INITIATIVE_1";
    private static final String ANY_NAME = "Initiative xyz";

    @Test
    public void givenAnInitiative_whenAddFunds_thenInitiativeCostIsIncrementedByFundsAdded() {
        Price cost = new Price(500);
        Initiative initiative = new Initiative(ANY_CODE, ANY_NAME, cost);

        float funds = 1500;
        initiative.addFund(funds);

        Price expected = cost.add(funds);
        assertEquals(expected, initiative.getCost());
    }

}
