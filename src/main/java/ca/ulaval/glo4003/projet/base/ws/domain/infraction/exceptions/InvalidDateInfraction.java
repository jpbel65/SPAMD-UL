package ca.ulaval.glo4003.projet.base.ws.domain.infraction.exceptions;

import ca.ulaval.glo4003.projet.base.ws.domain.infraction.InfractionCode;

public class InvalidDateInfraction extends InfractionException {

    @Override
    public InfractionCode getInfractionCode() {
        return InfractionCode.VIG_01;
    }

    public String getInfractionMessage() {
        return "User cannot access parking at given date";
    }
}
