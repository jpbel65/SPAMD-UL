package ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;

public abstract class InfractionException extends RuntimeException {
    public InfractionException() {
        super();
    }

    public InfractionException(String message) {
        super(message);
    }

    public abstract InfractionCode getInfractionCode();
}
