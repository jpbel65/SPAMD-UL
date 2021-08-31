package ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.InvalidFieldException;

public class NegativeCostException extends InvalidFieldException {
    private static final String CODE = "NEGATIVE_COST_EXCEPTION";
    private static final String MESSAGE = "Can not substract a negative cost from the InitiativeBudget";

    public NegativeCostException() {
        super(CODE, MESSAGE);
    }
}
