package ca.ulaval.glo4003.projet.base.ws.domain.sustainableMobility;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.InvalidFieldException;

public class InsufficientFundsException extends InvalidFieldException {
    private static final String CODE = "INSUFFICIENT_FUNDS";
    private static final String FORMAT = "Impossible to allocate %,.2f$ for initiative. Only %,.2f$ is available for initiatives.";

    public InsufficientFundsException(float cost, float availableFunds) {
        super(CODE, String.format(FORMAT, cost, availableFunds));
    }
}
