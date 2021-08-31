package ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;

public class InvalidZoneInfraction extends InfractionException {
    private final static String ERROR_MESSAGE = "Your parking permit doesn't give you access to this area.";

    public InvalidZoneInfraction() {
        super(ERROR_MESSAGE);
    }

    @Override
    public InfractionCode getInfractionCode() {
        return InfractionCode.ZONE_01;
    }
}
