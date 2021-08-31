package ca.ulaval.glo4003.projet.base.ws.infrastructure.exceptions.notFound;

import ca.ulaval.glo4003.projet.base.ws.domain.exceptions.ResourceNotFoundException;

public class InitiativeNotFoundException extends ResourceNotFoundException {
    private static final String CODE = "INITIATIVE_NOT_FOUND";
    private static final String message = "Initiative with code %s not found";

    public InitiativeNotFoundException(String initiativeCode) {
        super(CODE, String.format(message, initiativeCode));
    }
}
